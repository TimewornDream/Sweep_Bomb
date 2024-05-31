import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

public class GameBlock extends StackPane {
    private int status = 0;
    private boolean isPress = false;
    GameBlock(){
        super();
        this.setMaxSize(40, 40);

        ImageView block = new ImageView("./img/block.png");
        block.setFitWidth(40);
        block.setFitHeight(40);
        ImageView cursor = new ImageView("./img/cursor_block.png");
        cursor.setFitWidth(40);
        cursor.setFitHeight(40);
        ImageView empty = new ImageView("./img/empty_block.png");
        empty.setFitHeight(40);
        empty.setFitWidth(40);

        this.getChildren().add(block);


        // block 悬停变换图像
        this.setOnMouseEntered(e -> {
            if (status == 0) {
                block.setOpacity(0.8); // 将透明度设置为70%来实现变暗效果
            }
        });

        this.setOnMouseExited(e -> {
            if (status == 0) {
                block.setOpacity(1.8); // 恢复透明度
            }
        });

        // block 按下变换图像
        this.setOnMousePressed( e->{
            if(status == 0 && !isPress && e.getButton() == MouseButton.PRIMARY) {
                this.getChildren().clear();
                this.getChildren().add(cursor);
                isPress = true;
            }
        });

        // block 按下后松开变换图像
        this.setOnMouseMoved( e->{
            if(status == 0 && isPress){
                this.getChildren().clear();
                this.getChildren().add(empty);
            }
        });
    }
}
