module com.example.nagybendeguz_lotto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nagybendeguz_lotto to javafx.fxml;
    exports com.example.nagybendeguz_lotto;
}