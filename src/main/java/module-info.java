module com.example.Stockmaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens Aplicacoes.Modelos to javafx.base;

    opens com.example.stockmaster.Controles.LoginUsuario to javafx.fxml;
    opens com.example.stockmaster.Controles.Lotes to javafx.fxml;
    opens com.example.stockmaster.Controles.Materiais to javafx.fxml;
    opens com.example.stockmaster.Controles.Principal to javafx.fxml;
    opens com.example.stockmaster.Controles.Requisicoes to javafx.fxml;

    opens com.example.stockmaster.Telas.LoginUsuario to javafx.fxml;
    opens com.example.stockmaster.Telas.Lotes to javafx.fxml;
    opens com.example.stockmaster.Telas.Materiais to javafx.fxml;
    opens com.example.stockmaster.Telas.Principal to javafx.fxml;
    opens com.example.stockmaster.Telas.Requisicoes to javafx.fxml;

    exports com.example.stockmaster.Controles.LoginUsuario;
    exports com.example.stockmaster.Controles.Lotes;
    exports com.example.stockmaster.Controles.Materiais;
    exports com.example.stockmaster.Controles.Principal;
    exports com.example.stockmaster.Controles.Requisicoes;

    exports com.example.stockmaster.Telas.LoginUsuario;
    exports com.example.stockmaster.Telas.Lotes;
    exports com.example.stockmaster.Telas.Materiais;
    exports com.example.stockmaster.Telas.Principal;
    exports com.example.stockmaster.Telas.Requisicoes;
    exports com.example.stockmaster.Telas.Estoque;
    opens com.example.stockmaster.Telas.Estoque to javafx.fxml, javafx.graphics;
    exports com.example.stockmaster.Controles.Estoque;
    opens com.example.stockmaster.Controles.Estoque to javafx.fxml, javafx.graphics;
}
