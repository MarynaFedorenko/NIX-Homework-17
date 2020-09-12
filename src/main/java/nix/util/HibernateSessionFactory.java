package nix.util;

import nix.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactory(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Lecturer.class);
                configuration.addAnnotatedClass(Lesson.class);
                configuration.addAnnotatedClass(Mark.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Topic.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e){
                System.out.println("Problem with configurations in  HibernateSessionFactory!");
            }
        }
        return sessionFactory;
    }
}
