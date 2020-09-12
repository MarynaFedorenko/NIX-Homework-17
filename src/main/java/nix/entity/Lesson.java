package nix.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_and_time", nullable = false)
    private Instant dateAndTime;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Lesson(Long id, Instant dateAndTime, Topic topic, Course course) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.topic = topic;
        this.course = course;
    }

    public Lesson(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public Instant getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formattedDate = null;
        try{
            formattedDate = format.parse(dateAndTime);
        } catch(ParseException e){
            Instant instant = formattedDate.toInstant();
            this.dateAndTime = instant;
        }

    }
}
