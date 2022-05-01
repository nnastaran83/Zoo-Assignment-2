package plants;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
import utilities.MessageUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Random;

import static privateutil.MyStrings.CABBAGE;
import static privateutil.MyStrings.LETTUCE;

/**
 * @author baroh
 *
 */
public abstract class Plant implements IEdible, ILocatable, IDrawable {
	/**
	 * 
	 */
	private double height;
	/**
	 * 
	 */
	private Point location;
	/**
	 * 
	 */
	private double weight;
	private ZooPanel pan;
	private BufferedImage img;

	/**
	 * plant constructor
	 * @param pan
	 */
	public Plant(ZooPanel pan){
		this.pan = pan;
		this.location = new Point(pan.getWidth()/2, pan.getHeight()/2);


	}

	/**
	 * 
	 */
	public Plant() {
		Random rand = new Random();
		int x = rand.nextInt(30);
		int y = rand.nextInt(12);
		this.location = new Point(x, y);

		MessageUtility.logConstractor("Plant", "Plant");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see food.IFood#getFoodtype()
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
		return EFoodType.VEGETABLE;
	}

	/**
	 * @return
	 */
	public double getHeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getHeight", this.height);
		return this.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#getLocation()
	 */
	@Override
	public Point getLocation() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getLocation", this.location);
		return this.location;
	}

	/**
	 * @return
	 */
	public double getWeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getWeight", this.weight);
		return weight;
	}

	/**
	 * @param height
	 * @return
	 */
	public boolean setHeight(double height) {

		boolean isSuccess = (height >= 0);
		if (isSuccess) {
			this.height = height;
		} else {
			this.height = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setHeight", height, isSuccess);
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#setLocation(mobility.Point)
	 */
	@Override
	public boolean setLocation(Point newLocation) {
		boolean isSuccess = Point.checkBoundaries(newLocation);
		if (isSuccess) {
			this.location = newLocation;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setLocation", newLocation, isSuccess);
		return isSuccess;
	}

	/**
	 * @param weight
	 * @return
	 */
	public boolean setWeight(double weight) {
		boolean isSuccess = (weight >= 0);
		if (isSuccess) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setWeight", weight, isSuccess);

		return isSuccess;
	}


	/**
	 * load images for the plant
	 * @param nm name of the plant
	 */
	public void loadImages(String nm){
		switch (nm){
			case CABBAGE -> {
				try {
					this.img = ImageIO.read(new File(PICTURE_PATH+"cabbage.png"));
				} catch (IOException exception) {
					System.out.println("Image doesn't exist");
				}
			}

			case LETTUCE -> {
				try {
					this.img = ImageIO.read(new File(PICTURE_PATH+"lettuce.png"));
				} catch (IOException exception) {
					System.out.println("Image doesn't exist");
				}
			}
		}
	}

	public String getColor(){
		return "";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}

	/**
	 * draw the plant
	 * @param g
	 */
	public void drawObject (Graphics g) {
		System.out.println("drawing plant");
		g.drawImage(img, (int)this.getLocation().getX(), (int)this.getLocation().getY(), 40,40, pan);

	}

}
