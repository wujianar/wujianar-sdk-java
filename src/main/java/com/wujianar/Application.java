package com.wujianar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import com.wujianar.entity.TargetDetail;
import com.wujianar.entity.TargetList;
import com.wujianar.manager.TargetManager;

public class Application {
    public static void main(String[] args) throws IOException {
        testManager();
    }

    public static void testManager() throws IOException {
        // 服务器端密钥
        String accessKey = "3d9d6......6b50c88fd4b";
        String accessSecret = "2efDxOywyNEgVrKP......8NDcmoycKw9TvhGLFEr";
        String endpointUrl = "https://portal.wujianar.com/api";

        TargetManager manager = new TargetManager(accessKey, accessSecret, endpointUrl);

        try {
            TargetList data = manager.getList();
            // TargetList data = manager.getList(1, 5, "");
            // TargetDetail data = manager.getDetail("114720124a194943996bc79dcb65a5dc");
            // String uuid = manager.create("name", "{}", getImage());
            // boolean bl = manager.updateStatus(new
            // String[]{"114720124a194943996bc79dcb65a5dc"}, 1);
            // boolean bl = manager.update("114720124a194943996bc79dcb65a5dc", "konglong",
            // "{\"name\":\"konglong\"}", 1);
            // boolean bl = manager.update("114720124a194943996bc79dcb65a5dc", "A", "{}", 1,
            // getImage());
            // boolean bl = manager.delete(new
            // String[]{"114720124a194943996bc79dcb65a5dc"});
        } catch (Exception e) {
            e.printStackTrace();
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
