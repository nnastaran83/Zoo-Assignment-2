package graphics;

import animals.Animal;
import food.IEdible;
import mobility.ILocatable;
import mobility.Point;
import plants.Plant;
import privateutil.AnimalModel;
import privateutil.Meat;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * The type Zoo panel.
 * private manageZoo method in this class represents the Controller
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public class ZooPanel extends JPanel{
    private final ArrayList<Animal> animalsArray = new ArrayList<Animal>(); //represents all of animals at the zoo (Data Base)

    private final ArrayList<Object> foodArray = new ArrayList<Object>();
    private BufferedImage meatImage = null;
    private ImageIcon backgroundImage = null;

   public ZooPanel(){
       this.setLayout(new BorderLayout());

   }


    public void manageZoo() {
        if (isChange()){
            if(checkIfAnimalAte()){

            }
                repaint();

            changesDone();
        }


    }

    /**
     * checks if any animal is close enough to the food
     * if yes, and if it can, eat
     * @return  true if animal ate , else return false
     */
    public boolean checkIfAnimalAte() {
        int animalIndex;
        int foodIndex = 0;
        int otherAnimalIndex;
        for (Animal animal : animalsArray) {
            animalIndex = 0;
            System.out.println("Animal Index"+foodIndex);
            for (Object food : foodArray) {
                System.out.println("Animal Index"+animalIndex);
                if (food instanceof ILocatable && food instanceof IEdible) {
                    if (animal.calcDistance(((ILocatable) food).getLocation()) < animal.getEatDistance()) {
                        if(animal.eat((IEdible) food)){
                            foodArray.remove(foodIndex);
                            animalsArray.get(animalIndex).eatInc();
                            refreshInfoModel();
                            return true;
                        }
                    }
                }
                foodIndex += 1;
            }

            otherAnimalIndex = 0;
            for (Animal otherAnimal : animalsArray) {

                System.out.println("Animal Index"+animalIndex);
                if (animalIndex != otherAnimalIndex) {

                    if (animal.calcDistance(((ILocatable) otherAnimal).getLocation()) < otherAnimal.getSize() && animal.getWeight() >= otherAnimal.getWeight()*2) {
                        if(animal.eat((IEdible) otherAnimal)){
                            animalsArray.remove(otherAnimalIndex);
                            animalsArray.get(animalIndex).eatInc();
                            refreshInfoModel();
                            return true;
                        }
                    }
                }
                otherAnimalIndex += 1;
            }

            animalIndex += 1;

        }
        return false;
    }


    /**
     * @return true if animal coordinate changed, else return false
     */
    public boolean isChange(){
        boolean changed = false;
        for (Animal animal: animalsArray){
            changed = animal.getChanges();
            if(changed){
                System.out.println("==================>"+animal.getWeight());
                return true;
            }
        }
        return false;
    }

    /**
     * it turns back the changes the coordChanged filed of animals back to false
     * this method is used when the changes is already done on the view
     */
    public void changesDone(){
        for (Animal animal: animalsArray){
            animal.setChanges(false);
        }
    }
    /**
     *
     * @return an array of strings for each animal in the database
     */
    public ArrayList<String> getAnimalArray(){
        ArrayList<String> dataBaseStrings = new ArrayList<String>();
        for (Animal animal : animalsArray) {
            dataBaseStrings.add(animal.toString());
        }

        return dataBaseStrings;
    }

    /**
     * set background image
     * @param backgroundImage background image
     * @return true if the background image is not null, else return false
     */
    public boolean setBackgroundImage(ImageIcon backgroundImage){
        boolean is_successful = (backgroundImage != null);

        if(is_successful){
            System.out.println("set Background image");
            this.backgroundImage = backgroundImage;
        }
        return is_successful;
    }

    /**
     * if the number of animals in the zoo is less than 10,
     * checks if the object is instance of animal
     * then adds animal to the zoo
     * @param animal animal
     * @return true if animal was added to the zoo, else return false
     */
    public boolean addAnimalTooTheZoo(Object animal){
        boolean is_successfull = (animalsArray.size() < 10);
        if(is_successfull){
            if(animal instanceof Animal){
                animalsArray.add((Animal) animal);
            }

        }else{
            JOptionPane.showMessageDialog(null, "You cannot add more than 10 animals");
        }
        return is_successfull;

    }

    /**
     * add plant to plantArray and make it visible
     * @param food some type of food
     */
    public void makeFoodVisible(Object food){
        if(food instanceof Plant){
            ((Plant) food).loadImages(food.toString());
            foodArray.add((Plant) food);

        }else if(food instanceof Meat){//this is for Meat
            ((Meat) food).loadImages(food.toString());
            foodArray.add((Meat)food);
        }
        this.repaint();
    }

    /**
     * update the location of animal
     * @param indexOfAnimal
     */
    public void updateLocationOfAnimal(int indexOfAnimal, Point location) {
        animalsArray.get(indexOfAnimal).move(location);
        //animalsArray.get(indexOfAnimal).setLocation(location);
        //animalsArray.get(indexOfAnimal).setChanges(true);
        System.out.println(animalsArray.get(indexOfAnimal).getLocation());
    }


    /**
     * update the location of selected animal in the database
     * @param indexOfAnimal
     * @param location
     */
    public void updateLocationAtDataBase(int indexOfAnimal, Point location){
        this.updateLocationOfAnimal(indexOfAnimal, location);

        System.out.println("location updated");
    }


    /**
     * remove all elements from animal array
     */
    public void removeAllAnimals(){
        animalsArray.clear();

    }




    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("zoo panel pantComponent");
        if(backgroundImage != null){
            backgroundImage.paintIcon(this, g, 0, 0);//display background

        }

        for (Animal animal: animalsArray){
            animal.drawObject(g);
        }

        for (Object food: foodArray){
            if(food instanceof Plant){
                ((Plant) food).drawObject(g);
            }else if(food instanceof Meat){
                ((Meat) food).drawObject(g);
            }

        }




    }


    public void refreshInfoModel() {
        AnimalModel.getInstance().refreshInfoModel(this.animalsArray);
    }






}
