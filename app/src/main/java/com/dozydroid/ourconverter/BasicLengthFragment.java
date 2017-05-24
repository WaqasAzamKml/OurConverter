package com.dozydroid.ourconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BasicLengthFragment extends Fragment {

    EditText etLength;
    Spinner spnOption;
    TextView tvCM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basic_length, container, false);

        etLength = (EditText) v.findViewById(R.id.etLength);
        spnOption = (Spinner) v.findViewById(R.id.spnOption);

        return v;
    }

}
