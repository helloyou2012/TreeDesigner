package xuzhenzhen.structureDesign.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class HelpController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("帮助")){
			dealHelp();return;
		}
		if(e.getActionCommand().equals("关于")){
			dealAbout();return;
		}
	}
	public void dealHelp(){
		String str="抱歉暂时没有帮助！\n"+
		           "详情请参看“关于”菜单。";
		JOptionPane.showMessageDialog(null, str, "帮助",JOptionPane.PLAIN_MESSAGE);
	}
	public void dealAbout(){
		String str="Author：徐振震\n"+
		           "Blog：http://blog.sina.com.cn/zhenzhenhello\n"+
		           "Mail：helloyou2012@yeah.net\n"+      
		           "Call：18817332855\n"+
		           "QQ：865702942\n";
		JOptionPane.showMessageDialog(null, str, "关于",JOptionPane.PLAIN_MESSAGE);
	}

}
