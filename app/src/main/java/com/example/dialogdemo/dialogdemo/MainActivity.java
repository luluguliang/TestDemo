package com.example.dialogdemo.dialogdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.example.dialogdemo.dialogdemo.dialog.MyDialog;
import com.example.dialogdemo.dialogdemo.service.MyService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private MyService.MyBinder mBinder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.MyBinder)service;
            mBinder.startDownload();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* myDialog = new MyDialog(MainActivity.this);
                myDialog.setData("商品","10.0","");
                myDialog.show();*/
                startActivity(new Intent(MainActivity.this,RecycleViewActivity.class));
            }
        });
    }
    @OnClick({R.id.id_bind_service,R.id.id_unbind_service,R.id.id_start_service,R.id.id_close_service}) void onclick(View view){
        switch (view.getId()){
            case R.id.id_bind_service:
                Intent intent = new Intent(this, MyService.class);
                bindService(intent,conn,BIND_AUTO_CREATE);
                break;
            case R.id.id_unbind_service:
                unbindService(conn);
                break;
            case R.id.id_start_service:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.id_close_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
        }
    }
    public MyDialog myDialog;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
