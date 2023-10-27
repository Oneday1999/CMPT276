package com.example.myapplication.frag_record;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.AccountBean;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.db.TypeBean;
import com.example.myapplication.utils.KeyBoardUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaseRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseRecordFragment extends Fragment {
    KeyboardView keyboardView;
    EditText moneyEt;
    ImageView typeIv;
    TextView typeTv,notesTv,timeTv;
    GridView typeGv;
    List<TypeBean> typeList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TypeBaseAdapter typeBaseAdapter;
    public TypeBaseAdapter adapter;
    AccountBean accountBean;


    public BaseRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OutcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaseRecordFragment newInstance(String param1, String param2) {
        BaseRecordFragment fragment = new BaseRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        accountBean = new AccountBean();
        accountBean.setTypename("Other");
        accountBean.setImageId(R.mipmap.icon_other);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_outcome, container, false);
        initView(view);
        setInitTime();
        loadDataToGV();
        setGVListenr();
        return view;
    }

    private void setInitTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String time = sdf.format(date);
        timeTv.setText(time);
        accountBean.setTime(time);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        accountBean.setYear(year);
        accountBean.setMoney(month);
        accountBean.setDay(day);
    }


    private void setGVListenr() {
        typeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.selectPos = position;
                adapter.notifyDataSetChanged();
                TypeBean typeBean = typeList.get(position);
                String typename = typeBean.getTypename();
                typeTv.setText(typename);
                accountBean.setTypename(typename);
                int imgeID = typeBean.getImageId();
                typeIv.setImageResource(imgeID);
                accountBean.setImageId(imgeID);
            }
        });
    }

    public void loadDataToGV(){
        typeList = new ArrayList<>();
        adapter = new TypeBaseAdapter(getContext(), typeList);
        typeGv.setAdapter(adapter);
    }
    private void initView(View view){
        keyboardView = view.findViewById(R.id.fragment_record_keyboard);
        moneyEt = view.findViewById(R.id.fragment_record_money);
        typeIv = view.findViewById(R.id.fragment_record_iv);
        typeGv = view.findViewById(R.id.fragment_record_gv);
        typeTv = view.findViewById(R.id.fragment_record_type);
        notesTv = view.findViewById(R.id.fragment_record_notes);
        timeTv = view.findViewById(R.id.fragment_record_time);
        //Display the keyboard
        KeyBoardUtils boardUtils = new KeyBoardUtils(keyboardView,moneyEt);
        boardUtils.showKeyboard();
        //set and check the button was clicked
        boardUtils.setOnEnsureListener(new KeyBoardUtils.OnEnsureListener() {
            @Override
            public void onEnsure() {
                //click the button, get the record and save to database, then return the last page
                String moneyStr = moneyEt.getText().toString();
                TextUtils.isEmpty(moneyStr);
                if(!TextUtils.isEmpty(moneyStr) || moneyStr.equals("0")){
                    getActivity().finish();
                    return;
                }
                float money = Float.parseFloat(moneyStr);
                accountBean.setMoney(money);
                saveAccountToDB();
                getActivity().finish();
            }
        });

        }

    public void saveAccountToDB() {

    }


}


