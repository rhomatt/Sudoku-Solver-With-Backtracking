package Model;

public class Col {
    private int[] numbers;

    public Col(){
        this.numbers = new int[10];
    }

    /**
     * Make a deep copy of a col
     * @param col
     */
    public Col(Col col){
        this.numbers = new int[10];
        for (int i = 0; i < 10; i++){
            this.numbers[i] = col.numbers[i];
        }
    }

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

    public boolean isComplete(){
        if(!isValid()){
            return false;
        }

        return this.numbers[0] == 0;
    }

    //We added some number to this col.
    public void addNumber(int number){
        this.numbers[number]++;
        this.numbers[0]--;
    }

    public void removeNumber(int number){
        this.numbers[number]--;
        this.numbers[0]++;
    }
}
