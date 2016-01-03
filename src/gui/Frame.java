/**
 * 
 */
package gui;

import javax.swing.JFrame;

/**
 * @author eebritos
 *
 */
public class Frame {

	private int width;
	private int height;
	private JFrame frame;
	
//	public static void main(String [] args){
//		Frame testFrame = new Frame();
//	}
	
	public Frame() {
		final int WIDTH = 400;
		final int HEIGHT = 300;
		setupFrame( WIDTH, HEIGHT );
	}
	
	public Frame( int newWidth, int newHeight ){
		setupFrame( newWidth, newHeight );
	}
	
	private void setupFrame( int width, int height ){
		setFrame( new JFrame( "beeHave" ) );
		JFrame auxFrame = getFrame();
		setWidth(width);
		setHeight(height);
		auxFrame.setSize( width, height );
		auxFrame.setLocationRelativeTo( null );
		auxFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		auxFrame.setVisible( true );
	}

	/**
	 * @return the width
	 */
	protected int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	protected void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	protected int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	protected void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the frame
	 */
	protected JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	protected void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	

}
