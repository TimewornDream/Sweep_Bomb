package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Counter extends HBox {
    public static int numOfRemainingBomb;
    public static int userRemainingBomb;

    Counter(int initNumOfBomb) {
        numOfRemainingBomb = initNumOfBomb;
        userRemainingBomb = initNumOfBomb;

        this.setMaxHeight(40 + 6);
        this.setMinWidth(26.25 * 3 + 6);
        this.setStyle(
                "-fx-border-width: 3;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-radius: 6;" +
                        "-fx-padding: 2;" +
                        "-fx-border-style: dotted"
        );

        this.updateImage();
    }

    public void updateImage() {
        this.getChildren().clear();
        this.getChildren().addAll(getImageByType(1), getImageByType(2), getImageByType(3));
    }

    private ImageView getImageByType(int type) {
        String imagePath;
        switch (type) {
            case 1:
                imagePath = "/img/" + userRemainingBomb / 100 + ".png";
                break;
            case 2:
                imagePath = "/img/" + (userRemainingBomb / 10 % 10) + ".png";
                break;
            case 3:
                imagePath = "/img/" + userRemainingBomb % 10 + ".png";
                break;
            default:
                imagePath = "/img/0.png";
        }

        Image img;
        try {
            img = new Image(getClass().getResource(imagePath).toExternalForm());
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            img = new Image(getClass().getResource("/img/0.png").toExternalForm());
        }

        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(40);
        imageView.setFitWidth(26.25);
        return imageView;
    }

    public void reset(int initNumOfBomb) {
        numOfRemainingBomb = initNumOfBomb;
        userRemainingBomb = initNumOfBomb;
        updateImage();
    }
}
