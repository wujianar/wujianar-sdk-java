package com.wujianar;

import com.wujianar.base.Base;
import com.wujianar.entity.ResultDetail;
import com.wujianar.entity.ResultList;
import com.wujianar.entity.ResultNormal;
import com.wujianar.helper.HttpHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 识别资源管理
 * <p>
 * https://www.wujianar.com
 *
 * @author WuJianAR
 */
public class ImageManager extends Base {

    /**
     * 初始化识别资源管理
     *
     * @param accessKey    访问key
     * @param accessSecret 访问secret
     * @param endpointUrl  访问地址
     */
    public ImageManager(String accessKey, String accessSecret, String endpointUrl) {
        super(accessKey, accessSecret, endpointUrl);
    }

    /**
     * 获取图片列表
     *
     * @return ResultList
     * @throws IOException
     */
    public ResultList getList() throws IOException {
        return this.getList(1, 10, "");
    }

    /**
     * 获取图片列表
     *
     * @param page 第几页
     * @param size 每页多少条记录
     * @param word 名称搜索关键字
     * @return ResultList
     * @throws IOException
     */
    public ResultList getList(int page, int size, String word) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("word", word);

        String url = String.format("%s/images", this.getEndpointUrl());
        byte[] data = new HttpHelper(HttpHelper.GET, url).setHeader(this.getHeader()).setParameter(map).call();
        return this.toResult(data, ResultList.class);
    }

    /**
     * 获取识别资源详情
     *
     * @param uuid uuid
     * @return ResultDetail
     * @throws IOException
     */
    public ResultDetail getDetail(String uuid) throws IOException {
        String url = String.format("%s/images/%s", this.getEndpointUrl(), uuid);
        byte[] data = new HttpHelper(HttpHelper.GET, url).setHeader(this.getHeader()).call();
        return this.toResult(data, ResultDetail.class);
    }

    /**
     * 使用图片的base64数据创建识别资源
     *
     * @param name  名称
     * @param brief 说明
     * @param image 图片base64数据
     * @return ResultDetail
     * @throws IOException
     */
    public ResultDetail createByBase64(String name, String brief, String image) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("brief", brief);
        map.put("image", image);

        String url = String.format("%s/images", this.getEndpointUrl());
        byte[] data = new HttpHelper(HttpHelper.POST, url).setHeader(this.getHeader()).setParameter(map).call();
        return this.toResult(data, ResultDetail.class);
    }

    /**
     * 更新状态
     *
     * @param uuid   uuid
     * @param status 状态: 1启用; 2禁用
     * @return
     * @throws IOException
     */
    public ResultNormal updateStatus(String uuid, int status) throws IOException {
        return this.update(uuid, "status", status);
    }

    public ResultNormal update(String uuid, String name, String brief, int status) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("brief", brief);
        map.put("status", status);

        String url = String.format("%s/images/%s", this.getEndpointUrl(), uuid);
        byte[] data = new HttpHelper(HttpHelper.PUT, url).setHeader(this.getHeader()).setBody(map).call();
        return this.toResult(data, ResultNormal.class);
    }

    private ResultNormal update(String uuid, String key, Object value) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("value", value);

        String url = String.format("%s/images/%s/%s", this.getEndpointUrl(), uuid, key);
        byte[] data = new HttpHelper(HttpHelper.PUT, url).setHeader(this.getHeader()).setBody(map).call();
        return this.toResult(data, ResultNormal.class);
    }

    /**
     * 更新识别图 (base64数据方式)
     *
     * @param uuid  uuid
     * @param image 图片base64数据
     * @return ResultNormal
     * @throws IOException
     */
    public ResultNormal updateImageByBase64(String uuid, String image) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("image", image);

        String url = String.format("%s/images/%s/image", this.getEndpointUrl(), uuid);
        byte[] data = new HttpHelper(HttpHelper.PUT, url).setHeader(this.getHeader()).setBody(map).call();
        return this.toResult(data, ResultNormal.class);
    }

    /**
     * 删除识别资源
     *
     * @param uuid uuid
     * @return ResultNormal
     * @throws IOException
     */
    public ResultNormal delete(String uuid) throws IOException {
        String url = String.format("%s/images/%s", this.getEndpointUrl(), uuid);
        byte[] data = new HttpHelper(HttpHelper.DELETE, url).setHeader(this.getHeader()).call();
        return this.toResult(data, ResultNormal.class);
    }
}
