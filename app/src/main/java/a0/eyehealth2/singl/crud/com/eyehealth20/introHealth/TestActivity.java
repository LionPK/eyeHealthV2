package a0.eyehealth2.singl.crud.com.eyehealth20.introHealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("Message");

        TextView textData = (TextView)findViewById(R.id.textData);
        textData.setText(text);
    }
}
