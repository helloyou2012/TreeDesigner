package xuzhenzhen.structureDesign.model;

import java.awt.Graphics;

public interface TreePosition extends Position ,java.io.Serializable{
	public boolean hasParent();//判断是否有父亲
	public TreePosition getParent();//取得父亲节点
	public void setParent(TreePosition p);//设置父亲节点
	public boolean hasChild();//判断是否有孩子
	public boolean hasSibling();//判断是否有兄弟
	public boolean isFirstChild();//判断是否为第一个孩子
	public TreePosition getFirstChild();//取得第一个孩子
	public void setFirstChild(TreePosition p);//设置第一个孩子
	public TreePosition getNextSibling();//取得下一个兄弟
	public void setNextSibling(TreePosition p);//设置下一个兄弟 
	public TreePosition getPreSibling();//取得上一个兄弟
	public int getLeafCount();//取得叶子节点的个数
	public void updateLeafCount();//更新叶子节点
	public TreePosition getByPosition(int x,int y);//获取位于此坐标的节点
	public TreePosition deleteMe();//在树中删除此子树
	public void showMe(Graphics g);

}
