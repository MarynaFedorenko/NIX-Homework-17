package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Lecturer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LecturerRepository {

    private Session session;

    public LecturerRepository(){    }

    public Lecturer findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Lecturer.class, id);
    }

    public List<Lecturer> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Lecturer> lecturers = session.createQuery("From Lecturer").list();
        session.close();
        return lecturers;
    }


    public void save(Lecturer lecturers){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(lecturers);
        t.commit();
        session.close();
    }

    public void update(Lecturer lecturers){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(lecturers);
        t.commit();
        session.close();
    }

    public void delete(Lecturer lecturers){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(lecturers);
        t.commit();
        session.close();
    }
}
