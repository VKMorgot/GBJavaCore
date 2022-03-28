package lesson9.homework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student {

    private final String name;
    private final List<Course> courseList = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        courseList.add(course);
    }

    public void info() {
        System.out.println("Студент: " + name);
        System.out.println("Количество курсов: " + this.getAllCourses().size());
        courseList.stream()
                .sorted(Comparator.comparing(Course::getName))
                .forEach(course -> System.out.println(course.getName()));
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return courseList;
    }
}
