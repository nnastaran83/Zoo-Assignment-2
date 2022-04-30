package mobility;

import privateutil.Replicable;

/**
 * The class Point defines position on a 2D axis
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 March 27,22
 */
public class Point implements Replicable {

    private double x;
    private double y;
    private final double min_x = 0;
    private final double min_y = 0;
    private final double max_x = 800;
    private final double max_y = 600;

    //Constructors-------------------------------------------------------------------------------------------------------------------------------------------------

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //Getters---------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * getX
     * @return x
     */
    public double getX(){
        return this.x;
    }

    /**
     * getY
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     *
     * @return minimum value of x
     */
    public double getMin_x(){
        return this.min_x;
    }

    /**
     *
     * @return minimum value of y
     */
    public double getMin_y(){
        return this.min_y;
    }

    /**
     *
     * @return maximum value of x
     */
    public double getMax_x(){
        return this.max_x;
    }

    /**
     *
     * @return maximum value of y
     */
    public double getMax_y(){
        return this.max_y;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * checks if the point is in the legal bounds of Point class
     * @param point_to_check point to check
     * @return true if the point is in the legal bounds, else return false
     */
    public static boolean checkBoundaries(Point point_to_check){
        return point_to_check.getX() >= point_to_check.getMin_x() && point_to_check.getX() <= point_to_check.getMax_x() &&
                point_to_check.getY() >= point_to_check.getMin_y() && point_to_check.getY() <= point_to_check.getMax_y();
    }
    /**
     * make a copy of the object
     * @return a copy of point
     */

    @Override
    public Point replicate() {
        return new Point(this.getX(),this.getY());
    }

    /**
     * toString
     * @return Point(this.x,this.y)
     */
    public String toString(){
        return "Point(" + getX() + "," + getY() + ")";
    }
}
