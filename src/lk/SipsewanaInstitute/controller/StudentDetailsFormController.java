package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.SipsewanaInstitute.view.TM.StudentTM;

/**
 * @author : A.D.Liyanage
 * @since : 0.1.0
 **/

public class StudentDetailsFormController {
    public JFXTextField txtSName1;
    public JFXTextField txtAddr1;
    public JFXTextField txtContact1;
    public JFXDatePicker dataPicker1;
    public JFXTextField txtSID1;
    public JFXButton btnSearchStudent;
    public JFXRadioButton rbtnMale1;
    public ToggleGroup groupGender1;
    public JFXButton btnSaveS;
    public JFXButton btnUpdateS;
    public JFXButton btnNewS;
    public JFXCheckBox checkBoxSAll;
    public JFXRadioButton rbtnFemale1;
    public TableView<StudentTM> tblStudent;
    public TableColumn colSID;
    public TableColumn colSName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colDeleteS;
    public AnchorPane studentPane;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public void initialize() {
        //set student detail tab table col
        colSID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDeleteS.setCellValueFactory(new PropertyValueFactory<>("btn"));

        //set student detail tab table col click
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDataStudent(newValue);
        });

        loadAllStudent1();
    }

    //set student detail tab data to click table
    private void setDataStudent(StudentTM tm) {
    }

    ObservableList<StudentTM> tmlistS;
    //load student detail tab table
    void loadAllStudent1() {
    }

    //student detail tab field clear
    void clearStudentDetailField() {
    }

    //genarate student id
    public String generateSID() {
        String s = null;
        try {
            s = studentBO.newStudentID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void btnSearchStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveSOnAction(ActionEvent actionEvent) {
    }

    private boolean checkDuplicateSID() {
        for (StudentTM tm : tmlistS) {
            if (txtSID1.getText().equals(tm.getID())) {
                return false;
            }
        }
        return true;
    }

    public void btnUpdateSOnAction(ActionEvent actionEvent) {

    }

    void loadID() {
        txtSID1.setText(generateSID());
    }


    public void btnNewSOnAction(ActionEvent actionEvent) {
    }

    public void checkBoxSAllOnAction(ActionEvent actionEvent) {
    }
}
