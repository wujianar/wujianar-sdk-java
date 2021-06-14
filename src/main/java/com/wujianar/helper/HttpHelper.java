package com.wujianar.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpHelper {
    private static final OkHttpClient httpClient = new OkHttpClient();

    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";

    private final String method;
    private final String url;
    private Map<String, String> parameters;
    private Map<String, String> headers = new HashMap<>();
    private String bodyStr;
    private String filename;

    public HttpHelper(String method, String url) {
        this.url = url;
        this.method = method;
    }

    public HttpHelper setParameter(Map<String, String> map) {
        this.parameters = map;
        return this;
    }

    public HttpHelper setBodyStr(String str) {
        this.bodyStr = str;
        return this;
    }

    public HttpHelper setBody(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.bodyStr = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        }
        return this;
    }

    public HttpHelper setHeader(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    public HttpHelper setFile(String filename) {
        this.filename = filename;
        return this;
    }

    private RequestBody getRequestBody() {
        if (this.bodyStr != null) {
            return RequestBody.create(this.bodyStr, MediaType.parse("application/json; charset=utf-8"));
        }

        if (this.filename != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(Objects.requireNonNull(MediaType.parse("multipart/form-data")));
            if (this.parameters != null) {
                this.parameters.forEach(builder::addFormDataPart);
            }
            RequestBody b = RequestBody.create(new File(this.filename), MediaType.parse("application/octet-stream"));
            builder.addFormDataPart("file", this.filename, b);
            return builder.build();
        }

        FormBody.Builder builder = new FormBody.Builder();
        if (this.parameters != null) {
            this.parameters.forEach(builder::add);
        }

        return builder.build();
    }

    private HttpUrl getHttpUrl() {
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        if (this.parameters != null && (this.method.equals(HttpHelper.GET) || this.method.equals(HttpHelper.DELETE))) {
            this.parameters.forEach(builder::addQueryParameter);
        }

        return builder.build();
    }

    public byte[] call() throws IOException {
        Request.Builder builder = new Request.Builder();

        if (this.method.equals(HttpHelper.GET)) {
            builder.get();
        } else {
            builder.method(this.method, this.getRequestBody());
        }

        Request request = builder.url(this.getHttpUrl())
                .headers(Headers.of(this.headers))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().bytes();
        }
    }
}
