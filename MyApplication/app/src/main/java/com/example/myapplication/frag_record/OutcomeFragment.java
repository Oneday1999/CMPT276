package com.example.myapplication.frag_record;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.db.TypeBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OutcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OutcomeFragment extends BaseRecordFragment {
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("Other");
        typeIv.setImageResource(R.mipmap.icon_other);

    }
}