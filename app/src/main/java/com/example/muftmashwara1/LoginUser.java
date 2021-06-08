package com.example.muftmashwara1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class LoginUser extends AppCompatActivity {

    private EditText username, pass;
    private String str_username, str_pass;
    private Button login;
    private TextView usersignup  ;
    String url = "http://192.168.1.103/Myloginphp/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);


        username = findViewById(R.id.nameLU);
        pass = findViewById(R.id.passLU);
        usersignup = findViewById(R.id.singUpLU);
        login = findViewById(R.id.btnSingInLU);



        usersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usersigmup();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("")) {
                    Toast.makeText(LoginUser.this, "Enter User Name", Toast.LENGTH_SHORT).show();

                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(LoginUser.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {

                    str_username = username.getText().toString().trim();

                    str_pass = pass.getText().toString().trim();


                    String myurl = "http://192.168.1.103/Myloginphp/login.php?name=" + str_username + "&password=" + str_pass;

                    StringRequest request = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(LoginUser.this, response, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), welcome.class);
                            startActivity(i);


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginUser.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", str_username);

                            params.put("pass", str_pass);

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(LoginUser.this);
                    requestQueue.add(request);
                }


            }
        });
    }
public void Usersigmup(){
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);

}

}