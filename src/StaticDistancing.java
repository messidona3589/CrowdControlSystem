
import java.util.Scanner;
public class StaticDistancing extends DynamicDistancing{
    //StaticDistancing inherits DynamicDistancing to determine the casual contact or the contact status of a user

    public int menuForAreas(){
        //Ask user to key-in into which restricted spot in the hospital he or she would like to enter
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter in the number of the restricted spots you want to enter:");
        System.out.println("1.Out-Patient Visitor's Main Waiting Area");
        System.out.println("2.Out-Patient Visitor's Sub Waiting Area");
        System.out.println("3.Intensive Care Unit Visiting Area");
        System.out.println("4.In-Patient Visitor's Waiting Area");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------------------------------");
        System.out.print("Choice : ");
        int visitArea = sc.nextInt();
        int index;

        //switch-case statement to assign index variable
        switch (visitArea){
            case 1:
                index = 0;
                break;
            case 2:
                index = 1;
                break;
            case 3:
                index = 2;
                break;
            case 4:
                index = 3;
                break;
            case 5:
                index = 4;
                break;
            default:
                index = -1;
                break;
        }
        System.out.println("------------------------------------------------------------");
        return index;
    }
    public boolean isPermitted(RestrictedSpots restrictedSpots, int index){
        //Determine if the maximum capacity of the selected spot is reached or not
        //then gives permission or non-permission for entrance into the selected restricted spot
        int currentVisitor = restrictedSpots.currentVisitors[index];
        int maximumCapacity = restrictedSpots.spotMaximumCapacity[index];

        //Based on the number of current visitors in the selected restricted spot
        if (currentVisitor < maximumCapacity){ //if the maximum capacity is not reached, display the available space
            System.out.println("Permitted : There are " + currentVisitor + " visitors out of " + maximumCapacity);
            System.out.println("--------------------------------------------------------------");
            return true;
        }
        else { //if the maximum capacity is reached, display the not-permitted statement
            System.out.println("Not Permitted : maximum capacity has reached");
            System.out.println("--------------------------------------------------------------");
            return false;
        }
    }

    public boolean wantToWait(RestrictedSpots restrictedSpots, int index){
        //If entrance is not permitted to the selected spot, this function is called
        //it displays the average time and ask the user if he or she wishes to wait for that long
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("Waiting Average Time is " + restrictedSpots.spotPermittedAverageTime[index] + " minutes");
            System.out.println("Do you want to wait? 1.Yes 2.No");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Choice : ");
            int answer = sc.nextInt();
            System.out.println("--------------------------------------------------------------");

            //If user’s answer is ‘Yes’, let the user enter the spot
            //(we assume that the waiting time is reached or over and user is allowed to enter to the spot)
            if (answer==1){
                System.out.println("You may enter in " + restrictedSpots.spotName[index]);
                System.out.println("--------------------------------------------------------------");
                return true;
            }
            //if user’s answer is ‘No’, display the menu to allow user to choose
            //if he or she wishes to visit another restricted spot within that hospital
            else if (answer==2){
                System.out.println("You choose to leave.");
                System.out.println("--------------------------------------------------------------");
                return false;
            }
            else { //go to while loop again til the user enter in the correct number
                System.out.println("Please enter in the correct number");
                System.out.println("--------------------------------------------------------------");
            }
        }
    }

    public boolean wantRevisit(){
        //This function checks the user whether he or she wants to visit different place or not
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Do you want to visit different place? 1.Yes 2.No");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Choice : ");
            int answer = sc.nextInt();
            System.out.println("--------------------------------------------------------------");
            if (answer==1){ //if the user wants to visit different place
                return true;

            } else if (answer==2){ //if the user does not want to visit
                System.out.println("Thank you for using our app");
                return false;
            } else { //if the user enters the wrong number, goto the while loop again
                System.out.println("Please enter in the correct number.");
                System.out.println("--------------------------------------------------------------");
            }
        }

    }

    public static void main(String[] args) {
        //make the objects of restricted spots and static distancing
        RestrictedSpots restrictedSpots = new RestrictedSpots();
        StaticDistancing staticDistancing = new StaticDistancing();
        int index;
        boolean again = true;

        while(again){
            //shows menu of locations for the user to visit
            index = staticDistancing.menuForAreas();

            //if wrong menu number choosen then try again
            if (index==-1) {
                System.out.println("Enter in the Correct Number");
                System.out.println("-----------------------------------------------------");
            }
            //if exit choosen from the menu then finish the program
            else if (index==4) {
                System.out.println("Thank you for using our app.");
                break;
            }
            else { //else continue the process
                if (staticDistancing.isPermitted(restrictedSpots, index)){ //if the user is permitted to enter then check contact status
                    staticDistancing.checkContactStatus(restrictedSpots, index, restrictedSpots.distancePolicy);
                }
                else { //if the user is not permitted to enter then ask if the user wants to wait
                    if (staticDistancing.wantToWait(restrictedSpots, index)){ //if the user wants to wait then check the contact status
                        staticDistancing.checkContactStatus(restrictedSpots, index, restrictedSpots.distancePolicy);
                    }
                }
                //ask the user to visit different place
                again = staticDistancing.wantRevisit();
            }
        }
    }
}
