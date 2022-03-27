package lesson9.homework;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    private static final int STUDENTS_LIMIT = 10;
    private static final int COURSES_LIMIT = 20;
    private static final int MOST_CURIOS_LIMIT = 3;
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
    private static void createCourses(Set<Course> courses, int numOfCourses) {
        // возможности используемого faker ограничены,
        // поэтому максимально возможное число сгенерированных уникальных курсов где-то около 50
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
    private static void setCourses(ArrayList<Student> studentsGroup, Set<Course> courses) {
        Random random = new Random();
        for (Student student : studentsGroup) {
            // определеяем, на какое число курсов будет подписан студент: от 1 до COURSES_LIMIT/2
            int courseNumber = 1 + random.nextInt(courses.size() / 2);
            // создаем список уникальных курсов для записи, чтобы у студента курсы не повторялись
            Set<Course> prepareListCourses = new HashSet<>();
            while (prepareListCourses.size() != courseNumber) {
                prepareListCourses.add((Course) courses.toArray()[random.nextInt(courses.size())]);
            }
            // записывает студента на курсы
            prepareListCourses.forEach(student::setCourse);
        }
    }

    public static void main(String[] args) {

        // создаем группу студентов
        ArrayList<Student> studentsGroup = new ArrayList<>();
        createGroup(studentsGroup, STUDENTS_LIMIT);

        // создаем набор уникальных курсов
        Set<Course> courses = new HashSet<>();
        createCourses(courses, COURSES_LIMIT);

        // записываем студентов на курсы
        setCourses(studentsGroup, courses);

        // выводим на печать всех студентов и их курсы
        //todo переписать в функцию печати для красоты
        System.out.println("Список всех студентов и их курсов");
        studentsGroup.forEach(Student::info);
        System.out.println("=================================================================");

        // выводим на печать список всех курсов
        //todo переписать в функцию печати для красоты
        System.out.println("Список всех курсов (" + courses.size() + "):");
        courses.stream().sorted().forEach(course -> System.out.println(course.getName()));
        System.out.println("=================================================================");

        // 1. Написать функцию, принимающую список Student и возвращающую список уникальных
        // курсов, на которые подписаны студенты.
        // todo переписать в виде отдельной функции
        Set<Course> uniqCourses = studentsGroup.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .collect(Collectors.toSet());
        System.out.println("Список уникальных курсов, на которые подписаны студенты " +
                "(" + uniqCourses.size() + " из " + courses.size() + "):");
        uniqCourses.stream().sorted().forEach(System.out::println);
        System.out.println("=================================================================");

        // 2. Написать функцию, принимающую на вход список Student и возвращающую список из трех
        // самых любознательных (любознательность определяется количеством курсов).
        // todo переписать в виде отдельной функции
        List<Student> mostCurious = studentsGroup.stream()
                .sorted(Collections.reverseOrder(
                        Comparator.comparingInt(student -> student.getAllCourses().size())))
                .limit(MOST_CURIOS_LIMIT)
                .collect(Collectors.toList());
        System.out.println("Список самых любознательных студентов:");
        mostCurious.forEach(Student::info);
        System.out.println("=================================================================");

        // 3. Написать функцию, принимающую на вход список Student и экземпляр Course,
        // возвращающую список студентов, которые посещают этот курс.
        // todo написать в виде отдельной функции
        Course courseToAttend = (Course) courses.toArray()[0];
        List<Student> attendStudents = studentsGroup.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .anyMatch(courseToAttend)

    }
}
