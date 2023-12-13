package com.example.student_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_app.Model.Model;
import com.example.student_app.Model.Student;

import java.util.List;

public class StudentListRvFragment extends Fragment {

    List<Student> studentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students_list, container, false);
        studentList = Model.instance.getAllStudents();

        RecyclerView list = view.findViewById(R.id.student_list_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Log.d("TAG", "row was clicked " + position);
                String stId = studentList.get(position).getId();
                Navigation.findNavController(v).navigate(StudentListRvFragmentDirections.actionStudentListRvFragmentToStudentDetailsFragment(stId));

            }
        });

        Button addBtn = (Button) view.findViewById(R.id.studentList_plusButton);
        // String stId = String.valueOf(studentList.size()); //TODO:: there is no need for it, new student should not get arguments
        addBtn.setOnClickListener(Navigation.createNavigateOnClickListener(StudentListRvFragmentDirections.actionStudentListRvFragmentToNewStudentFragment()));

        return view;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.listrow_name_tv);
            idTv = itemView.findViewById(R.id.listrow_id_tv);
            cb = itemView.findViewById(R.id.listrow_cb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(v, pos);
                }
            });
        }
    }

    interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        OnItemClickListener listener;

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view, listener);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = studentList.get(position);
            holder.nameTv.setText(student.getName());
            holder.cb.setChecked(student.isChecked());
            holder.idTv.setText(student.getId());
            holder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    student.setChecked(holder.cb.isChecked());
                }
            });
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }
    }
}
