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

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.student_app.Model.Model;
import com.example.student_app.Model.Student;


public class StudentDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_details_page, container, false);

        String stId = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId();
        Student s = Model.instance.getStudentById(stId);

        ImageView img = (ImageView) (view.findViewById(R.id.avatar_imv));
        img.setImageResource(R.drawable.avatar);

        TextView name = view.findViewById(R.id.details_name_str_tv);
        TextView id = view.findViewById(R.id.details_id_str_tv);
        TextView phone = view.findViewById(R.id.details_phone_str_tv);
        TextView address = view.findViewById(R.id.details_address_str_tv);
        CheckBox cb = view.findViewById(R.id.details_cb);

       // Log.d("TAG","+ student id: "+s.getId());

        name.setText(s.getName());
        id.setText(s.getId());
        phone.setText(s.getMobile());
        address.setText(s.getAddress());
        cb.setChecked(s.isChecked());
        cb.setEnabled(false);

        Button editBtn = view.findViewById(R.id.details_edit_btn);
        Log.d("TAG","edit student was clicked ");
        editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentEditFragment(stId)));

        // Override default back (of android)
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).navigate(StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentListRvFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return view;
    }
}