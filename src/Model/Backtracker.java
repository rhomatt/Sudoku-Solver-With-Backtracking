package Model;

import View.Controller;

import java.util.Stack;

public class Backtracker {
    private Stack<Board> boards;
    private Controller controller;

    /**
     * Create the backtracker
     */
    public Backtracker(Controller controller){
        this.boards = new Stack<>();
        this.controller = controller;
    }

    public boolean solve(Board board){
        //Push the board to the stack
        boards.push(board);

        //While the stack isn't empty
        while(!boards.empty()){
            //A reference to the topmost board on the stack
            Board topBoard = boards.peek();
            //Stop if we have a complete board
            if(topBoard.isComplete()){
                //Maybe do stuff
                System.out.println(topBoard);
                controller.setBoard(topBoard);
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        controller.setButtonText(topBoard.getNumber(i, j), i, j);
                    }
                }
                return true;
            }

            boards.pop();
            if(topBoard.isValid()){
                System.out.println(topBoard);
                for(int i = 9; i > 0; i--){
                    //Place it in the first empty spot
                    Board newBoard = new Board(topBoard);
                    newBoard.placeInEmptySpot(i);
                    if(newBoard.isValid()) {
                        boards.push(newBoard);
                    }
                }
            }
        }
        System.out.println("No solution");
        return false;
    }

}
