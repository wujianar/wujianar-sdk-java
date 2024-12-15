package com.wujianar;

import com.wujianar.entity.ResultDetail;
import com.wujianar.entity.ResultList;
import com.wujianar.entity.ResultNormal;
import com.wujianar.entity.ResultSearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Application {
    public static void main(String[] args) throws IOException {
        // testSearch();
        testManager();
    }

    public static void testManager() throws IOException {
        String accessKey = "3d9d6......a3c6b50c88fd4b";
        String accessSecret = "2efDxOywyN......oZnjcG8Um8NDcmoycKw9TvhGLFEr";
        String endpointUrl = "https://portal.wujianar.cn/api";

        ImageManager manager = new ImageManager(accessKey, accessSecret, endpointUrl);
        // ResultList list = manager.getList();
        // ResultList list = manager.getList(1, 2, "");
        // ResultDetail detail = manager.getDetail("e39a781343e54b34a5685bd5528e1eb8");
        // ResultDetail detail = manager.createByBase64("name", "{}", getImage());
        // ResultNormal normal = manager.updateStatus(new String[]{"7fa9d2c43c76423c8be9b86999795a18"}, 2);
        // ResultNormal normal = manager.update("7fa9d2c43c76423c8be9b86999795a18", "AAAAAAA", "{age:11}", 1);
        // ResultNormal normal = manager.delete(new String[]{"a11680010bcb419088f6364f6bc5603d"});

        // System.out.println(normal);
        System.out.println("done");
    }

    public static void testSearch() throws IOException {
        String accessKey = "887f1......948a792533d5fef646d";
        String accessSecret = "6eFRLOy......H1JJoNfTKCCml4yW3rRDuN4JeQS5kzK";
        String endpointUrl = "https://iss-api.wujianar.cn";

        ImageSearcher searcher = new ImageSearcher(accessKey, accessSecret, endpointUrl);
        // ResultSearch rs = searcher.searchByBase64(getImage());
        ResultSearch rs = searcher.searchByFile("/home/wujianar/Pictures/1.jpg");

        // 搜索到到目标
        if (rs.getCode() == 200) {
            System.out.println(rs.getData().getUuid());
        } else {
            // 未搜索到目标
            System.out.println(rs.getCode() + ":" + rs.getMessage());
        }
    }

    public static String getImage() {
        String image = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/home/wujianar/Pictures/1.jpg"));
            image = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return image;
    }
}
