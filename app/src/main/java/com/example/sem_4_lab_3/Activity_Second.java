package com.example.sem_4_lab_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Second extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemStudent> studentArrayList;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        db = new DatabaseHandler(this);

        List<Student> dataStudent = db.getAllStudent();

        createList();
        buildRecyclerView();


    }

    public void createList(){
        studentArrayList = new ArrayList<>();

        List<Student> students_local = db.getAllStudent();

        for (Student st : students_local){
            studentArrayList.add(new ItemStudent(st.getName(), st.getDate()));
        }
    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview_row);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new StudentAdapter(studentArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void insertItem(int position, String name, String date){
        studentArrayList.add(position, new ItemStudent(name, date));
        adapter.notifyDataSetChanged();
    }
}
