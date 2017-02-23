package yhcloud.com.myapplication.bean;

import yhcloud.com.myapplication.tree.TreeNodeId;
import yhcloud.com.myapplication.tree.TreeNodeLabel;
import yhcloud.com.myapplication.tree.TreeNodePid;

public class FileBean
{
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	private long length;
	private String desc;

	public FileBean(int _id, int parentId, String name)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
	}

}
