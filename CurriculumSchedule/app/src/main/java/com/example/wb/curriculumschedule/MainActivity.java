package com.example.wb.curriculumschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCurriculumList;
    private CurriculumAdapter ca;
    private ArrayList<CurriculumItemBean> mBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCurriculumList = (RecyclerView) findViewById(R.id.rv_curriculum_list);

        initData();

        if (null == ca) {
            ca = new CurriculumAdapter(this, mBeen);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (2 == mBeen.get(position).getType()) {
                        return 6;
                    }
                    return 1;
                }
            });
            rvCurriculumList.setLayoutManager(layoutManager);
            rvCurriculumList.addItemDecoration(new DividerGridItemDecoration(this));
            rvCurriculumList.setAdapter(ca);
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mBeen = new ArrayList<>();
        CurriculumItemBean curriculumItemBean;
        //处理表格头部
        for (int j = 0; j < 6; j++) {
            curriculumItemBean = new CurriculumItemBean();
            curriculumItemBean.setType(1);
            switch (j) {
                case 1:
                    curriculumItemBean.setTitle("星期一");
                    break;
                case 2:
                    curriculumItemBean.setTitle("星期二");
                    break;
                case 3:
                    curriculumItemBean.setTitle("星期三");
                    break;
                case 4:
                    curriculumItemBean.setTitle("星期四");
                    break;
                case 5:
                    curriculumItemBean.setTitle("星期五");
                    break;
                default:
                    curriculumItemBean.setTitle("星期天");
                    break;
            }
            mBeen.add(curriculumItemBean);
        }
        for (int i = 0; i < 50; i++) {
            switch (i) {
                //处理午休数据
                case 5:
                curriculumItemBean = new CurriculumItemBean();
                curriculumItemBean.setTitle("午 休");
                curriculumItemBean.setType(2);
                mBeen.add(curriculumItemBean);
                break;
                //处理其他列显示数据
                default:
                curriculumItemBean = new CurriculumItemBean();
                curriculumItemBean.setStartTime("2017-03-23");
                curriculumItemBean.setTitle("2017-03-23");
                curriculumItemBean.setEndTime("2017-03-23");
                mBeen.add(curriculumItemBean);
                break;
            }
        }
    }


}
