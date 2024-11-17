import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.*;



public class Main {
    static Random random = new Random();
    Randomize randomize = new Randomize();

    public static void main(String[] args) {
        Logger Logger = new Logger();
        Scanner scanner = new Scanner(System.in);
        Config config = new Config("config.properties");
        Logger.log("Программа запущена пользователем: " + config.getUsername() +"\n" + LocalDateTime.now());

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        if (login.equals(config.getUsername()) && password.equals(config.getPassword())) {
            System.out.println("Добро пожаловать — " + config.getUsername());
            if (config.isDebugMode()) {
                Logger.log("Режим отладки включен.");
            }

            if (config.isAutoTestMode()) {
                Logger.log("Режим автотестов включен. Выполнение автотестов...");
                Teacher teacher1 = new Teacher("Alice", "Math");
                Teacher teacher2 = new Teacher("Bob", "Physics");
                Teacher teacher3 = new Teacher("Carol", "Chemistry");

                // Создаем студентов
                List<Student> students = new ArrayList<>();
                students.add(new Student("John", 20, "M"));
                students.add(new Student("Anna", 19, "F"));
                students.add(new Student("Mike", 21, "M"));
                students.add(new Student("Emma", 18, "F"));
                students.add(new Student("Alex", 22, "M"));

                // Назначаем предметы студентам
                for (Student student : students) {
                    student.addSubject(new Subject("Math"));
                    student.addSubject(new Subject("Physics"));
                    student.addSubject(new Subject("Chemistry"));
                }

                // Создаем родителей и добавляем детей
                List<Parent> parents = new ArrayList<>();
                parents.add(new Parent("Mr. Smith"));
                parents.add(new Parent("Mrs. Johnson"));
                parents.add(new Parent("Mrs. Weak"));
                parents.add(new Parent("Mrs. Stethem"));
                parents.add(new Parent("Mrs. Bolt"));

                for (int i = 0; i < 5; i++){
                    parents.get(i).addChild(students.get(i));
                }

                for (Student student : students) {
                    teacher1.giveGrades(student);
                    teacher2.giveGrades(student);
                    teacher3.giveGrades(student);
                }


                for (Student student : students) {
                    student.calculateAverageGrade();
                }
                for (Parent parent : parents){
                    parent.evaluateAndRewardChildren();}






                // Выводим результаты
                System.out.println("\n");
                for (Student student : students) {
                    System.out.println(student);
                    Logger.log(student.toString());
                }
                System.out.println("\n");
                for (Parent parent : parents){
                    System.out.println(parent);
                    Logger.log(parent.toString());
                }
            }
            ConfigManager menu = new ConfigManager();
            menu.menu();
            scanner.close();
            Logger.log("Программа завершена." + LocalDateTime.now());

        } else {
            System.out.println("Неверный логин или пароль. Завершение программы.");
            Logger.log("Неудачная попытка входа.");
        }
        Logger.saveLog("logs.txt");
    }
}

