package com.wujianar.manager;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

public class Base {
    private String accessKey;
    private String accessSecret;
    private String endpointUrl;

    public Base(String accessKey, String accessSecret, String endpointUrl) {
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
        this.endpointUrl = endpointUrl;
    }

    /**
     * 获取认证token，默认有效期(3600秒)
     *
     * @return
     */
    private String getToken() {
        return this.getToken(3600);
    }

    public String getEndpointUrl() {
        return this.endpointUrl;
    }

    /**
     * 获取认证token
     *
     * @param expires 有效期(秒)
     * @return String
     */
    private String getToken(long expires) {
        // 将基本信息转换为JSON字符串 (这里设置的有效为3600秒)
        String json = String.format("{\"accessKey\":\"%s\",\"expires\":%d}",
                this.accessKey, System.currentTimeMillis() + expires * 1000);

        // 计算签名，拼接JSON字符串与访问密钥，再使用sha256哈希生成签名
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        String signature = sha256.digestHex(String.format("%s%s", json, this.accessSecret));

        // 原始token数据
        String raw = signature + json;

        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    protected Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", this.getToken());
        map.put("user-agent", "WuJianAR-Java-V3.0");
        return map;
    }

    protected <T> T toObj(String str, Type target) {
        return JSONUtil.toBean(str, target, true);
    }

    protected String getUrl(String method) {
        return String.format("%s%s", this.endpointUrl, method);
    }

    protected String httpGet(String method) throws HttpException {
        return this.httpGet(method, null);
    }

    protected String httpGet(String method, Map<String, Object> data) throws HttpException {
        return this.http(HttpRequest.get(this.getUrl(method)).form(data));
    }

    protected String httpDelete(String method, Map<String, Object> data) throws HttpException {
        return this.http(HttpRequest.delete(this.getUrl(method)).form(data));
    }

    protected String httpPost(String method, Map<String, Object> data) throws HttpException {
        return this.http(HttpRequest.post(this.getUrl(method)).body(JSONUtil.toJsonStr(data)));
    }

    protected String httpPut(String method, Map<String, Object> data) throws HttpException {
        return this.http(HttpRequest.put(this.getUrl(method)).body(JSONUtil.toJsonStr(data)));
    }

    private String http(HttpRequest request) {
        String body = null;
        try {
            body = request.headerMap(this.getHeader(), true).execute().body();
        } catch (HttpException e) {
            e.printStackTrace();
        }

        return body;
    }
}
