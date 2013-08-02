package xuzhenzhen.structureDesign.view;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FileMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8797966109990884260L;
	private JMenuItem openItem;
	private JMenuItem newItem;
	private JMenuItem saveItem;
	private JMenuItem exportItem;
	private JMenuItem exitItem;
	public FileMenu(){
		super("文件");
		init();
	}
	public void init(){
		openItem=new JMenuItem("打开");
		newItem=new JMenuItem("新建");
		saveItem=new JMenuItem("保存");
		exportItem=new JMenuItem("导出");
		exitItem=new JMenuItem("退出");
		setMnemonic('F');
		openItem.setMnemonic('O');
		saveItem.setMnemonic('S');
		exportItem.setMnemonic('E');
		exitItem.setMnemonic('X');
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.Event.CTRL_MASK, false));
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK, false));
		exportItem.setAccelerator(KeyStroke.getKeyStroke('E', java.awt.Event.CTRL_MASK, false));
		exitItem.setAccelerator(KeyStroke.getKeyStroke('X', java.awt.Event.CTRL_MASK, false));
		add(openItem);
		add(newItem);
		add(saveItem);
		add(exportItem);
		addSeparator();
		add(exitItem);
	}
	public void addListener(ActionListener l){
		openItem.addActionListener(l);
		newItem.addActionListener(l);
		saveItem.addActionListener(l);
		exportItem.addActionListener(l);
		exitItem.addActionListener(l);
	}
}
