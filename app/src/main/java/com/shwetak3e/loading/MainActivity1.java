package com.shwetak3e.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shwetak3e.loading.Utility.Constants;
import com.shwetak3e.loading.fragments.FragmentAddNewTruck;
import com.shwetak3e.loading.fragments.FragmentAddNewTruck_1;
import com.shwetak3e.loading.fragments.FragmentIssueVideo;
import com.shwetak3e.loading.fragments.FragmentIssues;
import com.shwetak3e.loading.fragments.FragmentTruckDetails;
import com.shwetak3e.loading.fragments.FragmentTruckList;

public class MainActivity1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentAddNewTruck.onBackListener,FragmentIssues.onBackListener , FragmentIssueVideo.onBackListener, FragmentTruckDetails.onBackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment fragment= FragmentIssueVideo.newInstance();
        ft.add(R.id.container_body,fragment);
        ft.commit();
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity1, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment fragment=null;
        if (id == R.id.nav_add_truck) {
            fragment= FragmentAddNewTruck_1.newInstance();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if(id==R.id.nav_log_out){
            startActivity(new Intent(MainActivity1.this,WelcomeScreen.class));
        }

        if(fragment!=null){
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_body,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if(fragment!=null);
        return true;
    }


    @Override
    public void onBackPressed() {
        onBack();
    }

    @Override
    public void onBack() {
        android.support.v4.app.Fragment f= getSupportFragmentManager().findFragmentById(R.id.container_body);
        if(f instanceof FragmentIssues) {

            if(getSupportFragmentManager().getBackStackEntryCount()!=0){
                getSupportFragmentManager().popBackStack();
            }

        }else if(f instanceof FragmentTruckDetails){
            /*android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment=null;
            if(f.getArguments().getBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS)){
                fragment=FragmentTruckList.newInstance();
            }else {
                fragment = FragmentAddNewTruck.newInstance();
            }
            ft.replace(R.id.container_body,fragment);
            ft.commit();*/
            if(getSupportFragmentManager().getBackStackEntryCount()!=0){
                getSupportFragmentManager().popBackStack();
            }

        }else if(f instanceof FragmentAddNewTruck){
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment=FragmentTruckList.newInstance();
            ft.replace(R.id.container_body,fragment);
            ft.commit();
            /*if(getSupportFragmentManager().getBackStackEntryCount()!=0){
                getSupportFragmentManager().popBackStack();
            }*/

        }else if(f instanceof FragmentIssueVideo){
           /* android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment=FragmentIssues.newInstance();
            Bundle args=new Bundle();
            args.putString(Constants.SHIPMENT_ID,f.getArguments().getString(Constants.SHIPMENT_ID));
            args.putBoolean(Constants.SHOW_ISSUE_ON_TRUCK_DETAILS,false);
            args.putString(Constants.ISSUE_TYPE, f.getArguments().getString(Constants.ISSUE_TYPE));
            fragment.setArguments(args);
            ft.replace(R.id.container_body,fragment);
            ft.commit();*/
           if(getSupportFragmentManager().getBackStackEntryCount()!=0) {
               getSupportFragmentManager().popBackStack();
           }

        }
    }
}
