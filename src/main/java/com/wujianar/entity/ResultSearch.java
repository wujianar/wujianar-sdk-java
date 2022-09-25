package com.wujianar.entity;

public class ResultSearch {
    private int code;
    private ImageSimple data;
    private String message;

    public ResultSearch() {
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

    public ImageSimple getData() {
        return data;
    }

    public void setData(ImageSimple data) {
        this.data = data;
    }
}
