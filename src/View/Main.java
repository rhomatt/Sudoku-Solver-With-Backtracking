package View;
import Model.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class that we run to start the application
 */
public class Main extends Application{


    public void start(Stage stage){
        stage.setTitle("Sudoku Solver");
        VBox vbox = new VBox();
        GridPane gridPane = new GridPane();

        Controller controller = new Controller();

        GridPane[][] gridPanes = new GridPane[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                GridPane miniGridPane = new GridPane();
                miniGridPane.setStyle("-fx-border-style: solid inside;");
                gridPanes[i][j] = miniGridPane;
                gridPane.add(miniGridPane, j, i);
            }
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                NumberBox numberBox = new NumberBox(i, j);
                numberBox.addController(controller);
                controller.addNumberBox(numberBox);
                gridPanes[i/3][j/3].add(numberBox, j, i);
            }
        }

        Board board = new Board(controller);
        vbox.getChildren().add(gridPane);
        Button solveButton = new Button("Solve");

        vbox.getChildren().add(solveButton);
        solveButton.setOnAction(event -> {
            controller.solve();
        });

        stage.setScene(new Scene(vbox));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
