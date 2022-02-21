package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this is the MouseHandler for the Sudoku game, it handles
 * all of the buttons in the game.
 * @author tonyc
 */
public class MouseHandler implements ActionListener{
    private Sudoku game;
    
    /**
     * constructor for the MouseHandler class
     * @param game the game that the MouseHandler is responsible for
     */
    public MouseHandler(Sudoku game) {
        this.game = game;
    }
    
    /**
     * takes in an action event whenever a button is pressed and 
     * does the corresponding action.
     * @param e the action event that happened
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Button) {
            //sets the value of the button to the current selected value
            Button b = (Button)e.getSource();
            int curVal = game.getVal();
            if(!b.isGiven() && curVal != -1 && !game.isWon()) {
                b.setVal(curVal);
                game.checkWin();
            }
        } else {
            //sets the current game value
            PadButton b = (PadButton)e.getSource();
            int buttonVal = b.getVal();
            game.setVal(buttonVal);
            game.highlightNumpadButton(buttonVal);
        }
    }
}
