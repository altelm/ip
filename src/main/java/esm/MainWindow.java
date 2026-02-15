package esm;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Esm esm;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/download1.jpg"));
    private Image esmImage = new Image(this.getClass().getResourceAsStream("/images/download2.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Esm instance */
    public void setEsm(Esm e) {
        esm = e;
        dialogContainer.getChildren().add(
                DialogBox.getEsmDialog("Greetings I am thy humble Esm\nSpeak and I shall head the!\n", esmImage));
        String startupMessage = esm.getStartupMessage();
        if (startupMessage != null) {
            dialogContainer.getChildren().add(
                    DialogBox.getEsmDialog(startupMessage + "\n", esmImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Esm's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert esm != null : "Restart the Program";
        String input = userInput.getText();
        String response = esm.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEsmDialog(response, esmImage)
        );
        userInput.clear();

        if (response.equals("Fare thee well\n")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }
}
