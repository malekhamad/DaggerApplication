package com.example.daggerapplication.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class NetworkResource<T> {

    public enum Status {
        SUCCESS, ERROR, LOADING, EMPTY, NETWORK_ERROR
    }

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private NetworkResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> NetworkResource<T> success(@NonNull T data) {
        return new NetworkResource<>(Status.SUCCESS, data, null);
    }

    public static <T> NetworkResource<T> error(String message) {
        return new NetworkResource<>(Status.ERROR, null, message);
    }

    public static <T> NetworkResource<T> loading(@Nullable T data) {
        return new NetworkResource<>(Status.LOADING, data, null);
    }

    public static <T> NetworkResource<T> networkError(@Nullable String message) {
        return new NetworkResource<>(Status.NETWORK_ERROR, null, message);
    }

}

