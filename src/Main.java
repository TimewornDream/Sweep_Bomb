import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        Scene scene = new Scene(root,393,852);
        root.setStyle(
                "-fx-background-color: #FFFFFF"
        );
        primaryStage.setTitle("扫雷");

        // 上面的文本
        Text text = new Text("扫雷——拙略的模仿");
        text.setFont(Font.font("KaiTi_GB2312", FontWeight.BOLD, 22));
        text.setFill(Paint.valueOf("#3155ED"));

        HBox top = new HBox();
        top.getChildren().add(text);
        top.setAlignment(Pos.CENTER);
        top.setStyle(
                "-fx-padding: 10px"
        );

        // 中间三个按钮
        MainButton junButton = new MainButton("初级", 240, 240);
        MainButton midButton = new MainButton("中级", 480, 480);
        MainButton senButton = new MainButton("高级", 900, 480);

        junButton.initStyle();
        midButton.initStyle();
        senButton.initStyle();

        VBox center = new VBox();
        center.getChildren().addAll(junButton, midButton, senButton);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);

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