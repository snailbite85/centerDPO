package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Teacher> teachers = new ArrayList<>();
    static ArrayList<Program> programs = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        importData(); // Импорт данных при запуске

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1 - Добавить студента");
            System.out.println("2 - Добавить преподавателя");
            System.out.println("3 - Добавить программу");
            System.out.println("4 - Добавить курс");
            System.out.println("0 - Выход");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Введите число.");
                continue;
            }

            switch (choice) {
                case 1: addStudent(); break;
                case 2: addTeacher(); break;
                case 3: addProgram(); break;
                case 4: addCourse(); break;
                case 0:
                    exportData(); // Экспорт перед выходом
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }

    // ================= СТУДЕНТ =================
    public static void addStudent() {
        try {
            System.out.print("Фамилия: "); String lastName = sc.nextLine();
            System.out.print("Имя: "); String firstName = sc.nextLine();
            System.out.print("Отчество: "); String middleName = sc.nextLine();
            System.out.print("Дата рождения (yyyy-mm-dd): "); LocalDate birthDate = LocalDate.parse(sc.nextLine());
            System.out.print("Пол: "); String gender = sc.nextLine();
            System.out.print("Телефон: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("ID студента: "); String studentId = sc.nextLine();
            System.out.print("Название программы: "); String programName = sc.nextLine();

            students.add(new Student(lastName, firstName, middleName, birthDate,
                    gender, phone, email, studentId, programName));
            System.out.println("Студент добавлен!");
        } catch (Exception e) {
            System.out.println("Ошибка ввода! Данные не сохранены.");
        }
    }

    // ================= ПРЕПОДАВАТЕЛЬ =================
    public static void addTeacher() {
        try {
            System.out.print("Фамилия: "); String lastName = sc.nextLine();
            System.out.print("Имя: "); String firstName = sc.nextLine();
            System.out.print("Отчество: "); String middleName = sc.nextLine();
            System.out.print("Дата рождения (yyyy-mm-dd): "); LocalDate birthDate = LocalDate.parse(sc.nextLine());
            System.out.print("Пол: "); String gender = sc.nextLine();
            System.out.print("Телефон: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Специализация: "); String specialization = sc.nextLine();

            teachers.add(new Teacher(lastName, firstName, middleName, birthDate,
                    gender, phone, email, specialization));
            System.out.println("Преподаватель добавлен!");
        } catch (Exception e) {
            System.out.println("Ошибка ввода! Данные не сохранены.");
        }
    }

    // ================= ПРОГРАММА =================
    public static void addProgram() {
        try {
            System.out.print("Название программы: "); String name = sc.nextLine();
            System.out.print("Длительность (месяцы): "); int duration = Integer.parseInt(sc.nextLine());

            programs.add(new Program(name, duration));
            System.out.println("Программа добавлена!");
        } catch (Exception e) {
            System.out.println("Ошибка ввода! Данные не сохранены.");
        }
    }

    // ================= КУРС =================
    public static void addCourse() {
        try {
            System.out.print("Название курса: ");
            String title = sc.nextLine().trim();

            System.out.print("ФИО преподавателя : ");
            String teacherFullName = sc.nextLine().trim();

            Teacher foundTeacher = teachers.stream()
                    .filter(t -> t.getFullName().equalsIgnoreCase(teacherFullName))
                    .findFirst()
                    .orElse(null);

            if (foundTeacher == null) {
                System.out.println("Преподаватель не найден. Добавьте его в систему перед созданием курса.");
                return;
            }

            // ПРОВЕРКА НА ДУБЛИКАТ
            boolean exists = courses.stream()
                    .anyMatch(c ->
                            c.getTitle().equalsIgnoreCase(title) &&
                                    c.getTeacher().getFullName().equalsIgnoreCase(teacherFullName)
                    );

            if (exists) {
                System.out.println("❌ Такой курс уже существует! Добавление невозможно.");
                return;
            }

            courses.add(new Course(title, foundTeacher));
            System.out.println("✅ Курс добавлен!");

        } catch (Exception e) {
            System.out.println("Ошибка ввода! Данные не сохранены.");
        }
    }

    // ================= ИМПОРТ ДАННЫХ =================
    public static void importData() {
        System.out.println("\n--- Загрузка данных из файлов ---");
        try {
            if (new File("students.txt").exists()) {
                FileInputOutput.studentsImport(FileInputOutput.textFileRead("students.txt"), students);
                System.out.println("Студенты загружены: " + students.size());
            }
            if (new File("teachers.txt").exists()) {
                FileInputOutput.teachersImport(FileInputOutput.textFileRead("teachers.txt"), teachers);
                System.out.println("Преподаватели загружены: " + teachers.size());
            }
            if (new File("programs.txt").exists()) {
                FileInputOutput.programsImport(FileInputOutput.textFileRead("programs.txt"), programs);
                System.out.println("Программы загружены: " + programs.size());
            }
            if (new File("courses.txt").exists()) {
                FileInputOutput.coursesImport(
                        FileInputOutput.textFileRead("courses.txt"),
                        courses,
                        teachers
                );
                System.out.println("Курсы загружены: " + courses.size());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файлов: " + e.getMessage());
        }
        System.out.println("--- Загрузка завершена ---\n");
    }

    // ================= ЭКСПОРТ ДАННЫХ =================
    public static void exportData() {
        System.out.println("\n--- Сохранение данных в файлы ---");
        try {
            FileInputOutput.textFileWrite("students.txt", FileInputOutput.studentsExport(students));
            FileInputOutput.textFileWrite("teachers.txt", FileInputOutput.teachersExport(teachers));
            FileInputOutput.textFileWrite("programs.txt", FileInputOutput.programsExport(programs));
            FileInputOutput.textFileWrite(
                    "courses.txt",
                    FileInputOutput.coursesExport(courses)
            );
            System.out.println("Все данные успешно сохранены!");
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}