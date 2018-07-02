package Model;

public class Row {
    private int[] numbers;

    /**
     * Make a row
     */
    public Row(){
        this.numbers = new int[10];
        //The 0 index will keep track of the empty spaces
        this.numbers[0] = 9;
    }

    /**
     * Make a deep copy of a row
     * @param row
     */
    public Row(Row row){
        this.numbers = new int[10];
        for (int i = 0; i < 10; i++){
            this.numbers[i] = row.numbers[i];
        }
    }

    /**
     * Valid?
     * @return t/f
     */
    public boolean isValid(){
        for (int num = 0; num <= 9; num++) {
            if(num == 0){
                continue;
            }
            //Are there too many 1's, 9's, etc.
            if(numbers[num] > 1){
                return false;
            }
        }
        return true;
    }

    /**
     * Complete and valid?
     * @return t/f
     */
    public boolean isComplete(){
        if(!isValid()){
            return false;
        }

        return this.numbers[0] == 0;
    }

    /**
     * We added some number to this row
     * @param number
     */
    public void addNumber(int number){
        this.numbers[number]++;
        this.numbers[0]--;
    }

    /**
     * Remove a number. We shouldn't actually have to ever use this
     * @param number
     */
    public void removeNumber(int number){
        this.numbers[number]--;
        this.numbers[0]++;
    }
}
