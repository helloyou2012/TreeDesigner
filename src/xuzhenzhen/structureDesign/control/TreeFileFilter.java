package xuzhenzhen.structureDesign.control;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TreeFileFilter extends FileFilter{
	private String ext;

	public TreeFileFilter(String ext) {
		super();
		this.ext = ext;
	}

	@Override
	public boolean accept(File file) {
		// TODO Auto-generated method stub
		if(file.isDirectory())
			return true;
		String fileName=file.getName();
		int index=fileName.lastIndexOf('.');
		if(index>0&&index<fileName.length()-1){
			String extension=fileName.substring(index+1).toLowerCase();
			if(extension.equals(ext))
				return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		if(ext.equals("tree"))
			return "结构文件(*.tree)";
		return "";
	}

}
