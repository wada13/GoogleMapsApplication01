package com.example.wadamikako.googlemapsapplication01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){

        super.onStart();

        Button btn = (Button)findViewById(R.id.RegisterButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MapTopActivity.class);

                EditText address1 = (EditText)findViewById(R.id.AddressEditText);
                EditText place1 = (EditText)findViewById(R.id.PlaceEditText);
                EditText date1 = (EditText)findViewById(R.id.DateEditText);
                EditText tell1 = (EditText)findViewById(R.id.TellEditText);
                EditText memo1 = (EditText)findViewById(R.id.MemoEditText);

                String address = address1.getText().toString();
                String place = place1.getText().toString();
                String date = date1.getText().toString();
                String tell = tell1.getText().toString();;
                String memo = memo1.getText().toString();;

                intent.putExtra("address",address);
                intent.putExtra("place",place);
                intent.putExtra("date",date);
                intent.putExtra("tell",tell);
                intent.putExtra("memo",memo);

                startActivity(intent);
            }
        });

    }

    }

