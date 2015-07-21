package com.example.wadamikako.googlemapsapplication01;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//リストを表示するためのActivity


public class MapTopActivity extends Activity{

    private ListView myListView;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_top);

        myListView = (ListView)findViewById(R.id.listView);

        final MyDbHelper dbHelper = new MyDbHelper(this);
        db = dbHelper.getWritableDatabase();

        String[] regData = new String[5];

        final Intent intent = getIntent();
        final String address = intent.getStringExtra("address");

        if(intent != null ){

            regData[0] = intent.getStringExtra("address");
            regData[1] = intent.getStringExtra("place");
            regData[2] = intent.getStringExtra("date");
            regData[3] = intent.getStringExtra("tell");
            regData[4] = intent.getStringExtra("memo");

        }

        try{
            //データの挿入
            insertToDB(regData);

            //データの検索
            final Cursor c = searchToDB();

            String[] from = {MyDbHelper.ADDRESS,MyDbHelper.PLACE,MyDbHelper.DATE,MyDbHelper.TELL,MyDbHelper.MEMO};
            int[] to = {R.id.reg_address,R.id.reg_place,R.id.reg_date,R.id.reg_tell,R.id.reg_memo};

            //adapterの作成
            final SimpleCursorAdapter addressList = new SimpleCursorAdapter(this,R.layout.listitem,c,from,to,0);
            myListView.setAdapter(addressList);

            //リストの各項目をクリックしたときの処理
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?>adapterView,View view,int i,long l){

                    Intent intent = new Intent(MapTopActivity.this,MapsActivity.class);
                    intent.putExtra("address",address);

                    startActivity(intent);

                }
            });

        } catch(Exception e){

            e.printStackTrace();
        } finally {

            db.close();
        }

    }

    //DBへデータ挿入
    private void insertToDB(String[] regData) throws Exception{

        db.execSQL("insert into myData(" +MyDbHelper.ADDRESS +","
                        +MyDbHelper.PLACE +","
                        +MyDbHelper.DATE +","
                        +MyDbHelper.TELL +","
                        +MyDbHelper.MEMO + ")values('" + regData[0] +"','" + regData[1] + "','" + regData[2] + "','" + regData[3] + "','" + regData[4] + "')");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_top, menu);
        return true;
    }

    //DBのデータ検索
    private Cursor searchToDB() throws Exception {

        Cursor c = db.rawQuery("select * from "+ MyDbHelper.TABLE_NAME,null);

        return c;

    }

    //登録画面(RegisterActivity)へ遷移する
    @Override
    public void onStart(){
        super.onStart();

        Button btn = (Button)findViewById(R.id.MainButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MapTopActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    }



