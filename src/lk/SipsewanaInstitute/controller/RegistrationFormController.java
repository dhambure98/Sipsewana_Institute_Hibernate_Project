package lk.SipsewanaInstitute.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.SipsewanaInstitute.bo.BOFactory;
import lk.SipsewanaInstitute.bo.custom.CourseBO;
import lk.SipsewanaInstitute.bo.custom.RegisterBO;
import lk.SipsewanaInstitute.bo.custom.StudentBO;
import lk.SipsewanaInstitute.dto.CourseDTO;
import lk.SipsewanaInstitute.dto.RegistrationDTO;
import lk.SipsewanaInstitute.dto.StudentDTO;
import lk.SipsewanaInstitute.view.TM.CourseTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegistrationFormController {
    public JFXTextField txtSName;
    public JFXTextField txtAddr;
    public JFXTextField txtContact;
    public JFXDatePicker dataPicker;
    public JFXButton btnSearchStudentR;
    public JFXTextField txtSIDR;
    public JFXRadioButton rbtnMale;
    public ToggleGroup groupGender;
    public JFXRadioButton rbtnFemale;
    public JFXButton btnNewStudent;
    public JFXTextField txtDuration;
    public Label lblCID;
    public JFXComboBox cmbCourseR;
    public JFXRadioButton rbtnFullTime;
    public ToggleGroup group;
    public JFXRadioButton rbtnPartTime;
    public JFXTextField txtFee;
    public JFXButton btnAddtoCourse;
    public JFXButton btnRefresh;
    public Label lblCID11;
    public JFXTextField txtRegFee;
    public Text lblTot;
    public TableView<CourseTM> tblCourse1;
    public TableColumn colCode1;
    public TableColumn colCName1;
    public TableColumn colType1;
    public TableColumn colDurationC1;
    public TableColumn colFeeC1;
    public TableColumn colDeleteC1;
    public JFXButton btnReg;
    public Label lblRegNo;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);
    RegisterBO registerBO = BOFactory.getInstance().getBO(BOFactory.BOType.REGISTER);
    ObservableList<CourseTM> listaddC = FXCollections.observableArrayList();

    public void initialize() {
        genarateRegNo();
        loadAllCourseCmb();

        //set reg course detail tab table col
        colCode1.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCName1.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colType1.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDurationC1.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFeeC1.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDeleteC1.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public String getGender() {
        if (rbtnMale.isSelected()) {
            return "Male";
        } else if (rbtnFemale.isSelected()) {
            return "Female";
        } else {
            return null;
        }
    }



    void genarateRegNo() {
        try {
            lblRegNo.setText(registerBO.newRegNo() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load all course name
    void loadAllCourseCmb() {
        try {
            List<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO dto : allCourse) {
                cmbCourseR.getItems().addAll(dto.getCourseName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnSearchStudentROnAction(ActionEvent actionEvent) {
        String sid = txtSIDR.getText();
        try {
            StudentDTO s = studentBO.getStudent(sid);
            System.out.println(s + "s");
            if (s == null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Can't find this student...\nPlease use new button for register new stuednt").show();
                btnNewStudent.requestFocus();
            } else {
                Date date = s.getDob();
                txtSIDR.setText(s.getID());
                txtSName.setText(s.getName());
                txtAddr.setText(s.getAddress());
                txtContact.setText(s.getContactNo() + "");
                dataPicker.setValue(date.toLocalDate());
                if (s.getGender().equals("Male")) {
                    rbtnMale.setSelected(true);
                } else {
                    rbtnFemale.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void cmbCourseROnAction(ActionEvent actionEvent) {
        try {
            String cname = (String) cmbCourseR.getValue();
            System.out.println("NAme" + cname);
            if (cname != null) {
                System.out.println("in" + cname);

                CourseDTO courseN = courseBO.getCourseN(cname);
                try {
                    lblCID.setText(courseN.getCode());
                    txtDuration.setText(courseN.getDuration());
                    txtFee.setText(courseN.getFee() + "");
                    if (courseN.getType().equals("Part Time")) {
                        rbtnPartTime.setSelected(true);
                    } else {
                        rbtnFullTime.setSelected(true);
                    }
                } catch (NullPointerException e) {
                }


                txtRegFee.requestFocus();
                txtRegFee.setFocusColor(Paint.valueOf("red"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddtoCourseOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCourseR.getSelectionModel().isEmpty()) {
                JFXButton btn = new JFXButton("Remove");
                CourseDTO dto = courseBO.getCourse(lblCID.getText());
                CourseTM tm = new CourseTM(dto.getCode(), dto.getCourseName(), dto.getType(), dto.getDuration(), dto.getFee(), btn);

                listaddC.add(tm);
                tblCourse1.setItems(listaddC);
                tblCourse1.refresh();
                loadAllCourseCmbR();
                clearCourse();
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            int index = tblCourse1.getSelectionModel().getFocusedIndex();
                            listaddC.remove(index);
                            tblCourse1.refresh();
                        } else {
                            //no
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Choose course...", ButtonType.OK).show();
                cmbCourseR.requestFocus();
                cmbCourseR.setFocusColor(Paint.valueOf("red"));
            }
        } catch (Exception e) {
        }
    }

    // load all course name
    void loadAllCourseCmbR() {
        try {
            ObservableList<String> cmbCourseRItems = FXCollections.observableArrayList();
            cmbCourseRItems.clear();
            cmbCourseRItems = cmbCourseR.getItems();
            if (!cmbCourseRItems.isEmpty()) {
                ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
                for (int i = 0; i < allCourse.size(); i++) {
                    for (int j = 0; j < tblCourse1.getItems().size(); j++) {
                        if (allCourse.get(i).getCode().equals(listaddC.get(j).getCode())) {
                            CourseDTO course = courseBO.getCourse(listaddC.get(j).getCode());
                            cmbCourseRItems.remove(course.getCourseName());
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearCourse() {
        txtDuration.clear();
        txtFee.clear();
        rbtnPartTime.setSelected(false);
        rbtnFullTime.setSelected(false);
        lblCID.setText("");
        cmbCourseR.getSelectionModel().clearSelection();
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        clearAll();
        loadAllCourseCmb();
    }

    private void clearAll() {
        txtSName.clear();
        txtContact.clear();
        txtAddr.clear();
        dataPicker.setValue(null);
        rbtnFemale.setSelected(false);
        rbtnMale.setSelected(false);
       listaddC.clear();
        tblCourse1.refresh();
    }

    public void btnRegOnAction(ActionEvent actionEvent) {
        try {
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtSName.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddr.getText()).matches()) {
                    if (Pattern.compile("^\\d{10}$").matcher(txtContact.getText()).matches()) {
                        if (dataPicker.getValue() != null) {
                            if (rbtnMale.isSelected() || rbtnFemale.isSelected()) {

                                if (Pattern.compile("^[0-9.]{1,}$").matcher(txtRegFee.getText().trim()).matches()) {
                                    if (tblCourse1.getItems().size() > 0) {

                                        LocalDate value = dataPicker.getValue();
                                        Date dob1 = Date.valueOf(value);

                                        StudentDTO studentDTO = new StudentDTO(txtSIDR.getText(), txtSName.getText(), txtAddr.getText(), Integer.parseInt(txtContact.getText()), dob1, getGender());
                                        List<CourseDTO> courseDTOList = new ArrayList<>();
                                        for (CourseTM tm : listaddC) {
                                            CourseDTO courseDTO = new CourseDTO(tm.getCode(), tm.getCourseName(), tm.getType(), tm.getDuration(), tm.getFee());
                                            courseDTOList.add(courseDTO);
                                        }

                                        RegistrationDTO registrationDTO = new RegistrationDTO(Integer.parseInt(lblRegNo.getText()), date, Double.parseDouble(txtRegFee.getText()), studentDTO, courseDTOList);
//                                        RegistrationDTO registrationDTO = new RegistrationDTO(date, Double.parseDouble(txtRegFee.getText()), studentDTO, courseDTOList);

                                        boolean register = registerBO.saveRegister(registrationDTO);
                                        if (register) {
                                           clearCourse();
                                            clearAll();
                                            genarateRegNo();
                                            new Alert(Alert.AlertType.CONFIRMATION, "Student Register...!").show();
                                        } else {
                                            new Alert(Alert.AlertType.ERROR, "Registration failed...!").show();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Please add course ").show();
                                        cmbCourseR.requestFocus();
                                        cmbCourseR.setFocusColor(Paint.valueOf("red"));
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Check Registration Fee Field...\n(Use only numbers for fill Fee...!)").show();
                                    txtRegFee.setFocusColor(Paint.valueOf("red"));
                                    txtRegFee.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Please Choose gender...!").show();
                                rbtnMale.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Please Input Date of birth...!").show();
                            dataPicker.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check contact no field...\n(Use only 10 numbers for fill contact no...!)").show();
                        txtContact.requestFocus();
                        txtContact.setFocusColor(Paint.valueOf("red"));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check address field...!)").show();
                    txtAddr.requestFocus();
                    txtAddr.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input student name...!").show();
                txtSName.setFocusColor(Paint.valueOf("red"));
                txtSName.requestFocus();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }
}

