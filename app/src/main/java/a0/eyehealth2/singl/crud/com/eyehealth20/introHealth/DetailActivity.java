package a0.eyehealth2.singl.crud.com.eyehealth20.introHealth;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;
import a0.eyehealth2.singl.crud.com.eyehealth20.startup.GuestUserActivity;

public class DetailActivity extends AppCompatActivity {

    TextView nameTxt;
    TextView detailTxt;
    ImageView imageKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eye_detail_activity);

        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        detailTxt= (TextView) findViewById(R.id.descDetailTxt);
        imageKnow = (ImageView) findViewById(R.id.articleDetailImg);

        // Tool bar back menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_detail);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.content.Intent intent = new android.content.Intent(DetailActivity.this,
                        GuestUserActivity.class);
                startActivity(intent);
            }
        });


        //GET INTENT
        Intent i=this.getIntent();

        //RECEIVE DATA
        String name=i.getExtras().getString("NAME_KEY");
        String desc=i.getExtras().getString("DETAIL_KEY");
        String image=i.getExtras().getString("IMAGE_KEY");

        //BIND DATA
        nameTxt.setText(name);
        detailTxt.setText(desc);
        Glide.with(this).load(image).into(imageKnow);



    }
}
