package com.example.studentmeetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.studentmeetup.viewmodel.ViewModelUser;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ViewModelUser userModel;
    private static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //Loading the ViewModelLogin into loginModel
        userModel = new ViewModelProvider(this).get(ViewModelUser.class);

        //Loading the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Loading the drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_sessions, R.id.nav_search_session, R.id.nav_new_session,
                R.id.nav_my_sessions, R.id.nav_search_user)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);






    }


    public static void navigateTo(int navigationId){

        navController.navigate(navigationId);
    }



    @Override
    protected void onResume() {
        super.onResume();
        //Observing the user name;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_my_profile:
                NavigationUI.onNavDestinationSelected(item, navController);
                break;
            case R.id.action_logout:
                SharedPreferences preferences = this.getSharedPreferences(getString(R.string.shared_preferences_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear()
                        .commit();
                finishAndRemoveTask();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
