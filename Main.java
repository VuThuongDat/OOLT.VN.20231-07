import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Main extends JFrame implements ActionListener {

    AVLTree logicalTree = new AVLTree();
    VisualAVLTree visualTree = new VisualAVLTree();

    //Array to store the coodinates of the nodes of the tree
    protected int[][] nodeCoords = new int[500][3];
    int counter = 0;
    int numberOfNodes = 0;

    //Boolean variable to indicate that the tree has been painted
    boolean treePainted = false;
    //Boolean variable to indicate that the image has been painted
    boolean imagePainted = false;

    //Button vector for the various tree functions (exit, reset, etc)
    private JButton[] button = new JButton[10];

    //Text field vector for the various tree functions
    private JTextField[] field = new JTextField[8];

    //Panel that contains the buttons
    private JPanel buttonPanel = new JPanel();

    //Panel contains the text fields
    private JPanel fieldPanel = new JPanel();
    Font font = new Font("Verdana", Font.BOLD, 12);

    //Panel to display the tree
    public VisualAVLTree view = new VisualAVLTree();

    //Panel to display the mirror tree image
    public VisualAVLTree mirrorView = new VisualAVLTree();

    public static void main(String[] args) {
        Main mainProgram = new Main();
    }

    public Main() {
        //Adding the buttons to the vector
        button[0] = new JButton("Tree");
        button[1] = new JButton("In Order");
        button[2] = new JButton("Post Order");
        button[3] = new JButton("Pre Order");
        button[4] = new JButton("Level Order");
        button[5] = new JButton("Mirror Tree");
        button[6] = new JButton("Search and Height");
        button[7] = new JButton("Search and Delete");
        button[8] = new JButton("Reset");
        button[9] = new JButton("Exit");

        //Adding the text fields to the vector
        for (int i = 0; i < 8; i++) {
            field[i] = new JTextField(15);
        }

        //Constructor to create the GUI of the class
        setTitle("AVL Tree");
        setBackground(Color.white);

        //Adding the tree view panel to the frame, setting border and size
        add(view);
        view.setBorder(new TitledBorder("Tree View"));
        view.setPreferredSize(new Dimension(800, 400));
        view.setVisible(true);
        view.setBackground(Color.white);

        //Adding the mirror tree panel to the frame, setting border and size
        add(mirrorView);
        mirrorView.setBorder(new TitledBorder("Mirror Tree View"));
        mirrorView.setPreferredSize(new Dimension(400, 400));
        mirrorView.setVisible(true);
        mirrorView.setBackground(Color.white);

        //Adding panels containing the buttons and textfields
        add(buttonPanel);
        add(fieldPanel);

        //Setting layouts for the button panel and field panel
        buttonPanel.setLayout(new GridLayout(9, 1));
        fieldPanel.setLayout(new GridLayout(9, 1));

        //GridLayout for the whole frame
        setLayout(new GridLayout(2, 2));

        //Setting the color scheme for the buttons and adding the text fields and buttons
        for (int i = 0; i < 8; i++) {
            button[i].setBackground(Color.LIGHT_GRAY);
            buttonPanel.add(button[i]);
            fieldPanel.add(field[i]);
        }

        //Exit and reset buttons are different
        buttonPanel.add(button[8]);
        button[8].setBackground(Color.black);
        button[8].setForeground(Color.white);
        fieldPanel.add(button[9]);
        button[9].setBackground(Color.black);
        button[9].setForeground(Color.white);

        //Adding ActionListeners to all the buttons
        for (int i = 0; i < 10; i++) {
            button[i].addActionListener(this);
        }

        //All buttons other than Tree, Reset and Exit are disabled by default
        for (int i = 1; i < 8; i++) {
            button[i].setEnabled(false);
        }
        //All fields other than the right next to the tree button are disabled by default
        for (int i = 1; i < 8; i++) {
            field[i].setEditable(false);
        }

        //Finishing up
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Show initial instructions
        JOptionPane.showMessageDialog(null, "Your nodes values should be written in the text field right next to the 'Tree' button. "
                + "The correct format would be like: 1,2,3,4,5."
                + "\nAfter the tree is painted, you can insert more node values to the tree just by adding them in the text field. "
                + "You can even delete them!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //ActionListener 
        //Tree button
        if (e.getSource() == button[0]) {
            clean();
            String str = field[0].getText();
            try {
                //Input is split using "," and is parsed
                String[] treeSplit = str.split(",");
                numberOfNodes = treeSplit.length;

                //Root node created
                Node temp = new Node(Integer.parseInt(treeSplit[0]));
                for (int i = 1; i < 8; i++) {
                    field[i].setText("");
                }
                field[6].setEditable(true);
                field[7].setEditable(true);

                //Iterate through the split input and add nodes to the tree
                for (int i = 1; i < treeSplit.length; i++) {
                    temp = logicalTree.insert(temp, Integer.parseInt(treeSplit[i]));
                }

                //Set root to temp
                logicalTree.root = temp;
                logicalTree.mirrorRoot = null;

                //Paint the tree on the panel
                view.paintTree(this, logicalTree.root);

                //Indicate that the tree has been painted
                treePainted = true;
                imagePainted = false;

                //Enable AVL tree function buttons
                for (int i = 1; i < 8; i++) {
                    button[i].setEnabled(true);
                }

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Icorrect input format.\nCorrect format e.g. 1,7,3,9.");
                for (int i = 0; i < 8; i++) {
                    button[i].setEnabled(false);
                    field[i].setText("");
                }
                field[6].setEditable(false);
                field[7].setEditable(false);
            }
            //In Order traversal button
        } else if (e.getSource() == button[1]) {
            //Perform inorder traversal and display it
            logicalTree.iOrder = "";
            logicalTree.inOrder(logicalTree.root);
            field[1].setText(logicalTree.iOrder);
            //Post Order traversal button
        } else if (e.getSource() == button[2]) {
            //Perform postorder traversal and display it
            logicalTree.pOrder = "";
            logicalTree.postOrder(logicalTree.root);
            field[2].setText(logicalTree.pOrder);
            //Pre Order traversal button
        } else if (e.getSource() == button[3]) {
            //Perform preorder traversal and display it
            logicalTree.prOrder = "";
            logicalTree.preOrder(logicalTree.root);
            field[3].setText(logicalTree.prOrder);
            //Level Order traversal button
        } else if (e.getSource() == button[4]) {
            //Perform levelorder traversal and display it
            logicalTree.lvlOrder = "";
            logicalTree.levelOrder(logicalTree.root);
            field[4].setText(logicalTree.lvlOrder);
            //Mirror Tree button
        } else if (e.getSource() == button[5]) {
            String str = field[0].getText();
            String[] treeSplit = str.split(",");

            //Creating image Binary Tree
            Node temp = new Node(Integer.parseInt(treeSplit[0]));
            for (int i = 1; i < treeSplit.length; i++) {
                temp = logicalTree.insert(temp, Integer.parseInt(treeSplit[i]));
            }
            field[5].setText("Mirror Tree displayed");

            //Compute the Image
            logicalTree.mirrorTree(temp);
            logicalTree.mirrorRoot = temp;

            //Display the image Tree
            mirrorView.paintTree(this, logicalTree.mirrorRoot);

            //Indicate image has been displayed
            imagePainted = true;
            //Search and Height button
        } else if (e.getSource() == button[6]) {
            if (field[6].getText() != null) {
                try {
                    //Parse search value
                    int x = Integer.parseInt(field[6].getText());
                    view.paintTree(this, logicalTree.root);

                    //Search parsed value in the tree
                    Node node = searchTree(logicalTree.root, x);
                    field[6].setFont(font);
                    if (node != null) {
                        //if value is found, inform user
                        field[6].setForeground(Color.green);
                        field[6].setText("FOUND!");
                        JOptionPane.showMessageDialog(null, "Node value " + x + " is in the tree and its Height is " + node.h);
                    } else {
                        //if value is not found, inform user
                        field[6].setForeground(Color.red);
                        field[6].setText("NOT FOUND");
                        JOptionPane.showMessageDialog(null, "There is no node value " + x + " in the tree.");
                    }
                    field[6].setForeground(null);
                    field[6].setText("");
                } catch (HeadlessException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Incorrect input format.\nIt should be an integer.");
                }
            }
            //Search and delete button
        } else if (e.getSource() == button[7]) {
            if (field[7].getText() != null) {
                try {
                    //Parse search value
                    int x = Integer.parseInt(field[7].getText());
                    view.paintTree(this, logicalTree.root);

                    //Search parsed value in the tree
                    field[7].setFont(font);
                    if (searchTree(logicalTree.root, x) != null) {
                        //if value is found, inform user
                        field[7].setForeground(Color.green);
                        field[7].setText("FOUND!");
                        int response = JOptionPane.showConfirmDialog(this, x + " is in the tree. Do you want to delete it?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            String str = field[0].getText();
                            String deleted = str.replaceAll("," + String.valueOf(x), "");
                            field[0].setText(deleted);
                            //Input is split using "," and is parsed
                            String[] treeSplit = deleted.split(",");
                            numberOfNodes = treeSplit.length;

                            //Root node created
                            Node temp = new Node(Integer.parseInt(treeSplit[0]));
                            for (int i = 1; i < 8; i++) {
                                field[i].setText("");
                            }
                            field[6].setEditable(true);
                            field[7].setEditable(true);

                            //Iterate through the split input and add nodes to the tree
                            for (int i = 1; i < treeSplit.length; i++) {
                                temp = logicalTree.insert(temp, Integer.parseInt(treeSplit[i]));
                            }

                            //Set root to temp
                            logicalTree.root = temp;
                            logicalTree.mirrorRoot = null;
                            
                            //Clean panels
                            clean();
                            
                            //Paint the tree on the panel
                            view.paintTree(this, logicalTree.root);

                            //Indicate that the tree has been painted
                            treePainted = true;
                            imagePainted = false;
                        }
                    } else {
                        //if value is not found, inform user
                        field[7].setForeground(Color.red);
                        field[7].setText("NOT FOUND");
                        JOptionPane.showMessageDialog(null, x + " is not in the tree");
                    }
                    field[7].setForeground(null);
                    field[7].setText("");
                } catch (HeadlessException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Incorrect input format");
                }
            }
            //Reset button
        } else if (e.getSource() == button[8]) {
            //Reset the program
            dispose();
            Main mainProgram = new Main();
            //Exit button
        } else if (e.getSource() == button[9]) {
            //Exit from the program
            dispose();
        }
    }

    //Clean previuos draws in the JPanel
    public void clean() {
        Graphics g = getGraphics();
        //Clean the Tree side
        g.clearRect(25, 45, view.getWidth() - 25, view.getHeight() - 45);
        //Clean the Mirror Tree side
        g.clearRect(view.getWidth() + 25, 45, view.getWidth() - 25, view.getHeight() - 45);
    }

    //This method among with searchIncoords and VisualAVLTree class is here in the Main just for efficiency reasons
    public Node searchTree(Node node, int data) {
        //Recursive function to perform search of passed data on a Binary Tree
        //If data is found, returns true else false
        Graphics g = getGraphics();
        //Node animation speed
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (node == null) {
            return node;
        }

        //if data is found, its node is found and is changed to green color
        if (data == node.data) {
            int[] temp = searchInCoords(node.data);
            if (temp[0] != 0 && temp[1] != 0) {
                g.setColor(Color.green);
                System.out.println("Green - Found!");
                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                g.setColor(Color.black);
                g.drawString(node.data + "", temp[0] + 20, temp[1] + 50);
            }
            return node;
        } //If data is not found, the current node is changed to yellow color and the search is called recursively
        //on the left subtree since data is smaller than current node data
        else if (data < node.data) {
            int[] temp = searchInCoords(node.data);
            if (temp[0] != 0 && temp[1] != 0) {
                g.setColor(Color.yellow);
                System.out.println("Yellow - Not found");
                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                g.setColor(Color.black);
                g.drawString(node.data + "", temp[0] + 20, temp[1] + 50);
            }
            return searchTree(node.leftChild, data);
        } //If data is not found, the current node is changed to yellow color and the search is called recursively
        //on the right subtree since data is greater than current node data
        else {
            int[] temp = searchInCoords(node.data);
            if (temp[0] != 0 && temp[1] != 0) {
                g.setColor(Color.yellow);
                System.out.println("Yellow - Not found");
                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                g.setColor(Color.black);
                g.drawString(node.data + "", temp[0] + 20, temp[1] + 50);
            }
            return searchTree(node.rightChild, data);
        }
    }

    //Method to search for the coordinates of the passed value
    private int[] searchInCoords(int x) {
        //used to find the node coordinates for passed data
        System.out.println("x = " + x);
        for (int[] nodeCoord : nodeCoords) {
            if (x == nodeCoord[2]) {
                int[] temp = {nodeCoord[0], nodeCoord[1]};
                return temp;
            }
        }
        return new int[2];
    }

    //Class to manage visual representations of the Trees
    //For efficiency reasons this class was put here instead of being separated from the main
    class VisualAVLTree extends JPanel {

        //Node radius
        public int circleRadius = 15;
        //Vertical Gap between two nodes in a Binary Tree
        public int verticalSeperation = 30;

        //Principal method to paint the tree
        protected void paintTree(Main main, Node root) {
            Graphics g = getGraphics();
            if (root != null) {
                displayTree(main, g, root, getWidth() / 2, 35, getWidth() / 4);
            }
        }

        //Secondary method to paint the tree
        public void displayTree(Main main, Graphics g, Node node, int x, int y, int horizatalSeperation) {
            //Function to display a subtree with root as x,y        
            g.setColor(Color.CYAN);
            g.fillOval(x - circleRadius, y - circleRadius, 2 * circleRadius, 2 * circleRadius);

            //Store the coordinates of the node, to be used for searching
            main.nodeCoords[main.counter][0] = x - circleRadius;
            main.nodeCoords[main.counter][1] = y - circleRadius;
            main.nodeCoords[main.counter][2] = node.data;
            main.counter++;

            //Write the data value on the node
            g.setColor(Color.black);
            g.drawString(node.data + "", x - 6, y + 4);

            if (node.leftChild != null) {
                //Using drawLine to draw line to the left node
                if (!treePainted) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //Draw line
                drawLineBetween2Circles(g, x - horizatalSeperation, y + verticalSeperation, x, y);

                //Recursively draw the left subtree
                //Decrease the horizontal and vertical gaps
                displayTree(main, g, node.leftChild, x - horizatalSeperation, y + verticalSeperation, horizatalSeperation / 2);
            }
            if (node.rightChild != null) {
                //Using drawLine to draw line to the right node
                if (!treePainted) {
                    try {

                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //Draw line
                drawLineBetween2Circles(g, x + horizatalSeperation, y + verticalSeperation, x, y);
                //Recursively draw the right subtree
                //Decrease the horizontal and vertical gaps
                displayTree(main, g, node.rightChild, x + horizatalSeperation, y + verticalSeperation, horizatalSeperation / 2);
            }
        }

        //Method to draw a line between two circles centered at x1,y1 and x2,y2
        private void drawLineBetween2Circles(Graphics g, int x1, int y1, int x2, int y2) {
            //Computing adjusted coordinates
            double d = Math.sqrt(verticalSeperation * verticalSeperation + (x2 - x1) * (x2 - x1));
            int xAdjusted = (int) (x1 - circleRadius * (x1 - x2) / d);
            int yAdjusted = (int) (y1 - circleRadius * (y1 - y2) / d);
            int xAdjusted1 = (int) (x2 + circleRadius * (x1 - x2) / d);
            int yAdjusted1 = (int) (y2 + circleRadius * (y1 - y2) / d);

            //Draw line between adjusted coordinates
            g.drawLine(xAdjusted, yAdjusted, xAdjusted1, yAdjusted1);
        }
    }

}