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
//        testSearch();
        testManager();
    }

    public static void testManager() throws IOException {
        String accessKey = "8b89187720054aca9aa69509b33eef8e";
        String accessSecret = "1Dl0csbOy8FsvtoJlYmTmJzfkFYZWgrS6nOLpxqzDx6E1rgZ8T5I3w0xGlCuSyQh";
        String endpointUrl = "https://iss-cn1.wujianar.com";

        ImageManager manager = new ImageManager(accessKey, accessSecret, endpointUrl);
//        ResultList list = manager.getList();
//        ResultList list = manager.getList(1, 2, "");
//        ResultDetail detail = manager.getDetail("e582d10c1b634139bbf91539317055af");
//        ResultDetail detail = manager.createByBase64("A", "{}", getImage());
//        ResultNormal normal = manager.update("7afaef8e1ab748d5af53bfb174133e30", "AAAAAAA", "{age:10}", 2);
//        ResultNormal normal = manager.updateStatus("7afaef8e1ab748d5af53bfb174133e30", 1);
        ResultNormal normal = manager.updateImageByBase64("7afaef8e1ab748d5af53bfb174133e30", getImage());
//        ResultNormal normal = manager.delete("7afaef8e1ab748d5af53bfb174133e30");

        System.out.println(normal);
    }

    public static void testSearch() throws IOException {
        String accessKey = "657473afdc204512825fa93edbc56a66";
        String accessSecret = "LXMrTYo8DCX7IZcyK29kcJzZruxR4ueUzRfyJkQhsZu2KhY1HiUllQpiFLs0ryjN";
        String endpointUrl = "https://iss-cn1.wujianar.com";

        ImageSearcher searcher = new ImageSearcher(accessKey, accessSecret, endpointUrl);
//        ResultSearch rs = searcher.searchByBase64(getImage());
        ResultSearch rs = searcher.searchByFile("/home/jerry/Pictures/aaaaaaaaaaa.jpg");

        if (rs.getCode() == 0) {
            System.out.println(rs.getResult().getUuid());
        } else {
            System.out.println(rs.getCode() + ":" + rs.getMessage());
        }
    }

    public static String getImage() {
        String image = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/home/jerry/Pictures/10.jpg"));
            image = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return image;
    }
}
