package lk.SipsewanaInstitute.dao.custom;

import lk.SipsewanaInstitute.dao.CrudDAO;
import lk.SipsewanaInstitute.entity.Course;

public interface CourseDAO extends CrudDAO<Course, String> {

    String getLastCourseID() throws Exception;

    Course getCourseN(String name) throws Exception;

}
