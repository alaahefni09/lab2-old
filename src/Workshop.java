import java.util.ArrayList;

public class Workshop<T extends Car> {

    private int capacity;
    private ArrayList<T> cars;

    public Workshop(int capacity) {
        this.capacity = capacity;
        this.cars = new ArrayList<>();
    }

    public void loadCar(T car) {
        if (cars.size() < capacity) {
            cars.add(car);
        }
    }

    public void unloadCar(T car) {
        cars.remove(car);
    }

    public ArrayList<T> getCars() {
        return cars;
    }
}