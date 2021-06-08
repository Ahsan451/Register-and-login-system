package com.example.muftmashwara1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private TextView login;
private Button back , register;
private EditText username , pass , phone , email;
private String str_username , str_pass , str_phone , str_email;
private CheckBox checkBox;
private String url = "http://192.168.1.103/Myloginphp/signup.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
checkBox = findViewById(R.id.checkBoxRU);
        back = findViewById(R.id.btnBackRU);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
back();
            }
        });

        login = findViewById(R.id.loginRU);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRU();
            }
        });


        email = findViewById(R.id.emailRU);
        username = findViewById(R.id.nameRU);
        pass = findViewById(R.id.passRU);
        phone = findViewById(R.id.phoneRU);


        register = findViewById(R.id.btnSingUpRU);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("please wait..");

                if (username.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter User Name", Toast.LENGTH_SHORT).show();
                }

                else if (email.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if (pass.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (phone.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }

                else if(checkBox.isChecked()) {
                    str_username = username.getText().toString().trim();
                    str_email = email.getText().toString().trim();
                    str_pass = pass.getText().toString().trim();
                    str_phone= phone.getText().toString().trim();

                    String myurl = "http://192.168.1.103/Myloginphp/signup.php?name="+ str_username + "&email=" + str_email + "&mobile=" + str_phone + "&password=" + str_pass;

                    StringRequest request = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
params.put("username",str_username);
params.put("email",str_email);
        params.put("pass",str_pass);
                params.put("phone",str_phone);
return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(request);
                }
                else {
                    Toast.makeText(MainActivity.this, "please check the check box", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    public void back(){
        Intent intentback = new Intent(this , backRU.class);
        startActivity(intentback);
    }
    public void loginRU(){
        Intent intentlogin = new Intent(this,LoginUser.class);
        startActivity(intentlogin);
    }
}



