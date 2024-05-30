import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBlock extends Rectangle {
    GameBlock(){
        super(39, 39);
        this.setFill(Color.BLUE);
        this.setStroke(Color.ALICEBLUE);
        this.setStrokeWidth(1);
    }
}
