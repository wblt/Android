package yhcloud.com.recyc.viewholder.citytree;

import yhcloud.com.recyc.R;
import yhcloud.com.recyc.viewholder.TreeParentItem;
import yhcloud.com.recyc.viewholder.TreeItem;
import yhcloud.com.recyc.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TwoItem extends TreeParentItem<CityBean.CitysBean> {

    public TwoItem(CityBean.CitysBean data, TreeParentItem parentItem) {
        super(data, parentItem);
    }

    @Override
    protected List<TreeItem> initChildsList(CityBean.CitysBean data) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        List<CityBean.CitysBean.AreasBean> citys = data.getAreas();
        if (citys == null) {
            return null;
        }
        for (int i = 0; i < citys.size(); i++) {
            ThreeItem threeItem = new ThreeItem(citys.get(i), this);
            treeItems.add(threeItem);
        }
        return treeItems;
    }

    @Override
    public int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getCityName());
    }
}
