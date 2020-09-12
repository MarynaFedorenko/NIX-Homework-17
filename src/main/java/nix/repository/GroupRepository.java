package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GroupRepository {

    private Session session;

    public GroupRepository(){
    }

    public Group findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Group.class, id);
    }

    public List<Group> findAllByCourseId(Long courseId){
        session = HibernateSessionFactory.getSessionFactory().openSession();

        CourseRepository courseRepository = new CourseRepository();
        Query query = session.createQuery("from Group where course = :courseId");
        query.setParameter("courseId", courseRepository.findById(courseId));
        List <Group> groups = query.list();

        session.close();
        return groups;
    }


    public List<Group> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Group> groups = session.createQuery("From Group").list();
        session.close();
        return groups;
    }

    public void save(Group group){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(group);
        t.commit();
        session.close();
    }

    public void update(Group group){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(group);
        t.commit();
        session.close();
    }

    public void delete(Group group){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(group);
        t.commit();
        session.close();
    }
}
