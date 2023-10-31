package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class MoreDialog extends Dialog implements View.OnClickListener {
    Button aboutBtn,settingBtn,historyBtn,infoBtn;
    ImageView errorIv;
    public MoreDialog(@NonNull Context context){
        super(context);
        setContentView(R.layout.dialog_more);
        aboutBtn = findViewById(R.id.dialog_more_btn_about);
        settingBtn=findViewById(R.id.dialog_more_btn_setting);
        historyBtn=findViewById(R.id.dialog_more_btn_record);
        infoBtn=findViewById(R.id.dialog_more_btn_info);
        errorIv=findViewById(R.id.dialog_more_iv);

        aboutBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
        infoBtn.setOnClickListener(this);
        errorIv.setOnClickListener(this);

    }
    public void onClick(View v){
        if (v.getId()== R.id.dialog_more_btn_about) {
        }
        if (v.getId()== R.id.dialog_more_btn_setting) {

        }
        if (v.getId()== R.id.dialog_more_btn_record) {

        }
        if (v.getId()== R.id.dialog_more_btn_info) {
        }
        if (v.getId()== R.id.dialog_more_iv) {
        }
        cancel();
    }
    public void setDialogSize(){
        Window window=getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width=(int) (d.getWidth());
        wlp.gravity= Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);


    }
}
