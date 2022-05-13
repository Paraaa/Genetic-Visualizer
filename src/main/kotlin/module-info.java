module com.example.thesis {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.logging.jvm;

    opens com.geneticVisualizer.gui to javafx.fxml;
    exports com.geneticVisualizer;
}