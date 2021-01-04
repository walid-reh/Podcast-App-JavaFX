package Vue;

import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    public static String screenPODID = "screenPOD";
    public static String screenPODFile = "MainViewer.fxml";
    public static String screenSEARCHID = "screenSEARCH";
    public static String screenSEARCHFile = "MenuSearch.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screenSEARCHID, Main.screenSEARCHFile);
        mainContainer.loadScreen(Main.screenPODID, Main.screenPODFile);

        mainContainer.setScreen(Main.screenSEARCHID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/sample/img/FORTIFY.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
