package xuzhenzhen.structureDesign.model;

import java.awt.Graphics;

public interface Tree {
	public TreePosition getRoot();
	public void setRoot(TreePosition p);
	public int getWidth();
	public int getHeight();
	public boolean isEmpty();
	public TreePosition getPointer();
	public void setPointer(int x,int y);
	public void addChild(Object elem);
	public void addSibling(Object elem);
	public Object deletePointer();
	public void updatePointer(Object elem);
	public void moveUp();
	public void moveDown();
	public void movePointerUp();
	public void movePointerDown();
	public void movePointerLeft();
	public void movePointerRight();
	public void show(Graphics g);
}
