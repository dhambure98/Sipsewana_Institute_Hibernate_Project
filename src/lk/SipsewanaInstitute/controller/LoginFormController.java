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
import javafx.scene.layout.Pane;
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
    public Pane loginPane;

    public void initialize() {
        txtPasswordClone.setVisible(false);
        btnInvisible.setVisible(false);
    }

    public void btnViewPWOnAction(ActionEvent actionEvent) {
    }

    public void btnInvisibleOnAction(ActionEvent actionEvent) {
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
}
