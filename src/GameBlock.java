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
        ImageView flag = new ImageView("./img/block_flag.png");
        flag.setFitWidth(40);
        flag.setFitHeight(40);
        ImageView doubt = new ImageView("./img/block_doubt.png");
        doubt.setFitWidth(40);
        doubt.setFitHeight(40);
        ImageView press = new ImageView("./img/press_block.png");
        press.setFitWidth(40);
        press.setFitHeight(40);
        ImageView empty = new ImageView("./img/empty_block.png");
        empty.setFitHeight(40);
        empty.setFitWidth(40);

        this.getChildren().add(block);

        // block 右键变换图像
        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY && !isPress) {
                this.getChildren().clear();
                switch (status) {
                    case 0:
                        this.getChildren().add(flag);
                        status = 1;
                        break;
                    case 1:
                        this.getChildren().add(doubt);
                        status = 2;
                        break;
                    case 2:
                        this.getChildren().add(block);
                        status = 0;
                        break;
                }
                this.getChildren().get(0).setOpacity(0.8);
            }
        });

        // block 悬停变换透明度
        this.setOnMouseEntered(e -> {
            if (!isPress) {
                this.getChildren().get(0).setOpacity(0.8);
            }
        });

        this.setOnMouseExited(e -> {
            if (!isPress) {
                this.getChildren().get(0).setOpacity(1.8);
            }
        });

        // block 按下变换图像
        this.setOnMousePressed( e->{
            if(status == 0 && !isPress && e.getButton() == MouseButton.PRIMARY) {
                this.getChildren().clear();
                this.getChildren().add(press);
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
