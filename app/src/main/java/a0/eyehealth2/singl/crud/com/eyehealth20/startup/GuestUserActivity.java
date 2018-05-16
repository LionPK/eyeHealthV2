package a0.eyehealth2.singl.crud.com.eyehealth20.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;
import a0.eyehealth2.singl.crud.com.eyehealth20.introHealth.IntroEyeActivity;

public class GuestUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eye_guest_user_activity);

        //Tool bar back menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_infor_eye);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuestUserActivity.this,
                        SignInActivity.class);
                startActivity(intent);
            }
        });


        // Set grid action
        findViewById(R.id.card_view_eyeDis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuestUserActivity.this, IntroEyeActivity.class));
            }
        });


    }
}
