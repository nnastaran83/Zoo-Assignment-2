package graphics;

import mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoveAnimalDialog extends JDialog implements ActionListener {
    private ZooFrame parent;
    private final JComboBox<String> comboBox;
    private JSlider sliderX;
    private JSlider sliderY;
    private JButton moveButton;


    /**
     * MoveAnimal constructor
     * @param parent the frame that opened MoveAnimalDialog
     */
    public MoveAnimalDialog(ZooFrame parent) {
        super(parent, true);

        if(parent instanceof ZooFrame){
            this.parent =(ZooFrame) parent;
        }

        GridLayout layout = new GridLayout(3,1);
        this.setLayout(layout);

        comboBox= new JComboBox<>();

        if (parent != null) {
            this.setMinimumSize(parent.getMinimumSize());
            this.setMaximumSize(parent.getMinimumSize());
            this.setLocation(parent.getLocation());
        }

        this.addCustomComboBox();
        this.addChooseDirectionSection();
        this.addMoveButtonSection();
    }

    /**
     * create a custom CompoBox
     */
    private void addCustomComboBox(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        panel.setBackground(new Color(138, 138, 194));

        JLabel label= new JLabel("Choose an animal to move: ");
        label.setFont(new Font("", Font.PLAIN,20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(138, 138, 194));
        panel.add(label);

        // add a string for each animal in the database
        ArrayList<String> dataBaseStrings = this.parent.getZooPanelDataBaseStrings();
        int i = 0;

        for (String animalString : dataBaseStrings) {
            comboBox.addItem(animalString + " " + i);
            i++;
        }

        panel.add(comboBox);
        this.add(panel);
        
    }

    private void addChooseDirectionSection(){
        JPanel sliderPanel = new JPanel();
        sliderPanel.setBackground(new Color(138, 138, 194));
        GridLayout layout = new GridLayout(4,1);
        sliderPanel.setLayout(layout);

        JLabel label1= new JLabel("Choose the horizontal location of the animal: ");
        label1.setFont(new Font("", Font.PLAIN,20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setBackground(new Color(138, 138, 194));
        label1.setOpaque(true);

        sliderPanel.add(label1);

        sliderX = new JSlider(JSlider.HORIZONTAL, 0,800,0);
        sliderX.setMinorTickSpacing(1);
        sliderX.setMajorTickSpacing(100);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);
        sliderX.setBackground(new Color(179,196,232));

        sliderPanel.add(sliderX);

        JLabel label2= new JLabel("Choose the vertical location of the animal: ");
        label2.setFont(new Font("", Font.PLAIN,20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setOpaque(true);
        label2.setBackground(new Color(138, 138, 194));

        sliderPanel.add(label2);

        sliderY = new JSlider(JSlider.HORIZONTAL, 0,600,0);
        sliderY.setMinorTickSpacing(1);
        sliderY.setMajorTickSpacing(100);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);
        sliderY.setBackground(new Color(179,196,232));

        sliderPanel.add(sliderY);

         this.add(sliderPanel);

    }

    private void addMoveButtonSection(){
        JPanel rootPanel = new JPanel(new GridLayout(1,3));
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(138, 138, 194));

        rootPanel.add(leftPanel);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(138, 138, 194));

        JPanel panel = new JPanel(new GridLayout(3,1));
        panel.add(panel1);//add empty panel just for the decoration

        //add a button in the middle
        moveButton = new JButton("MOVE THE ANIMAL");
        moveButton.setBackground(new Color(62, 219, 119));
        moveButton.setBorder(BorderFactory.createLineBorder(new Color(73,97,82),1));

        moveButton.addActionListener(this);


        panel.add(moveButton);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(138, 138, 194));
        panel.add(panel2);//add empty panel just for the decoration

        rootPanel.add(panel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(138, 138, 194));

        rootPanel.add(rightPanel);
        this.add(rootPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedAnimalIndex;
        int selectedXlocation = sliderX.getValue();
        int selectedYlocation = sliderY.getValue();
        if(e.getSource() == moveButton){
            selectedAnimalIndex = comboBox.getSelectedIndex();
            if(selectedAnimalIndex == -1){
                JOptionPane.showMessageDialog(getContentPane(), "Please select an animal");
            }else{
                parent.getPanel().updateLocationAtDataBase(selectedAnimalIndex,new Point(selectedXlocation,selectedYlocation));
                parent.getPanel().manageZoo();//call the manageZoo to make the changes on the panel
                parent.getPanel().refreshInfoModel();
            }

        }
    }
}
