package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name) {
		this.name = name;
	}
	
	public void draw(Graphics g, int xCoord, int yCoord, int width, int height) {
        g.setColor(Color.BLACK);

        // get font properties
        Font originalFont = g.getFont();
        g.setFont(new Font(originalFont.getFontName(), Font.BOLD, 15));

        
        // center font based on font / size
        FontMetrics fontDim = g.getFontMetrics(g.getFont());
        int textWidth = fontDim.stringWidth(name);
        int textHeight = fontDim.getHeight();
        int fixedX = xCoord + (width - textWidth) / 2;
        int fixedY = yCoord + (height - textHeight) / 2 + fontDim.getAscent();

        
        //redraw font with updated location
        g.drawString(name, fixedX, fixedY);
        g.setFont(originalFont);
        
        
		
	}
	
	// getters
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public String getName() {
		return name;
	}
	
	// setters
	public void setCenterCell(BoardCell cell) {
		this.centerCell = cell;
	}
	
	public void setLabelCell(BoardCell cell) {
		this.labelCell = cell;
	}
	

}
