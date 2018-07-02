package View;
import javafx.scene.control.Button;

import javax.swing.*;

/**
 * This should be the numberBox that we add to the view
 * Can either be empty or contain a number from 1 to 9
 */
public class NumberBox extends Button{
    private int number;
    private int row;
    private int col;
    private Controller controller;

    public NumberBox(int row, int col){
        super();
        this.setMinWidth(50);
        this.setMinHeight(50);
        this.row = row;
        this.col = col;
        this.number = 0;
    }

    /**
     * Helper method for the while loop
     * @return t/f
     */
    private boolean isNumberValid(){
        return this.number >= 0 && this.number <= 9;
    }

    /**
     * Add a controller
     * @param controller
     */
    public void addController(Controller controller){
        this.controller = controller;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setNumber(int value){
        number = value;
        if(number == 0){
            this.setText("");
        }else {
            this.setText(String.valueOf(value));
        }
    }

    @Override
    public void fire(){
        do {
            try{
                number = Integer.parseInt(JOptionPane.showInputDialog("Enter a number from 1-9. Enter 0 to reset the box"));
                if(number == 0){
                    if(!this.getText().equals("")){
                        this.setText("");
                        this.controller.removeNumber(row, col);
                    }
                }
                else if(number > 0 && number <= 9){
                    if(!this.getText().equals("")){
                        //There is something that is here, remove it and replace it (because of how empty spaces are tracked)
                        this.controller.removeNumber(row, col);
                        this.controller.placeNumber(number, row, col);
                    }else{
                        this.controller.placeNumber(number, row, col);
                    }
                    this.setText(String.valueOf(number));
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid or no input");
            }
        }while(!isNumberValid());
    }

}
