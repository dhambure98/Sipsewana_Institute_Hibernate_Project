package lk.SipsewanaInstitute.dao.custom;

import lk.SipsewanaInstitute.dao.SuperDAO;
import lk.SipsewanaInstitute.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getCourseWiseStudent(String code) throws Exception;
}
