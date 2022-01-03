package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.SipsewanaInstitute.bo.BOFactory;
import lk.SipsewanaInstitute.bo.custom.CourseBO;
import lk.SipsewanaInstitute.dto.CourseDTO;
import lk.SipsewanaInstitute.view.TM.CourseTM;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author : A.D.Liyanage
 * @since : 0.1.0
 **/

public class CourseDetailsFormController {
    public JFXButton btnNewCourse;
    public JFXComboBox cmbDurationType;
    public JFXTextField txtDuration;
    public Label lblCID;
    public ToggleGroup groupC;
    public JFXRadioButton rbtnPartTime;
    public JFXRadioButton rbtnFullTime;
    public JFXTextField txtFee;
    public JFXTextField txtCourseName;
    public JFXButton btnUpdate;
    public JFXButton btnSave;
    public TableView<CourseTM> tblCourse;
    public TableColumn colCode;
    public TableColumn colCName;
    public TableColumn colType;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableColumn colDelete;

    CourseBO courseBO = BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    public void initialize() {

        //set course detail tab table col
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDataCourse(newValue);
        });

        loadDurationTypes();
        loadAllCourseDetail();
    }

    //set Course detail tab data to click table
    private void setDataCourse(CourseTM tm) {
        try {
            String duration = tm.getDuration();

            lblCID.setText(tm.getCode());
            txtCourseName.setText(tm.getCourseName());
            txtDuration.setText(duration.substring(0, 2));
            txtFee.setText(tm.getFee() + "");
            if (tm.getType().equals("Part Time")) {
                rbtnPartTime.setSelected(true);
            } else {
                rbtnFullTime.setSelected(true);
            }
            String d = null;
            if (duration.contains("Year")) {
                d = "Year";
            } else if (duration.contains("Month")) {
                d = "Month";
            } else if (duration.contains("Week")) {
                d = "Week";
            } else {
                d = "Day";
            }

            System.out.println("cmb " + d);
            cmbDurationType.setValue(d);

        } catch (NullPointerException e) {

        }
    }

    void loadDurationTypes() {
        cmbDurationType.getItems().add("Year");
        cmbDurationType.getItems().add("Month");
        cmbDurationType.getItems().add("Week");
        cmbDurationType.getItems().add("Day");
    }

    //load course detail tab table
    ObservableList<CourseTM> tmlistC;
    void loadAllCourseDetail() {
        try {
            tmlistC = FXCollections.observableArrayList();
            ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO dto : allCourse) {
                JFXButton btn = new JFXButton("Delete");
                CourseTM tm = new CourseTM(dto.getCode(), dto.getCourseName(), dto.getType(), dto.getDuration(), dto.getFee(), btn);
                tmlistC.add(tm);
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (courseBO.deleteCourse(tm.getCode())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllCourseDetail();
                                clearCourseDetailField();
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
            tblCourse.setItems(tmlistC);
        } catch (Exception e) {
        }
    }

    //course detail tab field clear
    void clearCourseDetailField() {
        txtCourseName.clear();
        txtDuration.clear();
        txtFee.clear();
        rbtnPartTime.setSelected(false);
        rbtnFullTime.setSelected(false);
        lblCID.setText(generateCCode());
        cmbDurationType.getSelectionModel().clearSelection();

    }

    //genarate course code
    public String generateCCode() {
        String s = null;
        try {
            s = courseBO.newCourseID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public void btnNewCourseOnAction(ActionEvent actionEvent) {
        clearCourseDetailField();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCourseName.getText()).matches()) {
                if (rbtnPartTime.isSelected() || rbtnFullTime.isSelected()) {
                    if (Pattern.compile("^[1-9.]{1,}$").matcher(txtDuration.getText().trim()).matches()) {
                        if (!cmbDurationType.getSelectionModel().isEmpty()) {
                            if (Pattern.compile("^[0-9.]{1,}$").matcher(txtFee.getText().trim()).matches()) {

                                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Update Course Detail? ", yes, no);
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.orElse(no) == yes) {
                                    boolean updateCourse = courseBO.updateCourse(new CourseDTO(lblCID.getText(), txtCourseName.getText(), getType(), (txtDuration.getText() + " " + cmbDurationType.getValue()), Double.parseDouble(txtFee.getText().trim())));
                                    if (updateCourse) {
                                        loadAllCourseDetail();
                                        generateCCode();
                                        clearCourseDetailField();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Course Updated...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "This Course ID Already exsist!\n      Please use new Button for new course\n or\nPlease use update Button for update course ").show();
                                txtFee.setFocusColor(Paint.valueOf("red"));
                                txtFee.requestFocus();
                            }
                        } else {
                            cmbDurationType.setFocusColor(Paint.valueOf("red"));
                            cmbDurationType.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check your Duration Field...\n(Use only numbers for fill duration...!)").show();
                        txtDuration.setFocusColor(Paint.valueOf("red"));
                        txtDuration.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Choose course type...!").show();
                    rbtnFullTime.requestFocus();
                    rbtnPartTime.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input course name...!").show();
                txtCourseName.setFocusColor(Paint.valueOf("red"));
                txtCourseName.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbDurationTypeOnAction(ActionEvent actionEvent) {
        String s = (String) cmbDurationType.getValue();
        System.out.println(s);
    }

    public void btnSaveCOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCourseName.getText()).matches()) {
                if (rbtnPartTime.isSelected() || rbtnFullTime.isSelected()) {
                    if (Pattern.compile("^[1-9.]{1,}$").matcher(txtDuration.getText().trim()).matches()) {
                        if (!cmbDurationType.getSelectionModel().isEmpty()) {
                            if (Pattern.compile("^[0-9.]{1,}$").matcher(txtFee.getText().trim()).matches()) {
                                if (checkDuplicateCode()) {
                                    boolean saveCourse = courseBO.saveCourse(new CourseDTO(lblCID.getText(), txtCourseName.getText(), getType(), (txtDuration.getText() + " " + cmbDurationType.getValue()), Double.parseDouble(txtFee.getText().trim())));
                                    if (saveCourse) {
                                        loadAllCourseDetail();
                                        generateCCode();
                                        clearCourseDetailField();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Course Saved...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "This Course ID Already exsist!\n      Please use new Button for new course\n or\nPlease use update Button for update course ").show();
                                    btnNewCourse.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Check Fee Field...\n(Use only numbers for fill Fee...!)").show();
                                txtFee.setFocusColor(Paint.valueOf("red"));
                                txtFee.requestFocus();
                            }
                        } else {
                            cmbDurationType.setFocusColor(Paint.valueOf("red"));
                            cmbDurationType.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check Duration Field...\n(Use only numbers for fill duration...!)").show();
                        txtDuration.setFocusColor(Paint.valueOf("red"));
                        txtDuration.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Choose course type...!").show();
                    rbtnFullTime.requestFocus();
                    rbtnPartTime.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input course name...!").show();
                txtCourseName.setFocusColor(Paint.valueOf("red"));
                txtCourseName.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkDuplicateCode() {
        for (CourseTM tm : tmlistC) {
            if (lblCID.getText().equals(tm.getCode())) {
                return false;
            }
        }
        return true;
    }

    private String getType() {
        if (rbtnFullTime.isSelected()) {
            return "Full Time";
        } else {
            return "Part Time";
        }
    }
}
