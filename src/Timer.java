import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.Date;

public class Timer extends HBox {
    private final long startTime;
    private long second = 0;
    private final Timeline clock;
    Timer(){
        Date start = new Date();
        startTime = start.getTime();
        clock = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            this.updateTime();
            System.out.println(second);
        }));
        clock.setCycleCount(Timeline.INDEFINITE);
    }
    private void updateTime() {
        // 获取当前时间并格式化
        Date now = new Date();
        second = (now.getTime() - startTime) / 1000;
    }
    public void start(){
        clock.play();
    }
}