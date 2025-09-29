package com.kdde.basemodule.basemodule.common.exception;

/**
 * 账号不存在异常
 */
public class InvalidRegistInfoException extends BaseException {

    public InvalidRegistInfoException() {
    }

    public InvalidRegistInfoException(String msg) {
        super(msg);
    }

}
