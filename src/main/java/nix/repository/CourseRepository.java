package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseRepository {
    private Session session;

    public CourseRepository(){
    }

    public Course findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Course.class, id);
    }


    public Course findByLecturerId(Long lecturerId){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        LecturerRepository lecturerRepository = new LecturerRepository();

        Query query = session.createQuery("from Course where lecturer = :lecturerId");
        query.setParameter("lecturerId", lecturerRepository.findById(lecturerId));
        Course  course = (Course) query.getSingleResult();

        session.close();

        return course;

    }

    public List<Course> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Course> courses = session.createQuery("From Course").list();
        session.close();
        return courses;
    }

    public void save(Course course){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(course);
        t.commit();
        session.close();
    }

    public void update(Course course){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(course);
        t.commit();
        session.close();
    }

    public void delete(Course course){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(course);
        t.commit();
        session.close();
    }




}
