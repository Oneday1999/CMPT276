package com.example.myapplication.utils;
import static com.example.myapplication.R.id.dialog_notes_btn_ensure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;


import com.example.myapplication.R;

public class NotesDialog extends Dialog implements View.OnClickListener {
    EditText et;
    Button cancelBtn,ensureBtn;
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener){
        this.onEnsureListener = onEnsureListener;
    }

    public NotesDialog(@NonNull Context context){
        super(context);
    }
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.dialog_notes);
        et = findViewById(R.id.dialog_notes_et);
        cancelBtn = findViewById(R.id.dialog_notes_btn_cancel);
        ensureBtn = findViewById(R.id.dialog_notes_btn_ensure);
        cancelBtn.setOnClickListener(this);
        ensureBtn.setOnClickListener(this);

    }
    public interface OnEnsureListener{
        public void onEnsure();
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
       if(v.getId() ==  R.id.dialog_notes_btn_cancel){
           cancel();} else {
           return;

           /* case R.id.dialog_notes_btn_cancel:
                cancel();
                break;
            case R.id.dialog_notes_btn_ensure:
                if (onEnsureListener != null){
                    onEnsureListener.onEnsure();
                }
                break;*/
        }

    }
    public String getEditText(){
        return et.getText().toString().trim();
    }

    public void setDialogSize(){
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int)(d.getWidth());
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
        handler.sendEmptyMessageDelayed(1,100);
    }

    Handler handler = new Handler(){
        public void handleMessage(@NonNull Message msg){
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    };
}
