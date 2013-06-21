/*
* Andrew Frost
* 6/16/13
* J. L. Bhola
* Assignment 1
 */
import java.util.Arrays;
import java.util.Scanner;

/**
 * The purpose of this program is to simulate an elevator going up and then down.
 * The building has 12 floors and starts at floor 1 making 8 stops while going  
 * up and then makes up to 5 stops while going down.
 * The values for the stops are stored into two separate arrays and then sorted.
 * If the first stop for the down elevator is above the last stop of the up 
 * elevator then the elevator will first
 *  go up to retrieve the people on the highest floor
 * before continuing down. The elevator takes 2 seconds to go up a floor
 * and waits for 3 seconds on the floors that it makes stops on.
 * Loops are used to fill the arrays with random numbers and also to then
 * go through those arrays when going up and down the elevator.
 * 
 * In order to use the program all you have to do is run it and then watch
 * it will do everything on its own and then go through the floors first up
 * then down. When it is finished it will ask the user for input as to whether
 * or not they wish it to continue and then according to that input it will run
 * again or close.
 * 
 * The output is a very concise telling of what the elevator is doing and where
 * it is as it goes up and down the building.
 */

public class Elevator{
    public static void main(String[] args) {
/* 
 * This part of the program is used to determine whether the user will continue
*  running the program by using a boolean value in a while loop that 
*  has the call to the elevatorrun method.
*  It starts out true so that the program will execute 
*  the first time and then ask the user whether or not they want it to repeat.
*/
         
        boolean run = true;
        String ans;
        Scanner br = new Scanner(System.in);
        while(run){
        elevatorrun();
        System.out.println("Do you want to run the elevator "
                + "again?'Y or y' continue. 'N or n' stop");
        ans = br.next();
        if(ans.equals("y")||ans.equals("Y"))
            run = true;
        else if(ans.equals("n")||ans.equals("N"))
            run = false;
        else{
            System.out.println("Next time follow the directions!");
            run = false;
        }
        }
    }
    /* 
     * This method is used when waiting on a floor for 3 seconds and will
     * count to 3 displaying each number. This is done using the for loop and 
     * displaying I for the second and only doing 1 second each iteration
     */
    public static void timer1(){
        for(int i=1;i<=3;i++){
        try {
            System.out.print(" "+ i);
            Thread.sleep(1000);
        }
        catch (InterruptedException ie) {
            // Handle the exception
        }
        }
    }
    /* 
     * This method is used when moving between floors to wait 2 seconds
     * It just waits for 2 seconds and there is nothing special about it.
     * This is called by each time the elevator going up or down a floor
     */
    public static void timer2(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ie) {
            // Handle the exception
        }
    }
    /*
     * This is the method that does all of the work for the elevator
     * and is called by the main method.
     */
    public static void elevatorrun(){
        //Array declarations with enough spots for the stops
        int [] UPARRAY = new int [8];
        int [] DOWNARRAY = new int [5];
        /*
         * This is for the randomization of floors to stop on while going up
         * without repetition of floors.
         */
        for(int i=0; i<8; i++){
        int random = 0;
        boolean repeat = true;
            while(repeat){
            //This line generates one of the floors between 2 and 12
            random = (int)(Math.random()*11+2);
/*
 * This loop is what checks for whether or not the number is repeated
 * if there is repetition it sets repeat to true and breaks from the loop
 * a new random number is generated and then runs again
 * the only time a number is kept is if it is false for each run of the loop
 * and then it is placed in the array at position i.
 */
                for(int k=0; k<=i; k++){
                 if(UPARRAY[k] == random){
                   repeat = true;
                   break;
                  }
                 else if(UPARRAY[k] != random)
                  repeat = false;
        }
        }
        UPARRAY[i] = random;
        }
        //This is the same as what is used above except for the down array.
        for(int i=0; i<5; i++){
        int random = 0;
        boolean repeat = true;
            while(repeat){
            random = (int)(Math.random()*12+1);
                for(int k=0; k<=i; k++){
                 if(DOWNARRAY[k] == random){
                   repeat = true;
                   break;
                  }
                 else if(DOWNARRAY[k] != random)
                  repeat = false;
        }
        }
        DOWNARRAY[i] = random;
        }
        /*
         * These are part of the java.util.arrays class and sort the arrays
         * in ascending order.
        */
        Arrays.sort(UPARRAY);
        Arrays.sort(DOWNARRAY);
        /* Uncomment this if you want to see the arrays after they've been made.
        for(int i=0;i<UPARRAY.length;i++)
            System.out.println("up" + UPARRAY[i]);
        for(int i=0;i<DOWNARRAY.length;i++)
            System.out.println("down" + DOWNARRAY[i]);
         */
        /* 
         * This variable is used to keep track of what floor the elevator is on
         * and is set to 2 to start because the first loop it is used in 
         * doesn't add 1 to the floor until after saying which floor it is on
        */
        int floor = 2;
        /*
         * This loop is used when the elevator is going up and runs 8 times
         * one for each stop that is on the up array.
         */
        for(int k=0;k<8;k++){
        if(k==0)
        System.out.println("Starting at floor 1");
        else
        System.out.println("Starting at floor " + UPARRAY[k-1]);
        //This loop is used for going up floors and calls the timer 2 method.
        for(;floor<=UPARRAY[k];floor++){
            System.out.print("Going up: ");
            timer2();
        System.out.println("now at floor " + floor);
        }
        /*
         * This is the end of the for function for going up and says the floor
         * that is being stopped on and calls the timer1 function so that it can
         * wait 3 seconds before moving on to the next iteration of the loop
         */
        System.out.print("Stopping at floor " + (floor-1) + " for 3 seconds");
        timer1();
        System.out.println();
        }
        
        //Here we set the floor equal to the top floor of the uparray.
        floor = UPARRAY[7];
        
        
        /*
         * This is the loop for going down and is very much the same as the
         * up loops but with some if statements at the beginning to handle
         * some different scenarios
         */
        for(int k=5;k>0;k--){
        if(k==5){
        System.out.println("Starting at floor " + UPARRAY[7]);
        /* 
         * This line is to handle if the last stop on up and first on down are
         * the same and decrements the loop counter so it can skip on to the
         * the next floor in the array.
         */
        if(DOWNARRAY[k-1]==UPARRAY[7])
            k--;
        }else
        System.out.println("Starting at floor " + DOWNARRAY[k]);
        /*
         * This statment is used only when the first stop on the down is higher
         * than the last stop on the up array so that the elevator will go up
         * instead of down.
         */
        if(DOWNARRAY[k-1]>UPARRAY[7]){
            for(;floor<=DOWNARRAY[k-1];floor++){
            System.out.print("Going up: ");
            timer2();
        System.out.println("now at floor " + floor);
        }
     System.out.print("Stopping at floor " + DOWNARRAY[k-1] + " for 3 seconds");
        timer1();
        System.out.println();
        }else{
        /*
         * This is the same as the uparray version except with a different
         * setup to adjust for the fact that the array is going from top to
         * bottom and because I couldn't figure out how to sort in 
         * descending order.
         */
        for(;floor>DOWNARRAY[k-1];floor--){
            System.out.print("Going down: ");
            timer2();
            System.out.println("now at floor " + (floor - 1));
        }
     System.out.print("Stopping at floor " + DOWNARRAY[k-1] + " for 3 seconds");
        timer1();
        System.out.println();
        }
        }
        //This is the last line before exiting the method!
        System.out.println("Thank you for riding the elevator!");
        }
}