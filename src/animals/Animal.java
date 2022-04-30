
package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Mobile;
import utilities.MessageUtility;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static privateutil.MyStrings.*;

/**
 * The class Animal defines the characteristics common to all animals.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 2.0 April 28,2022
 */
public abstract class Animal extends Mobile implements IEdible, IDrawable, IAnimalBehavior {

    private final int EAT_DISTANCE = 10;
    private int size;
    private String col;
    private int horSpeed;
    private int verSpeed;
    private boolean coordChanged;
    private int x_dir = 1;
    private int y_dir = 1;
    private int eatCount;
    private ZooPanel pan;
    private BufferedImage img1, img2;
    private String name;
    private double weight;
    private IDiet diet;//used for eating appropriate food.

    //Constructors----------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Animal constructor
     * @param size size of animal
     * @param horSpeed horizontal speed of animal
     * @param verSpeed vertical speed of animal
     * @param col color of animal
     * @param diet diet of animal
     */
    protected Animal(int size, int horSpeed, int verSpeed, String col, IDiet diet, Point location, ZooPanel pan){
        super(location);
        this.size = size;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.col = col;
        this. diet =(IDiet)diet.replicate();
        this.pan = pan;
        MessageUtility.logConstractor(this.toString(),"*");

    }




    //Setters----------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * set weight, if weight >= 0
     * @see MessageUtility#logSetter(String, String, Object, boolean)
     * return true if weight was set, else return false
     * @param weight weight
     */
    public boolean setWeight(double weight) {
        boolean is_successful = (weight >= 0);
        if(is_successful){
            this.weight = weight;
        }
        MessageUtility.logSetter(getName(), SETWEIGHT, weight, is_successful);
        return is_successful;
    }

    /**
     * sets the size of the animal if the parameter size > 0
     * @param size size of the animal
     * @return true if size>0 else return false
     */
    public boolean setSize(int size){
        boolean is_successful = (size > 0);
        if(is_successful){
            this.size = size;
        }
        return is_successful;

    }

    //Getters---------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @return horizontal speed of animal
     */
    public int getHorSpeed(){
        return this.horSpeed;
    }

    /**
     * @return vertical speed of animal
     */
    public int getVerSpeed(){
        return this.verSpeed;
    }





    /**
     * get name
     * @return name
     */
    public String getName() {
        MessageUtility.logGetter(name, GETNAME, this.name);
        return this.name;
    }

    /**
     * get weight
     * @return weight
     */
    public double getWeight() {
        MessageUtility.logGetter(this.name, GETWEIGHT, this.weight);
        return this.weight;
    }

    /**
     *
     * @return diet
     */
    public IDiet getDiet() {
        MessageUtility.logGetter(this.name,GETDIET, diet);
        return (IDiet) diet.replicate();
    }

    /**
     * tells which type of food is this animal; every animal's food type is MEAT except lions.
     * @return food type
     */
    public EFoodType getFoodtype() {
        if (this.getClass().getSimpleName().equals("Lion")) {
            MessageUtility.logGetter(this.name, "getFoodType", EFoodType.NOTFOOD);
            return EFoodType.NOTFOOD;
        }else{
            MessageUtility.logGetter(this.name, "getFoodType", EFoodType.MEAT);
            return EFoodType.MEAT;
        }
    }

    /**
     * getSize method
     * @return the size of the animal
     */
    public int getSize(){
        return this.size;
    }

    /**
     * @return color of animal
     */
    public String getColor(){
        return this.col;

    }

    /**
     * @return how much the animal ate
     */
    public int getEatCount(){
        return this.eatCount;
    }


    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Make sound.
     * this method activates the method roar for animals that can roar
     * and activate the method chew for animals that can chew
     * @see privateutil.ChewerAnimal#makeSound()
     * @see privateutil.RoarerAnimal#makeSound()
     */
    public abstract void makeSound();

