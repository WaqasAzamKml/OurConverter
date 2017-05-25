package com.dozydroid.ourconverter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class BasicFragment extends Fragment {
    FragmentManager fragmentManager;
    FrameLayout basicFragmentContainer;
    LinearLayout layoutLength, layoutArea, layoutWeight, layoutVolume;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_basic, container, false);
        basicFragmentContainer = (FrameLayout) v.findViewById(R.id.fragmentBasicContainer);
        layoutLength = (LinearLayout) v.findViewById(R.id.layoutLength);
        layoutArea = (LinearLayout) v.findViewById(R.id.layoutArea);
        layoutWeight = (LinearLayout) v.findViewById(R.id.layoutWeight);
        layoutVolume = (LinearLayout) v.findViewById(R.id.layoutVolume);
        fragmentManager = getChildFragmentManager();
        layoutLength.setBackgroundResource(R.drawable.selected_bg);
        fragmentManager.beginTransaction().replace(R.id.fragmentBasicContainer, new BasicLengthFragment()).commit();
        layoutLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutLength.setBackgroundResource(R.drawable.selected_bg);
                layoutArea.setBackgroundResource(R.drawable.deselected_bg);
                layoutWeight.setBackgroundResource(R.drawable.deselected_bg);
                layoutVolume.setBackgroundResource(R.drawable.deselected_bg);
                fragmentManager.beginTransaction().replace(R.id.fragmentBasicContainer, new BasicLengthFragment()).commit();
            }
        });
        layoutArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutArea.setBackgroundResource(R.drawable.selected_bg);
                layoutLength.setBackgroundResource(R.drawable.deselected_bg);
                layoutWeight.setBackgroundResource(R.drawable.deselected_bg);
                layoutVolume.setBackgroundResource(R.drawable.deselected_bg);
                fragmentManager.beginTransaction().replace(R.id.fragmentBasicContainer, new BasicAreaFragment()).commit();
            }
        });
        return v;
    }}
