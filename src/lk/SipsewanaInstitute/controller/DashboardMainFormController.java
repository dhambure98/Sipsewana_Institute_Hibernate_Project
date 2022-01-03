package lk.SipsewanaInstitute.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.SipsewanaInstitute.view.TM.CourseTM;

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

    public void initialize() {

        //set course detail tab table col
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }


}
