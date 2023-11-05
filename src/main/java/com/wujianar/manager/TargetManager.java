package com.wujianar.manager;

import java.util.HashMap;
import java.util.Map;

import com.wujianar.entity.ResponseData;
import com.wujianar.entity.TargetDetail;
import com.wujianar.entity.TargetList;
import com.wujianar.entity.TargetSimple;

import cn.hutool.core.lang.TypeReference;

/**
 * 识别资源管理
 * https://www.wujianar.com
 *
 * @author WuJianAR
 */
public class TargetManager extends Base {

    /**
     * 初始化识别资源管理
     *
     * @param accessKey    访问key
     * @param accessSecret 访问secret
     * @param endpointUrl  访问地址
     */
    public TargetManager(String accessKey, String accessSecret, String endpointUrl) {
        super(accessKey, accessSecret, endpointUrl);
    }

    /**
     * 获取图片列表
     * 
     * @return
     */
    public TargetList getList() {
        return this.getList(1, 10, "");
    }

    /**
     * 获取图片列表
     *
     * @param page 第几页
     * @param size 每页多少条记录
     * @param word 搜索关键字
     * @return
     */
    public TargetList getList(int page, int size, String word) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("word", word);

        String body = this.httpGet("/images", map);
        System.out.println("getList");
        System.out.println(body);

        ResponseData<TargetList> response = this.toObj(body, new TypeReference<ResponseData<TargetList>>() {
        });
        return response.getData();
    }

    /**
     * 获取识别资源详情
     * 
     * @param uuid
     * @return
     */
    public TargetDetail getDetail(String uuid) {
        String body = this.httpGet(String.format("/images/%s", uuid));
        System.out.println("getDetail");
        System.out.println(body);

        ResponseData<TargetDetail> response = this.toObj(body, new TypeReference<ResponseData<TargetDetail>>() {
        });
        return response.getData();
    }

    /**
     * 创建识别资源
     *
     * @param name  名称
     * @param brief 说明
     * @param image 图片base64数据(不包含base64前缀)
     * @return
     */
    public String create(String name, String brief, String image) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("brief", brief);
        map.put("image", image);

        String body = this.httpPost("/images", map);
        System.out.println("create");
        System.out.println(body);

        ResponseData<TargetSimple> response = this.toObj(body, new TypeReference<ResponseData<TargetSimple>>() {
        });
        return response.getData().getUuid();
    }

    /**
     * 更新状态
     * 
     * @param uuids
     * @param status 状态: 1启用; 2禁用
     * @return
     */
    public boolean updateStatus(String[] uuids, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("uuid", uuids);

        String body = this.httpPut("/images/0/status", map);
        System.out.println("getDetail");
        System.out.println(body);

        return this.checkResult(body);
    }

    /**
     * 更新识别图
     * 
     * @param uuid
     * @param name
     * @param brief
     * @param status
     * @return
     */
    public boolean update(String uuid, String name, String brief, int status) {
        return this.update(uuid, name, brief, status, "");
    }

    /**
     * 更新识别图
     * 
     * @param uuid
     * @param name
     * @param brief
     * @param status
     * @param image
     * @return
     */
    public boolean update(String uuid, String name, String brief, int status, String image) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("brief", brief);
        map.put("status", status);
        map.put("image", image);

        String body = this.httpPut(String.format("/images/%s", uuid), map);
        System.out.println("update");
        System.out.println(body);

        return this.checkResult(body);
    }

    /**
     * 删除识别资源
     * 
     * @param uuids
     * @return
     */
    public boolean delete(String[] uuids) {
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuids);

        String body = this.httpPost("/images/0/delete", map);
        System.out.println("delete");
        System.out.println(body);

        return this.checkResult(body);
    }

    /**
     * 检测返回结果
     * 
     * @param body
     * @return
     */
    private boolean checkResult(String body) {
        ResponseData<Object> response = this.toObj(body, new TypeReference<ResponseData<Object>>() {
        });
        return response.getCode() == 200;
    }
}
