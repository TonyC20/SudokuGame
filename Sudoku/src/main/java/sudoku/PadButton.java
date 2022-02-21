package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 * the button that is used for the number pad in the Sudoku game
 * @author tonyc
 */
public class PadButton extends JButton{
    private int value;
    
    /**
     * the constructor for the PadButton class
     * @param value the value of the PadButton
     */
    
    public PadButton(int value) {
        this.value = value;
        this.setText(String.valueOf(value));
        
        //sets the look of the button
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Courier", Font.PLAIN, 60));
    }
    
    /**
     * getter method for the value of the button
     * @return the value of the button
     */
    public int getVal() {
        return value;
    }
    
    /**
     * highlights or removes the highlight from the button
     * @param state if to highlight the button
     */
    public void setHighlighted(boolean state) {
        if(state) {
            this.setBackground(Color.DARK_GRAY);
        } else {
            this.setBackground(Color.BLACK);
        }
    }
}
