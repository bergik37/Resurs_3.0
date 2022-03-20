package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane firstScene = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Resources/firstScene.fxml"));
        primaryStage.setTitle("Ресурс_Ver2");
        primaryStage.setScene(new Scene(firstScene, 1200, 900));
        primaryStage.show();
    }
}
