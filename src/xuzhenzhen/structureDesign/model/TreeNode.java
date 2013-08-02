package xuzhenzhen.structureDesign.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import xuzhenzhen.structureDesign.global.GlobalVar;

public class TreeNode implements TreePosition{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2459119123720872851L;
	protected int x;
	protected int y;
	protected int width;
	protected boolean fold;
	protected int leafCount;
	protected Object element;
	protected TreePosition parent;
	protected TreePosition firstChild;
	protected TreePosition nextSibling;

	public TreeNode(Object e, TreePosition p,
			TreePosition child, TreePosition sibling) {
		super();
		x=y=0; width=0; leafCount=0;
		fold=false;
		parent=firstChild=nextSibling=null;
		element=e;
		if(child!=null)setFirstChild(child);
		if(sibling!=null)setNextSibling(sibling);
		updateWidth();
		updateLeafCount();
	}
	public TreeNode(Object elem){
		this(elem,null,null,null);
	}

	@Override
	public Object getElem() {
		// TODO Auto-generated method stub
		return element;
	}

	@Override
	public Object setElem(Object element) {
		// TODO Auto-generated method stub
		Object temp=this.element;
		this.element=element;
		updateWidth();
		return temp;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return getLeafCount()*GlobalVar.hSize;
	}
	@Override
	public boolean hasParent() {
		// TODO Auto-generated method stub
		return parent!=null;
	}

	@Override
	public TreePosition getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setParent(TreePosition p) {
		// TODO Auto-generated method stub
		if(p!=null)	parent=p;
	}
	
	@Override
	public boolean hasChild() {
		// TODO Auto-generated method stub
		return firstChild!=null;
	}

	@Override
	public TreePosition getFirstChild() {
		// TODO Auto-generated method stub
		return firstChild;
	}

	@Override
	public void setFirstChild(TreePosition p) {
		// TODO Auto-generated method stub
		if(p==null){
			firstChild=p;return;
		}
		if(hasChild()){
			TreePosition first=getFirstChild();
			firstChild=p;
			p.setParent(this);
			p.setNextSibling(first);
		}
		else{
			firstChild=p;
			p.setParent(this);
		}
		p.updateLeafCount();
	}

	@Override
	public TreePosition getNextSibling() {
		// TODO Auto-generated method stub
		return nextSibling;
	}

	@Override
	public void setNextSibling(TreePosition p) {
		// TODO Auto-generated method stub
		if(p==null){
			nextSibling=p;return;
		}
		if(hasSibling()){
			TreePosition sibling=getNextSibling();
			nextSibling=p;
			p.setParent(getParent());
			p.setNextSibling(sibling);
		}
		else{
			this.nextSibling=p;
			p.setParent(getParent());			
		}
		p.updateLeafCount();
	}

	@Override
	public boolean hasSibling() {
		// TODO Auto-generated method stub
		return nextSibling!=null;
	}

	@Override
	public void updateX() {
		// TODO Auto-generated method stub
		x=hasParent()?parent.getWidth()+parent.getX():0;
		TreePosition pointer=getFirstChild();
		while(pointer!=null){
			pointer.updateX();
			pointer=pointer.getNextSibling();
		}
	}

	@Override
	public void updateY() {
		// TODO Auto-generated method stub
		if(!hasParent()){
			y=0;
		}
		else{
			TreePosition p=getPreSibling();
			if(p==null)y=parent.getY();
			else{
				y=p.getY()+p.getHeight();
			}
		}
		TreePosition temp=getFirstChild();
		while(temp!=null){
			temp.updateY();
			temp=temp.getNextSibling();
		}
	}

	@Override
	public void updateWidth() {
		// TODO Auto-generated method stub
		Font font=new Font(null, Font.PLAIN, GlobalVar.fontSize);
		FontMetrics metrics=new FontMetrics(font) {
			private static final long serialVersionUID = 2340153443259259454L;};
		Rectangle2D bound=metrics.getStringBounds((String)element,null);
		width=(int)bound.getWidth()+GlobalVar.parenWidth;
	}
	
	@Override
	public boolean isFolded() {
		// TODO Auto-generated method stub
		return fold;
	}

	@Override
	public void setFold(boolean fold) {
		// TODO Auto-generated method stub
		this.fold=fold;
	}

	@Override
	public int getLeafCount() {
		// TODO Auto-generated method stub
		return leafCount;
	}

	@Override
	public void updateLeafCount() {
		// TODO Auto-generated method stub
		if(!hasChild())leafCount=1;
		else{
			leafCount=0;
			TreePosition p=firstChild;
			while(p!=null){
				leafCount+=p.getLeafCount();
				p=p.getNextSibling();
			}
		}
		if(hasParent())parent.updateLeafCount();
	}

	@Override
	public boolean isFirstChild() {
		// TODO Auto-generated method stub
		if(!hasParent())return true;
		else{
			if(parent.getFirstChild().equals(this))return true;
		}
		return false;
	}

	@Override
	public TreePosition getPreSibling() {
		// TODO Auto-generated method stub
		if(hasParent()&&!isFirstChild()){
			TreePosition p=parent.getFirstChild();
			while(p.getNextSibling()!=this)p=p.getNextSibling();
			return p;
		}
		return null;
	}

	@Override
	public void showMe(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(x, y, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawString((String)element, x, y+(getHeight()+GlobalVar.fontSize)/2);
		if(hasChild()){
			showParen(g);
			if(!isFolded()){
				TreePosition p=getFirstChild();
				while(p!=null){
					p.showMe(g);
					p=p.getNextSibling();
				}
			}
		}
	}
	public void showParen(Graphics g){
		int h0=GlobalVar.hSize/2;
		int x0=x+getWidth()-h0;
		int y0=y+getHeight()/2;
		if(isFolded()){
			g.setColor(Color.red);
			g.drawRect(x0-h0, y0-h0,GlobalVar.hSize, GlobalVar.hSize);
		}
		else{
			g.setColor(Color.black);
			g.drawArc(x0, y,h0,h0,180,-45);
			g.drawLine(x0, y+h0/2,x0,y0-h0/2);
			g.drawArc(x0-h0,y0-h0, h0, h0, 0, -90);
			g.drawArc(x0-h0, y0, h0, h0, 0, 90);
			g.drawLine(x0, y0+h0/2, x0, y+getHeight()-h0/2);
			g.drawArc(x0, y+getHeight()-h0, h0, h0, 180, 45);
		}
	}

	@Override
	public TreePosition getByPosition(int x, int y) {
		// TODO Auto-generated method stub
		if(getX()<x&&x<getX()+getWidth()&&y>getY()&&y<getY()+getHeight())
			return this;
		return null;
	}

	@Override
	public TreePosition deleteMe() {
		// TODO Auto-generated method stub
		if(isFirstChild()){
			if(!hasSibling()){
				parent.setFirstChild(null);
				parent=null;
			}
			else{
				TreePosition p=getParent();
				TreePosition child=getNextSibling();
				p.setFirstChild(null);
				p.setFirstChild(child);
				parent=null;
				nextSibling=null;
				p.updateLeafCount();
			}
		}
		else{
			TreePosition pre=getPreSibling();
			TreePosition next=getNextSibling();
			pre.setNextSibling(null);
			pre.setNextSibling(next);
			parent.updateLeafCount();
			parent=null;
			nextSibling=null;
		}
		return this;
	}

}
