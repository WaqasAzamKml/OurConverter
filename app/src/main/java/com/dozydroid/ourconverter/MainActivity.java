package com.dozydroid.ourconverter;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    PagerTitleStrip pagerTitleStrip;
    TabsPagerAdapter adapter;
    boolean isBasic = true;
    int pos = 0;
    CheckBox chkCurrency, chkTemperature, chkTime, chkSpeed, chkShoesM, chkShoesW;
    List<CheckBox> checked = new ArrayList<>();
    List<CheckBox> livingCheckBoxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        adapter = new TabsPagerAdapter(fragmentManager);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                if(position>0){
                    isBasic = false;
                }else{
                    isBasic = true;
                }
                invalidateOptionsMenu();
//                onCreateOptionsMenu()

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        BasicFragment fragment1 = new BasicFragment();
        LivingFragment fragment2 = new LivingFragment();
        BasicFragment fragment3 = new BasicFragment();
        BasicFragment fragment4 = new BasicFragment();
        adapter.addFragment(fragment1, "Basic");
        adapter.addFragment(fragment2, "Living");
        adapter.addFragment(fragment3, "Science");
        adapter.addFragment(fragment4, "Misc.");

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(isBasic) {
            getMenuInflater().inflate(R.menu.main, menu);
        }else{
            getMenuInflater().inflate(R.menu.custom_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_favorites){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
            View dialogView;
            if(pos == 1){
                dialogView = getLayoutInflater().inflate(R.layout.living_favourites_dialog, null);
                chkCurrency = (CheckBox) dialogView.findViewById(R.id.chkCurrency);
                chkTemperature = (CheckBox) dialogView.findViewById(R.id.chkTemperature);
                chkTime = (CheckBox) dialogView.findViewById(R.id.chkTime);
                chkSpeed = (CheckBox) dialogView.findViewById(R.id.chkSpeed);
                chkShoesM = (CheckBox) dialogView.findViewById(R.id.chkShoesM);
                chkShoesW = (CheckBox) dialogView.findViewById(R.id.chkShoesW);
                livingCheckBoxes.add(chkCurrency);
                livingCheckBoxes.add(chkTemperature);
                livingCheckBoxes.add(chkTime);
                livingCheckBoxes.add(chkSpeed);
                livingCheckBoxes.add(chkShoesM);
                livingCheckBoxes.add(chkShoesW);
                for(final CheckBox chk: livingCheckBoxes){
                    chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                                checked.add(chk);
                            else
                                checked.remove(chk);
                            if(checked.size()==4){
                                for(CheckBox chkIn: livingCheckBoxes){
                                    if(!chkIn.isChecked())
                                        chkIn.setEnabled(false);
                                }
                            }else{
                                for(CheckBox chkIn: livingCheckBoxes){
                                    chkIn.setEnabled(true);
                                }
                            }
                        }
                    });
                }

                chkCurrency.setChecked(true);
                chkTemperature.setChecked(true);
                chkTime.setChecked(true);
                chkSpeed.setChecked(true);
            }else if(pos==2){
                dialogView = getLayoutInflater().inflate(R.layout.science_favourites_dialog, null);
            }else{
                //Todo: R.layout.misc_favourites_dialog
                dialogView = getLayoutInflater().inflate(R.layout.living_favourites_dialog, null);
            }
            dialogBuilder.setView(dialogView);
            dialogBuilder.create();
            dialogBuilder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


class TabsPagerAdapter extends FragmentPagerAdapter{
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titlesList = new ArrayList<>();

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }

    public void addFragment(Fragment fragment, String fragmentTitle){
        fragmentList.add(fragment);
        titlesList.add(fragmentTitle);
    }
}