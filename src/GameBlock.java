import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("unchecked")
public class GameBlock extends Button {
    public static int edgeLength = 40;
    private int status = 0;
    private boolean isUnfold = false;
    private int type = 0;
    private final int row;
    private final int column;

    GameBlock(int row, int column) {
        super();
        this.setMaxSize(edgeLength, edgeLength);
        this.setMinSize(edgeLength, edgeLength);
        this.row = row;
        this.column = column;

        String flag = "-fx-background-image: url(./img/block_flag.png);";
        String doubt = "-fx-background-image: url(./img/block_doubt.png);";
        String press = "-fx-background-image: url(./img/block_press.png);";

        this.setBlockStyle();

        // block 右键变换图像
        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY && !isUnfold) {
                switch (status) {
                    case 0:
                        this.setBlockStyle(flag);
                        status = 1;

                        // 计数器数据域改变
                        Counter.userRemainingBomb--;
                        if (this.type == 9) {
                            Counter.numOfRemainingBomb--;
                        }

                        break;
                    case 1:
                        this.setBlockStyle(doubt);
                        status = 2;

                        // 计数器数据域改变
                        Counter.userRemainingBomb++;
                        if (this.type == 9) {
                            Counter.numOfRemainingBomb++;
                        }
                        break;
                    case 2:
                        this.setBlockStyle();
                        status = 0;
                        break;
                }
                this.setOpacity(0.6);
            }
        });

        // block 悬停变换透明度
        this.setOnMouseEntered(e -> {
            if (!isUnfold) {
                this.setOpacity(0.6);
            }
        });

        this.setOnMouseExited(e -> {
            if (!isUnfold) {
                this.setOpacity(1.8);
            }
        });

        // block 按下变换图像
        this.setOnMousePressed(e -> {
            if (status == 0 && !isUnfold && e.getButton() == MouseButton.PRIMARY) {
                this.setBlockStyle(press);
                this.setOpacity(1.8);
            }
        });

        // block 按下后松开变换图像
        this.setOnMouseReleased(e -> {
            if (status == 0 && !isUnfold && e.getButton() == MouseButton.PRIMARY) {
                if (type == 0) {
                    this.emptyBlockUnfold();
                    return;
                }
                isUnfold = true;
                this.setBlockStyle();
            }
            if (status != 0 && !isUnfold && e.getButton() == MouseButton.PRIMARY) {
                if (status == 1) {
                    // 计数器数据域改变
                    Counter.userRemainingBomb++;
                    if (this.type == 9) {
                        Counter.numOfRemainingBomb++;
                    }
                    System.out.println(Counter.userRemainingBomb);
                }
                status = 0;
                this.setBlockStyle();
            }
        });
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStyleLineWithType() {
        if (!isUnfold){
            return "-fx-background-image: url(./img/block.png);";
        }
        return switch (type) {
            case 0 -> "-fx-background-image: url(./img/empty_block.png);";
            case 1 -> "-fx-background-image: url(./img/block_1.png);";
            case 2 -> "-fx-background-image: url(./img/block_2.png);";
            case 3 -> "-fx-background-image: url(./img/block_3.png);";
            case 4 -> "-fx-background-image: url(./img/block_4.png);";
            case 5 -> "-fx-background-image: url(./img/block_5.png);";
            case 6 -> "-fx-background-image: url(./img/block_6.png);";
            case 7 -> "-fx-background-image: url(./img/block_7.png);";
            case 8 -> "-fx-background-image: url(./img/block_8.png);";
            case 9 -> "-fx-background-image: url(./img/block_bomb.png);";
            default -> "";
        };
    }

    private void setBlockStyle(){
        this.setStyle(
                getStyleLineWithType() +
                        "-fx-background-size: cover"
        );
    }

    private void setBlockStyle(String line){
        this.setStyle(
                line +
                        "-fx-background-size: cover"
        );
    }

    public boolean isPress() {
        return isUnfold;
    }

    public void addAdditionalMouseClickedHandler(EventHandler<MouseEvent> handler) {
        EventHandler<MouseEvent>originalHandler = (EventHandler<MouseEvent>) this.getOnMouseClicked();
        this.setOnMouseClicked(e-> {
            // 调用原始处理器
            originalHandler.handle(e);

            // 调用新增的处理器
            handler.handle(e);
        });
    }

    public void addAdditionalMousePressedHandler(EventHandler<MouseEvent> handler) {
        EventHandler<MouseEvent>originalHandler = (EventHandler<MouseEvent>) this.getOnMousePressed();
        this.setOnMousePressed(e-> {
            // 调用新增的处理器
            handler.handle(e);

            // 调用原始处理器
            originalHandler.handle(e);
        });
    }

    public void addAdditionalMouseReleasedHandler(EventHandler<MouseEvent> handler) {
        EventHandler<MouseEvent>originalHandler = (EventHandler<MouseEvent>) this.getOnMouseReleased();
        this.setOnMouseReleased(e-> {
            // 调用新增的处理器
            handler.handle(e);

            // 调用原始处理器
            originalHandler.handle(e);
        });
    }

    // 空白块连锁展开
    private void emptyBlockUnfold (){
        this.isUnfold = true;
        this.setBlockStyle();
        for(int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                GameBlock block = GameWindow.blocks[row+i][column+j];

                if(block.type == 0 && !block.isUnfold) {
                    block.isUnfold = true;
                    block.emptyBlockUnfold();
                }
                block.isUnfold = true;
                block.setBlockStyle();
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}