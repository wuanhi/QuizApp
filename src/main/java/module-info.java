module com.tqh.design_pattern {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql; 
    requires lombok; 
    opens com.tqh.design_pattern to javafx.fxml;
    exports com.tqh.design_pattern;
}
