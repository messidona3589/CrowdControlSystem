import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DynamicDistancing {
    //If entrance is permitted to the selected spot, the function is executed.
    public void checkContactStatus(RestrictedSpots restrictedSpots, int index, double distancePolicy){
        double left, right, front, back; //variables to store the distance in four different directions
        String[] status = {"CLOSE CONTACT", "CAUSAL CONTACT", "DISTANCING CONTACT"}; //status array of close contact, distancing contact and casual contact.
        Scanner sc = new Scanner(System.in);
        //DateTime Java library to store current date and time
        DateTimeFormatter datef = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timef = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //String coloring unicode in terminal
        String Red = "\u001B[31m";
        String Yellow = "\u001B[33m";
        String Green = "\u001B[32m";
        String Reset = "\u001B[0m";

        //Ask the user to key-in the physical distance from the others in four different directions(left, right, font and back)
        System.out.println("Enter in the physical distance(in meters) from other people in the immediate surrounding in four different directions");
        System.out.print("Left : ");
        left = sc.nextDouble();
        System.out.print("Right : ");
        right = sc.nextDouble();
        System.out.print("Front : ");
        front = sc.nextDouble();
        System.out.print("Back : ");
        back = sc.nextDouble();
        System.out.println("------------------------------------------------------------");

        //If distances from all four directions are more than the social distance policy, display message "you are safe in dynamic distancing"
        if (left >= distancePolicy && right >=distancePolicy && front>=distancePolicy && back>=distancePolicy){
            System.out.println(Green + "LOW RISK : You are safe in dynamic distancing!" + Reset); //display warning
            checkMask(left, right, front, back, distancePolicy); //call function checkMask
            System.out.println("------------------------------------------------------------");
            System.out.println("USER STATE"); //display user state
            System.out.println("ID : 20417585\nFull Name : Myungsoo Son\nRestricted Spot ID : " + restrictedSpots.spotID[index] +"\nSpot Name: " + restrictedSpots.spotName[index] + "\nDate : " + datef.format(now) + "\nTime : " + timef.format(now) + "\nContact Status : " + status[2]);
        }
        //if any of the entered distance from any direction is less than the social distance policy, display the user advice
        else {
            // Display the user advice
            System.out.println("USER ADVICE");
            if (left<distancePolicy) System.out.println("Move away " + (distancePolicy-left) + " meters from the left");
            if (right<distancePolicy) System.out.println("Move away " + (distancePolicy-right) + " meters from the right");
            if (front<distancePolicy) System.out.println("Move away " + (distancePolicy-front) + " meters from the front");
            if (back<distancePolicy) System.out.println("Move away " + (distancePolicy-back) + " meters from the back");
            System.out.println("------------------------------------------------------------");

            //If any of the distance in for different directions is less than the half of the distance policy
            if (left<(distancePolicy/2) || right<(distancePolicy/2) || front<(distancePolicy/2) || back<(distancePolicy/2)){
                System.out.println(Red + "HIGH RISK : You are in the close contact!" + Reset); //display warning
                checkMask(left, right, front, back, distancePolicy); //call function checkMask
                System.out.println("------------------------------------------------------------");
                System.out.println("USER STATE"); //display user state
                System.out.println("ID : 20417585\nFull Name : Myungsoo Son\nRestricted Spot ID : " + restrictedSpots.spotID[index] +"\nSpot Name: " + restrictedSpots.spotName[index] + "\nDate : " + datef.format(now) + "\nTime : " + timef.format(now) + "\nContact Status : " + status[0]);
            }
            else { //If not the case in the above to conditions
                System.out.println(Yellow + "NORMAL RISK : You are in the casual contact!" + Reset); // display warning
                checkMask(left, right, front, back, distancePolicy); //call function checkMask
                System.out.println("------------------------------------------------------------");
                System.out.println("USER STATE"); //display user state
                System.out.println("ID : 20417585\nFull Name : Myungsoo Son\nRestricted Spot ID : " + restrictedSpots.spotID[index] +"\nSpot Name: " + restrictedSpots.spotName[index] + "\nDate : " + datef.format(now) + "\nTime : " + timef.format(now) + "\nContact Status : " + status[1]);
            }
        }
        System.out.println("-------------------------------------------------------");

        //call the function displayGuidline
        displayGuidline(distancePolicy);

    }

    public void checkMask(double left, double right, double front, double back, double distancePolicy){
        //Extra function for coloring the string output and alert masking
        //String coloring unicode in terminal
        String Red = "\u001B[31m";
        String Yellow = "\u001B[33m";
        String Green = "\u001B[32m";
        String Reset = "\u001B[0m";
        // if distance contact, then user can take off the mask (with green coloring)
        if (left >= distancePolicy && right >=distancePolicy && front>=distancePolicy && back>=distancePolicy){
            System.out.println(Green + " * You can take off your mask!" + Reset);
        }
        // if close contact, then user must wear KF94 mask (with red coloring)
        else if (left<(distancePolicy/2) || right<(distancePolicy/2) || front<(distancePolicy/2) || back<(distancePolicy/2)){
            System.out.println(Red + " * Please wear KF94 mask!" + Reset);
        }
        // if casual contact, then user must wear mask (with yellow coloring)
        else {
            System.out.println(Yellow + " * Please wear your mask!" + Reset);
        }
    }

    public void displayGuidline(double distancePolicy){
        //Extra function for displaying the guidlines if the user is in at the risk
        //Display the guidlines for social distancing
        System.out.println("SOCIAL DISTANCING GUIDLINES");
        System.out.println("1. The social distancing is restricted to " + distancePolicy +" meters.");
        System.out.println("2. HIGH RISK appears when the user is in the close contact (Less than " + distancePolicy/2 + " meters).");
        System.out.println("3. NORMAL RISK appears when the user is in the casual contact (Between " + distancePolicy/2 + " and " + distancePolicy + " meters).");
        System.out.println("4. LOW RISK appears when the user is in distancing contact (More than " + distancePolicy + " meters).");
        System.out.println();

        //Display the guidlines for mask
        System.out.println("MASK GUIDLINES");
        System.out.println("1. You MUST wear KF94 mask if you are in close contact");
        System.out.println("2. You MUST wear mask if you are in casual contact");
        System.out.println("3. You can take off your mask if you are in distancing contact");
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
}
