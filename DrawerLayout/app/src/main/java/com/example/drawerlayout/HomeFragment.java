package com.example.drawerlayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.NoCopySpan;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {


    Boolean isChecked;
   ;



    public HomeFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        //textView.setText(editText.getText());
        Bundle bundle = getArguments();
        isChecked = bundle.getBoolean("isChecked");

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override

    public void onViewCreated(View view, Bundle savedInstanceState) {



        changeText(this);



        if (isChecked) {
            editText.setEnabled(false);
        } else {
            editText.setEnabled(true);
        }

    }


}
