import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

final class StudentGroup {
    private final List<Student> students;
    public StudentGroup(List<Student> students) {
        this.students = students;
    }

    public StudentGroup addStudent(Student s) {
        this.students.add(s);
        return this;
    }

    public Iterable<String> overNames() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private final Iterator<Student> studentIterator = students.iterator();
                    @Override
                    public boolean hasNext() {
                        return studentIterator.hasNext();
                    }
                    @Override
                    public String next() {
                        return studentIterator.next().name();
                    }
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("Remove not sup");
                    }
                };
            }
        };
    }
}

record Student(String name, int age) { }

public class Main {
    //Прости меня
    public static List<Student> sortByName(List<Student> students) {
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.name().compareTo(s2.name());
            }
        });
        return students;
    }

    public static List<Student> sortByAge(List<Student> students) {
        students.sort((s1, s2) -> Integer.compare(s1.age(), s2.age()));
        return students;
    }

    public static void main(String[] args) {
        var g = new StudentGroup(new ArrayList<>());
        g.addStudent(new Student("Ann", 18));
        g.addStudent(new Student("Sam", 19));
        g.addStudent(new Student("Mary", 22));
        g.addStudent(new Student("Boris", 17));
        g.addStudent(new Student("Bob",  68));
        g.addStudent(new Student("Joe", 31));
        var names = g.overNames();
        var it1 = names.iterator();
        var it2 = names.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next().equals(it2.next()));
        }
        System.out.println(it1.hasNext() == it2.hasNext());
        for (var name : names) {
            System.out.println(name);
        }
    }
}