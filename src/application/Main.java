package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    private static BorderPane main_pane = new BorderPane();

    public static BorderPane getBorderPane() {
        return main_pane;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
            Parent rootFX = FXMLLoader.load(getClass().getResource("/application/Input_screen.fxml")) ;
            main_pane.setCenter(rootFX);
            Scene scene = new Scene(main_pane);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Signal Flow Graph");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
