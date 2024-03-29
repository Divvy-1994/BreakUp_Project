package com.example.breakup_project.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.breakup_project.Home.HomeActivity;
import com.example.breakup_project.Likes.LikesActivity;
import com.example.breakup_project.Profile.ProfileActivity;
import com.example.breakup_project.R;
import com.example.breakup_project.Search.SearchActivity;
import com.example.breakup_project.Share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHelper";

    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationView){



    }

    public static void enableNavigation(final Context context , BottomNavigationView view){
            view.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.ic_house :
                        Intent intent1 = new Intent(context , HomeActivity.class); //ACTIVITY_NUM=0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_alert :
                        Intent intent2 = new Intent(context , LikesActivity.class); //ACTIVITY_NUM=1
                        context.startActivity(intent2);
                        break;


                    case R.id.ic_android :
                        Intent intent3 = new Intent(context , ProfileActivity.class);//ACTIVITY_NUM=2
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_circle :
                        Intent intent4 = new Intent(context, ShareActivity.class);//ACTIVITY_NUM=3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_search :
                        Intent intent5 = new Intent(context, SearchActivity.class);//ACTIVITY_NUM=4
                        context.startActivity(intent5);
                        break;

                }
            }
        });

    }
}
