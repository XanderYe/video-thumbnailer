package cn.xanderye;

import cn.xanderye.config.Config;
import cn.xanderye.config.ThreadPoolConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @author XanderYe
 * @date 2020/2/6
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Config config = Config.getInstance();
        ThreadPoolConfig threadPoolConfig = ThreadPoolConfig.getInstance();
        threadPoolConfig.setScheduledExecutorService(Executors.newSingleThreadScheduledExecutor());
        config.setUserDir(System.getProperty("user.dir"));
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("video-thumbnailer");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            threadPoolConfig.getScheduledExecutorService().shutdownNow();
            Platform.exit();
            System.exit(0);
        });
    }
}
