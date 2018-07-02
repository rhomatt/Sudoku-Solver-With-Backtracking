package View;

import Model.Backtracker;
import Model.Board;

/**
 * The controller. Controls communication between the view and the model
 * @author Matthew
 */
public class Controller {
    private NumberBox[][] numberBoxes;
    private Board board;
    private Backtracker backtracker;

    public Controller(){
        this.numberBoxes = new NumberBox[9][9];
        backtracker = new Backtracker(this);
    }

    public void solve(){
        //Backtrack
        backtracker.solve(board);
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public void addNumberBox(NumberBox numberBox){
        this.numberBoxes[numberBox.getRow()][numberBox.getCol()] = numberBox;
    }

    public NumberBox getNumberBox(int row, int col){
        return numberBoxes[row][col];
    }

    public void setButtonText(int num, int row, int col){
        this.getNumberBox(row, col).setNumber(num);
    }

    public void placeNumber(int num, int row, int col){
        this.board.placeNumber(num, row, col);
    }

    public void removeNumber(int row, int col){
        this.board.removeNumber(row, col);
    }

}
