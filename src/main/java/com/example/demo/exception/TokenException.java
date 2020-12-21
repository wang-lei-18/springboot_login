package com.example.demo.exception;

/**
 * 业务异常
 */
public class TokenException extends RuntimeException{

    private String errMsg;
    private int errNo = 500;

    public TokenException(String errMsg){
        super(errMsg);
        this.errMsg = errMsg;
    }

    public TokenException(String errMsg, Throwable e) {
        super(errMsg, e);
        this.errMsg = errMsg;
    }

    public TokenException(String errMsg, int errNo) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errNo = errNo;
    }

    public TokenException(String errMsg, int errNo, Throwable e) {
        super(errMsg, e);
        this.errMsg = errMsg;
        this.errNo = errNo;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }
}
