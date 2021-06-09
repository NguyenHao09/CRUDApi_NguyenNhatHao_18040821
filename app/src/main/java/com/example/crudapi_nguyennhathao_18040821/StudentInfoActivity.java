package com.example.crudapi_nguyennhathao_18040821;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class StudentInfoActivity extends AppCompatActivity {

    LinkedList<Student> linkedList = new LinkedList<>();
    RecycleViewAdapter adapter;
    RecyclerView recyclerView;
    Button btnBack;
    String url = "https://60c03162b8d3670017554753.mockapi.io/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        mappingId();

        getDataFromJsonAPI();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentInfoActivity.this, ManagerActivity.class));
            }
        });
    }

    private void mappingId() {
        recyclerView = findViewById(R.id.recycleView);

        btnBack = findViewById(R.id.btnBack);
    }

    private void getDataFromJsonAPI() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        String studentClass = object.getString("studentClass");
                        String status = object.getString("status");
                        String workingAt = object.getString("workingAt");
                        linkedList.add(new Student(id, name, studentClass, status, workingAt));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new RecycleViewAdapter(linkedList, StudentInfoActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(StudentInfoActivity.this, 1));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentInfoActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}