package org.example;
import java.time.LocalDate;
import java.time.Period;
public class Person {
    //поля
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected LocalDate birthDate;
    protected String gender;
    protected String phone;
    protected String email;

    // конструктор
    public Person(String lastName, String firstName, String middleName,
                  LocalDate birthDate, String gender, String phone, String
                          email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }
    // геттеры и сеттеры
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName;
    }
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName =
            middleName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate =
            birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
    // метод подсчета возраста
    public int getAge() {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
