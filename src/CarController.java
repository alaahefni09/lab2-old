import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities is to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {

    private final int delay = 50;

    private Timer timer = new Timer(delay, new TimerListener());

    private CarSimulation sim;
    CarView frame;
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        cc.cars.get(0).setX(0); cc.cars.get(0).setY(0);
        cc.cars.get(1).setX(0); cc.cars.get(1).setY(100);
        cc.cars.get(2).setX(0); cc.cars.get(2).setY(200);

        cc.sim = new CarSimulation(cc.cars);

        cc.frame = new CarView("CarSim 1.0", cc);

        for(int i = 0; i < cc.cars.size(); i++){
            cc.frame.drawPanel.moveit(
                    i,
                    (int) cc.cars.get(i).getX(),
                    (int) cc.cars.get(i).getY());

        }

        // Start the timer
        cc.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sim.tick(
                    frame.drawPanel.getWidth(),
                    frame.drawPanel.getHeight()
            );

            for (int i = 0; i < cars.size(); i++ ){
                frame.drawPanel.moveit(
                        i,
                        (int) cars.get(i).getX(),
                        (int) cars.get(i).getY()
                );

            }

            frame.repaint();
        }

    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.gas(gas);
        }
    }

    void setTurboOn(){
        for (Car car: cars) {
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void setTurboOff(){
        for (Car car: cars){
            if (car instanceof Saab95){
                Saab95 saab = (Saab95) car;
                saab.setTurboOff();
            }
        }
    }

    void lowerFlak(double angle){
        for (Car car: cars){
            if (car instanceof Scania){
                Scania scania = (Scania) car;
                scania.lowerFlak(angle);
            }
        }
    }

    void raiseFlak (double angle) {
        for (Car car : cars) {
            if (car instanceof Scania) {
                Scania scania = (Scania) car;
                scania.raiseFlak(angle);
            }
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }

    void startAllCars(){
        for(Car car: cars) {
            car.startEngine();
        }
    }

    void stopAllCars(){
        for(Car car: cars) {
            car.stopEngine();
        }
    }


}