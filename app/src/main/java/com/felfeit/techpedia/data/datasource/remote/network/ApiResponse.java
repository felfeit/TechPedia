package com.felfeit.techpedia.data.datasource.remote.network;

public abstract class ApiResponse<T> {
    public static class Success<T> extends ApiResponse<T> {
        public final T data;

        public Success(T data) {
            this.data = data;
        }
    }

    public static class Error<T> extends ApiResponse<T> {
        public final String errorMessage;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    public static class Empty<T> extends ApiResponse<T> {
    }
}