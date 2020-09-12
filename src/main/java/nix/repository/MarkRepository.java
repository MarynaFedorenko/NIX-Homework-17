package nix.repository;

import nix.util.HibernateSessionFactory;
import nix.entity.Mark;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MarkRepository {

    private Session session;

    public MarkRepository(){
    }

    public Mark findById(Long id){
        return HibernateSessionFactory.getSessionFactory().openSession().get(Mark.class, id);
    }

    public double countAvgMarkOfGroup(Long groupId){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        GroupRepository groupRepository = new GroupRepository();
        Query query = session.createQuery("select avg(mark) from Mark " +
                "where group = :groupId group by group");
        query.setParameter("groupId", groupRepository.findById(groupId));
        Object obj = query.uniqueResult();
        double mark = Double.parseDouble(obj.toString());

        session.close();
        return mark;

    }

    public List<Mark> findAll(){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Mark> marks = session.createQuery("From Mark").list();
        session.close();
        return marks;
    }

    public void save(Mark marks){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(marks);
        t.commit();
        session.close();
    }

    public void update(Mark marks){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(marks);
        t.commit();
        session.close();
    }

    public void delete(Mark marks){
        session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(marks);
        t.commit();
        session.close();
    }
}
