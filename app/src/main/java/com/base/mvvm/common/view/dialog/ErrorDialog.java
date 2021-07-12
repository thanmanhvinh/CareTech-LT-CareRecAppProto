/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 10:08 AM
 *
 */

package com.base.mvvm.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.base.mvvm.R;
import com.base.mvvm.databinding.DialogErrorBinding;

/**
 * ErrorDialog
 */
public class ErrorDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private DialogButtonClickListener listener;

    private String message;

    private DialogErrorBinding binding;

    public ErrorDialog(Context context) {
        super(context, R.style.StyleCommonDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = DataBindingUtil
                .inflate(LayoutInflater.from(getContext()), R.layout.dialog_error,
                        null, false);
        setContentView(binding.getRoot());
        setCanceledOnTouchOutside(false);

        binding.btnConfirm.setOnClickListener(this);
        if (message != null) {
            if (!message.isEmpty()) {
                if (binding != null) {
                    binding.txtContent.setText(message);
                }
            }
        }

    }


    @Override
    public void onBackPressed() {
        dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm:
                if (listener != null) {
                    listener.onConfirmClickListener();
                }
                dismiss();
                break;
        }
    }

    public void setListener(DialogButtonClickListener listener) {
        this.listener = listener;
    }

    public void setMessage(String message) {
        this.message = message;
        if (this.message != null) {
            if (binding != null) {
                binding.txtContent.setText(this.message);
            }
        }
    }

    public interface DialogButtonClickListener {
        void onConfirmClickListener();
    }

}