    /**
     *
     * @param point the point
     * @return distance traveled
     */
    @Override
    public double move(Point point){
        double distance_traveled = super.move(point);
        setWeight(getWeight()-(distance_traveled * getWeight() * 0.00025));
        this.setChanges(true);
        return distance_traveled;
    }

    /**
     * Eat boolean.
     * checks if the animal can eat the food , if yes , eat it
     * after eating the animal makes a sound
     * @see utilities.MessageUtility#logBooleanFunction(String, String, Object, boolean)
     * @param edible some edible object
     * @return true if the animal could eat, else return false
     */
    public boolean  eat(IEdible edible){
        boolean is_successful = diet.canEat(edible.getFoodtype());
        if(is_successful){
            diet.eat(this, edible);
            this.makeSound();
        }
        MessageUtility.logBooleanFunction(getName(),EAT,edible,is_successful);
        return is_successful;
    }

    /**
     * Increase the eatCount field of the animal by one
     */
    public void eatInc(){
        this.eatCount += 1;

    }


    /**
     * load Images of th animal
     * @param nm animal name(Bear, Lion,etc)
     */
    public void loadImages(String nm){
        switch(nm){
            case BEAR-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"bea_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"bea_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"bea_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"bea_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"bea_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"bea_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case ELEPHANT-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"elf_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"elf_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"elf_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"elf_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"elf_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"elf_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case GIRAFFE-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"grf_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"grf_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"grf_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"grf_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        System.out.println("lalalalala");
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"grf_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"grf_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case LION-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"lio_b_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"lio_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"lio_n_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"lio_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        System.out.println("lalalalala");
                        try {
                            this.img1 = ImageIO.read(new File(PICTURE_PATH+"lio_r_1.png"));
                            this.img2 = ImageIO.read(new File(PICTURE_PATH+"lio_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }

            case TURTLE-> {
                switch (this.getColor()){
                    case BLUE -> {
                        try {
                            this.img1 = ImageIO.read(new File("src/assignment2_pictures/trt_b_1.png"));
                            this.img2 = ImageIO.read(new File("src/assignment2_pictures/trt_b_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case NATURAL -> {
                        try {
                            this.img1 = ImageIO.read(new File("src/assignment2_pictures/trt_n_1.png"));
                            this.img2 = ImageIO.read(new File("src/assignment2_pictures/trt_n_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }

                    case RED -> {
                        try {
                            this.img1 = ImageIO.read(new File("src/assignment2_pictures/trt_r_1.png"));
                            this.img2 = ImageIO.read(new File("src/assignment2_pictures/trt_r_2.png"));
                        } catch (IOException exception) {
                            System.out.println("Image doesn't exist");
                        }
                    }
                }
            }
        }

    }


    /**
     *
     * @return which kind of animal is this
     */
    public String getAnimalName(){
        return this.getClass().getSimpleName();
    }


    /**
     *
     * @return true if coordinates of animal changed else return false
     */
    public boolean getChanges (){
        return this.coordChanged;

    }

    /**
     * @param state set to true if coordinates of animal changes, else set to false
     */
    public void setChanges (boolean state){
        this.coordChanged = state;

    }

    /**
     * TODO: get it back to assignment1 , and use instead it getAnimalName
     * toString
     * @return name of class
     */
    public String toString(){
        return this.getClass().getSimpleName();
    }


    /**
     * draw the animal
     * @param g
     */
    public void drawObject (Graphics g)
    {
        System.out.println("drawing animal");
        if(x_dir==1) // giraffe goes to the right side
            g.drawImage(img1, (int)getLocation().getX()-getSize()/2, (int)getLocation().getY()-getSize()/10, getSize()/2, getSize(), pan);
        else // giraffe goes to the left side
            g.drawImage(img2, (int)getLocation().getX(), (int)getLocation().getY()-getSize()/10, getSize()/2, getSize(), pan);
    }

}
