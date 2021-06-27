package com.example.muftmashwara1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView login;
    private Button back, register;
    private Spinner spinner;
    private EditText username, pass, phone, email;
    private String str_username, str_pass, str_phone, str_email, spinner_hold;
    private CheckBox checkBox;
    private String url = "http://192.168.1.103/Myloginphp/signupc.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkBox.findViewById(R.id.checkBoxRC);
        back = findViewById(R.id.btnBackRC);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        login = findViewById(R.id.loginRC);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRC();
            }
        });


        spinner = findViewById(R.id.spinnerRC);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        email = findViewById(R.id.emailRC);
        username = findViewById(R.id.nameRC);
        pass = findViewById(R.id.passRC);
        phone = findViewById(R.id.phoneRC);


        register = findViewById(R.id.btnSingUpRC);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("")) {
                    Toast.makeText(MainActivity2.this, "Enter User Name", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().equals("")) {
                    Toast.makeText(MainActivity2.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(MainActivity2.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (phone.getText().toString().equals("")) {
                    Toast.makeText(MainActivity2.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else if (checkBox.isChecked()) {


                    register();

                    String myurl = "http://192.168.1.103/Myloginphp/signupc.php?name=" + str_username + "&email=" + str_email + "&mobile=" + str_phone + "&password=" + str_pass;

                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity2.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", str_username);
                            params.put("email", str_email);
                            params.put("pass", str_pass);
                            params.put("phone", str_phone);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
                    requestQueue.add(request);
                } else {
                    Toast.makeText(MainActivity2.this, "please check the check box", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public void back() {
        Intent intentback = new Intent(this, backRC.class);
        startActivity(intentback);

    }

    public void loginRC() {
        Intent intentlogin = new Intent(this, loginCounsellor.class);
        startActivity(intentlogin);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner_hold = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void register() {
        str_username = username.getText().toString().trim();
        str_email = email.getText().toString().trim();
        str_pass = pass.getText().toString().trim();

        str_phone = phone.getText().toString().trim();
        str_phone = spinner_hold + str_phone;
    }

}