package controller;

import datastorage.ConnectionBuilder;
import datastorage.LoginDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    private static boolean eingeloggt = false;

    @FXML
    private TextField txfBenutzername;
    @FXML
    private TextField txfPasswort;

    private void login() throws SQLException {
        String benutzername = this.txfBenutzername.getText();
        String passwort = this.txfPasswort.getText();

        LoginDao login = new LoginDao();

        if(login.login(benutzername, passwort)) {
            eingeloggt = true;
        }
    }
}
