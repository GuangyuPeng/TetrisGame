package pub.fashioner.tetris.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pub.fashioner.tetris.controller.TetrisController;

import java.io.IOException;

public class TetrisView extends Application {

    private Stage primaryStage;

    private TetrisViewController viewController;

    private TetrisController gameController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tetris Game");
        this.primaryStage.setResizable(false);
        initRootPane();

        gameController = new TetrisController(viewController, primaryStage);

    }

    private void initRootPane() {
        try {
            // Load fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TetrisView.class.getResource("GameBoard.fxml"));
            AnchorPane rootPane = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootPane);
            primaryStage.setScene(scene);

            // get controller
            this.viewController = loader.getController();
            this.viewController.initSize();

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
