package com.dozydroid.ourconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class LivingFragment extends Fragment {

    FragmentManager fragmentManager;
    FrameLayout basicFragmentContainer;
    LinearLayout layoutCurrency, layoutTemperature, layoutTime, layoutSpeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_living, container, false);
        basicFragmentContainer = (FrameLayout) v.findViewById(R.id.fragmentBasicContainer);
        layoutCurrency = (LinearLayout) v.findViewById(R.id.layoutCurrency);
        layoutTemperature = (LinearLayout) v.findViewById(R.id.layoutTemperature);
        layoutTime = (LinearLayout) v.findViewById(R.id.layoutTime);
        layoutSpeed = (LinearLayout) v.findViewById(R.id.layoutSpeed);
        fragmentManager = getChildFragmentManager();
        layoutCurrency.setBackgroundResource(R.drawable.selected_bg);
        fragmentManager.beginTransaction().replace(R.id.fragmentBasicContainer, new LivingCurrencyFragment()).commit();
        layoutCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCurrency.setBackgroundResource(R.drawable.selected_bg);
                layoutTemperature.setBackgroundResource(R.drawable.deselected_bg);
                layoutTime.setBackgroundResource(R.drawable.deselected_bg);
                layoutSpeed.setBackgroundResource(R.drawable.deselected_bg);
                fragmentManager.beginTransaction().replace(R.id.fragmentBasicContainer, new LivingCurrencyFragment()).commit();
            }
        });
        layoutTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutTemperature.setBackgroundResource(R.drawable.selected_bg);
                layoutCurrency.setBackgroundResource(R.drawable.deselected_bg);
                layoutTime.setBackgroundResource(R.drawable.deselected_bg);
                layoutSpeed.setBackgroundResource(R.drawable.deselected_bg);
                fragmentManager.beginTransaction().replace(R.id.fragmentBasicContainer, new LivingTemperatureFragment()).commit();
            }
        });
        return v;
    }
}
