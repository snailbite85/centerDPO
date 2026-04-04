package org.example;
import java.time.LocalDate;

public class Student extends Person {
    private String studentId;
    private String programName;

    public Student(String lastName, String firstName, String middleName,
                   LocalDate birthDate, String gender, String phone, String email,
                   String studentId, String programName) {
        super(lastName, firstName, middleName, birthDate, gender, phone, email);
        this.studentId = studentId;
        this.programName = programName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getProgramName() {
        return programName;
    }
}