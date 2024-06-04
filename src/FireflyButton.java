import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class FireflyButton extends Button {
    private int status = 0;
    FireflyButton(){
        super();
        this.setMinWidth(74);
        this.setMaxHeight(74);
        this.setInitStyle();
        this.setOnMousePressed(e->{
            if(e.getButton() == MouseButton.PRIMARY){
                this.setClickedStyle();
            }
        });
    }
    public void setInitStyle(){
        this.setStyle(
                getStyleLineWithType(0) +
                        "-fx-background-size: cover;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-width: 2"
        );
    }
    public void setClickedStyle(){
        this.setStyle(
                getStyleLineWithType(1) +
                        "-fx-background-size: cover;" +
                        "-fx-border-color: #8ca386;" +
                        "-fx-border-width: 2"
        );
    }

    // type 为0表示未按下，为1表示按下
    private String getStyleLineWithType(int x){
        String end;
        if (x == 0) {
            end = ".png);";
        } else {
            end = "_onClick.png);";
        }
        return switch (status) {
            case 0 -> "-fx-background-image: url(./img/firefly" + end;
            case 1 -> "-fx-background-image: url(./img/firefly_press" + end;
            case 2 -> "-fx-background-image: url(./img/firefly_win" + end;
            case 3 -> "-fx-background-image: url(./img/firefly_lose" + end;
            default -> "err";
        };
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
