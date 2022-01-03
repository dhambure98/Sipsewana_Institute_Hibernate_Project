package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.SipsewanaInstitute.bo.BOFactory;
import lk.SipsewanaInstitute.bo.custom.StudentBO;
import lk.SipsewanaInstitute.dto.StudentDTO;
import lk.SipsewanaInstitute.view.TM.StudentTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

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
        try {
            Date date = tm.getDob();
            txtSID1.setText(tm.getID());
            txtSName1.setText(tm.getName());
            txtAddr1.setText(tm.getAddress());
            txtContact1.setText(tm.getContactNo() + "");
            dataPicker1.setValue(date.toLocalDate());
            if (tm.getGender().equals("Male")) {
                rbtnMale1.setSelected(true);
            } else {
                rbtnFemale1.setSelected(true);
            }
        } catch (NullPointerException e) {

        }
    }

    ObservableList<StudentTM> tmlistS;

    void loadAllStudent1() {
        try {
            tmlistS = FXCollections.observableArrayList();
            ArrayList<StudentDTO> list = studentBO.getAllStudent();
            for (StudentDTO dto : list) {
                JFXButton btn = new JFXButton("Delete");
                StudentTM tm = new StudentTM(dto.getID(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender(), btn);
                tmlistS.add(tm);
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            System.out.println(tm.getID());
                            if (studentBO.deleteStudent(tm.getID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllStudent1();
                                clearStudentDetailField();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        } else {
                            //no
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblStudent.setItems(tmlistS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void clearStudentDetailField() {
        txtSName1.clear();
        txtContact1.clear();
        txtAddr1.clear();
        dataPicker1.setValue(null);
        rbtnFemale1.setSelected(false);
        rbtnMale1.setSelected(false);
        txtSID1.setText(generateSID());
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
        String SID = txtSID1.getText();
        try {
            JFXButton btn = new JFXButton("Delete");
            StudentDTO dto = studentBO.getStudent(SID);
            if (dto != null) {
                tmlistS.clear();
                tblStudent.refresh();
                StudentTM tm = new StudentTM(dto.getID(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender(), btn);
                tmlistS.add(tm);
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            System.out.println(tm.getID());
                            if (studentBO.deleteStudent(tm.getID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllStudent1();
                                clearStudentDetailField();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
                        } else {
                            //no
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Can't find ID number.\nUse new button...", ButtonType.OK).show();
                btnNewS.requestFocus();
            }
        } catch (Exception exception) {
        }
    }

    public void btnSaveSOnAction(ActionEvent actionEvent) {
        try {

            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtSName1.getText()).matches()) {
                if (Pattern.compile("^[A-z0-9/ ]{3,40}$").matcher(txtAddr1.getText()).matches()) {
                    if (Pattern.compile("^\\d{10}$").matcher(txtContact1.getText()).matches()) {
                        if (dataPicker1.getValue() != null) {
                            if (rbtnMale1.isSelected() || rbtnFemale1.isSelected()) {
                                if (checkDuplicateSID()) {
                                    LocalDate value = dataPicker1.getValue();
                                    Date dob1 = Date.valueOf(value);
                                    boolean saved = studentBO.saveStudent(new StudentDTO(txtSID1.getText(), txtSName1.getText(), txtAddr1.getText(), Integer.parseInt(txtContact1.getText()), dob1, getGender1()));
                                    if (saved) {
                                        loadID();
                                        loadAllStudent1();
                                        clearStudentDetailField();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Student Saved...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "This Student ID Already exsist!\n      Please use new Button for new student add\n or\nPlease use update Button for update student detail ").show();
                                    btnNewS.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Please Choose gender...!").show();
                                rbtnMale1.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Please Input Date of birth...!").show();
                            dataPicker1.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check contact no field...\n(Use only 10 numbers for fill contact no...!)").show();
                        txtContact1.requestFocus();
                        txtContact1.setFocusColor(Paint.valueOf("red"));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check address field...!)").show();
                    txtAddr1.requestFocus();
                    txtAddr1.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input student name...!").show();
                txtSName1.setFocusColor(Paint.valueOf("red"));
                txtSName1.requestFocus();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
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
        try {

            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtSName1.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddr1.getText()).matches()) {
                    if (Pattern.compile("^\\d{10}$").matcher(txtContact1.getText()).matches()) {
                        if (dataPicker1.getValue() != null) {
                            if (rbtnMale1.isSelected() || rbtnFemale1.isSelected()) {
                                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Update Course Detail? ", yes, no);
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.orElse(no) == yes) {
                                    LocalDate value = dataPicker1.getValue();
                                    Date dob1 = Date.valueOf(value);
                                    boolean updated = studentBO.updateStudent(new StudentDTO(txtSID1.getText(), txtSName1.getText(), txtAddr1.getText(), Integer.parseInt(txtContact1.getText()), dob1, getGender1()));
                                    if (updated) {
                                        loadID();
                                        loadAllStudent1();
                                        clearStudentDetailField();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Student Updated...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Please Choose gender...!").show();
                                rbtnMale1.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Please Input Date of birth...!").show();
                            dataPicker1.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check contact no field...\n(Use only 10 numbers for fill contact no...!)").show();
                        txtContact1.requestFocus();
                        txtContact1.setFocusColor(Paint.valueOf("red"));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check address field...!)").show();
                    txtAddr1.requestFocus();
                    txtAddr1.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input student name...!").show();
                txtSName1.setFocusColor(Paint.valueOf("red"));
                txtSName1.requestFocus();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    void loadID() {

        txtSID1.setText(generateSID());
    }

    public String getGender1() {
        if (rbtnMale1.isSelected()) {
            return "Male";
        } else if (rbtnFemale1.isSelected()) {
            return "Female";
        } else {
            return null;
        }
    }

    public void btnNewSOnAction(ActionEvent actionEvent) {
        clearStudentDetailField();
    }

    public void checkBoxSAllOnAction(ActionEvent actionEvent) {
        if (checkBoxSAll.isSelected()) {
            loadAllStudent1();
        }
    }
}
