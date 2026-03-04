import java.util.ArrayList;

public class CarSimulation {

    private ArrayList<Car> cars;
    private Workshop<Volvo240> volvo240Workshop;

    public CarSimulation(ArrayList<Car> cars) {
        this.cars = cars;
        this.volvo240Workshop = new Workshop<>(5);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void tick(int panelWidth, int panelHeight) {

        int carWidth = 100;
        int carHeight = 60;

        for (Car car : cars) {

            // Om Volvo redan är i workshop → hoppa över
            if (car instanceof Volvo240 volvo && volvo.isLoadedInWorkshop()) {
                continue;
            }

            // Flytta bilen
            car.move();

            // Studsa mot vänster vägg
            if (car.getX() < 0) {
                car.setX(0);
                bounce(car);
            }

            // Studsa mot höger vägg
            if (car.getX() > panelWidth - carWidth) {
                car.setX(panelWidth - carWidth);
                bounce(car);
            }

            // Studsa mot övre vägg
            if (car.getY() < 0) {
                car.setY(0);
                bounce(car);
            }

            // Studsa mot nedre vägg
            if (car.getY() > panelHeight - carHeight) {
                car.setY(panelHeight - carHeight);
                bounce(car);
            }

            // Ladda Volvo i workshop
            if (car instanceof Volvo240 volvo) {

                if (!volvo.isLoadedInWorkshop()
                        && Math.abs(volvo.getX() - 0) < 50
                        && Math.abs(volvo.getY() - 465) < 50) {

                    volvo.stopEngine();
                    volvo240Workshop.loadCar(volvo);
                    volvo.setLoadedInWorkshop(true);
                }
            }
        }
    }

    private void bounce(Car car) {
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
    }
}