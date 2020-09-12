package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentRepository {

    private Session session;

    public StudentRepository(){

    }

    public Student findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Student.class, id);
    }

    public List<Student> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Student> students = session.createQuery("From Student").list();
        session.close();
        return students;
    }

    public void save(Student students){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(students);
        t.commit();
        session.close();
    }

    public void update(Student students){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(students);
        t.commit();
        session.close();
    }

    public void delete(Student students){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(students);
        t.commit();
        session.close();
    }
}
