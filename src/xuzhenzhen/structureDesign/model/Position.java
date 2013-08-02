package xuzhenzhen.structureDesign.model;

public interface Position {
	public Object getElem();
	public Object setElem(Object element);
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public void updateX();
	public void updateY();
	public void updateWidth();
	public boolean isFolded();
	public void setFold(boolean fold);
}
