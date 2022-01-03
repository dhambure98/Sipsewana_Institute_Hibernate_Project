package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.SipsewanaInstitute.view.TM.CourseTM;

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
//    StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);

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
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void cmbDurationTypeOnAction(ActionEvent actionEvent) {
        String s = (String) cmbDurationType.getValue();
        System.out.println(s);
    }

    public void btnSaveCOnAction(ActionEvent actionEvent) {
    }

    private String getType() {
        if (rbtnFullTime.isSelected()) {
            return "Full Time";
        } else {
            return "Part Time";
        }
    }
}
