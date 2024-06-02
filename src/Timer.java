import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.Date;

public class Timer extends HBox {
    private long startTime;
    private long second = 0;
    private final Timeline clock;
    Timer(){
        clock = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            this.updateTime();
            this.getChildren().clear();
            this.getChildren().addAll(getImageByType(1), getImageByType(2), getImageByType(3));
        }));
        clock.setCycleCount(Timeline.INDEFINITE);

        // 初始化计时器图片
        Image img = new Image("./img/0.png");
        for (int i = 0;i < 3; i++) {
            ImageView imageView = new ImageView(img);
            imageView.setImage(img);
            imageView.setFitHeight(40);
            imageView.setFitWidth(26.25);
            this.getChildren().add(imageView);
        }

        this.setMaxHeight(40+6);
        this.setMinWidth(26.25*3+6);
        this.setStyle(
                "-fx-border-width: 3;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-radius: 6;" +
                        "-fx-padding: 2;" +
                        "-fx-border-style: dotted"
        );

    }
    private void updateTime() {
        // 获取当前时间并格式化
        Date now = new Date();
        second = (now.getTime() - startTime) / 1000;
    }
    public void start(){
        Date start = new Date();
        startTime = start.getTime();
        clock.play();
    }

    // type 表示时间位数，1为百位，2为十位，3为个位
    private ImageView getImageByType (int type) {
        Image img = switch (type) {
            case 1 -> new Image("./img/" + second / 100 + ".png");
            case 2 -> new Image("./img/" + (second % 100 - second % 10) / 10 + ".png");
            case 3 -> new Image("./img/" + second % 10 + ".png");
            default -> new Image("./img/0.png");
        };
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(40);
        imageView.setFitWidth(26.25);
        return imageView;
    }
}