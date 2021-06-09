package com.example.crudapi_nguyennhathao_18040821;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.LinkedList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private LinkedList<Student> linkedList;
    private LayoutInflater inflater;
    private Context context;

    public RecycleViewAdapter(LinkedList<Student> linkedList, Context context) {
        inflater = LayoutInflater.from(context);
        this.linkedList = linkedList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleview_student, parent, false);
        return new RecycleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.RecycleViewHolder holder, int position) {
        Student student = linkedList.get(position);

        holder.tvStudentId.setText(String.valueOf(student.getId()));
        holder.tvName.setText(student.getName());
        holder.tvClass.setText(student.getStudentClass());
        holder.tvStatus.setText(student.getStatus());
        holder.tvWorkingat.setText(student.getWorkingAt());
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder{

        private RecycleViewAdapter adapter;

        TextView tvName, tvClass, tvStatus, tvWorkingat, tvStudentId;

        public RecycleViewHolder(View view, RecycleViewAdapter adapter) {
            super(view);
            tvStudentId = view.findViewById(R.id.tvStudentId);
            tvName = view.findViewById(R.id.tvName);
            tvClass = view.findViewById(R.id.tvClass);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvWorkingat = view.findViewById(R.id.tvWorkingat);

            Button btnDelete = view.findViewById(R.id.btnDelete);
            Button btnUpdate = view.findViewById(R.id.btnUpdate);

            String url = "https://60b75dac17d1dc0017b89d03.mockapi.io/student";

            this.adapter = adapter;

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Student element = linkedList.get(position);
                    DeleteDataToJsonAPI(url, element.getId());
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Student element = linkedList.get(position);
                    Intent intent = new Intent(context, AddStudentActivity.class);

                    intent.putExtra("id", element.getId());
                    intent.putExtra("name", element.getName());
                    intent.putExtra("studentClass", element.getStudentClass());
                    intent.putExtra("status", element.getStatus());
                    intent.putExtra("workingAt", element.getWorkingAt());

                    context.startActivity(intent);
                }
            });

        }

        private void DeleteDataToJsonAPI(String url, int id) {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    linkedList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    }
}
