package xuzhenzhen.structureDesign.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import xuzhenzhen.structureDesign.global.GlobalVar;
import xuzhenzhen.structureDesign.model.TreeList;

public class TreeDesignPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9199663216630443119L;
	private TreeList tree;
	private MyPopupMenu popMenu;
	private int transX;
	private int transY;
	public TreeDesignPanel(){
		this.transX=this.transY=0;
		popMenu=new MyPopupMenu();
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(transX,transY);
		if(tree!=null){
			Font font=new Font("default", Font.PLAIN, GlobalVar.fontSize);
			g.setFont(font);
			tree.show(g);
		}
		g.translate(-transX, -transY);
	}
	public void diplay(TreeList t){
		System.out.println("面板显示");
		tree=t;
		repaint();
	}
	public void dealPopup(MouseEvent e){
		popMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	public void addPopActionListener(ActionListener l){
		popMenu.addListener(l);
	}
	public void addPopMouseListener(MouseListener l){
		popMenu.addMouseListener(l);
	}
	public void addTransX(int x){
		this.transX+=x;
	}
	public void addTransY(int y){
		this.transY+=y;
	}
	public int getTransX(){
		return this.transX;
	}
	public int getTransY(){
		return this.transY;
	}
}
