package a0.eyehealth2.singl.crud.com.eyehealth20.introHealth;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;

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
