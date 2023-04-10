import java.util.Random;

public class RestrictedSpots { //Stores 4 locations in a hospital

    double humanRadius = 1; //assume that human body radius is 1 meter
    double distancePolicy = 2; //social distance policy is 2 meters

    //Arrays for spot data : Spot ID, Spot Name, Spot Area, Spot Permitted Average Time, Spot Maximum Capacity, Current Visitors
    String[] spotID = {"1", "2", "3", "4"};
    String[] spotName = {"Out-Patient Visitor's Main Waiting Area", "Out-Patient Visitor's Sub Waiting Area", "Intensive Care Unit Visiting Area", "In-Patient Visitor's Waiting Area"};
    int[] spotArea = {100, 200, 300, 400, 500}; //in meter square
    int[] spotPermittedAverageTime = {10, 20, 30, 40, 50}; //in minutes
    int[] spotMaximumCapacity = {getSpotMaximumCapacity(spotArea[0]), getSpotMaximumCapacity(spotArea[1]), getSpotMaximumCapacity(spotArea[2]), getSpotMaximumCapacity(spotArea[3])};
    int[] currentVisitors = {getCurrentVisitors(spotMaximumCapacity[0]), getCurrentVisitors(spotMaximumCapacity[1]), getCurrentVisitors(spotMaximumCapacity[2]), getCurrentVisitors(spotMaximumCapacity[3])};

    public int getSpotMaximumCapacity(int area) { //Caculate maximum capacity based on spot's area and social distancing guidlines
        double humanSpace = Math.pow(distancePolicy + humanRadius,2) * Math.PI; //one person takes space of body size and social distance
        return (int)(area/humanSpace);
    }

    public int getCurrentVisitors(int maximumCapacity){ //Random number generator to allocate number of current visitors
        Random random = new Random();
        return random.nextInt(maximumCapacity+1); //Between 0 and maximum capacity of a restricted spot
    }
}