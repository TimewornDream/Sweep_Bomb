import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends Stage {
    private boolean isInit = false;
    private int numOfBomb;
    private int mapWidth;
    private int mapHeight;
    private static GameBlock[][] blocks;
    public GameWindow(String difficulty) {
        // 根据难度初始化地图参数
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

        VBox gameRoot = new VBox();

        // 顶部
        BorderPane top = new BorderPane();
        top.setMaxHeight(120);
        top.setMinHeight(120);
        top.setStyle(
                "-fx-background-color: #c9d0ed"
        );


        HBox topBox = new HBox();
        topBox.setStyle(
                "-fx-border-width: 4;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-radius: 20px;" +
                        "-fx-background-radius: 22;" +
                        "-fx-background-color: #e7f0e0"
        );
        topBox.setMaxHeight(80+4*2);
        topBox.setMaxWidth((mapWidth+2) * GameBlock.edgeLength);


        Timer timer = new Timer();
        topBox.getChildren().addAll(timer);
        topBox.setAlignment(Pos.CENTER);
        top.setCenter(topBox);

        FlowPane center = new FlowPane();

        center.setStyle(
                "-fx-background-color: #c9d0ed;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-color: #2851f9"
        );

        // 获取第一个点击的 block 位置,并初始化,并开始计时
        center.setOnMousePressed(e-> {
            if (!isInit){
                for (int i = 0; i < blocks.length; i++) {
                    for (int j = 0; j < blocks[0].length; j++) {
                        if (blocks[i][j].isPress()) {
                            int initPos = (i - 1) * mapWidth + (j - 1);
                            initMap(blocks, initPos);
                            isInit = true;
                            timer.start();
                            return;
                        }
                    }
                }
            }
        });

        int width = calculateWidth(mapWidth);
        int height = calculateHeight(mapHeight);
        center.setMaxWidth(mapWidth * GameBlock.edgeLength + 6);
        center.setMinHeight(mapWidth * GameBlock.edgeLength + 6);
        blocks = new GameBlock[mapHeight + 2][mapWidth + 2];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                GameBlock block = new GameBlock(); // 创建GameBlock对象
                blocks[i][j] = block; // 将GameBlock对象添加到blocks数组
                if(i >= 1 && i <= mapHeight && j >= 1 && j <= mapWidth) {
                    center.getChildren().add(block);
                }
            }
        }

        gameRoot.setAlignment(Pos.TOP_CENTER);
        gameRoot.getChildren().addAll(top, center);
        gameRoot.setStyle(
                "-fx-background-color: #c9d0ed;"
        );

        Scene gameScene = new Scene(gameRoot, width, height);
        this.setScene(gameScene);
    }

    private static int calculateWidth (int width) {
        return GameBlock.edgeLength*(width+4);
    }

    private static int calculateHeight (int height) {
        return GameBlock.edgeLength*(height+5);
    }
    public void initMap(GameBlock[][] blocks, int initPos){
        Random random = new Random();
        List<Integer> bombIndexes = new ArrayList<>();

        // 随机生成地雷位置
        while (bombIndexes.size() < numOfBomb) {
            int index = random.nextInt(mapWidth * mapHeight);
            if (!bombIndexes.contains(index) && index != initPos) {
                bombIndexes.add(index);
            }
        }

        // 将地雷位置设置为地雷
        for (int index : bombIndexes) {
            int x = index / mapWidth + 1;
            int y = index % mapWidth + 1;
            blocks[x][y].setType(9); // 9 表示地雷
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int newX = x + dx;
                    int newY = y + dy;
                    if (blocks[newX][newY].getType() != 9) {
                        blocks[newX][newY].setType(blocks[newX][newY].getType() + 1);
                    }
                }
            }
        }
    }
}