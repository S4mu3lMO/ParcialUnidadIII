module co.edu.uniquindio.parcialunidadiii {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.parcialunidadiii to javafx.fxml;
    exports co.edu.uniquindio.parcialunidadiii;
    exports co.edu.uniquindio.parcialunidadiii.factory;
    opens co.edu.uniquindio.parcialunidadiii.factory to javafx.fxml;
    exports co.edu.uniquindio.parcialunidadiii.model;
    opens co.edu.uniquindio.parcialunidadiii.model to javafx.fxml;
    exports co.edu.uniquindio.parcialunidadiii.controller;
    opens co.edu.uniquindio.parcialunidadiii.controller to javafx.fxml;
    exports co.edu.uniquindio.parcialunidadiii.app;
    opens co.edu.uniquindio.parcialunidadiii.app to javafx.fxml;
    exports co.edu.uniquindio.parcialunidadiii.observer;
    opens co.edu.uniquindio.parcialunidadiii.observer to javafx.fxml;
}