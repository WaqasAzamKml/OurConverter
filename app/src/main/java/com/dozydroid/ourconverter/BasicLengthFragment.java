package com.dozydroid.ourconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicLengthFragment extends Fragment {

    EditText etLength;
    Spinner spnOption;
    TextView tvCM, tvMM, tvDM;
    String[] optionsArray = {"mm", "cm", "dm", "inch", "ft", "yd", "mile", "km", "nm"};
    String selectedItem = "mm";
    ArrayList<String> convertedValues = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basic_length, container, false);

        etLength = (EditText) v.findViewById(R.id.etLength);
        spnOption = (Spinner) v.findViewById(R.id.spnOption);
        tvCM = (TextView) v.findViewById(R.id.tvCM);
        tvMM = (TextView) v.findViewById(R.id.tvMM);
        tvDM = (TextView) v.findViewById(R.id.tvDM);

        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.our_spinner_item, optionsArray);
        spnOption.setAdapter(optionsAdapter);
        spnOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = optionsArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(etLength.getText().toString())) {
                    convertedValues = convertValues(selectedItem, Double.valueOf(etLength.getText().toString()));
                    tvMM.setText(convertedValues.get(0));
                    tvCM.setText(convertedValues.get(1));
                    tvDM.setText(convertedValues.get(2));
                }else{
                    tvCM.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    public ArrayList<String> convertValues(String selectedItem, double value){
        ArrayList<String> results = new ArrayList<>();
        double mm=0, cm=0, dm=0, inch=0, ft=0, yd=0, mile=0, km=0, nm=0;
        if(selectedItem.equals("mm")){
            mm = value;
            cm = 100*value;
            dm = 0.01*value;
        }else if(selectedItem.equals("cm")){
            mm = value/100;
            cm = value;
            dm = value*0.1;

        }


        results.add(0, String.valueOf(mm));
        results.add(1, String.valueOf(cm));
        results.add(2, String.valueOf(dm));

        return results;
    }

}
