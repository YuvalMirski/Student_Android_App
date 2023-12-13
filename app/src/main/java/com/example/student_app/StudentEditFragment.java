package com.example.student_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.student_app.Model.Model;
import com.example.student_app.Model.Student;

import java.util.List;

public class StudentEditFragment extends Fragment {

    List<Student> studentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_edit_page, container, false);
        String stId = StudentEditFragmentArgs.fromBundle(getArguments()).getStudentId();
        Student s = Model.instance.getStudentById(stId);

        studentList = Model.instance.getAllStudents();
        ImageView img = (ImageView) (view.findViewById(R.id.avatar_imv));
        img.setImageResource(R.drawable.avatar);

        // Student s = studentList.get(position);
        TextView name = view.findViewById(R.id.edit_name_et);
        TextView id = view.findViewById(R.id.edit_id_et);
        TextView phone = view.findViewById(R.id.edit_phone_et);
        TextView address = view.findViewById(R.id.edit_address_et);
        CheckBox cb = view.findViewById(R.id.edit_cb);

        name.setText(s.getName());
        id.setText(s.getId());
        phone.setText(s.getMobile());
        address.setText(s.getAddress());
        cb.setChecked(s.isChecked());

        Button saveBtn = view.findViewById(R.id.edit_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().matches(""))
                {
                    Toast.makeText(getActivity(),"Please enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(id.getText().toString().matches(""))
                {
                    Toast.makeText(getActivity(),"Please enter id!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone.getText().toString().matches(""))
                {
                    Toast.makeText(getActivity(),"Please enter phone!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(address.getText().toString().matches(""))
                {
                    Toast.makeText(getActivity(),"Please enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                s.setName(name.getText().toString());
                s.setId(id.getText().toString());
                s.setMobile(phone.getText().toString());
                s.setAddress(address.getText().toString());
                s.setChecked(cb.isChecked());
                Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionStudentEditFragmentToStudentDetailsFragment(s.getId()));
            }
        });

        Button cancelBtn = view.findViewById(R.id.edit_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionStudentEditFragmentToStudentDetailsFragment(s.getId()));
            }
        });

        Button deleteBtn = view.findViewById(R.id.edit_delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "+ delete user ");
                Model.instance.deleteStudentById(stId);
                Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionStudentEditFragmentToStudentListRvFragment());
            }
        });

        return view;
    }
}