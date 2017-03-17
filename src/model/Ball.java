package model;

public class Ball {
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	public Ball(){
		this.x = 0;
		this.y = 0;
		this.width = 20;
		this.height = 20;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
