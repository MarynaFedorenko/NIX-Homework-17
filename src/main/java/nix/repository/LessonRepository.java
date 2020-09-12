package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Course;
import nix.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class LessonRepository {

    private Session session;
    private final static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public LessonRepository(){    }

    public Lesson findNearestLesson(Course course, String currentDate) {
        Date formattedDate = null;
        Instant currentDateResult = null;
        try {
            formattedDate = format.parse(currentDate);
            currentDateResult= formattedDate.toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Lesson where dateAndTime > :currentDate " +
                "order by dateAndTime");
        query.setParameter("currentDate", currentDateResult);

        Lesson lesson = (Lesson) query.list().get(0);

        session.getTransaction().commit();
        session.close();

        return lesson;
    }

    public Lesson findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Lesson.class, id);
    }

    public List<Lesson> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Lesson> lessons = session.createQuery("From Lesson").list();
        session.close();
        return lessons;
    }

    public void save(Lesson lessons){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(lessons);
        t.commit();
        session.close();
    }

    public void update(Lesson lessons){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(lessons);
        t.commit();
        session.close();
    }

    public void delete(Lesson lessons){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(lessons);
        t.commit();
        session.close();
    }
}
