package org.example;
public class Course {
    private String title;
    private Teacher teacher;
    public Course(String title, Teacher teacher) { this.title = title; this.teacher = teacher; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}