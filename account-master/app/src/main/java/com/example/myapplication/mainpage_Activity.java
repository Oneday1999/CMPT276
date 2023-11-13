package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.AccountAdapter;
import com.example.myapplication.db.AccountBean;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.utils.BudgetDialog;
import com.example.myapplication.utils.MoreDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class mainpage_Activity extends AppCompatActivity implements View.OnClickListener{
    ListView todayLv;
    ImageView searchIv;
    ImageButton editBtn;
    ImageButton moreBtn;
    AccountAdapter adapter;

    List<AccountBean> mDatas;
    int year,month,day;
    TextView topOutTv,topInTv,topbudgetTv,topConTv;
    View headerView;
    ImageView topShowIv;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        todayLv = findViewById(R.id.main_lv);
        mDatas = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(this );
        initTime();
        initView();
        addLVHeaderView();
        // Adapter
        adapter = new AccountAdapter(this, mDatas);
        todayLv.setAdapter(adapter);
    }

    private void initView() {
        todayLv = findViewById(R.id.main_lv);
        editBtn = findViewById(R.id.main_btn_record);
        moreBtn = findViewById(R.id.main_btn_menu);
        searchIv = findViewById(R.id.main_iv_search);
        editBtn.setOnClickListener(this);
        moreBtn.setOnClickListener(this);
        searchIv.setOnClickListener(this);
    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDBData();
        setTopTvShow();
    }

    @SuppressLint("SetTextI18n")
    private void setTopTvShow() {
        float incomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 1);
        float outcomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 0);
        String infoOneDay = "Outcome today $"+ " " + outcomeOneDay+" Income today$"+incomeOneDay;
        topConTv.setText(infoOneDay);

        float incomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        topInTv.setText("$"+incomeOneMonth);
        topOutTv.setText("$"+outcomeOneMonth);

        float bmoney = preferences.getFloat("bmoney", 0);
        if (bmoney == 0) {
            topbudgetTv.setText("$ 0");
        }else{
            float syMoney = bmoney-outcomeOneMonth;
            topbudgetTv.setText("$"+syMoney);
        }
    }

    private void addLVHeaderView() {
        headerView = getLayoutInflater().inflate(R.layout.list_main_head, null);
        todayLv.addHeaderView(headerView);
        topOutTv = headerView.findViewById(R.id.list_main_head_out);
        topInTv = headerView.findViewById(R.id.list_main_head_income_num);
        topbudgetTv = headerView.findViewById(R.id.list_main_head_budget_num);
        topConTv = headerView.findViewById(R.id.list_main_head_todayIncomeOutcome);
        topShowIv = headerView.findViewById(R.id.list_main_head_map);

        topbudgetTv.setOnClickListener(this);
        headerView.setOnClickListener(this);
        topShowIv.setOnClickListener(this);
    }


    // 加载数据库数据
    private void loadDBData() {
        List<AccountBean> list = DBManager.getAccountListOneDayFromAccounttb(year, month, day);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.main_btn_record) {
            Intent it1 = new Intent(this, RecordActivity.class);  //跳转界面
            startActivity(it1);
        } else if (view.getId() == R.id.list_main_head_budget_num) {
            showBudgetDialog();
        } else if (view.getId() == R.id.main_btn_menu) {
            MoreDialog moreDialog= new MoreDialog(this);
            moreDialog.show();
            moreDialog.setDialogSize();
        } else if (view.getId() == R.id.main_iv_search) {
        logoutMenu(mainpage_Activity.this);
    }
    }

    private void showBudgetDialog() {
        BudgetDialog dialog = new BudgetDialog(this);
        Log.d("show", "showBudgetDialog: ");
        dialog.show();
        dialog.setDialogSize();
        dialog.setOnEnsureListener(money -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat("bmoney",money);
            editor.apply();
            float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
            float syMoney = money-outcomeOneMonth;
            topbudgetTv.setText("$"+syMoney);
        });
    }
    private void logoutMenu(mainpage_Activity mainpage_Activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainpage_Activity);
        builder.setTitle("LOGOUT");
        builder.setMessage("Are you sure you want to logout?");
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();
    }
}
