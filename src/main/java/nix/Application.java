package nix;

import com.sun.jdi.InterfaceType;
import nix.entity.*;
import nix.repository.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(
            Application.class);

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter student ID:  ");
        Long studentId = in.nextLong();


        CourseRepository courseRepository = new CourseRepository();
            GroupRepository groupRepository = new GroupRepository();
        LessonRepository lessonRepository = new LessonRepository();
            MarkRepository markRepository = new MarkRepository();
        StudentRepository studentRepository = new StudentRepository();

        try{

            String currentDate = "2020-08-31 10:00:00";
            Course courseResult = studentRepository.findById(studentId).getGroup().getCourse();
            Lecturer lecturerResult = courseResult.getLecturer();
            Lesson lessonResult = lessonRepository.findNearestLesson(courseResult, currentDate);
            Topic topicResult = lessonResult.getTopic();

            logger.info("-------------Task 1-------------");
            logger.info("Next lesson: Teacher full name: " + lecturerResult.getFirstName() + "  "+lecturerResult.getLastName());
            logger.info("Next lesson: Date and time: " + lessonResult.getDateAndTime());
            logger.info("Next lesson: Topic name: " + topicResult.getName());

        } catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("\n Enter lecturer ID:  ");
        Long lecturerId = in.nextLong();

        try {
            Course course  = courseRepository.findByLecturerId(lecturerId);
            List<Group> groups = groupRepository.findAllByCourseId(course.getId());

            Group maxGroup = new Group(); double maxMark = 0;
            for(Group group : groups){
                double mark = markRepository.countAvgMarkOfGroup(group.getId());
                if(maxMark==0){
                    maxGroup = group;
                    maxMark = mark;
                }
                else{
                    if(maxMark<mark){
                        maxGroup = group;
                        maxMark = mark;
                    }
                }
            }
            logger.info("-------------Task 2-------------");
            logger.info("Most successful group course name: " + course.getName()+ " ID "+course.getId());
            logger.info("Most successful group name: " + maxGroup.getName() + " ID "+maxGroup.getId());
            logger.info("Most successful group average mark value: " + maxMark);


        } catch(Exception e){
            e.printStackTrace();
        }
    }



}
