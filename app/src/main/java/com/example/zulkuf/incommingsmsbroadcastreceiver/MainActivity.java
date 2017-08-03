package com.example.zulkuf.incommingsmsbroadcastreceiver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.zulkuf.incommingsmsbroadcastreceiver.permission.AppSettingsDialog;
import com.example.zulkuf.incommingsmsbroadcastreceiver.permission.EasyPermissions;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_PERMISSONS = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checkPermission();
        smsPermission();
    }

    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){

        }else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_SMS)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},1);
            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},1);
            }
        }
    }

    public void smsPermission(){
        String[] perms = {
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.INTERNET

                        };
        if (EasyPermissions.hasPermissions(this,perms)){
            Toast.makeText(this,"Internet and sms things",Toast.LENGTH_SHORT).show();
        }else {
            EasyPermissions.requestPermissions(this,"This apps need permission",REQUEST_PERMISSONS,perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("PERM","onPermissionGranted: " + requestCode + " : " + perms.size() );
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("PERM","onPermissionDenied: " + requestCode + " : " + perms.size() );
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE){
            Toast.makeText(this, "Returned from app to MainActivity",Toast.LENGTH_SHORT).show();
        }
    }
}
