package xuzhenzhen.structureDesign.control;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;

import xuzhenzhen.structureDesign.listener.TreeListener;
import xuzhenzhen.structureDesign.model.TreeList;
import xuzhenzhen.structureDesign.view.TreeDesignPanel;

public class Controller extends KeyAdapter implements TreeListener,MouseListener,MouseMotionListener{
	private TreeList tree;
	private TreeDesignPanel panel;
	private int x,y;
	public Controller(TreeList tree, TreeDesignPanel panel) {
		super();
		this.tree = tree;
		this.panel = panel;
	}
	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		super.keyPressed(key);
		System.out.println("按下了键");
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			tree.movePointerUp();
			break;
		case KeyEvent.VK_DOWN:
			tree.movePointerDown();
			break;
		case KeyEvent.VK_LEFT:
			tree.movePointerLeft();
			break;
		case KeyEvent.VK_RIGHT:
			tree.movePointerRight();
			break;
		default:
			break;
		}
	}
	@Override
	public void treeChange(TreeList tree) {
		// TODO Auto-generated method stub
		System.out.println("改变了树 ");
		panel.diplay(tree);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getModifiers()==InputEvent.BUTTON1_MASK){//左键
			if(e.getClickCount()==1){//左键一次
				leftClickOne(e);
			}
			if(e.getClickCount()==2){//左键两次
				leftClickTwo(e);
			}
		}
		if(e.getModifiers()==InputEvent.BUTTON3_MASK){
			System.out.println("右键");
			panel.dealPopup(e);
		}
	}
	public void leftClickOne(MouseEvent e){//左键一次
		if(tree!=null){
			tree.setPointer(e.getX()-panel.getTransX(), e.getY()-panel.getTransY());
		}
	}
	public void leftClickTwo(MouseEvent e){//左键两次
		if(tree.getPointer()!=null){
			String str=(String)tree.getPointer().getElem();
			String newstr=JOptionPane.showInputDialog("请输入条目：", str);
			if(newstr!=null&&!newstr.equals(str)){
				tree.updatePointer(newstr);
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(panel!=null){
			panel.addTransX(e.getX()-x);
			panel.addTransY(e.getY()-y);
			x=e.getX();
			y=e.getY();
			panel.diplay(tree);
		}
	}
	
}
