package com.dozydroid.ourconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BasicLengthFragment extends Fragment {

    EditText etLength;
    Spinner spnOption;
    TextView tvCM;
    String[] optionsArray = {"mm", "cm", "dm", "inch", "ft", "yd", "mile", "km", "nm"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basic_length, container, false);

        etLength = (EditText) v.findViewById(R.id.etLength);
        spnOption = (Spinner) v.findViewById(R.id.spnOption);
        tvCM = (TextView) v.findViewById(R.id.tvCM);

        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, optionsArray);
        spnOption.setAdapter(optionsAdapter);

        etLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCM.setText(etLength.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

}
