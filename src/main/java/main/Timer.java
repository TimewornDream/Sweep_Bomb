package main;

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

    Timer() {
        clock = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            this.updateTime();
            this.getChildren().clear();
            this.getChildren().addAll(getImageByType(1), getImageByType(2), getImageByType(3));
        }));
        clock.setCycleCount(Timeline.INDEFINITE);

        // 初始化计时器图片
        Image img = null;
        try {
            img = new Image(getClass().getResource("/img/0.png").toExternalForm());
        } catch (Exception e) {
            System.err.println("Failed to load image: /img/0.png");
            e.printStackTrace();
        }

        if (img != null) {
            for (int i = 0; i < 3; i++) {
                ImageView imageView = new ImageView(img);
                imageView.setImage(img);
                imageView.setFitHeight(40);
                imageView.setFitWidth(26.25);
                this.getChildren().add(imageView);
            }
        }

        this.setMaxHeight(40 + 6);
        this.setMinWidth(26.25 * 3 + 6);
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

    public void start() {
        Date start = new Date();
        startTime = start.getTime();
        clock.play();
    }

    public void stop() {
        clock.stop();
    }

    public void reset() {
        second = 0;
        this.getChildren().clear();
        this.getChildren().addAll(getImageByType(1), getImageByType(2), getImageByType(3));
    }

    // type 表示时间位数，1为百位，2为十位，3为个位
    private ImageView getImageByType(int type) {
        Image img = null;
        try {
            img = switch (type) {
                case 1 -> new Image(getClass().getResource("/img/" + second / 100 + ".png").toExternalForm());
                case 2 -> new Image(getClass().getResource("/img/" + (second % 100 - second % 10) / 10 + ".png").toExternalForm());
                case 3 -> new Image(getClass().getResource("/img/" + second % 10 + ".png").toExternalForm());
                default -> new Image(getClass().getResource("/img/0.png").toExternalForm());
            };
        } catch (Exception e) {
            System.err.println("Failed to load image for type: " + type);
            e.printStackTrace();
            img = new Image(getClass().getResource("/img/0.png").toExternalForm());
        }

        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(40);
        imageView.setFitWidth(26.25);
        return imageView;
    }
}
