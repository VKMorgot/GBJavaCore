package lesson9.homework;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    private static final int STUDENTS_LIMIT = 10;
    private static final int COURSES_LIMIT = 20;
    private static final Faker faker = new Faker();

    /**
     * Создаем группу студентов с использованием генерации случайных имен
     *
     * @param numOfStudents число студентов в группе
     * @param studentsGroup группа студентов
     */
    private static void createGroup(ArrayList<Student> studentsGroup, int numOfStudents) {
        for (int i = 0; i < numOfStudents; i++) {
            studentsGroup.add(new Student(faker.name().fullName()));
        }
    }

    /**
     * Создаем список курсов с использованием генерации случайных курсов
     *
     * @param courses      список курсов
     * @param numOfCourses число курсов
     */
    private static void createCourses(ArrayList<Course> courses, int numOfCourses) {
        for (int i = 0; i < numOfCourses; i++) {
            courses.add(new Course(faker.educator().course()));
        }
    }

    /**
     * Присваиваем студентам группы произвольное число курсов.
     * Студент может записаться на все курсы.
     * Студент должен быть записан хотя бы на один курс.
     *
     * @param studentsGroup группа студентов
     * @param courses       список курсов
     */
    private static void setCourses(ArrayList<Student> studentsGroup, ArrayList<Course> courses) {
        Random random = new Random();
        for (Student student : studentsGroup) {
            // определеяем, на какое число курсов будет подписан студент: от 1 до COURSES_LIMIT/2
            int groupNumber = 1 + random.nextInt(courses.size() / 2);
            System.out.println(groupNumber);
            // записываем студента на курсы
            while (student.getAllCourses().size() != groupNumber) {
                student.setCourse(courses.get(random.nextInt(courses.size())));
            }
        }
    }

    public static void main(String[] args) {

        // создаем группу студентов
        ArrayList<Student> studentsGroup = new ArrayList<>();
        createGroup(studentsGroup, STUDENTS_LIMIT);

        // создаем курсы
        ArrayList<Course> courses = new ArrayList<>();
        createCourses(courses, COURSES_LIMIT);

        // записываем студентов на курсы
        setCourses(studentsGroup, courses);

        // выводим на печать студентов и их курсы
        for (Student student : studentsGroup) {
            student.info();
            System.out.println();
        }

        // 1. Написать функцию, принимающую список Student и возвращающую список уникальных
        // курсов, на которые подписаны студенты.
        Set<Course> uniqCourses = studentsGroup.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .collect(Collectors.toSet());
        uniqCourses.stream().sorted().forEach(System.out::println);


    }
}
