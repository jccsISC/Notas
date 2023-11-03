package com.jccsisc.myroomdb.core.error;

/**
 * Project: MyRoomDB
 * FROM: com.jccsisc.myroomdb.core.resources
 * Created by Julio Cesar Camacho Silva on 02/11/23
 */
public class Error<T> {
    private int code = -1;
    private String message = "";
    private T errorBody = null;

    public Error() { }
    public Error(int code, String message, T errorBody) {
        this.code = code;
        this.message = message;
        this.errorBody = errorBody;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(T errorBody) {
        this.errorBody = errorBody;
    }
}
