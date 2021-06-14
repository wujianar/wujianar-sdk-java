package com.wujianar;

import com.wujianar.base.Base;
import com.wujianar.entity.ResultSearch;
import com.wujianar.helper.HttpHelper;

import java.io.IOException;

/**
 * 图片搜索
 * <p>
 * https://www.wujianar.com
 *
 * @author WuJianAR
 */
public class ImageSearcher extends Base {

    /**
     * 初始化识别图片搜索
     *
     * @param accessKey    访问key
     * @param accessSecret 访问secret
     * @param endpointUrl  访问地址
     */
    public ImageSearcher(String accessKey, String accessSecret, String endpointUrl) {
        super(accessKey, accessSecret, endpointUrl);
    }

    private String getSearcherUrl() {
        return String.format("%s/search", this.getEndpointUrl());
    }

    /**
     * 使用本地图片搜索 (推荐：传输的数据量比base64小，传输速度比较快)
     *
     * @param filename 文件路径
     * @return ResultDetail
     * @throws IOException
     */
    public ResultSearch searchByFile(String filename) throws IOException {
        byte[] data = new HttpHelper(HttpHelper.POST, this.getSearcherUrl())
                .setHeader(this.getHeader())
                .setFile(filename)
                .call();
        return this.toResult(data, ResultSearch.class);
    }

    /**
     * 使用图片的base64数据搜索 (不包含”data:image/jpg;base64,“前缀)
     *
     * @param image 图片base数据
     * @return ResultDetail
     * @throws IOException
     */
    public ResultSearch searchByBase64(String image) throws IOException {
        byte[] data = new HttpHelper(HttpHelper.POST, this.getSearcherUrl())
                .setHeader(this.getHeader())
                .setBodyStr(String.format("{\"image\":\"%s\"}", image))
                .call();
        return this.toResult(data, ResultSearch.class);
    }
}
