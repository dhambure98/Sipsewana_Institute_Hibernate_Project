package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * @author : A.D.Liyanage
 * @since : 0.1.0
 **/

public class LoginFormController {
    public AnchorPane root;
    public JFXTextField txtName;
    public JFXPasswordField txtPassword;
    public JFXButton btnViewPW;
    public JFXTextField txtPasswordClone;
    public JFXButton btnInvisible;
    public JFXButton btnLogin;
    public ImageView imgLogo;

    public void initialize() {
        fadeTransition();
        rotateAnimation();
        txtPasswordClone.setVisible(false);
        btnInvisible.setVisible(false);
    }

    public void btnViewPWOnAction(ActionEvent actionEvent) {
        txtPassword.setVisible(false);
        txtPasswordClone.setVisible(true);
        btnInvisible.setVisible(true);
        btnViewPW.setVisible(false);
        txtPasswordClone.setText(txtPassword.getText());
    }

    public void btnInvisibleOnAction(ActionEvent actionEvent) {
        txtPassword.setVisible(true);
        txtPasswordClone.setVisible(false);
        btnInvisible.setVisible(false);
        btnViewPW.setVisible(true);
        txtPassword.setText(txtPasswordClone.getText());
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        if (Pattern.compile("^[A-z ]{1,}$").matcher(name).matches()) {
            String password = txtPassword.getText();
            if (name != null && name.equalsIgnoreCase("a")) {
                if (password != null && password.equalsIgnoreCase("a")) {
                    Stage s = (Stage) root.getScene().getWindow();
                    s.close();
                    loadUI();
                } else {
                    txtPassword.requestFocus();
                    txtPassword.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                txtName.requestFocus();
                txtName.setFocusColor(Paint.valueOf("red"));
            }
        } else {
            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("red"));
        }
    }

    void loadUI() {
        try {
            URL resource = this.getClass().getResource("../view/DashboardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void fadeTransition() {

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    void rotateAnimation() {
        RotateTransition transition = new RotateTransition();
        transition.setAxis(Rotate.Y_AXIS);
        transition.setByAngle(360);
        transition.setCycleCount(500);
        transition.setDuration(Duration.seconds(15));
        transition.setAutoReverse(true);
        transition.setNode(imgLogo);
        transition.play();
    }
}
