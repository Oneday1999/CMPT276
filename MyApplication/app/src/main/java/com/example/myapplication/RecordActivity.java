package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.adapter.RecordPagerAdapter;
import com.example.myapplication.frag_record.OutcomeFragment;
import com.example.myapplication.frag_record.IncomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_vp);

        initPager();
    }

    private void initPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        OutcomeFragment outFrag = new OutcomeFragment();
        IncomeFragment inFrag = new IncomeFragment();
        fragmentList.add(outFrag);
        fragmentList.add(inFrag);

        RecordPagerAdapter pagerAdapter = new RecordPagerAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public void onClick(View view) {
        finish();
    }
}