package controller;

import datastorage.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.sql.SQLException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TextField txfBenutzername;
    @FXML
    private TextField txfPasswort;
    @FXML
    private Label warningLabel;

    private static boolean eingeloggt = false;

    @FXML
    private void handleShowAllPatient(ActionEvent e) {

        System.out.println(eingeloggt);
        if(eingeloggt) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AllPatientController controller = loader.getController();
        }

    }

    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        System.out.println(eingeloggt);
        if(eingeloggt)
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AllTreatmentController controller = loader.getController();
        }
    }

    @FXML
    private void handleShowAllCaregivers(ActionEvent e) {
        System.out.println(eingeloggt);
        if(eingeloggt) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            AllCaregiverController controller = loader.getController();
        }
    }

    @FXML
    private void login() throws SQLException {
        String benutzername = this.txfBenutzername.getText();
        String passwort = this.txfPasswort.getText();

        LoginDao login = new LoginDao();

        if(login.login(benutzername, passwort)) {
            eingeloggt = true;
        } else {
            warningLabel.setText("Falscher Benutzername oder Passwort!");
        }
    }

    @FXML
    private void logout() {
        this.eingeloggt = false;
    }
}
