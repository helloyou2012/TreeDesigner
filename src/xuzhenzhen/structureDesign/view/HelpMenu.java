package xuzhenzhen.structureDesign.view;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class HelpMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3286745712329532205L;
	private JMenuItem helpItem;
	private JMenuItem aboutItem;
	public HelpMenu(){
		super("帮助选项");
		init();
	}
	public void init(){
		helpItem=new JMenuItem("帮助");
		aboutItem=new JMenuItem("关于");
		helpItem.setAccelerator(KeyStroke.getKeyStroke('H', java.awt.Event.CTRL_MASK, false));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', java.awt.Event.CTRL_MASK, false));
		add(helpItem);
		add(aboutItem);
	}
	public void addListener(ActionListener l){
		helpItem.addActionListener(l);
		aboutItem.addActionListener(l);
	}
}
