package com.example.crudapi_nguyennhathao_18040821;

public class Student {
    private  int id;
    private String name;
    private String studentClass;
    private String status;
    private String workingAt;

    public Student(int id, String name, String studentClass, String status, String workingAt) {
        this.id = id;
        this.name = name;
        this.studentClass = studentClass;
        this.status = status;
        this.workingAt = workingAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkingAt() {
        return workingAt;
    }

    public void setWorkingAt(String workingAt) {
        this.workingAt = workingAt;
    }
}
