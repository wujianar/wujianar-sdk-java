package com.wujianar.entity;

public class ResultSearch {
    private int code;
    private ImageSimple result;
    private String message;

    public ResultSearch() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ImageSimple getResult() {
        return result;
    }

    public void setResult(ImageSimple result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
