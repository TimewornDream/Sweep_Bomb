import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameWindow extends Stage {
    public GameWindow(String difficulty) {
        // 创建游戏界面的内容，例如雷区、计时器等
        // 这里只是一个示例，你需要根据实际情况创建游戏界面的内容
        BorderPane gameRoot = new BorderPane();
        // 添加游戏界面的其他组件和布局

        // 创建一个 Scene 并将游戏界面的内容设置为根节点
        Scene gameScene = new Scene(gameRoot, 800, 600);
        this.setScene(gameScene);
    }
}