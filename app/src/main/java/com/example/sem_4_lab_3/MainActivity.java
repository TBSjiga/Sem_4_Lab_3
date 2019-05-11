package com.example.sem_4_lab_3;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;

    Button button_conclusion;
    Button button_insert;
    Button button_update;

    String name, date;

    List<Student> students;

    EditText name_student;

    Date date_now;

    int count_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        db.deleteAllStudent();

        date_now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss a zzz");

        date = simpleDateFormat.format(date_now);

        db.addStudent(new Student("ALEKSEEV DMITRII ALEKSANDROVICH", date));
        db.addStudent(new Student("GORBACHEV ANDREY DMITRIEVICH", date));
        db.addStudent(new Student("TROFIMOV NIKITA ANDREEVICH", date));
        db.addStudent(new Student("KOVALEV MAKSIM SERGEEVICH", date));
        db.addStudent(new Student("SAGDEEV RUSTAM BATKOVICH", date));

        button_conclusion = (Button)findViewById(R.id.button);
        button_conclusion.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        button_insert = (Button)findViewById(R.id.button2);
        button_insert.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final View view1 = getLayoutInflater().inflate(R.layout.create_student, null);

                name_student = (EditText)view1.findViewById(R.id.name_student);
                Button create = (Button)view1.findViewById(R.id.button_create);

                builder.setView(view1);

                final AlertDialog dialog = builder.create();
                dialog.show();

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = String.valueOf(name_student.getText().toString());

                        date_now = new Date();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss a zzz");

                        date = simpleDateFormat.format(date_now);

                        db.addStudent(new Student(name, date));

                        dialog.dismiss();
                    }
                });

                students = db.getAllStudent();

                for (Student st : students){
                    String log = "Id: " + st.getId() + " , Name: " + st.getName() + " , Date: " + st.getDate();

                    System.out.print("Student: ");
                    System.out.println(log);
                }
            }
        });

        button_update = (Button)findViewById(R.id.button3);
        button_update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                students = db.getAllStudent();

                for (Student st : students){
                    count_List = st.getId();
                }

                date_now = new Date();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss a zzz");

                date = simpleDateFormat.format(date_now);

                db.updateStudent(new Student("Иванов Иван Иванович", date), count_List);
            }
        });
    }

    public void openActivity(){
        Intent intent = new Intent(this, Activity_Second.class);
        startActivity(intent);
    }
}
