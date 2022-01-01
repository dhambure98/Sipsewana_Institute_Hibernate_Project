package lk.SipsewanaInstitute.db;

import entity.Course;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static db.FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties=new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("Royal_Institute.properties"));
            sessionFactory = new Configuration().mergeProperties(properties)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Registration.class)
                    .buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static db.FactoryConfiguration getInstance(){
        return (null == factoryConfiguration) ? factoryConfiguration = new db.FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
