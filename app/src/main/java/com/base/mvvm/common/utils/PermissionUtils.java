package com.base.mvvm.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.base.mvvm.R;


public class PermissionUtils {
    private Context mContext;

    private String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // Permission request code of any integer value
    private static final int PERMISSION_REQ_CODE = 1 << 4;
    private Activity mActivity;

    public PermissionUtils(Activity activity){
//        mContext = context;
        mActivity = activity;
    }
    public boolean checkPermission() {
        boolean granted = true;
        for (String per : PERMISSIONS) {
            if (!permissionGranted(per)) {
                granted = false;
                break;
            }
        }

        if (granted) {
           return true;
        } else {
            requestPermissions();
        }
        return false;
    }
    private boolean permissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(
                mActivity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(mActivity, PERMISSIONS, PERMISSION_REQ_CODE);
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults, RequestPermissionListener requestPermissionListener) {
        if (requestCode == PERMISSION_REQ_CODE) {
            boolean granted = true;
            for (int result : grantResults) {
                granted = (result == PackageManager.PERMISSION_GRANTED);
                if (!granted) break;
            }

            if (granted) {
                requestPermissionListener.success();
            } else {
                Toast.makeText(mActivity, R.string.need_necessary_permissions, Toast.LENGTH_LONG).show();
            }
        }
    }

    public  interface  RequestPermissionListener{
        void success();
    }
}
