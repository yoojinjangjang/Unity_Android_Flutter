package com.example.untitled;

import android.os.Bundle;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends FlutterActivity {
    private  static final String CHANNEL = "example.com/value";


//    @Override
//    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
//        super.configureFlutterEngine(flutterEngine);
//        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(),CHANNEL).setMethodCallHandler(
//                new MethodChannel.MethodCallHandler() {
//                    @Override
//                    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
//                        if (call.method.equals("getValue")) {
//                            result.success("success");
//
//                        } else {
//                            result.notImplemented();
//                        }
//                    }
//                }
//        );
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        GeneratedPluginRegistrant.registerWith(this.getFlutterEngine());
//
//        new MethodChannel(this.getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler(
//                new MethodChannel.MethodCallHandler() {
//
//
//                    @Override
//                    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
//                        if (call.method.equals("getValue")){
//                            result.success("success");
//
//                        }else{
//                            result.notImplemented();
//                        }
//                    }
//                });
//
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this.getFlutterEngine());
       // Intent intent = new Intent(this,UnityHandlerActivity.class);
        Intent intent = new Intent(MainActivity.this, UnityPlayerActivity.class);


        new MethodChannel(this.getFlutterEngine().getDartExecutor().getBinaryMessenger(),CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                        // Note: this method is invoked on the main thread.
                        if (call.method.equals("getBatteryLevel")) {
                            startActivity(intent);
                            int batteryLevel = getBatteryLevel();

                            if (batteryLevel != -1) {
                                result.success(batteryLevel);
                            } else {
                                result.error("UNAVAILABLE", "Battery level not available.", null);
                            }
                        } else {
                            result.notImplemented();
                        }
                    }

                }
        );


    }



    private int getBatteryLevel() {
        int batteryLevel = -1;
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }

        return batteryLevel;
    }


}
