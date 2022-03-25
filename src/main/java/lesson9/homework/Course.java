package lesson9.homework;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Course implements Comparable {

    private final String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course that = (Course) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(@NotNull Object o) {
        Course course = (Course) o;
        return this.getName().compareTo(((Course) o).getName());
    }
}
