package lk.SipsewanaInstitute.db;

import lk.SipsewanaInstitute.entity.Course;
import lk.SipsewanaInstitute.entity.Registration;
import lk.SipsewanaInstitute.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();

        Metadata meta = new MetadataSources(ssr)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Registration.class).getMetadataBuilder().build();

        sessionFactory = meta.getSessionFactoryBuilder().build();

    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

