package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import javax.xml.catalog.Catalog;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private ComboBox<String> cbCaregiver;

    private AllTreatmentController controller;
    private Caregiver caregiver;
    private Patient patient;
    private Stage stage;
    private ArrayList<Caregiver> caregiverArrayList;

    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();


    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        cbCaregiver.setItems(myComboBoxData);
        cbCaregiver.getSelectionModel().select(0);

        createComboBoxData();

        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        showPatientData();
    }

    private void createComboBoxData(){
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverArrayList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver careGiver: caregiverArrayList) {
                this.myComboBoxData.add(careGiver.getSurname());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleComboBox() throws SQLException {
        String c = this.cbCaregiver.getSelectionModel().getSelectedItem();
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregiver = dao.readAll();
        for (Caregiver cg : allCaregiver) {
            if(cg.getSurname().equals(c)) {
                this.caregiver = cg;
            }
        }
    }

    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Treatment treatment = new Treatment(patient.getPid(), date,
                begin, end, description, remarks, caregiver.getCid());
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }
}