package a0.eyehealth2.singl.crud.com.eyehealth20.startup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import a0.eyehealth2.singl.crud.com.eyehealth20.R;
import a0.eyehealth2.singl.crud.com.eyehealth20.app.AppConfig;
import a0.eyehealth2.singl.crud.com.eyehealth20.app.AppController;
import a0.eyehealth2.singl.crud.com.eyehealth20.helper.SQLiteHandler;
import a0.eyehealth2.singl.crud.com.eyehealth20.helper.SessionManager;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnCancel;
    private Button btnLinkToLogin;
    private EditText inputName;
    private EditText inputSurname;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputRePassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eye_sign_up_activity);

        //Tool bar back menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_sign_up);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,
                        SignInActivity.class);
                startActivity(intent);
            }
        });

        //declare type link to id on view .xml file
        inputName = (EditText) findViewById(R.id.name_edit_text);
        inputSurname = (EditText) findViewById(R.id.surname_edit_text);
        inputEmail = (EditText) findViewById(R.id.email_edit_text);
        inputPassword = (EditText) findViewById(R.id.password_edit_text);
        inputRePassword = (EditText) findViewById(R.id.re_enter_password_edit_text);
        btnRegister = (Button) findViewById(R.id.sign_in_button);
        btnCancel = (Button) findViewById(R.id.cancel_button);
        btnLinkToLogin = (Button) findViewById(R.id.link_to_sign_in_button);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to Menu activity
            Intent intent = new Intent(SignUpActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputName.getText().toString().trim();
                String surname = inputSurname.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String rePassword = inputRePassword.getText().toString().trim();

                final TextInputLayout nError = findViewById(R.id.name_text_input);
                final TextInputLayout sError = findViewById(R.id.surname_text_input);
                final TextInputLayout eError = findViewById(R.id.email_text_input);
                final TextInputLayout pError = findViewById(R.id.password_text_input);
                final TextInputLayout rError = findViewById(R.id.re_enter_password_text_input);

                String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
                        "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
                        "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

                nError.setError(null);
                sError.setError(null);
                eError.setError(null);
                pError.setError(null);
                rError.setError(null);

                if (name.isEmpty() && surname.isEmpty() && email.isEmpty() && password.isEmpty() && rePassword.isEmpty()) {
                    nError.setError(getString(R.string.eye_error_not_enter_name));
                    sError.setError(getString(R.string.eye_error_not_enter_surname));
                    eError.setError(getString(R.string.eye_error_not_enter_email));
                    pError.setError(getString(R.string.eye_error_not_enter_pass));
                    rError.setError(getString(R.string.eye_error_not_enter_re_pass));

                }else if (name.isEmpty() &&!surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()) {
                    nError.setError(getString(R.string.eye_error_not_enter_name));

                }else if (!name.isEmpty() && surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()) {
                    sError.setError(getString(R.string.eye_error_not_enter_surname));

                }else if (!name.isEmpty() &&!surname.isEmpty() && email.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()) {
                    eError.setError(getString(R.string.eye_error_not_enter_email));

                }else if (name.isEmpty() &&surname.isEmpty() && (!email.isEmpty() && !email.matches(emailPattern)) && password.isEmpty() && rePassword.isEmpty()) {
                    eError.setError(getString(R.string.eye_error_not_match_email));

                }else if (!name.isEmpty() &&!surname.isEmpty() && (!email.isEmpty() && !email.matches(emailPattern)) && (!password.isEmpty() && password.length() < 8) && (!rePassword.isEmpty() && !password.equals(rePassword))) {
                    eError.setError(getString(R.string.eye_error_not_match_email));
                    pError.setError(getString(R.string.eye_error_password_less));
                    rError.setError(getString(R.string.eye_error_not_enter_re_pass_notmatch));

                }else if (!name.isEmpty() &&!surname.isEmpty() && (!email.isEmpty() && email.matches(emailPattern)) && (!password.isEmpty() && password.length() < 8) && (!rePassword.isEmpty() && !password.equals(rePassword))) {
                    pError.setError(getString(R.string.eye_error_password_less));
                    rError.setError(getString(R.string.eye_error_not_enter_re_pass_notmatch));

                }else if (!name.isEmpty() &&!surname.isEmpty() && (!email.isEmpty() && email.matches(emailPattern)) && (!password.isEmpty() && password.length() >= 8) && (!rePassword.isEmpty() && !password.equals(rePassword))) {
                    rError.setError(getString(R.string.eye_error_not_enter_re_pass_notmatch));

                }else if (!name.isEmpty() &&!surname.isEmpty() && !email.isEmpty() && password.isEmpty() && !rePassword.isEmpty()) {
                    pError.setError(getString(R.string.eye_error_not_enter_pass));

                }else if (!name.isEmpty() &&!surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && rePassword.isEmpty()) {
                    rError.setError(getString(R.string.eye_error_not_enter_re_pass));

                }else if (!name.isEmpty() &&!surname.isEmpty() && (!email.isEmpty() && email.matches(emailPattern)) && (!password.isEmpty() && password.length() >= 8) && (!rePassword.isEmpty() && password.equals(rePassword))) {
                    registerUser(name, surname, email, password);
                }

//                if (!name.isEmpty() &&!surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()) {
//                    registerUser(name, surname, email, password);
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "กรุณากรอกรายละเอียดของคุณ!", Toast.LENGTH_LONG)
//                            .show();
//                }
            }
        });

        //Cancel Sign in
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        SignInActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        SignInActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String name, final String surname, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String surname = user.getString("surname");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, surname, email, uid, created_at);

                        Toast.makeText(getApplicationContext(), "ผู้ใช้ลงทะเบียนเรียบร้อยแล้ว ลองเข้าสู่ระบบทันที!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                SignUpActivity.this,
                                SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("surname", surname);
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
