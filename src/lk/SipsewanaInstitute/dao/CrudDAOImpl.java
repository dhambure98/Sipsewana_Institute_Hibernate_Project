package lk.SipsewanaInstitute.dao;

import lk.SipsewanaInstitute.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(T entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(T entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(ID id) throws Exception {
        return false;
    }

    @Override
    public T get(ID id) throws Exception {
        return session.get(entity,id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery("FROM"+entity.getName()).list();
    }
}
