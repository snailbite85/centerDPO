package org.example;


import java.time.LocalDate;

public class Teacher extends Person {

    private String specialization;

    public Teacher(String lastName, String firstName, String middleName,
                   LocalDate birthDate, String gender, String phone, String email,
                   String specialization) {

        super(lastName, firstName, middleName, birthDate, gender, phone, email);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
