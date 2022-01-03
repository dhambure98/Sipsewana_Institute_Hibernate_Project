package lk.SipsewanaInstitute.dao.custom;

import lk.SipsewanaInstitute.dao.CrudDAO;
import lk.SipsewanaInstitute.entity.Registration;

public interface RegisterDAO  extends CrudDAO<Registration, String> {
    int getLastRegNo() throws Exception;
}