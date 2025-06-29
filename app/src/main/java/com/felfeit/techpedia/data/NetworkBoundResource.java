package com.felfeit.techpedia.data;

import com.felfeit.techpedia.data.datasource.remote.network.ApiResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final PublishSubject<Resource<ResultType>> result = PublishSubject.create();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public NetworkBoundResource() {
        Flowable<ResultType> dbSource = loadFromDB();

        compositeDisposable.add(dbSource
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe(value -> {
                    if (shouldFetch(value)) {
                        fetchFromNetwork();
                    } else {
                        result.onNext(new Resource.Success<>(value));
                    }
                }));
    }

    protected abstract Flowable<ResultType> loadFromDB();

    protected abstract boolean shouldFetch(ResultType data);

    protected abstract Flowable<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    protected void onFetchFailed() {
    }

    private void fetchFromNetwork() {
        result.onNext(new Resource.Loading<>(null));

        Flowable<ApiResponse<RequestType>> apiResponse = createCall();

        compositeDisposable.add(apiResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe(response -> {
                    if (response instanceof ApiResponse.Success) {
                        saveCallResult(((ApiResponse.Success<RequestType>) response).data);

                        compositeDisposable.add(loadFromDB()
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .take(1)
                                .subscribe(data -> result.onNext(new Resource.Success<>(data))));

                    } else if (response instanceof ApiResponse.Empty) {
                        compositeDisposable.add(loadFromDB()
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .take(1)
                                .subscribe(data -> result.onNext(new Resource.Success<>(data))));

                    } else if (response instanceof ApiResponse.Error) {
                        onFetchFailed();
                        result.onNext(new Resource.Error<>(((ApiResponse.Error<RequestType>) response).errorMessage));
                    }
                }));
    }

    public Flowable<Resource<ResultType>> asFlowable() {
        return result.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void dispose() {
        compositeDisposable.dispose();
    }
}
