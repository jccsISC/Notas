package com.jccsisc.myroomdb.core.resources;

import static com.jccsisc.myroomdb.core.status.Status.ERROR;
import static com.jccsisc.myroomdb.core.status.Status.LOADING;
import static com.jccsisc.myroomdb.core.status.Status.SUCCESS;

import com.jccsisc.myroomdb.core.error.Error;
import com.jccsisc.myroomdb.core.status.Status;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.core.resources
 * Created by Julio Cesar Camacho Silva on 02/11/23
 */
public class Resource <T> {
    public final Status status;
    public final T data;
    public final Error error;
    public final Throwable mThrowable;
    public Resource(Status status, T data, Error error, Throwable mThrowable) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.mThrowable = mThrowable;
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(LOADING, data, null, null);
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(SUCCESS, data, null, null);
    }

    public static <T> Resource<T> error(Error error, T data) {
        return new Resource<>(ERROR, data, error, null);
    }

    public static <T> Resource<T> mThrowable(Throwable mThrowable, T data) {
        return new Resource<>(ERROR, null, null, mThrowable);
    }
}
