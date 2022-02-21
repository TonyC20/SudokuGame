package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * this application implements the Sudoku game 
 * and a GUI which the user can interact with
 * to play the game
 * @since 2022-02-02
 * @author tonyc
 */
public class Sudoku extends JFrame{
    private int[][] grid = {{8, 9, 4, 1, 3, 2, 5, 6, 7},
                            {1, 7, 2, 5, 8, 6, 4, 9, 3}, 
                            {3, 5, 6, 9, 7, 4, 1, 2, 8},
                            {7, 8, 1, 3, 5, 9, 6, 4, 2},
                            {4, 2, 3, 8, 6, 1, 9, 7, 5},
                            {9, 6, 5, 2, 4, 7, 8, 3, 1},
                            {2, 3, 9, 4, 1, 8, 7, 5, 6},
                            {6, 4, 8, 7, 2, 5, 3, 1, 9},
                            {5, 1, 7, 6, 9, 3, 2, 8, 4}};
    private boolean[][] given = {{false, true , true , false, false, false, true , false, true },
                                 {false, true , false, false, true , false, false, false, false},
                                 {false, false, true , false, false, true , true , true , false},
                                 {false, false, true , false, false, false, false, false, true },
                                 {false, false, false, true , true , false, true , true , false},
                                 {false, true , false, true , false, true , false, false, false},
                                 {true , true , false, true , true , true , true , false, true },
                                 {true , true , false, true , false, true , true , true , true },
                                 {true , true , false, false, true , false, false, false, false}};
    private Button[][] buttons = new Button[9][9];
    private PadButton[] padButtons = new PadButton[9];
    private int curNum = -1;
    private boolean won;
    
    private float rainbowOffset;
    private long prevTime;
    
    /**
     * constructor for the Sudoku class which 
     * sets the name of the window to "Sudoku Game"
     */
    public Sudoku() {
        super("Sudoku Game");
    }
    
    /**
     * setter method to set the current number that the user
     * has inputted
     * @param value the value to be set to
     */
    public void setVal(int value) {
        curNum = value;
    }
    
    /**
     * getter method for the current value that the
     * user has inputted
     * @return the current value
     */
    public int getVal() {
        return curNum;
    }
    
    /**
     * checks if all the buttons have values that are correct
     * and creates a new win screen if they are
     */
    public void checkWin() {
        boolean win = true;
        for(Button[] line : buttons) {
            for(Button b : line) {
                if(!b.isCorrect()) {
                    win = false;
                }
            }
        }
        if(win) {
            this.createWinScreen();
            this.won = true;
        }
    }
    
    /**
     * creates a new win screen in a separate window
     */
    private void createWinScreen() {
        //new Jframe
        JFrame winScreen = new JFrame("Yay!");
        winScreen.setSize(new Dimension(600, 570));
        
        //contents
        JPanel panel = new JPanel();
        JLabel message = new JLabel("You Win!");
        message.setFont(new Font("Courier", Font.PLAIN, 50));
        ImageIcon cat = new ImageIcon("C:\\Users\\tonyc\\OneDrive\\Documents\\NetBeansProjects\\Sudoku\\src\\main\\java\\sudoku\\cat.jpg");
        
        //add components to frame
        panel.add(message);
        panel.add(new JLabel(cat));
        winScreen.add(panel);
        winScreen.setVisible(true);
    }
    
    /**
     * sets all of the buttons to their correct colors
     * for the rainbow effect in the gamePanel
     */
    public void rainbowGame() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                Button b = buttons[i][j];
                
                //brightness higher if it's the current selected game number
                float brightness;
                if(b.getVal() == curNum) {
                    brightness = 1.00f;
                } else {
                    brightness = 0.55f;
                }
                
                //Sets color of button based off of distance + angle to the center
                float ang = (float)(rainbowOffset + Math.atan2(j-4, i-4) / (2 * Math.PI));
                float dist = (float)Math.sqrt((j-4)*(j-4) + (i-4)*(i-4));
                float scaler1 = 0.08f;
                float scaler2 = 0.04f;
                Color col = Color.getHSBColor(ang, 0.65f - dist * scaler1, brightness - dist * scaler2);
                b.setBackground(col);
            }
        }
        rainbowOffset = (float)(rainbowOffset + Math.PI/2000) % 1;
    }
    
    /**
     * creates the GUI for the Sudoku game
     */
    private void createGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        MouseHandler mh = new MouseHandler(this);
        
        //Layouts and borders
        GridLayout layout = new GridLayout(3, 3);
        BorderLayout borderLayout = new BorderLayout();
        Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE, 3);
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        
        //JPanels
        JPanel mainPanel = new JPanel();
        JPanel gamePanel = new JPanel(layout);
        JPanel sidePanel = new JPanel(borderLayout);
        JPanel numpad = new JPanel(layout);
        //customize JPanels
        numpad.setBorder(whiteBorder);
        gamePanel.setBorder(redBorder);
        sidePanel.setBorder(redBorder);
        sidePanel.setBackground(Color.BLACK);
        mainPanel.setBackground(Color.BLACK);
        
        //fill gamePanel with buttons
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JPanel innerPanel = new JPanel(layout);
                innerPanel.setBorder(whiteBorder);
                for(int n = 0; n < 3; n++) {
                    for(int m = 0; m < 3; m++) {
                        int row = 3*i + n;
                        int col = 3*j + m;
                        //make new button
                        Button b = new Button(grid[row][col], given[row][col]);
                        b.addActionListener(mh);
                        innerPanel.add(b);
                        buttons[row][col] = b;
                    }
                }
                gamePanel.add(innerPanel);
            }
        }
        
        //fill numpad
        for(int i = 0; i < 9; i++) {
            PadButton b = new PadButton(i + 1);
            b.addActionListener(mh);
            numpad.add(b);
            padButtons[i] = b;
        }
        
        
        //numpad label
        JLabel numpadLabel = new JLabel("Number Pad", JLabel.CENTER);
        numpadLabel.setFont(new Font("Courier", Font.PLAIN, 40));
        numpadLabel.setForeground(Color.WHITE);
        numpadLabel.setBorder(whiteBorder);
        
        //add the rest of the panels
        sidePanel.add(numpadLabel, BorderLayout.NORTH);
        sidePanel.add(numpad, BorderLayout.CENTER);
        mainPanel.add(gamePanel);
        mainPanel.add(sidePanel);
        this.add(mainPanel);
        this.pack();
    }
    
    /**
     * highlights the button in the number pad that
     * is a certain value
     * @param num the value to be highlighted
     */
    public void highlightNumpadButton(int num) {
        for(PadButton b : padButtons) {
            b.setHighlighted(b.getVal() == num);
        }
    }
    
    /**
     * getter method for if the game is won
     * @return a Boolean showing if the game is won
     */
    public boolean isWon() {
        return won;
    }
    
    /**
     * this is the main method which creates the Sudoku game
     * @param args Unused
     */
    public static void main(String[] args) {
        Sudoku game = new Sudoku();
//        for(int i = 0; i < 9; i++) {
//            for(int j = 0; j < 9; j++) {
//                if(i != 8 || j != 8) game.given[i][j] = true;
//            }
//        }
        game.createGUI();
        //loop for rainbow game
        while(true) { 
            long curTime = System.currentTimeMillis();
            if(curTime - game.prevTime > 20) {
                game.rainbowGame();
                game.prevTime = curTime;
            }
        }
    }
}
