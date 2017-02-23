package yhcloud.com.recyc.viewholder.citytree;

import yhcloud.com.recyc.R;
import yhcloud.com.recyc.viewholder.TreeParentItem;
import yhcloud.com.recyc.viewholder.TreeItem;
import yhcloud.com.recyc.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneItem extends TreeParentItem<CityBean> {


    public OneItem(CityBean data) {
        super(data);
    }

    @Override
    protected List<TreeItem> initChildsList(CityBean data) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        List<CityBean.CitysBean> citys = data.getCitys();
        if (null == citys) {
            return null;
        }
        for (int i = 0; i < citys.size(); i++) {
            TwoItem twoItem = new TwoItem(citys.get(i), this);
            if (citys.get(i).getCityName().equals("朝阳区")) {
                twoItem.setCanChangeExpand(false, true);
            }
            treeItems.add(twoItem);
        }
        return treeItems;
    }

    @Override
    public int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }
}
