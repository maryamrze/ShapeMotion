/*
 *	===============================================================================
 *	PolygonShape.java : A shape that is a polygon.
 *  YOUR UPI: mrez993
 *	=============================================================================== */
import java.awt.*;
import java.util.Arrays;
class PolygonShape extends SquareShape {
	private Point centre = new Point(DEFAULT_HEIGHT/2, DEFAULT_HEIGHT/2);
	private int radius = DEFAULT_HEIGHT/2;
	private int numberOfSides = 6;
    public PolygonShape() {}
	public PolygonShape(int x, int y, int s, int pw, int ph, Color c, Color bc, PathType pt, ShapeType st) {
		super(x ,y ,s, pw ,ph, c, bc, pt);
		this.numberOfSides = st.getNumberOfSides();
		radius = s/2;
		centre  = new Point(x + width/2, y + height/2);
	}
	public void draw(Graphics g) {
        g.setColor(color);
        centre = new Point(getX() + radius, getY() + radius);
        
        int[] xarray = new int[numberOfSides];
        int[] yarray = new int[numberOfSides];
        
        for (int i=0; i < numberOfSides; i++){
            double angle = (i * 2 * Math.PI) / numberOfSides;
            
            xarray[i] = (int) (centre.x + radius * Math.cos(angle));
            yarray[i] = (int) (centre.y + radius * Math.sin(angle)); 
        }
    
        // Create and fill the polygon
        Polygon polygon = new Polygon(xarray, yarray, numberOfSides);
        g.fillPolygon(polygon);
    
        // Draw the border of the polygon
        g.setColor(borderColor);
        g.drawPolygon(polygon);
    }
}