package yhcloud.com.drawerlayoutdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private List<String> list;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initData();
        homeAdapter = new HomeAdapter(list,mContext);
        recyclerView = (RecyclerView) findViewById(R.id.recyc_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(homeAdapter);
    }

    private void initData()
    {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            list.add("" + (char) i);
        }
    }


}
