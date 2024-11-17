import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;

class Subject {
    private String name;
    private List<Integer> grades;

    public Subject(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }
}


class Student {
    private String name;
    private int age;
    private String gender;
    private List<Subject> subjects;
    private float averageGrade;
    private int bonus;

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.subjects = new ArrayList<>();
        this.averageGrade = 0.0f;
        this.bonus = 0;
        }
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

     public void calculateAverageGrade() {
        double sum = 0;
        int count = 0;
        for (Subject subject : subjects) {
            for (int grade : subject.getGrades()) {
                sum += grade;
                count++;
            }
        }
        if (count > 0){this.averageGrade = (float) (sum / count);}
        else{this.averageGrade = 0;}

    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    @Override
    public String toString() {
        return "Студент : " +
                name + " , " + age + " , " + gender + ", Ср балл: " + String.format("%.2f",averageGrade) +
                ", Премиальные: " + bonus;
    }


}

class Parent {
    private String name;
    private List<Student> children;
    private String status;
    private int totalBonusPaid;

    public Parent(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.status = "neutral";
        this.totalBonusPaid = 0;
    }

    public void addChild(Student child) {
        this.children.add(child);
    }

    public void evaluateAndRewardChildren() {
        for (Student child : children) {
            double average = child.getAverageGrade();
            if (average >= 3 && average < 4) {
                status = "хмурый";
                child.setBonus(0);
            } else if (average >= 4 && average < 4.6) {
                status = "удовлетворенный";
                child.setBonus(5000);
                totalBonusPaid += 5000;
            } else if (average >= 4.6 && average <= 5) {
                status = "радостный";
                child.setBonus(10000);
                totalBonusPaid += 10000;
            }
        }
    }

    @Override
    public String toString() {
        return "Родитель : " +
                name + '\'' + "," + status + ", Выплата: " + totalBonusPaid ;
    }
}

class Teacher {
    private String name;
    private String subject;

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void giveGrades(Student student) {
        Random random = new Random();
        for (Subject s : student.getSubjects()) {
            if (s.getName().equals(subject)) {
                for (int i = 0; i < 5; i++) {
                    s.addGrade(random.nextInt(3) + 3); // оценки от 3 до 5
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Учитель: "+ name + " " + subject;
    }
}
class Randomize {
    String[] Names = {"john", "Anna", "Mike", "Emma", "Alex"};

    public int randage() {
        Random random = new Random();
        return (random.nextInt(5) + 19);
    }

    public String randname() {
        Random random = new Random();
        return (Names[random.nextInt(5)]);

    }

    public String randsex() {
        Random random = new Random();
        return ( random.nextInt(1) == 0 ? "M" : "F");

    }
}


class Logger {
    private int errorCount = 0;
    private StringBuilder logBuilder = new StringBuilder();

    public void log(String message) {
        logBuilder.append(message).append("\n");
    }

    public void logError(String error) {
        errorCount++;
        log("Ошибка: " + error);
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void saveLog(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(logBuilder.toString());

        } catch (IOException e) {
            System.err.println("Не удалось сохранить лог: " + e.getMessage());
        }
    }
}

