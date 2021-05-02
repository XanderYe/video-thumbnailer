package cn.xanderye.controller;

import cn.xanderye.config.ThreadPoolConfig;
import cn.xanderye.entity.Progress;
import cn.xanderye.util.JavaFxUtil;
import cn.xanderye.util.SystemUtil;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.StringUtils;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * @author XanderYe
 * @date 2020/2/6
 */
public class MainController implements Initializable {

    @FXML
    private TextField filePathText, outputPathText;
    @FXML
    private TextField widthText, rowText, colText;
    @FXML
    private Button genBtn;

    private FileChooser fileChooser;

    private DirectoryChooser outputChooser;

    private List<Progress> list = new CopyOnWriteArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setTitle("请选择要处理的视频文件");
        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter("All videos", "*.mp4", "*.mkv", "*.wmv", "*.avi");
        fileChooser.getExtensionFilters().addAll(videoFilter);
        outputChooser = new DirectoryChooser();
        outputChooser.setInitialDirectory(fsv.getHomeDirectory());
        outputChooser.setTitle("请选择图片保存位置");

        JavaFxUtil.checkNumberListener(widthText, 0L, null);
        JavaFxUtil.checkNumberListener(rowText, 0L, null);
        JavaFxUtil.checkNumberListener(colText, 0L, null);

        /*ScheduledExecutorService service = ThreadPoolConfig.getInstance().getScheduledExecutorService();
        service.scheduleAtFixedRate(() -> {
            if (!list.isEmpty()) {
                System.out.println(list.toString());
            }
        }, 0, 1, TimeUnit.SECONDS);*/
    }

    public void chooseFile() {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            filePathText.setText(file.getAbsolutePath());
        }
    }

    public void chooseOutput() {
        File file = outputChooser.showDialog(null);
        if (file != null) {
            outputPathText.setText(file.getAbsolutePath());
        }
    }

    public void generate() {
        if (StringUtils.isAnyEmpty(filePathText.getText(), outputPathText.getText())) {
            JavaFxUtil.errorDialog("错误", "参数不能为空");
            return;
        }
        genBtn.setDisable(true);
        String filePath = filePathText.getText();
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        Progress progress = new Progress();
        progress.setId(System.currentTimeMillis());
        progress.setName(fileName);
        progress.setPercent(0);
        list.add(progress);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            SystemUtil.execVcsi(progress, filePath, outputPathText.getText(), widthText.getText(), rowText.getText(), colText.getText());
            progress.setPercent(100);
            Platform.runLater(() -> {
                genBtn.setDisable(false);
                JavaFxUtil.alertDialog("恭喜", "处理成功");
            });
        });
    }
}
