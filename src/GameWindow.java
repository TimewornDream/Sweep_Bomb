import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends Stage {
    private int numOfBomb;
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
                numOfBomb = 10;
                break;
            case "中级":
                mapWidth = 16;
                mapHeight = 16;
                numOfBomb = 40;
                break;
            case "高级":
                mapWidth = 30;
                mapHeight = 16;
                numOfBomb = 99;
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

        initMap(blocks);

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
    public void initMap(GameBlock[][] blocks){
        Random random = new Random();
        List<Integer> bombIndexes = new ArrayList<>();

        // 随机生成地雷位置
        while (bombIndexes.size() < numOfBomb) {
            int index = random.nextInt(mapWidth * mapHeight);
            if (!bombIndexes.contains(index)) {
                bombIndexes.add(index);
            }
        }

        // 将地雷位置设置为地雷
        for (int index : bombIndexes) {
            int x = index % mapWidth;
            int y = index / mapWidth;
            blocks[x][y].setType(9); // 9 表示地雷
        }

        // 设置周围数字
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (blocks[i][j].getType() != 9) { // 不是地雷的方块
                    int count = 0;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int newX = i + dx;
                            int newY = j + dy;
                            if (newX >= 0 && newX < mapWidth && newY >= 0 && newY < mapHeight && blocks[newX][newY].getType() == 9) {
                                count++;
                            }
                        }
                    }
                    blocks[i][j].setType(count); // 设置周围地雷数量
                }
            }
        }
    }

}