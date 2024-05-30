import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainButton extends Button {
    private Integer mapWidth;
    private Integer mapHeight;

    MainButton (String content, Integer mapWidth, Integer mapHeight) {
        super(content);
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.setOnAction(e -> {
            Image icon = new Image("./img/bomb.png");

            GameWindow gameWindow = new GameWindow(this.getText());
            gameWindow.setTitle("扫雷——"+ this.getText());
            gameWindow.getIcons().add(icon);
            gameWindow.setResizable(false);
            gameWindow.show();
            Stage mainWindow = (Stage) this.getScene().getWindow();
            mainWindow.close();
        });
    }

    MainButton (String content) {
        super(content);
        this.setOnAction(e -> {
            Stage mainWindow = (Stage) this.getScene().getWindow();
            mainWindow.close();
        });
    }

    public Integer getMapHeight() {
        return mapHeight;
    }

    public Integer getMapWidth() {
        return mapWidth;
    }

    public void setMapHeight(Integer mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMapWidth(Integer mapWidth) {
        this.mapWidth = mapWidth;
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
