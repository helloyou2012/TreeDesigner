package xuzhenzhen.structureDesign.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import xuzhenzhen.structureDesign.control.ActionController;
import xuzhenzhen.structureDesign.control.Controller;
import xuzhenzhen.structureDesign.control.HelpController;
import xuzhenzhen.structureDesign.model.TreeList;

public class TreeFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8944043313080351878L;
	private TreeList tree;
	private TreeDesignPanel panel;
	private JMenuBar bar;
	public TreeFrame(){
		setSize(800, 640);
		setTitle("结构图编辑器-Version-1.0");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
	}
	public void init(){
		tree=new TreeList();
		panel=new TreeDesignPanel();
		bar=new JMenuBar();
		FileMenu fileMenu=new FileMenu();
		OperateMenu operateMenu=new OperateMenu();
		HelpMenu helpMenu=new HelpMenu();
		bar.add(fileMenu);
		bar.add(operateMenu);
		bar.add(helpMenu);
		helpMenu.addListener(new HelpController());
		ActionController actController=new ActionController(tree);
		fileMenu.addListener(actController);
		operateMenu.addListener(actController);
		Controller controler=new Controller(tree, panel);
		tree.addListener(controler);
		panel.addKeyListener(controler);
		panel.addMouseListener(controler);
		panel.addMouseMotionListener(controler);
		panel.addPopActionListener(actController);
		panel.addPopMouseListener(controler);
		addKeyListener(controler);
		setJMenuBar(bar);
		add(panel);
		addWindowListener(actController);
	}
}
