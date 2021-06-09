package com.example.crudapi_nguyennhathao_18040821;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {

    Button btnCreate, btnReturn;

    EditText edt_name, edt_class, edt_status, edt_workingat ;

    String url = "https://60c03162b8d3670017554753.mockapi.io/student";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mappingId();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String studentClass = intent.getStringExtra("studentClass");
        String status = intent.getStringExtra("status");
        String workingAt = intent.getStringExtra("workingAt");

        if(id != 0){
            edt_name.setText(name);
            edt_class.setText(studentClass);
            edt_status.setText(status);
            edt_workingat.setText(workingAt);
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudentActivity.this, StudentInfoActivity.class));
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != 0) {
                    putDataToJsonAPI(url, id);
                } else
                    postDataToJsonAPI(url);
            }
        });
    }

    private void putDataToJsonAPI(String url, int id) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddStudentActivity.this, "Update Successfully!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", edt_name.getText().toString() + "");
                params.put("studentClass", edt_class.getText().toString() + "");
                params.put("status", edt_status.getText().toString() + "");
                params.put("workingAt", edt_workingat.getText().toString() + "");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void postDataToJsonAPI(String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddStudentActivity.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddStudentActivity.this, "Lỗi khi thêm dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", edt_name.getText().toString());
                params.put("studentClass", edt_class.getText().toString());
                params.put("status", edt_status.getText().toString());
                params.put("workingAt", edt_workingat.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void mappingId() {
        btnCreate = findViewById(R.id.btnCreate);
        btnReturn = findViewById(R.id.btnReturn);

        edt_name = findViewById(R.id.edt_name);
        edt_class = findViewById(R.id.edt_class);
        edt_status = findViewById(R.id.edt_status);
        edt_workingat = findViewById(R.id.edt_workingat);
    }
}