package a0.eyehealth2.singl.crud.com.eyehealth20.startup;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;
import a0.eyehealth2.singl.crud.com.eyehealth20.json.JSONDownloaderOne;

public class GuestUserActivity extends AppCompatActivity {
    String jsonURL="http://eyehealthimpact.com/android_knowlage_newapi/knowledge_one.php";
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eye_knowledge_activity);

        //Tool bar back menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_infor_eye);
        setSupportActionBar(toolbar);

        // Set grid action
        gv= (GridView) findViewById(R.id.knowledge_gv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.knowledge_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONDownloaderOne(GuestUserActivity.this,jsonURL,gv).execute();

            }
        });


    }
}
