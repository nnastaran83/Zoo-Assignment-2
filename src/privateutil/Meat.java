package privateutil;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
import plants.Lettuce;
import plants.Plant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;

/**
 * The class Meat.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 May 1,22
 */
public class Meat implements IEdible, ILocatable, IDrawable {
    Lettuce helper;//just to avoid code duplication
    private ZooPanel pan;
    private BufferedImage img;

    public Meat(ZooPanel pan) {
        this.pan = pan;
        helper = new Lettuce(pan);

    }
    @Override
    public boolean setLocation(Point point) {
        return helper.setLocation(point);
    }
    @Override
    public EFoodType getFoodtype() {
        return EFoodType.MEAT;
    }



    @Override
    public String getColor() {
        return "";
    }

    @Override
    public Point getLocation() {
        return helper.getLocation();
    }


    @Override
    public void loadImages(String nm) {
        try {
            this.img = ImageIO.read(new File("src/assignment2_pictures/meat.gif"));
        } catch (IOException exception) {
            System.out.println("Image doesn't exist");
        }

    }

    @Override
    public void drawObject(Graphics g) {
        System.out.println("drawing Meat");
        g.drawImage(img, (int)this.getLocation().getX(), (int)this.getLocation().getY(), 40,40, pan);

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
