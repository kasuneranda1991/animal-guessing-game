module com.cqu.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.cqu.game to javafx.fxml;
    exports com.cqu.game;
}
