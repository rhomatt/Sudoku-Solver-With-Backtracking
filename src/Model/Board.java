package Model;

import View.Controller;

public class Board {
    private Row[] rows;
    private Col[] cols;
    private Cell[][] cells;
    private int[][] grid;
    private int[] numbers;
    private Controller controller;

    /**
     * Make a board
     */
    public Board(Controller controller){
        //Imitate the actual sudoku board
        this.rows = new Row[9];
        this.cols = new Col[9];
        for(int i = 0; i < 9; i++){
            this.rows[i] = new Row();
            this.cols[i] = new Col();
        }

        this.cells = new Cell[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.cells[i][j] = new Cell();
            }
        }
        this.grid = new int[9][9];
        this.numbers = new int[10];
        this.numbers[0] = 81;
        this.controller = controller;
        controller.setBoard(this);
    }

    /**
     * Make a deep copy of a board
     * @param board
     */
    public Board(Board board){
        this.rows = new Row[9];
        this.cols = new Col[9];
        for(int i = 0; i < 9; i++){
            this.rows[i] = new Row(board.rows[i]);
            this.cols[i] = new Col(board.cols[i]);
        }
        this.cells = new Cell[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.cells[i][j] = new Cell(board.cells[i][j]);
            }
        }
        this.grid = new int[9][9];
        //Copy the int values of the numbers on the grid
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.grid[i][j] = board.grid[i][j];
            }
        }
        this.numbers = new int[10];
        //Copy the int values of the number array of the board we are trying to copy
        for(int i = 0; i < 10; i++){
         this.numbers[i] = board.numbers[i];
        }
        this.controller = board.controller;
    }

    public int getNumber(int row, int col){
        return grid[row][col];
    }

    /**
     * Place a num on a spot of the board
     * @param num the num to place
     * @param row
     * @param col
     */
    public void placeNumber(int num, int row, int col){
        this.rows[row].addNumber(num);
        this.cols[col].addNumber(num);
        this.cells[row/3][col/3].addNumber(num);
        this.grid[row][col] = num;
        this.numbers[num]++;
        this.numbers[0]--;
    }

    /**
     * Place a number on the board optimally. Otherwise place a given default number
     * @param num the default number to place
     * @return true if placed optimally, false if not
     */
    public boolean placeInEmptySpot(int num){//TODO
        //Look at all the rows, then if we find a row that has 8 filled, look for the corresponding column

        defaultPlace(num);
        return false;
    }

    private void defaultPlace(int num){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(grid[i][j] == 0){
                    placeNumber(num, i, j);
                    return;
                }
            }
        }

    }

    public void removeNumber(int row, int col){
        int num = this.grid[row][col];
        this.rows[row].removeNumber(num);
        this.cols[col].removeNumber(num);
        this.cells[row/3][col/3].removeNumber(num);
        this.grid[row][col] = 0;
        this.numbers[num]--;
        this.numbers[0]++;
    }

    /**
     * Is this a valid iteration of the board?
     * @return t/f
     */
    public boolean isValid(){
        //Are all the rows valid?
        for (Row row : rows) {
            if(!row.isValid()){
                return false;
            }
        }
        //Are all the cols valid?
        for (Col col : cols) {
            if(!col.isValid()){
                return false;
            }
        }
        //Are all the cells valid?
        for(int i = 0; i < 3; i++){
            for (Cell cell : cells[i]) {
                if(!cell.isValid()){
                    return false;
                }
            }
        }

        //If there are too many numbers, don't count the 0's (placeholders for empty space)
        for (int num = 0; num <= 9; num++) {
            if(num == 0){
                continue;
            }
            if(numbers[num] > 9){
                return false;
            }
        }

        return true;
    }

    /**
     * Is this a complete, valid iteration of the board?
     * @return t/f
     */
    public boolean isComplete(){
        if(!isValid()){
            return false;
        }

        return this.numbers[0] == 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(this.getNumber(i, j));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
