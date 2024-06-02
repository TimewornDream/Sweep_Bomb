import javafx.scene.control.Button;

public class FireflyButton extends Button {
    private int status = 0;
    FireflyButton(){
        super();
        this.setMinWidth(70);
        this.setMaxHeight(70);
        this.setStyle(
                "-fx-background-image: url(./img/firefly.png);" +
                        "-fx-background-size: cover;" +
                        "-fx-border-radius: 4;" +
                        "-fx-border-color: #8ca386"
        );
    }
}
