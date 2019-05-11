package com.example.sem_4_lab_3;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

interface IDatabaseHandler {
    void onCreate(SQLiteDatabase db);

    public void addStudent(Student student);

    public List<Student> getAllStudent();
    public int updateStudent(Student student, int id);
    public void deleteAllStudent();
}
