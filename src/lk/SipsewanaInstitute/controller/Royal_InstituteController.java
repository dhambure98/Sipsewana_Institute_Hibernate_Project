package lk.SipsewanaInstitute.controller;

import bo.BOFactory;
import bo.BOType;
import bo.custom.CourseBO;
import bo.custom.RegistrationBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.*;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CourseTM;
import model.RegistrationTM;
import model.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Royal_InstituteController implements Initializable {
    public JFXTabPane root;
    public TableView tblStudents;
    public TableColumn colNIC;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colStudentAddress;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colGender;
    public JFXTextField txtNIC;
    public JFXTextField txtStudentID;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtContact;
    /*public JFXTextField txtDob;*/
    public JFXDatePicker txtDob;
    public JFXTextField txtGender;
    public JFXButton btnAdd;
    public JFXButton btnadd;
    public JFXButton btnadd2;
    public JFXButton btnadd3;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtCode;
    public JFXTextField txtCourseName;
    public JFXTextField txtCourseFee;
    public JFXTextField txtDuration;
    public JFXButton btnCourseAdd;
    public JFXButton btnCourseUpdate;
    public JFXButton btnCourseDelete;
    public TableView tblCourse;
    public JFXComboBox cmbStudentID;
    public JFXComboBox cmbCourseCode;
    public Label lblRegistrationID;
    public Label lblRegname;
    public Label lblStudentNum;
    public Label lblStudentNum1;
    public Label lblCourseNum;
    public Label lblCourseNum1;
    public JFXTextField txtStudentName2;
    public JFXTextField txtCourseName2;
    public JFXTextField txtCourseFee2;
    public JFXTextField txtCourseDuration2;
    public JFXTextField txtRegistrationFee;
    public TableView tblRegistration;
    public JFXButton btnRegistration;
    public TableColumn colCourseCode;
    public TableColumn colCourseName;
    public TableColumn colCourseFee;
    public TableColumn colCourseDuration;
    public TableColumn colRegNo;
    public TableColumn colRegDate;
    public TableColumn colRegFee;
    public TableColumn colStuId;
    public TableColumn colCouCode;
    public Label lblTime;
    public Label lblDate;
    public Label lblTime2;
    public Label lblDate2;
    public Label lblTime3;
    public Label lblDate3;
    public Label lblTime4;
    public Label lblDate4;
    public Label lblRegDate;
    public Label lblStuid;
    public Label lblCoucode;
    /*public ImageView imgLock;*/
    public javafx.scene.image.ImageView imgLock;
    public javafx.scene.image.ImageView imgLock2;
    public javafx.scene.image.ImageView imgLock3;




    StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOType.REGISTRATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateandtime();
        /*Students*/
        colStudentID.setCellValueFactory(new PropertyValueFactory("id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory("student_name"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));

        try {
            loadAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblStudents.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) ->{
                    setDataStudent((StudentTM) newValue);
                } );
        /*Course*/
        colCourseCode.setCellValueFactory(new PropertyValueFactory("code"));
        colCourseName.setCellValueFactory(new PropertyValueFactory("course_name"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory("course_fee"));
        colCourseDuration.setCellValueFactory(new PropertyValueFactory("duration"));

        try {
            loadAllCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblCourse.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) ->{
                    setDataCourse((CourseTM) newValue);
                } );

        /*colRegNo.setCellValueFactory(new PropertyValueFactory("regNo"));
        colRegDate.setCellValueFactory(new PropertyValueFactory("regDate"));
        colRegFee.setCellValueFactory(new PropertyValueFactory("regFee"));
        colStuId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colCouCode.setCellValueFactory(new PropertyValueFactory("courseCode"));

        try {
            loadAllRegistration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblRegistration.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) ->{
                    setDataRegistration((RegistrationTM) newValue);
                } );*/

        /*student cmb loder*/
        loadStudent();
        /*course cmb loder*/
        loadCourse();
        /*reg id*/
        /*generateStudentId();
        generateCourseId();*/
        txtStudentID.setDisable(true);
        txtStudentName.setDisable(true);
        txtStudentAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDob.setDisable(true);
        txtGender.setDisable(true);
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtCode.setDisable(true);
        txtCourseName.setDisable(true);
        txtCourseFee.setDisable(true);
        txtDuration.setDisable(true);
        btnCourseAdd.setDisable(true);
        btnCourseUpdate.setDisable(true);
        btnCourseDelete.setDisable(true);

        cmbStudentID.setDisable(true);
        cmbCourseCode.setDisable(true);
        txtStudentName2.setDisable(true);
        txtCourseName2.setDisable(true);
        txtCourseFee2.setDisable(true);
        txtRegistrationFee.setDisable(true);
        lblRegDate.setDisable(true);
        lblStuid.setDisable(true);
        lblCoucode.setDisable(true);
        lblRegistrationID.setDisable(true);
        lblRegname.setDisable(true);
        btnRegistration.setDisable(true);
        setLable();
        setLable1();
        setLable2();
        setLable3();
    }
    /*cmb load-------------------------------------------------------------------------------*/
    private void loadStudent()  {

        List<StudentDTO> list= null;
        try {
            list = studentBO.getAllStudents();
        } catch (Exception e) {
            System.out.println("1");
        }
        ObservableList<String> list1=FXCollections.observableArrayList();
        for (StudentDTO c:list){
            list1.add(c.getId());
        }
        cmbStudentID.setItems(list1);
    }
    private void loadCourse()  {

        List<CourseDTO> list= null;
        try {
            list = courseBO.getAllCourse();
        } catch (Exception e) {
            System.out.println("1");
        }
        ObservableList<String> list1=FXCollections.observableArrayList();
        for (CourseDTO c:list){
            list1.add(c.getCode());
        }
        cmbCourseCode.setItems(list1);
    }
    /*set data------------------------------------------------------------------------------*/
    private void setDataStudent(StudentTM tm){
        txtStudentID.setText(tm.getId());
        txtStudentName.setText(tm.getStudent_name());
        txtStudentAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        txtDob.setValue(LocalDate.parse(tm.getDob()));
        txtGender.setText(tm.getGender());
    }
    private void setDataCourse(CourseTM tm){
        txtCode.setText(tm.getCode());
        txtCourseName.setText(tm.getCourse_name());
        txtCourseFee.setText(tm.getCourse_fee()+"");
        txtDuration.setText(tm.getDuration());
    }
    private void setDataRegistration(RegistrationTM tm){
        lblRegistrationID.setText(tm.getRegNo()+"");
        lblRegDate.setText(tm.getRegDate());
        txtRegistrationFee.setText(tm.getRegFee()+"");
        lblStuid.setText(String.valueOf(tm.getStudentId()));
        lblCoucode.setText(String.valueOf(tm.getCourseCode()));
    }
    /*table load------------------------------------------------------------------------------------*/
    private void loadAllStudents() throws Exception {
        tblStudents.getItems().clear();
        List<StudentDTO> allStudents=studentBO.getAllStudents();
        ObservableList<StudentTM> tmList= FXCollections.observableArrayList();
        for(StudentDTO studentDTO:allStudents){
            StudentTM tm=new StudentTM(
                    studentDTO.getId(),
                    studentDTO.getStudent_name(),
                    studentDTO.getAddress(),
                    studentDTO.getContact(),
                    studentDTO.getDob(),
                    studentDTO.getGender()
            );
            tmList.add(tm);
        }
        tblStudents.setItems(tmList);
    }
    private void loadAllCourses() throws Exception {
        tblCourse.getItems().clear();
        List<CourseDTO> allCourses=courseBO.getAllCourse();
        ObservableList<CourseTM> tmList= FXCollections.observableArrayList();

        for(CourseDTO courseDTO:allCourses){
            CourseTM tm=new CourseTM(
                    courseDTO.getCode(),
                    courseDTO.getCourse_name(),
                    courseDTO.getCourse_fee(),
                    courseDTO.getDuration()
            );
            tmList.add(tm);
        }
        tblCourse.setItems(tmList);
    }
    private void loadAllRegistration() throws Exception {
        tblRegistration.getItems().clear();
        List<RegistrationDTO> allRegistration=registrationBO.getAllReg();
        ObservableList<RegistrationTM> tmList= FXCollections.observableArrayList();

        for(RegistrationDTO registrationDTO:allRegistration){
            RegistrationTM tm=new RegistrationTM(
                    registrationDTO.getRegNo(),
                    registrationDTO.getRegDate(),
                    registrationDTO.getRegFee(),
                    registrationDTO.getStudentDTO(),
                    registrationDTO.getCourseDTO()
            );
            tmList.add(tm);
        }
        tblRegistration.setItems(tmList);
    }

    /*Students crud-------------------------------------------------------------------*/
    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contact = txtContact.getText();
        String dob = txtDob.getValue()+"";
        String gender = txtGender.getText();

            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtStudentName.getText()).matches()){
                if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtStudentAddress.getText()).matches()){
                    if (Pattern.compile("^[0-9]{1,12}$").matcher(txtContact.getText()).matches()) {
        boolean isAdded=studentBO.saveStudent(new StudentDTO(
                txtStudentID.getText(),
                txtStudentName.getText(),
                txtStudentAddress.getText(),
                txtContact.getText(),
                txtDob.getValue()+"",
                txtGender.getText()

        ));
        /*System.out.println("UShan");*/
        if (name.trim().length() == 0 | address.trim().length() == 0 | contact.trim().length() == 0 | dob.trim().length() == 0 | gender.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Input feilds can't be empty", ButtonType.OK).show();
            return;
        }
        if(isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Student Successfully Added", ButtonType.OK).show();

            txtStudentID.clear();
            txtStudentName.clear();
            txtStudentAddress.clear();
            txtContact.clear();
            txtDob.getEditor().clear();
            txtGender.clear();
            generateStudentId();
            loadAllStudents();
            loadStudent();
            setLable();
            setLable1();
            setLable2();
            setLable3();
        } else {
            new Alert(Alert.AlertType.WARNING,
                    "Someting Went Wrong ! Try Again", ButtonType.OK).show();}
                    }else{
                        txtContact.setUnFocusColor(Paint.valueOf("red"));
                        txtContact.requestFocus();
                    }

                }else{
                    txtStudentAddress.setUnFocusColor(Paint.valueOf("red"));
                    txtStudentAddress.requestFocus();
                }
            } else {
                txtStudentName.setUnFocusColor(Paint.valueOf("red"));
                txtStudentName.requestFocus();
            }

    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contact = txtContact.getText();
        String dob = txtDob.getValue()+"";
        String gender = txtGender.getText();

        if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtStudentName.getText()).matches()){
            if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtStudentAddress.getText()).matches()){
                if (Pattern.compile("^[0-9]{1,12}$").matcher(txtContact.getText()).matches()) {
                Boolean isAdded= studentBO.updateStudent(new StudentDTO(txtStudentID.getText(),
                txtStudentName.getText(),
                txtStudentAddress.getText(),
                txtContact.getText(),
                txtDob.getValue()+"",
                txtGender.getText()
                ));
                    if (name.trim().length() == 0 | address.trim().length() == 0 | contact.trim().length() == 0 | dob.trim().length() == 0 | gender.trim().length() == 0) {
                        new Alert(Alert.AlertType.ERROR, "Input feilds can't be empty", ButtonType.OK).show();
                        return;
                    }
                    if(isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION,
                                "Student Successfully Updated", ButtonType.OK).show();

                        txtStudentID.clear();
                        txtStudentName.clear();
                        txtStudentAddress.clear();
                        txtContact.clear();
                        txtDob.getEditor().clear();
                        txtGender.clear();
                        generateStudentId();
                        loadAllStudents();
                        loadStudent();
                        setLable();
                        setLable1();
                        setLable2();
                        setLable3();
                    } else {
                        new Alert(Alert.AlertType.WARNING,
                                "Someting Went Wrong ! Try Again", ButtonType.OK).show();}
                }else{
                    txtContact.setUnFocusColor(Paint.valueOf("red"));
                    txtContact.requestFocus();
                }

            }else{
                txtStudentAddress.setUnFocusColor(Paint.valueOf("red"));
                txtStudentAddress.requestFocus();
            }
        } else {
            txtStudentName.setUnFocusColor(Paint.valueOf("red"));
            txtStudentName.requestFocus();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Student ?",
                ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){
            StudentTM selectedItem= (StudentTM) tblStudents.getSelectionModel().getSelectedItem();
            try{
                studentBO.deleteStudent(selectedItem.getId());
                tblStudents.getItems().remove(selectedItem);
                tblStudents.getSelectionModel().clearSelection();

                txtStudentID.clear();
                txtStudentName.clear();
                txtStudentAddress.clear();
                txtContact.clear();
                txtDob.getEditor().clear();
                txtGender.clear();
                generateStudentId();
                loadAllStudents();
                loadStudent();
                setLable();
                setLable1();
                setLable2();
                setLable3();

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to delete the Student",ButtonType.OK).show();

            }
        }

    }
    public void txtStudentIDOnAction(ActionEvent actionEvent) throws Exception {
        StudentDTO studentDTO=studentBO.getStudent(txtStudentID.getText());
        if(studentDTO!=null){
            txtStudentID.setText(studentDTO.getId());
            txtStudentName.setText(studentDTO.getStudent_name());
            txtStudentAddress.setText(studentDTO.getAddress());
            txtContact.setText(studentDTO.getContact());
            txtDob.setValue(LocalDate.parse(studentDTO.getDob()));
            txtGender.setText(studentDTO.getGender());

        }
    }
    public void btnaddOnAction(ActionEvent actionEvent) {
        txtStudentID.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtContact.clear();
        txtDob.getEditor().clear();
        txtGender.clear();
        txtStudentName.setDisable(false);
        txtStudentAddress.setDisable(false);
        txtContact.setDisable(false);
        txtDob.setDisable(false);
        txtGender.setDisable(false);
        btnAdd.setDisable(false);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        tblStudents.getSelectionModel().clearSelection();
        txtStudentName.requestFocus();
        generateStudentId();
    }
    /*Course crud-----------------------------------------------------------------------*/
    public void btnCourseAddOnAction(ActionEvent actionEvent) throws Exception {
        String course_name = txtCourseName.getText();
        double course_fee = Double.parseDouble(txtCourseFee.getText());
        String duration = txtDuration.getText();

        if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtCourseName.getText()).matches()){
            if (Pattern.compile("^[0-9]{1,20}$").matcher(txtCourseFee.getText()).matches()){
                if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtDuration.getText()).matches()) {
        boolean isAdded = courseBO.saveCourse(new CourseDTO(txtCode.getText(),
                txtCourseName.getText(),
                Double.parseDouble(txtCourseFee.getText()),
                txtDuration.getText()));
        if (course_name.trim().length() == 0 || duration.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Input feilds can't be empty", ButtonType.OK).show();
            return;
        }
        if(isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Course Successfully Added", ButtonType.OK).show();

            txtCode.clear();
            txtCourseName.clear();
            txtCourseFee.clear();
            txtDuration.clear();
            generateCourseId();
            loadAllCourses();
            loadCourse();
            setLable();
            setLable1();
            setLable2();
            setLable3();
        }else {
            new Alert(Alert.AlertType.WARNING,
                    "Someting Went Wrong ! Try Again", ButtonType.OK).show();
        }
                }else{
                    txtDuration.setUnFocusColor(Paint.valueOf("red"));
                    txtDuration.requestFocus();
                }

            }else{
                txtCourseFee.setUnFocusColor(Paint.valueOf("red"));
                txtCourseFee.requestFocus();
            }
        } else {
            txtCourseName.setUnFocusColor(Paint.valueOf("red"));
            txtCourseName.requestFocus();
        }
    }
    public void btnCourseUpdateOnAction(ActionEvent actionEvent) throws Exception {
        String course_name = txtCourseName.getText();
        double course_fee = Double.parseDouble(txtCourseFee.getText());
        String duration = txtDuration.getText();

        if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtCourseName.getText()).matches()){
            if (Pattern.compile("^[0-9]{1,20}$").matcher(txtCourseFee.getText()).matches()){
                if (Pattern.compile("^[A-z ]{1,50}$").matcher(txtDuration.getText()).matches()) {
        boolean isAdded=courseBO.updateCourse(new CourseDTO(
                txtCode.getText(),
                txtCourseName.getText(),
                Double.parseDouble(txtCourseFee.getText()),
                txtDuration.getText())
        );
                    if (course_name.trim().length() == 0 || duration.trim().length() == 0) {
                        new Alert(Alert.AlertType.ERROR, "Input feilds can't be empty", ButtonType.OK).show();
                        return;
                    }
                    if(isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION,
                                "Course Successfully Updated", ButtonType.OK).show();
        txtCode.clear();
        txtCourseName.clear();
        txtCourseFee.clear();
        txtDuration.clear();
        generateCourseId();
        loadAllCourses();
        loadCourse();
                        setLable();
                        setLable1();
                        setLable2();
                        setLable3();
                    }else {
                        new Alert(Alert.AlertType.WARNING,
                                "Someting Went Wrong ! Try Again", ButtonType.OK).show();
                    }
                }else{
                    txtDuration.setUnFocusColor(Paint.valueOf("red"));
                    txtDuration.requestFocus();
                }

            }else{
                txtCourseFee.setUnFocusColor(Paint.valueOf("red"));
                txtCourseFee.requestFocus();
            }
        } else {
            txtCourseName.setUnFocusColor(Paint.valueOf("red"));
            txtCourseName.requestFocus();
        }
    }
    public void btnCourseDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Course?",
                ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){
            CourseTM selectedItem= (CourseTM) tblCourse.getSelectionModel().getSelectedItem();
            try{
                courseBO.deleteCourse(selectedItem.getCode());
                tblCourse.getItems().remove(selectedItem);
                tblCourse.getSelectionModel().clearSelection();

                txtCode.clear();
                txtCourseName.clear();
                txtCourseFee.clear();
                txtDuration.clear();
                generateCourseId();
                loadAllCourses();
                loadCourse();
                setLable();
                setLable1();
                setLable2();
                setLable3();

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to delete the Course",ButtonType.OK).show();

            }
        }
    }
    public void txtCodeOnAction(ActionEvent actionEvent) throws Exception {
        CourseDTO courseDTO=courseBO.getCourse(txtCode.getText());
        if(courseDTO !=null){
            txtCode.setText(courseDTO.getCode());
            txtCourseName.setText(courseDTO.getCourse_name());
            txtCourseFee.setText(courseDTO.getCourse_fee()+"");
            txtDuration.setText(courseDTO.getDuration());
        }
    }
    public void btnadd2OnAction(ActionEvent actionEvent) {
        txtCode.clear();
        txtCourseName.clear();
        txtCourseFee.clear();
        txtDuration.clear();
        txtCourseName.setDisable(false);
        txtCourseFee.setDisable(false);
        txtDuration.setDisable(false);
        btnCourseAdd.setDisable(false);
        btnCourseUpdate.setDisable(false);
        btnCourseDelete.setDisable(false);
        tblCourse.getSelectionModel().clearSelection();
        txtCourseName.requestFocus();
        generateCourseId();
    }

    /*generate id----------------------------------------------------------------------------------*/
    public void generateRegistrationId(){
         int no=001;
        try {
            no=registrationBO.getNewOrderId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblRegistrationID.setText(String.valueOf(no));
    }
    public void generateStudentId(){
        try {
            txtStudentID.setText(studentBO.getNewStudentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void generateCourseId(){
        try {
            txtCode.setText(courseBO.getNewCourseId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*Registration crud----------------------------------------------------------------------------*/

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws Exception {
        try {
            int regId = Integer.parseInt(lblRegistrationID.getText());
            String regDate = lblDate3.getText().trim();
            double fee = Double.parseDouble(txtRegistrationFee.getText());
            StudentDTO studentDTO = new StudentDTO(cmbStudentID.getValue().toString());
            CourseDTO courseDTO = new CourseDTO(cmbCourseCode.getValue().toString());

            boolean isAdded = registrationBO.saveReg(new RegistrationDTO(regId,
                    regDate,
                    fee,
                    studentDTO,
                    courseDTO
            ));
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Registration Successfully Complete", ButtonType.OK).show();
                txtStudentName2.clear();
                txtCourseName2.clear();
                txtCourseFee2.clear();
                txtCourseDuration2.clear();
                txtRegistrationFee.clear();
                lblRegDate.setText("");
                lblStuid.setText("");
                lblCoucode.setText("");
                cmbCourseCode.getSelectionModel().clearSelection();
                cmbStudentID.getSelectionModel().clearSelection();
                generateRegistrationId();
            }else {
                new Alert(Alert.AlertType.WARNING,
                        "Someting Went Wrong ! Try Again", ButtonType.OK).show();}
        } finally {

        }
    }
    public void btnadd3OnAction(ActionEvent actionEvent) {
        cmbStudentID.setDisable(false);
        cmbCourseCode.setDisable(false);
        txtStudentName2.setDisable(false);
        txtCourseName2.setDisable(false);
        txtCourseFee2.setDisable(false);
        txtRegistrationFee.setDisable(false);
        lblRegDate.setDisable(false);
        lblStuid.setDisable(false);
        lblCoucode.setDisable(false);
        lblRegistrationID.setDisable(false);
        btnRegistration.setDisable(false);
        lblRegname.setDisable(false);
        generateRegistrationId();

        txtStudentName2.clear();
        txtCourseName2.clear();
        txtCourseFee2.clear();
        txtCourseDuration2.clear();
        txtRegistrationFee.clear();
        lblRegDate.setText("Reg Date");
        lblStuid.setText("Student ID");
        lblCoucode.setText("Course Code");
        cmbCourseCode.getSelectionModel().clearSelection();
        cmbStudentID.getSelectionModel().clearSelection();

    }

    /*cmb to feild--------------------------------------------------------------------------------------------*/
    public void setStudent(ActionEvent actionEvent) {
        StudentDTO dto = null;
        try {
            dto = studentBO.getStudent(cmbStudentID.getValue().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtStudentName2.setText(dto.getStudent_name());
    }

    public void setCourse(ActionEvent actionEvent) {
        CourseDTO dto = null;
        try {
            dto = courseBO.getCourse(cmbCourseCode.getValue().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtCourseName2.setText(dto.getCourse_name());
        txtCourseFee2.setText(dto.getCourse_fee()+"");
        txtCourseDuration2.setText(dto.getDuration());
    }
    /*date and time----------------------------------------------------------------------------------------------*/
    public void dateandtime(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            lblTime2.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            lblTime3.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            /*lblTime4.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());*/
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        Timeline date = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDate currentDate = LocalDate.now();
            lblDate.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());
            lblDate2.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());
            lblDate3.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());
            /*lblDate4.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());*/
        }),
                new KeyFrame(Duration.seconds(1))
        );
        date.setCycleCount(Animation.INDEFINITE);
        date.play();
    }
    private void setLable() {
        List<StudentDTO> allstudent = null;
        try {
            allstudent = studentBO.getAllStudents();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblStudentNum.setText("Number of Students :  "+String.valueOf(allstudent.size()));
        lblStudentNum1.setText("Number of Students :  "+String.valueOf(allstudent.size()));
    }
    private void setLable1() {
        List<CourseDTO> allcourse = null;
        try {
            allcourse = courseBO.getAllCourse();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblCourseNum.setText("Number of Courses :  "+String.valueOf(allcourse.size()));

    }
    private void setLable2() {
        List<StudentDTO> allstudent = null;
        try {
            allstudent = studentBO.getAllStudents();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblStudentNum1.setText(String.valueOf(allstudent.size()));
    }
    private void setLable3() {
        List<CourseDTO> allcourse = null;
        try {
            allcourse = courseBO.getAllCourse();
        } catch (Exception e) {
            System.out.println("lable");
        }
        lblCourseNum1.setText(String.valueOf(allcourse.size()));
    }


    public void imgLockOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"))));
    }

    public void imgLock2OnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"))));
    }

    public void imgLock3OnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"))));
    }
}
