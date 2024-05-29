import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameWindow extends Stage {
    public GameWindow(String difficulty) {
        VBox gameRoot = new VBox();

        BorderPane top = new BorderPane();
        top.setMaxHeight(120);
        top.setMinHeight(120);
        top.setStyle(
                "-fx-background-color: #323232"
        );

        FlowPane center = new FlowPane();
        GameBlock[][] blocks = new GameBlock[1][1];

        center.setStyle(
                "-fx-background-color: #000000"
        );


        int width = 0, height = 0;
        switch (difficulty){
            case "初级":
                width = calculateWidth(8);
                height = calculateHeight(8);
                center.setMaxWidth(320);
                center.setMinHeight(320);
                blocks = new GameBlock[8][8];
                break;
            case "中级":
                width = calculateWidth(16);
                height = calculateHeight(16);
                center.setMaxWidth(640);
                center.setMinHeight(640);
                blocks = new GameBlock[16][16];
                break;
            case "高级":
                width = calculateWidth(30);
                height = calculateHeight(16);
                center.setMaxWidth(1200);
                center.setMinHeight(640);
                blocks = new GameBlock[30][16];
                break;
        }
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