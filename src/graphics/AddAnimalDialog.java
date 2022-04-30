package graphics;


import animals.*;
import privateutil.AnimalModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;



import static privateutil.MyStrings.*;

/**
 * The type Add animal dialog.
 * makes a "conversion" with ZooFrame
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public class AddAnimalDialog extends JDialog implements ActionListener {

    //animal radio buttons
   private JRadioButton elephantButton;
   private JRadioButton lionButton;
   private JRadioButton giraffeButton;
   private JRadioButton turtleButton;
   private JRadioButton bearButton;
   private ButtonGroup group1;

    //sliders
    private JSlider sizeSlider;
    private JSlider sliderX;
    private JSlider sliderY;

    //color radio buttons
    private JRadioButton blueButton;
    private JRadioButton redButton;
    private JRadioButton naturalButton;
    private ButtonGroup group2;

    private JButton addButton;
    private ZooFrame parent;

    BufferedImage img1 = null;
    BufferedImage img2 = null;





    /**
     * AddAnimalDialog constructor.
     * modal dialog
     *
     */

    public AddAnimalDialog(JFrame parent){
        super(parent, true);
        if(parent instanceof ZooFrame){
            this.parent =(ZooFrame) parent;
        }

        GridLayout layout = new GridLayout(11, 1);
        this.setLayout(layout);

        this.addChooseAnimalSection();
        this.addChooseSizeSection();
        this.addChooseSpeedSection();
        this.addChooseColorSection();
        this.addAddButtonSection();
        if (parent != null) {
            this.setMinimumSize(parent.getMinimumSize());
            this.setMaximumSize(parent.getMinimumSize());
            this.setLocation(parent.getLocation());
        }
    }



    /**
     * addChooseAnimalSection method creates components for choosing animal and add them to the dialog
     */
    private void addChooseAnimalSection(){

        //create the label for this section
        JLabel label1 = new JLabel("Choose an animal: ");
        label1.setFont(new Font("", Font.PLAIN,20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setOpaque(true);
        this.add(label1);

        //Create the radio buttons.
        elephantButton = new JRadioButton(ELEPHANT);
        elephantButton.setMnemonic('E');
        elephantButton.setSelected(true);
        elephantButton.setBackground(new Color(97,250,174));
        elephantButton.setActionCommand(ELEPHANT);
        elephantButton.addActionListener(this);

        lionButton = new JRadioButton(LION);
        lionButton.setMnemonic('L');
        lionButton.setSelected(true);
        lionButton.setBackground(new Color(230,229,76));
        lionButton.setActionCommand(LION);
        lionButton.addActionListener(this);

        giraffeButton = new JRadioButton(GIRAFFE);
        giraffeButton.setMnemonic('G');
        giraffeButton.setSelected(true);
        giraffeButton.setBackground(new Color(250,170,90));
        giraffeButton.setActionCommand(GIRAFFE);
        giraffeButton.addActionListener(this);


        turtleButton = new JRadioButton(TURTLE);
        turtleButton.setMnemonic('T');
        turtleButton.setSelected(true);
        turtleButton.setBackground(new Color(230,103,182));
        turtleButton.setActionCommand(TURTLE);
        turtleButton.addActionListener(this);

        bearButton = new JRadioButton(BEAR);
        bearButton.setMnemonic('B');
        bearButton.setSelected(true);
        bearButton.setBackground(new Color(120,129,255));
        bearButton.setActionCommand(BEAR);
        bearButton.addActionListener(this);


        //Group the radio buttons.
        group1 = new ButtonGroup();
        group1.add(bearButton);
        group1.add(elephantButton);
        group1.add(giraffeButton);
        group1.add(lionButton);
        group1.add(turtleButton);

        JPanel panel1 = new JPanel();

       GridLayout layout = new GridLayout(1,5);
       layout.setHgap(5);
       panel1.setLayout(layout);
       panel1.add(bearButton);
       panel1.add(elephantButton);
       panel1.add(giraffeButton);
       panel1.add(lionButton);
       panel1.add(turtleButton);

       this.add(panel1);
    }


    /**
     * addChooseSizeSection method creates components for choosing the size of animal in pixels
     */
    private void addChooseSizeSection(){
        JLabel label2= new JLabel("Choose the size of the animal: ");
        label2.setFont(new Font("", Font.PLAIN,20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setOpaque(true);
        this.add(label2);

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 50,300,50);
        sizeSlider.setMinorTickSpacing(1);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        this.add(sizeSlider);

    }



    /**
     * addChooseSpeedSection method create components for choosing the vertical and the horizontal speed of animal
     */
    private void addChooseSpeedSection(){
        GridLayout layout = new GridLayout(1,2);
        layout.setHgap(20);

        JLabel label1= new JLabel("Choose the horizontal speed of the animal: ");
        label1.setFont(new Font("", Font.PLAIN,20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setOpaque(true);

        JLabel label2= new JLabel("Choose the vertical speed of the animal: ");
        label2.setFont(new Font("", Font.PLAIN,20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setOpaque(true);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(layout);
        titlePanel.add(label1);
        titlePanel.add(label2);

        this.add(titlePanel);



        sliderX = new JSlider(JSlider.HORIZONTAL, 1,10,1);
        sliderX.setMinorTickSpacing(1);
        sliderX.setMajorTickSpacing(1);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);

        sliderY = new JSlider(JSlider.HORIZONTAL, 1,10,1);
        sliderY.setMinorTickSpacing(1);
        sliderY.setMajorTickSpacing(1);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(layout);
        sliderPanel.add(sliderX);
        sliderPanel.add(sliderY);

        this.add(sliderPanel);

    }

    private void addChooseColorSection(){

        //create the label for this section
        JLabel label = new JLabel("Choose the color of the animal: ");
        label.setFont(new Font("", Font.PLAIN,20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        this.add(label);

        //Create the radio buttons.
        blueButton = new JRadioButton("Blue");
        blueButton.setSelected(true);
        blueButton.setBackground(new Color(91, 123, 245));
        blueButton.setActionCommand(BLUE);

        redButton = new JRadioButton("Red");
        redButton.setSelected(true);
        redButton.setBackground(new Color(250, 50, 66));
        redButton.setActionCommand(RED);

        naturalButton = new JRadioButton("Natural");
        naturalButton.setSelected(true);
        naturalButton.setBackground(new Color(250, 202, 193));
        naturalButton.setActionCommand(NATURAL);

        //Group the radio buttons.
        group2 = new ButtonGroup();
        group2.add(redButton);
        group2.add(blueButton);
        group2.add(naturalButton);

        JPanel panel = new JPanel();

        GridLayout layout = new GridLayout(1,5);
        layout.setHgap(5);

        panel.setLayout(layout);

        panel.add(redButton);
        panel.add(blueButton);
        panel.add(naturalButton);

        this.add(panel);

    }

    private void addAddButtonSection(){
        this.add(new JPanel());//add empty panel just for the decoration
        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.add(new JPanel());//add empty panel just for the decoration

        //add a button in the middle
        addButton = new JButton("ADD ANIMAL TO THE ZOO");
        addButton.setBackground(new Color(62, 219, 119));
        addButton.setBorder(BorderFactory.createLineBorder(new Color(73,97,82),1));
        addButton.addActionListener(this);


        panel.add(addButton);
        panel.add(new JPanel());//add empty panel just for the decoration

        this.add(panel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // if add button was clicked , the add the animal to zoo panel
        if(e.getSource() == addButton){
            String selectedAnimal = group1.getSelection().getActionCommand();
            String selectedColor = group2.getSelection().getActionCommand();

            int sizeOfAnimal = sizeSlider.getValue();
            int horizontalSpeed = sliderX.getValue();
            int verticalSpeed = sliderY.getValue();

            //updates the zooPanel that is in ZooFrame(this.parent)
            //update infoModel which is in AnimalModel
            switch (selectedAnimal){
               case BEAR ->{
                   Bear bear = new Bear(sizeOfAnimal,horizontalSpeed,verticalSpeed,selectedColor, this.parent.getPanel());
                   bear.loadImages(BEAR);
                   if(this.parent.addToDataBase(bear)){
                       AnimalModel.getInstance().updateInfoModel(bear);
                   }



               }

               case ELEPHANT -> {
                   Elephant elephant = new Elephant(sizeOfAnimal,horizontalSpeed,verticalSpeed,selectedColor,this.parent.getPanel());
                   elephant.loadImages(ELEPHANT);
                   if(this.parent.addToDataBase(elephant)){
                       AnimalModel.getInstance().updateInfoModel(elephant);
                   }

               }

               case GIRAFFE -> {
                   Giraffe giraffe = new Giraffe(sizeOfAnimal,horizontalSpeed,verticalSpeed,selectedColor,this.parent.getPanel());
                   giraffe.loadImages(GIRAFFE);
                   if(this.parent.addToDataBase(giraffe)){
                       AnimalModel.getInstance().updateInfoModel(giraffe);

                   }
               }

               case LION -> {
                   Lion lion = new Lion(sizeOfAnimal,horizontalSpeed,verticalSpeed,selectedColor,this.parent.getPanel());
                   lion.loadImages(LION);
                   if(this.parent.addToDataBase(lion)){
                       AnimalModel.getInstance().updateInfoModel(lion);
                   }

               }

               case TURTLE -> {
                   Turtle turtle = new Turtle(sizeOfAnimal,horizontalSpeed,verticalSpeed,selectedColor,this.parent.getPanel());
                   turtle.loadImages(TURTLE);
                   if(this.parent.addToDataBase(turtle)){
                       AnimalModel.getInstance().updateInfoModel(turtle);
                   }


               }


            }

            parent.getPanel().repaint();

        }

    }
}

