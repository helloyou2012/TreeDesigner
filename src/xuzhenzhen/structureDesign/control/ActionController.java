package xuzhenzhen.structureDesign.control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import xuzhenzhen.structureDesign.model.TreeList;
import xuzhenzhen.structureDesign.model.TreePosition;

public class ActionController extends WindowAdapter implements ActionListener{
	private TreeList tree;
	public ActionController(TreeList tree) {
		super();
		this.tree = tree;
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		super.windowClosing(arg0);
		int result=JOptionPane.showConfirmDialog(null, "是否退出软件？", "提示",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(result==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String event=e.getActionCommand();
		if(event.equals("打开")){
			dealOpen();return;
		}
		if(event.equals("新建")){
			dealBuild();return;
		}
		if(event.equals("保存")){
			saveTree();return;
		}
		if(event.equals("导出")){
			dealExport();return;
		}
		if(event.equals("退出")){
			int result=JOptionPane.showConfirmDialog(null, "是否退出软件？", "提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION){
				System.exit(0);
			}			
		}
		if(event.equals("编辑")){
			dealEdit();return;
		}
		if(event.equals("上移")){
			dealUp();return;
		}
		if(event.equals("下移")){
			dealDown();return;
		}
		if(event.equals("折叠")){
			dealFold();return;
		}
		if(event.equals("展开")){
			dealUnFold();return;
		}
		if(event.equals("删除")){
			dealDelete();return;
		}
		if(event.equals("添加孩子")){
			dealAddChild();return;
		}
		if(event.equals("添加兄弟")){
			dealAddSibling();return;
		}
	}
	public void dealOpen(){
		if(tree.isEmpty()){
			reLoadTree();
		}
		else{
			int result=JOptionPane.showConfirmDialog(null, "是否保存当前的设计？", "提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION){
				saveTree();
			}
			else{
				reLoadTree();
			}
		}
	}
	public void reLoadTree(){
		JFileChooser fileChooser=new JFileChooser("D:\\");
		fileChooser.addChoosableFileFilter(new TreeFileFilter("tree"));
		int result=fileChooser.showOpenDialog(null);
		if(result==JFileChooser.APPROVE_OPTION){
			File file=fileChooser.getSelectedFile();
			try {
				FileInputStream stream=new FileInputStream(file);
				ObjectInputStream objIn=new ObjectInputStream(stream);
				TreePosition root=(TreePosition)objIn.readObject();
				objIn.close();
				stream.close();
				tree.setRoot(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void saveTree(){
		JFileChooser fileChooser=new JFileChooser("D:\\");
		fileChooser.addChoosableFileFilter(new TreeFileFilter("tree"));
		int result=fileChooser.showSaveDialog(null);
		if(result==JFileChooser.APPROVE_OPTION){
			File file=fileChooser.getSelectedFile();
			try {
				FileOutputStream stream=new FileOutputStream(file);
				ObjectOutputStream objOut=new ObjectOutputStream(stream);
				objOut.writeObject(tree.getRoot());
				objOut.flush();
				objOut.close();
				stream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void dealBuild(){
		if(!tree.isEmpty()){
			int result=JOptionPane.showConfirmDialog(null, "是否保存当前的设计？", "提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION){
				saveTree();
			}
			else{
				tree.setRoot(null);
			}
		}
	}
	public void dealExport(){
		try {
			if(tree.isEmpty()){
				System.out.println("无法输出：树为空！");
				return;
			}
			Iterator<ImageWriter> writers=ImageIO.getImageWritersByFormatName("jpeg");
			ImageWriter writer=(ImageWriter)writers.next();
			File file=new File("mytree.jpg");
			ImageOutputStream stream=ImageIO.createImageOutputStream(file);
			writer.setOutput(stream);
			BufferedImage bi=new BufferedImage(tree.getWidth(), tree.getHeight(),
					BufferedImage.TYPE_INT_BGR);
			Graphics2D g=bi.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0,tree.getWidth()+3, tree.getHeight()+3);
			tree.show(g);
			writer.write(bi);
			stream.close();
			JOptionPane.showMessageDialog(null, "文件已经输出到运行目录名为：mytree.jpg",
					"提示", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dealEdit(){
		if(tree.getPointer()!=null){
			String str=(String)tree.getPointer().getElem();
			String newstr=JOptionPane.showInputDialog("请输入条目：", str);
			if(newstr!=null&&!newstr.equals(str)){
				tree.updatePointer(newstr);
			}
		}
	}
	public void dealUp(){
		tree.moveUp();
	}
	public void dealDown(){
		tree.moveDown();
	}
	public void dealFold(){
		tree.setFold(true);
	}
	public void dealUnFold(){
		tree.setFold(false);
	}
	public void dealDelete(){
		if(tree.getPointer()!=null){
			tree.deletePointer();
		}
	}
	public void dealAddChild(){
		String str=JOptionPane.showInputDialog("请输入条目：","");
		if(str!=null){
			tree.addChild(str);
		}
	}
	public void dealAddSibling(){
		if(tree.getPointer()!=null){
			String str=JOptionPane.showInputDialog("请输入条目：","");
			if(str!=null){
				tree.addSibling(str);
			}
		}
	}

}
