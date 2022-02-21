package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 * this is the button for the main game panel in the
 * Sudoku game.
 * @author tonyc
 */
public class Button extends JButton{
    private boolean given;
    private int answer;
    private int value;
    
    /**
     * constructor for the button class that displayed the value of the button if it is given
     * @param answer the correct value of the button
     * @param given if the button is displayed at the start of the game
     */
    public Button(int answer, boolean given) {
        this.answer = answer;
        this.given = given;
        
        if(given) {
            this.setForeground(Color.WHITE);
            this.setVal(answer);
        } else {
            Color col = Color.getHSBColor(0, 0.2f, 1f);
            this.setForeground(col);
        }
        
        //sets the look of the button
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(80, 80));
        this.setFont(new Font("Courier", Font.BOLD, 50));
    }
    
    /**
     * setter method that sets the shown value of the button
     * @param value what the button is set to
     */
    public void setVal(int value) {
        this.value = value;
        this.setText(String.valueOf(value));
    }
    
    /**
     * getter method that gets the shown value of the button
     * @return returns the value the button is set to
     */
    public int getVal() {
        return value;
    }
    
    /**
     * getter method that returns if the button
     * is given at the beginning of the game
     * @return a Boolean showing if the button is given
     */
    public boolean isGiven() {
        return given;
    }
    
    /**
     * checks if the button has the correct value showing
     * @return a Boolean showing if the button is correct
     */
    public boolean isCorrect() {
        return value == answer;
    }
}
