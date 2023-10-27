package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

/*
pop up time in the record page
 */
public class SelectTimeDialog extends Dialog implements View.OnClickListener {
    EditText hourEt,minuteEt;
    DatePicker datePicker;
    Button ensureBtn,cancelBtn;
    public interface OnEnsureListener{
        public void onEnsure(String time, int year, int month, int day);
    }
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener){
        this.onEnsureListener = onEnsureListener;
    }
    public SelectTimeDialog(@NonNull Context context){
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time);
        hourEt = findViewById(R.id.dialog_time_et_hour);
        minuteEt = findViewById(R.id.dialog_time_et_minute);
        datePicker = findViewById(R.id.dialog_time_dp);
        ensureBtn = findViewById(R.id.dialog_time_btn_ensure);
        cancelBtn = findViewById(R.id.dialog_time_btn_cancel);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        hideDatePickerHeader();
    }

    /*public static class myConstants{
        public static final int cancel_view = R.id.dialog_time_btn_cancel;
        public static final int ensure_view = R.id.dialog_time_btn_ensure;
    }*/
    public void onClick(View v){
        /*switch (v.getId()){
            case myConstants.cancel_view:
                cancel();
                break;
            case myConstants.ensure_view:

                break;
        }*/
        if (v.getId() == R.id.dialog_time_btn_cancel){
            cancel();
        }else if (v.getId() == R.id.dialog_time_btn_ensure){
            int year = datePicker.getYear();// selected year
            int month = datePicker.getMonth()+1;// selected month
            int day = datePicker.getDayOfMonth();
            String monthStr = String.valueOf(month);
            String hourStr = hourEt.getText().toString();
            String minuteStr = minuteEt.getText().toString();
            int hour = 0;
            if(!TextUtils.isEmpty(hourStr)){
                hour = Integer.parseInt(hourStr);
                hour = hour%24;
            }
            int minute = 0;
            if(!TextUtils.isEmpty(minuteStr)){
                minute = Integer.parseInt(minuteStr);
                minute = minute%60;
            }
            String dayStr = String.valueOf(day);
            String yearStr = String.valueOf(year);
            hourStr = String.valueOf(hour);
            minuteStr = String.valueOf(minute);
            String timeFormat = monthStr+"/"+dayStr+"/"+yearStr+" "+ hourStr+":"+minuteStr;
            if(onEnsureListener!=null){
                onEnsureListener.onEnsure(timeFormat,year, month,day);
            }
            cancel();
            return;
        }

    }

    private void hideDatePickerHeader(){
        ViewGroup rootView = (ViewGroup) datePicker.getChildAt(0);
        if(rootView == null){
            return;
        }
        View headerView = rootView.getChildAt(0);
        if(headerView == null){
            return;
        }

        int headerId = getContext().getResources().getIdentifier("day_picker_selector_layout","id","android");
        if (headerId == headerView.getId()){
            headerView.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParamsRoot = rootView.getLayoutParams();
            layoutParamsRoot.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            rootView.setLayoutParams(layoutParamsRoot);
            ViewGroup animator = (ViewGroup) rootView.getChildAt(1);
            ViewGroup.LayoutParams layoutParamsAnimator = animator.getLayoutParams();
            layoutParamsAnimator.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            animator.setLayoutParams(layoutParamsAnimator);

            View child = animator.getChildAt(0);
            ViewGroup.LayoutParams layoutParamsChild = child.getLayoutParams();
            layoutParamsChild.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            child.setLayoutParams(layoutParamsChild);
            return;
        }
        headerId = getContext().getResources().getIdentifier("date_picker_header","id","android");
        if(headerId == headerView.getId()){
            headerView.setVisibility(View.GONE);
        }
    }
}
