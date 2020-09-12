package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Topic;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TopicRepository {

    private Session session;

    public TopicRepository(){
    }

    public Topic findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Topic.class, id);
    }

    public List<Topic> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Topic> topics = session.createQuery("From Topic").list();
        session.close();
        return topics;
    }

    public void save(Topic topics){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(topics);
        t.commit();
        session.close();
    }

    public void update(Topic topics){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(topics);
        t.commit();
        session.close();
    }

    public void delete(Topic topics){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(topics);
        t.commit();
        session.close();
    }
}
