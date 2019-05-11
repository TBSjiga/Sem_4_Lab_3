package com.example.sem_4_lab_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "students";

    //Student
    private static final String TABLE_STUDENT = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " text," + KEY_DATE + " text" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName());
        values.put(KEY_DATE, String.valueOf(student.getDate()));

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Student student = new Student();

                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setDate(cursor.getString(2));

                studentList.add(student);
            }while (cursor.moveToNext());
        }

        return studentList;
    }

    @Override
    public int updateStudent(Student student, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName());
        values.put(KEY_DATE, student.getDate());

        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?", new String[]{
                String.valueOf(id)
        });
    }

    @Override
    public void deleteAllStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        onCreate(db);
    }
}
