import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameWindow extends Stage {
    public GameWindow(String difficulty) {
        // 创建游戏界面的内容，例如雷区、计时器等
        // 这里只是一个示例，你需要根据实际情况创建游戏界面的内容
        BorderPane gameRoot = new BorderPane();

        int width = 0, height = 0;
        switch (difficulty){
            case "初级":
                width = calculateWidth(8);
                height = calculateHeight(8);
                System.out.println(width);
                System.out.println(height);
                break;
            case "中级":
                width = calculateWidth(16);
                height = calculateHeight(16);
                break;
            case "高级":
                width = calculateWidth(30);
                height = calculateHeight(16);
                break;
        }

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