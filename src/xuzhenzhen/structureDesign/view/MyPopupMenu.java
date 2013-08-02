package xuzhenzhen.structureDesign.view;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyPopupMenu extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6828611876637275620L;
	private JMenuItem editItem;
	private JMenuItem upItem;
	private JMenuItem downItem;
	private JMenuItem foldItem;
	private JMenuItem unfoldItem;
	private JMenuItem deleteItem;
	private JMenuItem addChildItem;
	private JMenuItem addSiblingItem;
	public MyPopupMenu(){
		init();
	}
	public void init(){
		editItem=new JMenuItem("编辑");
		upItem=new JMenuItem("上移");
		downItem=new JMenuItem("下移");
		foldItem=new JMenuItem("折叠");
		unfoldItem=new JMenuItem("展开");
		deleteItem=new JMenuItem("删除");
		addChildItem=new JMenuItem("添加孩子");
		addSiblingItem=new JMenuItem("添加兄弟");
		add(editItem);
		addSeparator();
		add(upItem);
		add(downItem);
		add(foldItem);
		add(unfoldItem);
		addSeparator();
		add(deleteItem);
		addSeparator();
		add(addChildItem);
		add(addSiblingItem);
	}
	public void addListener(ActionListener l){
		editItem.addActionListener(l);
		upItem.addActionListener(l);
		downItem.addActionListener(l);
		foldItem.addActionListener(l);
		unfoldItem.addActionListener(l);
		deleteItem.addActionListener(l);
		addChildItem.addActionListener(l);
		addSiblingItem.addActionListener(l);
	}
}
