package sample.simulation;

import static sample.model.Gloabal.allTripValue;

public class Simulation {
    public double startPath;
    public double endPath;

    public double a = 2000000; // start interval
    public double b = 4500000; // end interval

    public double time;
    public double speed;


    public double calculteSpeed(double allTrip) {
        time = a + (Math.random() * b);
        speed = time / allTrip;
        System.out.println("Speed on the this trip is: " + speed + " km/h");
        return speed;
    }

    public double calculateRandomSpeedPivot() {
        startPath = (allTripValue / 2);
        endPath = startPath;
        time = a + (Math.random() * b);
        double speed1 = time / startPath;
        double speed2 = time / endPath + 10;
        speed = (speed1 + speed2) / 2;
        System.out.println("Speed on the this trip is: " + (int) speed + " km/h");
        return (int) speed;
    }

    public double getAllTripValue() {
        return allTripValue;
    }


}
