package com.wujianar.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wujianar.helper.HashHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Base {
    private final String accessKey;
    private final String accessSecret;
    private final String endpointUrl;
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
        String signature = HashHelper.sha256(String.format("%s%s", json, this.accessSecret));

        // 原始token数据
        String raw = signature + json;

        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    protected Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", this.getToken());
        map.put("user-agent", "WuJianAR-Java-V2.0");
        return map;
    }

    public <T> T toResult(byte[] data, Class<T> target) throws IOException {
        return objectMapper.readValue(data, target);
    }
}
