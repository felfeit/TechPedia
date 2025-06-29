package com.felfeit.techpedia.data;

public abstract class Resource<T> {
    public final T data;
    public final String message;

    private Resource(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Success<>(data);
    }

    public static <T> Resource<T> loading() {
        return new Loading<>(null);
    }

    public static <T> Resource<T> loading(T data) {
        return new Loading<>(data);
    }

    public static <T> Resource<T> error(String message) {
        return new Error<>(message);
    }


    public static class Success<T> extends Resource<T> {
        public Success(T data) {
            super(data, null);
        }
    }

    public static class Loading<T> extends Resource<T> {
        public Loading(T data) {
            super(data, null);
        }
    }

    public static class Error<T> extends Resource<T> {
        public Error(String message) {
            super(null, message);
        }
    }
}