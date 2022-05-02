package graphics;

import plants.Cabbage;
import plants.Lettuce;
import privateutil.AnimalModel;
import mobility.Point;
import privateutil.Meat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

import static privateutil.MyStrings.*;
import static privateutil.MyStrings.MEAT;


/**
 * The type Zoo frame.
 * contains the main method
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public class ZooFrame extends JFrame implements ActionListener{
    protected final static String LETTUCE = "Lettuce";
    protected final static String CABBAGE = "Cabbage";
    protected final static String MEAT = "Meat";
    private final JTable infoTable  = new JTable(AnimalModel.getInstance().getInfoModel());
    private final ZooPanel zooPanel = new ZooPanel();
    private final JFrame infoFrame = new JFrame();


    /**
     * ZooFrame constructor
     */
    public  ZooFrame() {
        super("Zoo"); //set title
        this.setLayout(new BorderLayout());
        this.addCustomMenuBar();
        this.addCustomButtonPanel();
        this.add(zooPanel);

        this.infoTable.setFillsViewportHeight(true);
        infoFrame.add(new JScrollPane(infoTable));
        this.pack();

    }


    /**
     * the main method initialization the ZooFrame
     * @param args
     */
    public static void main(String[] args) {
        ZooFrame view = new ZooFrame();
        view.initView();

    }

    /**
     * Initializes the view
     */
    public void initView(){
        ZooFrame zooFrame = new ZooFrame();//create zoo frame
        zooFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        zooFrame.setMaximumSize(new Dimension(screenSize.width, screenSize.height));//Set the maximum width and height of zoo frame
        zooFrame.setMinimumSize(new Dimension(screenSize.width - screenSize.width / 3, screenSize.height - screenSize.height / 3));//Set the minimum width and height of zoo frame
        zooFrame.setLocation(screenSize.width/2- zooFrame.getSize().width/2, screenSize.height/2- zooFrame.getSize().height/2);//center the zoo frame
        zooFrame.setVisible(true);
    }



