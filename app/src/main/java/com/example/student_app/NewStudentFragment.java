package com.example.student_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.student_app.Model.Model;
import com.example.student_app.Model.Student;

import java.util.List;


public class NewStudentFragment extends Fragment {

    List<Student> studentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_new_page, container, false);

        ImageView img = (ImageView) (view.findViewById(R.id.avatar_imv));
        img.setImageResource(R.drawable.avatar);

        Button btnCancel = (Button) view.findViewById(R.id.new_cancel_btn);
        Button btnSave = (Button) view.findViewById(R.id.new_save_btn);

        EditText name = view.findViewById(R.id.new_name_et);
        EditText id = view.findViewById(R.id.new_id_et);
        EditText phone = view.findViewById(R.id.new_phone_et);
        EditText address = view.findViewById(R.id.new_address_et);
        CheckBox cb = view.findViewById(R.id.new_cb);

        studentList = Model.instance.getAllStudents();

        btnSave.setOnClickListener(new View.OnClickListener() {
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

                Student s = new Student(name.getText().toString(),id.getText().toString(),phone.getText().toString(),address.getText().toString(), cb.isChecked());
                studentList.add(s);
                Navigation.findNavController(v).navigate(NewStudentFragmentDirections.actionNewStudentFragmentToStudentListRvFragment());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return view;
    }
}