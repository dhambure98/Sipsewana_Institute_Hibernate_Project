package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * @author : A.D.Liyanage
 * @since : 0.1.0
 **/

public class DashboardFormController {
    public JFXButton btnExit;
    public JFXButton btnRegister;
    public JFXButton btnCourse;
    public JFXButton btnStudent;
    public AnchorPane DashboardContext;
    public JFXButton btnDashboard;
    public JFXButton btnHelp;
    public JFXButton btnSetting;
    public ImageView imgLogo;


    public void initialize() throws IOException {
    }

    public void goToDashboardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashboardMainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        DashboardContext.getChildren().clear();
        DashboardContext.getChildren().add(load);
    }

    public void goToSettingOnAction(ActionEvent actionEvent) {
    }

    public void goToHelpOnAction(ActionEvent actionEvent) {
    }

    public void goToStudentDetailOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/StudentDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        DashboardContext.getChildren().clear();
        DashboardContext.getChildren().add(load);
    }

    public void goToCourseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CourseDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        DashboardContext.getChildren().clear();
        DashboardContext.getChildren().add(load);
    }

    public void goToRegisterOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegistrationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        DashboardContext.getChildren().clear();
        DashboardContext.getChildren().add(load);
    }

    public void goToExitOnAction(ActionEvent actionEvent) throws IOException {
        Stage logStage = (Stage) btnExit.getScene().getWindow();
        logStage.close();

        URL resource = this.getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("SIPSEWANA INSTITUTE | login Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
