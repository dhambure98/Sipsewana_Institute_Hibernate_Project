package lk.SipsewanaInstitute.bo.custom;

import lk.SipsewanaInstitute.bo.SuperBO;

import java.util.ArrayList;

/**
 * @author : A.D.Liyanage
 * @since : 0.1.0
 **/

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;

    boolean deleteStudent(String id) throws Exception;

    StudentDTO getStudent(String id) throws Exception;

    ArrayList<StudentDTO> getAllStudent() throws Exception;

    String newStudentID() throws Exception;

}
