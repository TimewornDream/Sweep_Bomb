import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,900,480);
        root.setStyle(
                "-fx-background-color: #FFFFFF"
        );
        primaryStage.setTitle("扫雷");

        // 上面的文本
        Text text = new Text("扫雷——拙略的模仿");
        text.setFont(Font.font("楷体", FontWeight.BOLD, 22));
        text.setFill(Paint.valueOf("#3155ED"));

        HBox top = new HBox();
        top.getChildren().add(text);
        top.setAlignment(Pos.CENTER);
        top.setStyle(
                "-fx-padding: 10px"
        );

        // 中间三个按钮
        Button button1 = new Button("初级");
        Button button2 = new Button("中级");
        Button button3 = new Button("高级");

        VBox center = new VBox();
        center.prefWidthProperty().bind(scene.widthProperty().multiply(1));
        center.getChildren().addAll(button1, button2, button3);
        center.setAlignment(Pos.CENTER);

        // 添加各部分
        root.setTop(top);
        root.setCenter(center);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}