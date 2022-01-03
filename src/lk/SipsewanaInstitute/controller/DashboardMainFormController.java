package lk.SipsewanaInstitute.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.SipsewanaInstitute.bo.BOFactory;
import lk.SipsewanaInstitute.bo.custom.CourseBO;
import lk.SipsewanaInstitute.bo.custom.StudentBO;
import lk.SipsewanaInstitute.dto.CourseDTO;
import lk.SipsewanaInstitute.dto.StudentDTO;
import lk.SipsewanaInstitute.view.TM.CourseTM;

import java.util.ArrayList;

/**
 * @author : A.D.Liyanege
 * @since : 0.1.0
 **/

public class DashboardMainFormController {
    public Label lblNoOfStudent;
    public Label lblNoOfCourse;
    public TableView<CourseTM> tblCourse;
    public TableColumn colCode;
    public TableColumn colCName;
    public TableColumn colType;
    public TableColumn colDuration;
    public TableColumn colFee;

    CourseBO courseBO = BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);
    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public void initialize() {
        setNoOfCourse();
        setNoOfStudent();
        loadAllCourseDetail();

        //set course detail tab table col
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    ObservableList<CourseTM> tmlistC;
    void loadAllCourseDetail() {
        try {
            tmlistC = FXCollections.observableArrayList();
            ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO dto : allCourse) {
                CourseTM tm = new CourseTM(dto.getCode(), dto.getCourseName(), dto.getType(), dto.getDuration(), dto.getFee());
                tmlistC.add(tm);
            }
            tblCourse.setItems(tmlistC);
        } catch (Exception e) {
        }
    }

    //    get no of course available
    void setNoOfCourse() {
        int count = 0;
        try {
            ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO courseDTO : allCourse) {
                if (courseDTO != null) {
                    count++;
                }
            }
        } catch (Exception e) {

        }
        lblNoOfCourse.setText(count + "");

    }

    //    get no of student available
    void setNoOfStudent() {
        int count = 0;
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
            for (StudentDTO studentDTO : allStudent) {
                if (studentDTO != null) {
                    count++;
                }
            }
        } catch (Exception e) {

        }
        lblNoOfStudent.setText(count + "");
    }

}
