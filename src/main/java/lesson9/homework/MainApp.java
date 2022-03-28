package lesson9.homework;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    private static final int STUDENTS_LIMIT = 10;
    private static final int COURSES_LIMIT = 20;
    private static final int MOST_CURIOS_LIMIT = 3;
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

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

    /**
     * Печать списка студентов
     *
     * @param studentList список студентов
     * @param message     текст перед выведенным списком
     */
    private static void printStudents(List<Student> studentList, String message) {
        System.out.println(message);
        studentList.forEach(Student::info);
        System.out.println("=================================================================");
    }

    /**
     * Печать списка курсов
     *
     * @param courseSet набор курсов
     * @param message   текст перед выведенным списком
     */
    private static void printCourses(Set<Course> courseSet, String message) {
        System.out.println(message);
        courseSet.stream().sorted().forEach(System.out::println);
        System.out.println("=================================================================");
    }

    /**
     * Задаиние №1
     * Написать функцию, принимающую список Student и возвращающую список уникальных
     * курсов, на которые подписаны студенты.
     *
     * @param studentList список студентов
     * @return список уникальных курсов, на которые подписаны студенты
     */
    private static Set<Course> getUniqCourses(List<Student> studentList) {
        return studentList.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Задание №2
     * Написать функцию, принимающую на вход список Student и возвращающую список из трех
     * самых любознательных (любознательность определяется количеством курсов).
     *
     * @param studentList       список студентов
     * @param most_curios_limit число студентов в списке самаых любознательных
     * @return список самых любознательных студентов
     */
    private static List<Student> getMostCurios(List<Student> studentList, int most_curios_limit) {
        return studentList.stream()
                .sorted(Collections.reverseOrder(
                        Comparator.comparingInt(student -> student.getAllCourses().size())))
                .limit(most_curios_limit)
                .collect(Collectors.toList());
    }

    /**
     * Задание №3
     * Написать функцию, принимающую на вход список Student и экземпляр Course,
     * возвращающую список студентов, которые посещают этот курс.
     *
     * @param studentList    список студентов
     * @param courseToAttend курс для поиска
     * @return список студентов, посещающих заданный курс
     */
    private static List<Student> getStudentsOnCourse(List<Student> studentList, Course courseToAttend) {
        return studentList.stream()
                .filter(student -> student.getAllCourses().stream()
                        .anyMatch(course -> course.equals(courseToAttend)))
                .collect(Collectors.toList());
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
        printStudents(studentsGroup, "Список всех студентов и их курсов");

        // выводим на печать список всех курсов
        printCourses(courses, "Список всех курсов (" + courses.size() + "):");

        // Задание №1 - уникальные курсы
        Set<Course> uniqCourses = getUniqCourses(studentsGroup);
        String message1 = "Список уникальных курсов, на которые подписаны студенты " +
                "(" + uniqCourses.size() + " из " + courses.size() + "):";
        printCourses(uniqCourses, message1);

        // Задание №2 - любознательные студенты
        List<Student> mostCurious = getMostCurios(studentsGroup, MOST_CURIOS_LIMIT);
        printStudents(mostCurious, "Список самых любознательных студентов:");

        // Задание №3 - студенты на курсе
        Course courseToAttend = (Course) courses.toArray()[random.nextInt(courses.size())];
        List<Student> studentsOnCourse = getStudentsOnCourse(studentsGroup, courseToAttend);
        String message3 = "Студентов на курсе " + courseToAttend.getName() + ": " + studentsOnCourse.size();
        printStudents(studentsOnCourse, message3);
    }
}
