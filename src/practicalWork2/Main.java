package practicalWork2;

//Продемонструйте вплив модифікатора final на різні структурні частини коду на прикладі ієрархії Людина, Студент.
class Human {
    public int age;
    final public String name;
    public Human() {
        this.age = 0;
        this.name = null;
    }
    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }
    final public void showHumanInfo() {
        System.out.println("Age: "+age+" , Name: "+name);
    }
}
final class Student extends Human{
    public int course;
    final public String specialization;
    public Student(int age, String name, int course, String specialization) {
        super(age,name);
        this.course = course;
        this.specialization = specialization;
    }
}
//Створіть інтерфейс Спортивний з методами, притаманними будь якій спортивній діяльності з будь-якого виду спорту.
//Імплементуйте його в класах Волейболіст, Футболіст, Борець, Боксер.
//Створіть масив типу даних Спортивний та заповніть його різними спортсменами.
//Продемонструйте поліморфізм, пробігши по масиву в foreach.
interface Sportable {
    void train();
    void rest();
}
class VoleyballPlayer implements Sportable{

    @Override
    public void train() {
        System.out.println("Теперь волейболист тренируется");
    }

    @Override
    public void rest() {
        System.out.println("Теперь волейболист отдыхает");
    }
}
class FootballPlayer implements Sportable{

    @Override
    public void train() {
        System.out.println("Теперь футболист тренируется");
    }

    @Override
    public void rest() {
        System.out.println("Теперь фуболист отдыхает");
    }
}
class Fighter implements Sportable{

    @Override
    public void train() {
        System.out.println("Теперь борец тренируется");
    }

    @Override
    public void rest() {
        System.out.println("Теперь борец отдыхает");
    }
}
class Boxer implements Sportable{

    @Override
    public void train() {
        System.out.println("Теперь боксер тренируется");
    }

    @Override
    public void rest() {
        System.out.println("Теперь боксер отдыхает");
    }
}
//На тестовому прикладі продемонструйте відмінності використання змінних, методів звичайних та static методів та змінних.
//Використовуйте модель, яка потребує підраховувати кількість створених об'єктів певного типу,
//використайте для цього static-змінні.
class Smth {
    private static int counter;
    public static int getCounter() {
        return counter;
    }
    public Smth() {
        counter++;
    }
}
//Створіть метод, який дозволяє сортувати одновимірний масив об'єктів класу
//Студент по будь яким його властивостям (вік або зріст, або прізвище).
//Масив та умова сортування повинні передаватися як параметр методу.
class RealStudent {
    public int age;
    public int height;
    public String lastname;
    public RealStudent(int age, int height, String lastname) {
        this.age = age;
        this.height = height;
        this.lastname = lastname;
    }

}
class Sort {
    interface Compare {
        int compare(RealStudent obj1, RealStudent obj2);
    }
    public Compare cmpAge = new Compare() {
        @Override
        public int compare(RealStudent obj1, RealStudent obj2) {
            return Integer.compare(obj1.age,obj2.age);
        }
    };
    public Compare cmpHeight = new Compare() {
        @Override
        public int compare(RealStudent obj1, RealStudent obj2) {
            return Integer.compare(obj1.height,obj2.height);
        }
    };
    public Compare cmpName = new Compare() {
        @Override
        public int compare(RealStudent obj1, RealStudent obj2) {
            return obj1.lastname.compareTo(obj2.lastname);
        }
    };
    public void sort(RealStudent[] arr, Compare cmp) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                int rel;
                if (cmp.compare(arr[j], arr[min_idx]) < 1) {
                    min_idx = j;
                }
            }
            RealStudent temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}
//Створіть тестовий приклад, який демонструє відмінність між інтерфейсами та абстрактними класами за їхніми можливостями.
interface Flyable {
    int m = 10;
    void fly();
}
abstract class Bird {
    public String name;
    public abstract void tweet();
}
class Swallow extends Bird implements Flyable{

    @Override
    public void fly() {
        System.out.println("Ласточка летит!");
    }

    @Override
    public void tweet() {
        System.out.println("Ласточка "+name+" щебечет!");
    }
}
abstract class Person {
    abstract void todo();
}
public class Main {
    public static void main(String[] args) {

        //Створіть масив типу даних Спортивний та заповніть його різними спортсменами.
        //Продемонструйте поліморфізм, пробігши по масиву в foreach.
        Sportable[] men = {new VoleyballPlayer(), new FootballPlayer(), new Fighter(), new Boxer()};
        for(Sportable i : men) {
            i.train();
            i.rest();
        }

        //debug
        RealStudent[] realStudents = {
                new RealStudent(21,181,"Pesherov"),
                new RealStudent(18,177,"Tkachenko"),
                new RealStudent(19,176,"Bogdanov"),
                new RealStudent(22,180,"Cherkov"),
                new RealStudent(18,166,"Martyuk")
        };
        Sort obj = new Sort();
        System.out.println("<Original>");
        for(RealStudent s : realStudents) {
            System.out.println(s.age+" "+s.height+" "+s.lastname);
        }
        System.out.println("<Age>");
        obj.sort(realStudents, obj.cmpAge);
        for(RealStudent s : realStudents) {
            System.out.println(s.age+" "+s.height+" "+s.lastname);
        }
        System.out.println("<Height>");
        obj.sort(realStudents, obj.cmpHeight);
        for(RealStudent s : realStudents) {
            System.out.println(s.age+" "+s.height+" "+s.lastname);
        }
        System.out.println("<Lastname>");
        obj.sort(realStudents, obj.cmpName);
        for(RealStudent s : realStudents) {
            System.out.println(s.age+" "+s.height+" "+s.lastname);
        }
        Smth obj1 = new Smth();
    }
}
