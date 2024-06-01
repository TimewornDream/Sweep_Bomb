import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

public class GameBlock extends StackPane {
    public static int edgeLength = 40;
    private int status = 0;
    private boolean isPress = false;
    private int type = 0;

    GameBlock() {
        super();
        this.setMaxSize(edgeLength, edgeLength);

        ImageView block = new ImageView("./img/block.png");
        block.setFitWidth(edgeLength);
        block.setFitHeight(edgeLength);
        ImageView flag = new ImageView("./img/block_flag.png");
        flag.setFitWidth(edgeLength);
        flag.setFitHeight(edgeLength);
        ImageView doubt = new ImageView("./img/block_doubt.png");
        doubt.setFitWidth(edgeLength);
        doubt.setFitHeight(edgeLength);
        ImageView press = new ImageView("./img/press_block.png");
        press.setFitWidth(edgeLength);
        press.setFitHeight(edgeLength);
        ImageView empty = new ImageView("./img/empty_block.png");
        empty.setFitHeight(edgeLength);
        empty.setFitWidth(edgeLength);

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
            } else if (status == 0) {
                this.getChildren().clear();
                this.getChildren().add(this.getImageWithType());
            }
        });

        // block 按下变换图像
        this.setOnMousePressed(e -> {
            if (status == 0 && !isPress && e.getButton() == MouseButton.PRIMARY) {
                this.getChildren().clear();
                this.getChildren().add(press);
                isPress = true;
            }
        });

        // block 按下后松开变换图像
        this.setOnMouseMoved(e -> {
            if (status == 0 && isPress) {
                this.getChildren().clear();
                this.getChildren().add(this.getImageWithType());
            }
        });
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ImageView getImageWithType() {
        ImageView imageView = switch (type) {
            case 0 -> new ImageView("./img/empty_block.png");
            case 1 -> new ImageView("./img/block_1.png");
            case 2 -> new ImageView("./img/block_2.png");
            case 3 -> new ImageView("./img/block_3.png");
            case 4 -> new ImageView("./img/block_4.png");
            case 5 -> new ImageView("./img/block_5.png");
            case 6 -> new ImageView("./img/block_6.png");
            case 7 -> new ImageView("./img/block_7.png");
            case 8 -> new ImageView("./img/block_8.png");
            case 9 -> new ImageView("./img/block_bomb.png");
            default -> new ImageView();
        };
        imageView.setFitWidth(edgeLength);
        imageView.setFitHeight(edgeLength);
        return imageView;
    }
}