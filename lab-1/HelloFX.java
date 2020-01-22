import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        // request version info from the System class
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        // Create a label object showing the version strings
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        // Create a Scene object and pass it the label. Stack the objects
        Scene scene = new Scene(new StackPane(l), 640, 480);
        // pass the Scene object to the stage
        stage.setScene(scene);
        // make the stage visible
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}