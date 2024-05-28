import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
       primaryStage.setTitle("扫雷");
       primaryStage.setScene(new Scene(new StackPane(), 300, 300));
       primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}