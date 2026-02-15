package esm;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ESM using FXML.
 */
public class Main extends Application {

    private Esm esm = new Esm();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Esm");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEsm(esm);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
