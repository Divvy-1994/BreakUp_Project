package com.example.breakup_project.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.breakup_project.Login.LoginActivity;
import com.example.breakup_project.R;
import com.example.breakup_project.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = HomeActivity.this;

    //FireBase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        Log.d(TAG, "onCreate: Starting");

        setupBottomNavigationView();
        setupViewPager();
    }




    /*Responsible for adding the 3 tabs : Stories , Home , Messages*/
    private void setupViewPager(){
        SectionsPagerAdapter adapter =  new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StoryFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new MessageFragment());
        ViewPager viewPager =(ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_story);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_actionname);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);

    }
    /*
    *  BottomNavigationView Setup
    * */

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.enableNavigation(HomeActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);


   }
/*
* --------------------------------FIREBASE ----------------------------------
* */

    /**
     * Setting up firebase authentication object.
     *
     */

    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: Checking if the user is already logged in ");
        if(user == null){

            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);

            finish();
            return;
        }
        
    }
    
    private void setupFireBaseAuth(){

        Log.d(TAG, "setupFireBaseAuth: Setting up FireBase Authentication");



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                //Check if the user is logged in
                checkCurrentUser(user);
                if(user!= null){
                    Log.d(TAG, "onAuthStateChanged:signed in "+user.getUid());
                }else{

                    Log.d(TAG, "onAuthStateChanged: signed out ");
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener !=null){

            mAuth.removeAuthStateListener(mAuthListener);

        }
    }
}