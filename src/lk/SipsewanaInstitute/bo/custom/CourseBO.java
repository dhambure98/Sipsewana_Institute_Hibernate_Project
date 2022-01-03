package lk.SipsewanaInstitute.bo.custom;

import lk.SipsewanaInstitute.bo.SuperBO;

import java.util.ArrayList;

/**
 * @author : A.D.Liyanage
 * @since : 0.1.0
 **/

public interface CourseBO extends SuperBO {

    boolean saveCourse(CourseDTO dto) throws Exception;

    boolean updateCourse(CourseDTO dto) throws Exception;

    boolean deleteCourse(String id) throws Exception;

    CourseDTO getCourse(String id) throws Exception;

    ArrayList<CourseDTO> getAllCourse() throws Exception;

    String newCourseID() throws Exception;

    CourseDTO getCourseN(String name) throws Exception;
}

