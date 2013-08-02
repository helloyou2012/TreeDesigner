package xuzhenzhen.structureDesign.view;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class OperateMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9064115368653169044L;
	private JMenuItem editItem;
	private JMenuItem upItem;
	private JMenuItem downItem;
	private JMenuItem foldItem;
	private JMenuItem unfoldItem;
	private JMenuItem deleteItem;
	private JMenuItem addChildItem;
	private JMenuItem addSiblingItem;
	public OperateMenu(){
		super("操作");
		setMnemonic('P');
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
		editItem.setAccelerator(KeyStroke.getKeyStroke('I', java.awt.Event.CTRL_MASK, false));
		upItem.setAccelerator(KeyStroke.getKeyStroke('U', java.awt.Event.CTRL_MASK, false));
		downItem.setAccelerator(KeyStroke.getKeyStroke('D', java.awt.Event.CTRL_MASK, false));
		foldItem.setAccelerator(KeyStroke.getKeyStroke('F', java.awt.Event.CTRL_MASK, false));
		unfoldItem.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.Event.CTRL_MASK, false));
		deleteItem.setAccelerator(KeyStroke.getKeyStroke('L', java.awt.Event.CTRL_MASK, false));
		addChildItem.setAccelerator(KeyStroke.getKeyStroke('C', java.awt.Event.CTRL_MASK, false));
		addSiblingItem.setAccelerator(KeyStroke.getKeyStroke('B', java.awt.Event.CTRL_MASK, false));
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
