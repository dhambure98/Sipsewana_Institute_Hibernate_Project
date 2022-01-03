package lk.SipsewanaInstitute.dao.custom;

import lk.SipsewanaInstitute.dao.CrudDAO;
import lk.SipsewanaInstitute.entity.Student;

public interface StudentDAO extends CrudDAO<Student, String> {
    String getLastStudentID() throws Exception;
}
