package graphics;

import animals.Animal;
import mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Zoo panel.
 * private manageZoo method in this class represents the Controller
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public class ZooPanel extends JPanel{
    private final ArrayList<Animal> animalsArray = new ArrayList<Animal>(); //represents all of animals at the zoo (Data Base)
    private ImageIcon backgroundImage = null;


    public void manageZoo() {
        if (isChange()){
                repaint();

            // need to check if some animal can eat another animal according to their locations

            changesDone();
        }






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


    }


}
