package main;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainButton extends Button {

    public MainButton(String content) {
        super(content);
        this.setOnAction(e -> {
            if(Objects.equals(content, "退出")){
                Stage mainWindow = (Stage) this.getScene().getWindow();
                mainWindow.close();
                return;
            }

            Image icon = null;
            try {
                icon = new Image(getClass().getResource("/img/bomb.png").toExternalForm());
            } catch (Exception ex) {
                System.err.println("Failed to load image: /img/bomb.png");
                ex.printStackTrace();
            }

            GameWindow gameWindow = new GameWindow(this.getText());
            gameWindow.setTitle("扫雷——"+ this.getText());
            gameWindow.getIcons().add(icon);
            gameWindow.setResizable(false);
            gameWindow.show();
            Stage mainWindow = (Stage) this.getScene().getWindow();
            mainWindow.close();
        });
    }

    public void initStyle() {
        this.setStyle(
                "-fx-background-radius: 40;" +
                        "-fx-background-color: #3155ED;" +
                        "-fx-min-width: 132;" +
                        "-fx-min-height: 47;" +
                        "-fx-font-size: 16;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-font-family: 'KaiTi_GB2312';" +
                        "-fx-cursor: hand"
        );
    }
    public void initExitStyle() {
        this.setStyle(
                "-fx-background-radius: 40;" +
                        "-fx-background-color: #0F1F41;" +
                        "-fx-min-width: 132;" +
                        "-fx-min-height: 47;" +
                        "-fx-font-size: 16;" +
                        "-fx-text-fill: #3155ED;" +
                        "-fx-font-family: 'KaiTi_GB2312';" +
                        "-fx-border-color: #3155ED;" +
                        "-fx-border-radius: 40;" +
                        "-fx-border-width: 3;" +
                        "-fx-cursor: hand"
        );
    }
}
