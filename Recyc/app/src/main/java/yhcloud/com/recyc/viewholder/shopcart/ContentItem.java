package yhcloud.com.recyc.viewholder.shopcart;

import yhcloud.com.recyc.R;
import yhcloud.com.recyc.viewholder.TreeItem;
import yhcloud.com.recyc.viewholder.TreeParentItem;
import yhcloud.com.recyc.viewholder.ViewHolder;

/**
 * Created by baozi on 2016/12/22.
 */

public class ContentItem extends TreeItem<ShopListBean> {


    public ContentItem(ShopListBean data, TreeParentItem parentItem) {
        super(data, parentItem);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_shopcart_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setChecked(R.id.cb_ischeck, getData().isCheck());
    }

    @Override
    public void onClickChange(TreeItem treeItem) {
        getData().setCheck(!getData().isCheck());
        parentItem.onUpdate();
        mTreeItemManager.notifyDataSetChanged();
    }
}
