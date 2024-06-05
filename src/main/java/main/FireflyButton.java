package main;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class FireflyButton extends Button {
    private int status = 0;

    FireflyButton() {
        super();
        this.setMinWidth(74);
        this.setMaxHeight(74);
        this.setInitStyle();
        this.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                this.setClickedStyle();
            }
        });
    }

    public void setInitStyle() {
        this.setStyle(
                getStyleLineWithType(0) +
                        "-fx-background-size: cover;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-width: 2"
        );
    }

    public void setClickedStyle() {
        this.setStyle(
                getStyleLineWithType(1) +
                        "-fx-background-size: cover;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-width: 2"
        );
    }

    // type 为0表示未按下，为1表示按下
    private String getStyleLineWithType(int x) {
        String end;
        if (x == 0) {
            end = ".png";
        } else {
            end = "_onClick.png";
        }

        String imagePath;
        switch (status) {
            case 0:
                imagePath = "/img/firefly" + end;
                break;
            case 1:
                imagePath = "/img/firefly_press" + end;
                break;
            case 2:
                imagePath = "/img/firefly_win" + end;
                break;
            case 3:
                imagePath = "/img/firefly_lose" + end;
                break;
            default:
                imagePath = "/img/default.png"; // Assuming you have a default image
        }

        // Ensure the resource path is not null
        String resourcePath = null;
        try {
            resourcePath = getClass().getResource(imagePath).toExternalForm();
        } catch (NullPointerException e) {
            System.err.println("Error: Resource not found - " + imagePath);
            resourcePath = getClass().getResource("/img/default.png").toExternalForm(); // Assuming you have a default image
        }

        return "-fx-background-image: url('" + resourcePath + "');";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
