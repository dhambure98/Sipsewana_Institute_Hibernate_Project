package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane root;
    public JFXPasswordField txtPw;
    public JFXTextField txtUserName;
    public JFXButton btnLog;
    public ImageView icnVisible;
    public ImageView icnUnvisible;

    public void initialize(){
        icnUnvisible.setVisible(false);
    }
    public void btnLogOnAction(ActionEvent actionEvent) {
        String user=txtUserName.getText().trim();
        String password=txtPw.getText().trim();
        if (user.length() > 0 && password.length() > 0) {

            if (user.equalsIgnoreCase("admin")
                    && password.equalsIgnoreCase("admin")) {
                this.root.getChildren().clear();
                try {
                    this.root.getChildren()
                            .add(FXMLLoader.
                                    load(this.getClass().getResource("/view/Royal_Institute.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                new Alert(Alert.AlertType.WARNING, "Wrong Password Or User Name !",
                        ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty !",
                    ButtonType.OK).show();

        }
    }

    public void icnVisibleOnAction(MouseEvent mouseEvent) {
        icnUnvisible.setVisible(true);
        icnVisible.setVisible(false);
    }

    public void icnUnvisibleOnAction(MouseEvent mouseEvent) {
        icnVisible.setVisible(true);
        icnUnvisible.setVisible(false);
    }
}
