package sample.simulation;

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

}
