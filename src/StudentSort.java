import java.util.List;
import java.util.Comparator;
import java.util.Collections;
final class StudentSort {
    public static List<Student> sortByName(List<Student> students) {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.name.compareTo(s2.name);
            }
        });
        return students;
    }
    public static List<Student> sortByAge(List<Student> students) {
        Collections.sort(students, (s1, s2) -> Integer.compare(s1.age, s2.age));
        return students;
    }
}
record Student(String name, int age) {}