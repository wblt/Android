package yhcloud.com.recyc;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import yhcloud.com.recyc.viewholder.citytree.CityBean;
import yhcloud.com.recyc.viewholder.citytree.OneItem;
import yhcloud.com.recyc.adpater.TreeRecyclerViewAdapter;
import yhcloud.com.recyc.adpater.TreeRecyclerViewType;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<OneItem> treeBeen1 = new ArrayList<>();//一级
        List<CityBean> cityBeen = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);
        for (int i = 0; i < cityBeen.size(); i++) {
            treeBeen1.add(new OneItem(cityBeen.get(i)));
        }
        recyclerView.setAdapter(new TreeRecyclerViewAdapter<>(this, treeBeen1, TreeRecyclerViewType.SHOW_DEFUTAL));
    }

}
