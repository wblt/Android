package yhcloud.com.recyc.viewholder.citytree;

import yhcloud.com.recyc.R;
import yhcloud.com.recyc.viewholder.TreeItem;
import yhcloud.com.recyc.viewholder.TreeParentItem;
import yhcloud.com.recyc.viewholder.ViewHolder;

/**
 */
public class ThreeItem extends TreeItem<CityBean.CitysBean.AreasBean> {


    public ThreeItem(CityBean.CitysBean.AreasBean data, TreeParentItem parentItem) {
        super(data, parentItem);
    }

    @Override
    public int initLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }
}
