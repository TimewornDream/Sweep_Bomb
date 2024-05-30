import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameBlock extends StackPane {
    private int status = 0;
    GameBlock(){
        super();
        this.setMaxSize(40, 40);

        ImageView block = new ImageView("./img/block.png");
        block.setFitWidth(40);
        block.setFitHeight(40);
        ImageView cursor = new ImageView("./img/cursor_block.png");
        cursor.setFitWidth(40);
        cursor.setFitHeight(40);

        this.getChildren().add(block);

        // block 点击变换图像
        this.setOnMouseClicked( e->{
                ImageView empty = new ImageView("./img/empty_block.png");
                empty.setFitHeight(40);
                empty.setFitWidth(40);
                this.getChildren().clear();
                this.getChildren().add(empty);
                this.status = 1;
            }
        );

        // block 悬停变换图像
        this.setOnMouseEntered( e->{
            if(status == 0) {
                this.getChildren().clear();
                this.getChildren().add(cursor);
            }
        });
        this.setOnMouseExited( e->{
            if(status == 0){
                this.getChildren().clear();
                this.getChildren().add(block);
            }
        });
    }
}
