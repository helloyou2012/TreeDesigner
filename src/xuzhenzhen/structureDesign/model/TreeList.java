package xuzhenzhen.structureDesign.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import xuzhenzhen.structureDesign.listener.TreeListener;

public class TreeList implements Tree{
	private TreePosition root;
	private TreePosition pointer;
	private TreeListener listener;

	public TreeList(TreePosition root) {
		super();
		this.root = root;
		this.pointer=null;
	}
	public TreeList(){
		this(null);
	}
	public void addListener(TreeListener l){
		listener=l;
	}
	@Override
	public TreePosition getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void setRoot(TreePosition p) {
		// TODO Auto-generated method stub
		System.out.println("加载了新树……");
		root=p;
		pointer=null;
		if(listener!=null)listener.treeChange(this);
	}

	@Override
	public TreePosition getPointer() {
		// TODO Auto-generated method stub
		return pointer;
	}

	@Override
	public void setPointer(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("点坐标获取节点");
		if(!isEmpty()){
			pointer=null;
			LinkedList<TreePosition> list=new LinkedList<TreePosition>();
			list.addLast(root);
			while(!list.isEmpty()){
				TreePosition temp=list.removeFirst();
				pointer=temp.getByPosition(x, y);
				if(pointer!=null)break;
				temp=temp.getFirstChild();
				while(temp!=null){
					list.addLast(temp);
					temp=temp.getNextSibling();
				}
			}
			if(listener!=null)listener.treeChange(this);
		}
	}
	public void setFold(boolean fold){
		if(pointer!=null){
			pointer.setFold(fold);
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root==null;
	}
	@Override
	public void addChild(Object elem) {
		// TODO Auto-generated method stub
		System.out.println("添加了孩子");
		TreePosition child=new TreeNode(elem, null, null, null);
		if(root==null){
			setRoot(child);return;
		}
		if(pointer!=null&&elem!=null){
			pointer.setFirstChild(child);
			root.updateX();
			root.updateY();
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public void addSibling(Object elem) {
		// TODO Auto-generated method stub
		System.out.println("添加了兄弟");
		if(pointer!=null&&elem!=null){
			TreePosition sibling=new TreeNode(elem, null, null, null);
			pointer.setNextSibling(sibling);
			root.updateX();
			root.updateY();
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public Object deletePointer() {
		// TODO Auto-generated method stub
		System.out.println("删除了节点");
		Object obj=null;
		if(pointer!=null){
			obj=pointer.getElem();
			if(pointer==root){
				root=null;
				pointer=null;
			}
			else{
				pointer.deleteMe();
				pointer=null;
				root.updateX();
				root.updateY();
			}
			if(listener!=null)listener.treeChange(this);
		}
		return obj;
	}
	@Override
	public void updatePointer(Object elem) {
		// TODO Auto-generated method stub
		System.out.println("更新了节点");
		if(pointer!=null&&elem!=null){
			pointer.setElem(elem);
			root.updateX();
			//root.updateY();
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		System.out.println("节点向上移动");
		if(pointer!=null){
			TreePosition pre=pointer.getPreSibling();
			if(pre!=null){
				pre=pre.deleteMe();
				System.out.println("断绝关系");
				pointer.setNextSibling(pre);
				root.updateX();
				root.updateY();
				if(listener!=null)listener.treeChange(this);
			}
		}
	}
	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		System.out.println("节点向下移动 ");
		if(pointer!=null){
			TreePosition next=pointer.getNextSibling();
			if(next!=null){
				pointer=pointer.deleteMe();
				next.setNextSibling(pointer);
				root.updateX();
				root.updateY();
				if(listener!=null)listener.treeChange(this);
			}
		}
	}
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("显示树");
		if(!isEmpty()){
			root.showMe(g);
			if(pointer!=null){
				System.out.println("指针显示……………………………………");
				g.setColor(Color.blue);
				g.drawRect(pointer.getX(), pointer.getY(), pointer.getWidth(), pointer.getHeight());
			}
		}
	}
	@Override
	public void movePointerUp() {
		// TODO Auto-generated method stub
		System.out.println("指针向上移动");
		if(pointer!=null){
			TreePosition p=pointer.getPreSibling();
			pointer=p;
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public void movePointerDown() {
		// TODO Auto-generated method stub
		System.out.println("指针向下移动");
		if(pointer!=null){
			TreePosition p=pointer.getNextSibling();
			pointer=p;
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public void movePointerLeft() {
		// TODO Auto-generated method stub
		System.out.println("指针向左移动");
		if(pointer!=null){
			TreePosition p=pointer.getParent();
			pointer=p;
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public void movePointerRight() {
		// TODO Auto-generated method stub
		System.out.println("指针向右移动");
		if(pointer==null){
			pointer=root;
			if(listener!=null)listener.treeChange(this);
		}
		else{
			TreePosition p=pointer.getFirstChild();
			pointer=p;
			if(listener!=null)listener.treeChange(this);
		}
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		int width=0;
		if(!isEmpty()){
			LinkedList<TreePosition> queue=new LinkedList<TreePosition>();
			queue.addLast(root);
			while(!queue.isEmpty()){
				TreePosition v=queue.removeFirst();
				int w=v.getX()+v.getWidth();
				if(w>width)width=w;
				TreePosition p=v.getFirstChild();
				while(p!=null){
					queue.addLast(p);
					p=p.getNextSibling();
				}
			}
		}
		return width;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		if(!isEmpty())
			return root.getHeight();
		return 0;
	}

}
