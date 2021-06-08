package com.example.muftmashwara1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class loginCounsellor extends AppCompatActivity {

    private EditText username , pass;
    private String str_username,str_pass;
    private Button login;
    String url  = "http://192.168.1.103/Myloginphp/loginc.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        username = findViewById(R.id.nameLU);
        pass = findViewById(R.id.passLU);

        login = findViewById(R.id.btnSingInLU);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")){
                    Toast.makeText(loginCounsellor.this, "Enter User Name", Toast.LENGTH_SHORT).show();

                }
                else if(pass.getText().toString().equals("")){
                    Toast.makeText(loginCounsellor.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else {

                    str_username = username.getText().toString().trim();

                    str_pass = pass.getText().toString().trim();


                    String myurl = "http://192.168.1.103/Myloginphp/loginc.php?name="+ str_username + "&password=" + str_pass;

                    StringRequest request = new StringRequest(Request.Method.POST, myurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(loginCounsellor.this, response, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),welcome.class);
                            startActivity(i);


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(loginCounsellor.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("username",str_username);

                            params.put("pass",str_pass);

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(loginCounsellor.this);
                    requestQueue.add(request);
                }



            }
        });
    }
}
