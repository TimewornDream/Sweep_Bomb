import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Counter extends HBox {
    public static int numOfRemainingBomb;
    public static int userRemainingBomb;
    Counter(int initNumOfBomb){
        numOfRemainingBomb = initNumOfBomb;
        userRemainingBomb = initNumOfBomb;

        this.setMaxHeight(40+6);
        this.setMinWidth(26.25*3+6);
        this.setStyle(
                "-fx-border-width: 3;" +
                        "-fx-border-color: #b4c6b0;" +
                        "-fx-border-radius: 6;" +
                        "-fx-padding: 2"
        );

        this.updateImage();
    }
    public void updateImage(){
        this.getChildren().clear();
        this.getChildren().addAll(getImageByType(1), getImageByType(2), getImageByType(3));
    }
    private ImageView getImageByType (int type) {
        Image img = switch (type) {
            case 1 -> new Image("./img/" + userRemainingBomb / 100 + ".png");
            case 2 -> new Image("./img/" + (userRemainingBomb / 10 - userRemainingBomb / 100) + ".png");
            case 3 -> new Image("./img/" + userRemainingBomb % 10 + ".png");
            default -> new Image("./img/0.png");
        };
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(40);
        imageView.setFitWidth(26.25);
        return imageView;
    }
}