package test.gui;

public class Position {
	
	private final Integer ZERO = 0;
	private Integer x;
	private Integer y;
	
	public Position() {
		setX(ZERO);
		setY(ZERO);
	}
	
	public Position(int x, int y){
		setX(x);
		setY(y);
	}

	/**
	 * @return the x
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Integer y) {
		this.y = y;
	}

}