//GUI Building Methods--------------------------------------------------------------------------------------------------------------------------------------

    /**
     * add Custom MenuBar method creates a menu bar for zoo frame
     * adds menus to the menu bar
     * adds item menus to each menu
     */
    private void  addCustomMenuBar() {
        //create file menu
        JMenu fileMenu = new JMenu(FILE);//create file menu
        fileMenu.setMnemonic('F');//set mnemonic to F ->  it means: Pressing the Alt key and the letter E opens this menu


        //add items to file menu + add action listener for each item
        JMenuItem exitItem = new JMenuItem(EXIT);
        exitItem.setMnemonic('E');
        exitItem.addActionListener(this);//add action listener to exit item
        fileMenu.add(exitItem);// add exit item to file menu

        //create background menu
        JMenu backGroundMenu = new JMenu(BACKGROUND);
        backGroundMenu.setMnemonic('B');


        //add items to background menu
        JMenuItem imageItem = new JMenuItem(IMAGE);
        imageItem.setMnemonic('I');
        imageItem.addActionListener(this);
        backGroundMenu.add(imageItem);


        JMenuItem greenItem = new JMenuItem(GREEN);
        greenItem.setMnemonic('G');
        greenItem.addActionListener(this);
        backGroundMenu.add(greenItem);

        JMenuItem noneItem = new JMenuItem(NONE);
        noneItem.setMnemonic('N');
        noneItem.addActionListener(this);
        backGroundMenu.add(noneItem);

        //create help menu
        JMenu helpMenu = new JMenu(HELP);
        helpMenu.setMnemonic('H');

        //add items to help menu
        JMenuItem helpItem = new JMenuItem(HELP);
        helpItem.setMnemonic('H');
        helpItem.addActionListener(this);

        helpMenu.add(helpItem);

        //create the menu bar and add all the menus to it
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar); //add menu bar to zoo frame
        menuBar.add(fileMenu);
        menuBar.add(backGroundMenu);
        menuBar.add(helpMenu);
    }


    /**
     * createButtonPanel create a panel including buttons in it
     * adds the panel to the south of zoo frame
     * */
    private void addCustomButtonPanel(){
        //layout for the panel
        GridLayout layout = new GridLayout(1,6);


        //create panel
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(Color.GRAY);



        //add buttons to the panel
        JButton addAnimalButton = new JButton(ADD_ANIMAL);
        addAnimalButton.setBackground(new Color(131,141,242));
        addAnimalButton.addActionListener(this);

        JButton moveAnimalButton = new JButton(MOVE_ANIMAL);
        moveAnimalButton.setBackground(new Color(235,89,218));
        moveAnimalButton.addActionListener(this);

        JButton clearButton= new JButton(CLEAR);
        clearButton.setBackground(new Color(188,242,131));
        clearButton.addActionListener(this);

        JButton foodButton = new JButton(FOOD);
        foodButton.setBackground(new Color(235,202,120));
        foodButton.addActionListener(this);


        JButton infoButton = new JButton(INFO);
        infoButton.setBackground(new Color(129,219,199));
        infoButton.addActionListener(this);

        JButton exitButton= new JButton(EXIT);
        exitButton.setBackground(new Color(235,120,120));
        exitButton.addActionListener(this);
        panel.add(addAnimalButton);
        panel.add(moveAnimalButton);
        panel.add(clearButton);
        panel.add(foodButton);
        panel.add(infoButton);
        panel.add(exitButton);

        this.add(panel, BorderLayout.SOUTH);//add panel to the bottom of zoo frame

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @return an array of strings from each animal in the database
     */
    public ArrayList<String> getZooPanelDataBaseStrings(){
        return this.zooPanel.getAnimalArray();
    }

    /**
     * gets selected animal as parameter (Object type)
     * if sends the parameter to addAnimalTooTheZoo
     * @param selectedAnimal selected animal
     * @see ZooPanel#addAnimalTooTheZoo(Object)
     *
     */
    public boolean addToDataBase(Object selectedAnimal){
        return this.zooPanel.addAnimalTooTheZoo(selectedAnimal);
    }

    /**
     * remove all elements from database
     */
    public void removeAllFromDataBase(){
        this.zooPanel.removeAllAnimals();
    }


    //TODO: maybe there is a need to remove this method
    public ZooPanel getPanel(){
        return this.zooPanel;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * perform action depending on button or menu item that was pressed
     * @param e Actions on MenuItems and Buttons on zoo frame
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case NONE -> {
                zooPanel.setBackgroundImage(null);
                zooPanel.setBackground(null);
            }

            case GREEN -> {
                zooPanel.setBackgroundImage(null);
                zooPanel.setBackground(new Color(0x239354));//change the background color of zoo frame to green
            }

            case IMAGE -> zooPanel.setBackgroundImage(new ImageIcon("src/assignment2_pictures/savanna.jpg"));

            case HELP -> JOptionPane.showMessageDialog(getContentPane(), "Home work 2 GUI");

            case ADD_ANIMAL -> {
                AddAnimalDialog addAnimalDialog = new AddAnimalDialog(this);
                addAnimalDialog.setVisible(true);

            }

            case MOVE_ANIMAL -> {
                MoveAnimalDialog moveAnimalDialog = new MoveAnimalDialog(this);
                moveAnimalDialog.setVisible(true);
            }

            case CLEAR -> {
                this.removeAllFromDataBase();
                AnimalModel.getInstance().clearInfoModel();
                System.out.println("DataBase has been removed!");
                zooPanel.repaint();

            }

            case FOOD -> {
                //Custom button text
                Object[] options = {LETTUCE, CABBAGE, MEAT};
                String selectedFood = "";
                int result = JOptionPane.showOptionDialog(null,
                        "Please choose food",
                        "Food for animal",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                switch (result){
                    case 0-> zooPanel.makeFoodVisible(new Lettuce(this.zooPanel));

                    case 1-> zooPanel.makeFoodVisible(new Cabbage(this.zooPanel));

                    case 2-> zooPanel.makeFoodVisible(new Meat(this.zooPanel));

                }
            }

            case INFO -> {

                this.infoFrame.setSize(this.getWidth(),this.getHeight());
                this.infoFrame.setLocation(this.getLocation());
                this.infoFrame.setVisible(true);

            }

            case EXIT -> System.exit(0);



        }
        zooPanel.manageZoo();
        zooPanel.repaint();
    }


}
