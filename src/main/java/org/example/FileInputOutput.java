package org.example;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FileInputOutput {
    public static ArrayList<String> textFileRead(String path) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) data.add(line.trim());
            }
        }
        return data;
    }

    public static boolean textFileWrite(String path, ArrayList<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка записи в " + path + ": " + e.getMessage());
            return false;
        }
    }

    // STUDENTS
    public static boolean studentsImport(ArrayList<String> source, ArrayList<Student> dest) {
        for (String line : source) {
            try {
                String[] parts = line.split(", ");
                if (parts.length < 9) continue;
                dest.add(new Student(parts[0], parts[1], parts[2], LocalDate.parse(parts[3]),
                        parts[4], parts[5], parts[6], parts[7], parts[8]));
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Пропущена битая строка студента: " + line);
            }
        }
        return true;
    }
    public static ArrayList<String> studentsExport(ArrayList<Student> source) {
        ArrayList<String> result = new ArrayList<>();
        for (Student s : source) {
            result.add(String.join(", ", s.getLastName(), s.getFirstName(), s.getMiddleName(),
                    s.getBirthDate().toString(), s.getGender(), s.getPhone(), s.getEmail(),
                    s.getStudentId(), s.getProgramName()));
        }
        return result;
    }

    // TEACHERS
    public static boolean teachersImport(ArrayList<String> source, ArrayList<Teacher> dest) {
        for (String line : source) {
            try {
                String[] parts = line.split(", ");
                if (parts.length < 8) continue;
                dest.add(new Teacher(parts[0], parts[1], parts[2], LocalDate.parse(parts[3]),
                        parts[4], parts[5], parts[6], parts[7]));
            } catch (Exception e) { System.err.println("Пропущена битая строка преподавателя: " + line); }
        }
        return true;
    }
    public static ArrayList<String> teachersExport(ArrayList<Teacher> source) {
        ArrayList<String> result = new ArrayList<>();
        for (Teacher t : source) {
            result.add(String.join(", ", t.getLastName(), t.getFirstName(), t.getMiddleName(),
                    t.getBirthDate().toString(), t.getGender(), t.getPhone(), t.getEmail(),
                    t.getSpecialization()));
        }
        return result;
    }

    // PROGRAMS
    public static boolean programsImport(ArrayList<String> source, ArrayList<Program> dest) {
        for (String line : source) {
            try {
                String[] parts = line.split(", ");
                if (parts.length < 2) continue;
                dest.add(new Program(parts[0], Integer.parseInt(parts[1])));
            } catch (Exception e) { System.err.println("Пропущена битая строка программы: " + line); }
        }
        return true;
    }
    public static ArrayList<String> programsExport(ArrayList<Program> source) {
        ArrayList<String> result = new ArrayList<>();
        for (Program p : source) {
            result.add(String.join(", ", p.getName(), String.valueOf(p.getDuration())));
        }
        return result;
    }

    // COURSES
    public static boolean coursesImport(ArrayList<String> source,
                                        ArrayList<Course> dest,
                                        ArrayList<Teacher> teachers) {
        for (String line : source) {
            try {
                String[] parts = line.split(", ");
                if (parts.length < 2) continue;

                String title = parts[0];
                String teacherName = parts[1];

                Teacher foundTeacher = teachers.stream()
                        .filter(t -> t.getFullName().equalsIgnoreCase(teacherName))
                        .findFirst()
                        .orElse(null);

                if (foundTeacher != null) {
                    dest.add(new Course(title, foundTeacher));
                } else {
                    System.err.println("Преподаватель не найден для курса: " + line);
                }

            } catch (Exception e) {
                System.err.println("Пропущена битая строка курса: " + line);
            }
        }
        return true;
    }

    public static ArrayList<String> coursesExport(ArrayList<Course> source) {
        ArrayList<String> result = new ArrayList<>();
        for (Course c : source) {
            result.add(String.join(", ",
                    c.getTitle(),
                    c.getTeacher().getFullName()
            ));
        }
        return result;
    }
}
