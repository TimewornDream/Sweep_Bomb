import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameWindow extends Stage {
    private int mapWidth;
    private int mapHeight;
    public GameWindow(String difficulty) {
        VBox gameRoot = new VBox();

        BorderPane top = new BorderPane();
        top.setMaxHeight(120);
        top.setMinHeight(120);
        top.setStyle(
                "-fx-background-color: #323232"
        );

        FlowPane center = new FlowPane();

        center.setStyle(
                "-fx-background-color: #000000"
        );

        switch (difficulty){
            case "初级":
                mapWidth = 8;
                mapHeight = 8;
                break;
            case "中级":
                mapWidth = 16;
                mapHeight =16;
                break;
            case "高级":
                mapWidth = 30;
                mapHeight = 30;
                break;
        }
        int width = calculateWidth(mapWidth);
        int height = calculateHeight(mapHeight);
        center.setMaxWidth(mapWidth * GameBlock.edgeLength);
        center.setMinHeight(mapWidth * GameBlock.edgeLength);
        GameBlock[][] blocks = new GameBlock[mapWidth][mapHeight];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                GameBlock block = new GameBlock(); // 创建GameBlock对象
                blocks[i][j] = block; // 将GameBlock对象添加到blocks数组
                center.getChildren().add(block);
            }
        }


        gameRoot.setAlignment(Pos.TOP_CENTER);
        gameRoot.getChildren().addAll(top, center);

        Scene gameScene = new Scene(gameRoot, width, height);
        this.setScene(gameScene);
    }

    private static int calculateWidth (int width) {
        return 40*width+80;
    }

    private static int calculateHeight (int height) {
        return 40*height+160;
    }
}