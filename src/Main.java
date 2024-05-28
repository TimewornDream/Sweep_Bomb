import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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

        // 中间三个圆
        StackPane center = new StackPane();
        Circle circle1 = new Circle(319);
        circle1.setFill(Paint.valueOf("#F2F3F5"));
        circle1.setTranslateY(180);

        Circle circle2 = new Circle(319);
        circle2.setFill(Paint.valueOf("#E8EAED"));
        circle2.setTranslateY(240);

        Circle circle3 = new Circle(319);
        circle3.setFill(Paint.valueOf("#0F1F41"));
        circle3.setTranslateY(300);

        // 中间三个按钮
        MainButton junButton = new MainButton("初级", 240, 240);
        MainButton midButton = new MainButton("中级", 480, 480);
        MainButton senButton = new MainButton("高级", 900, 480);

        junButton.initStyle();
        midButton.initStyle();
        senButton.initStyle();

        VBox buttons = new VBox();
        buttons.getChildren().addAll(junButton, midButton, senButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.setTranslateY(150);

        center.getChildren().addAll(circle1, circle2, circle3, buttons);

        // 添加各部分
        root.setTop(top);
        root.setCenter(center);
        root.setMinWidth(scene.getWidth());
        root.setMinHeight(scene.getHeight());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}