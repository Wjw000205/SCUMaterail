package com.kdde.basemodule.basemodule.common.exception;

/**
 * 账号不存在异常
 */
public class AccountExitsException extends BaseException {

    public AccountExitsException() {
    }

    public AccountExitsException(String msg) {
        super(msg);
    }

}
