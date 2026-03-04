import java.awt.*;

public abstract class Car implements Movable {

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    protected double x;
    protected double y;
    protected Direction direction;


    protected Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.currentSpeed = 0;
        this.modelName = modelName;
        this.x = 0;
        this.y = 0;
        this.direction = Direction.NORTH;
    }

    public Direction getDirection() {
        return direction;
    }

    public abstract String getSpriteKey();


    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public int getNrDoors() {
        return nrDoors;
    }
    public double getX(){ //lägg till getters för att kunna testa
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public double getY(){
        return y;
    }
    protected double getEnginePower() {
        return enginePower;
    }

    protected double getCurrentSpeed() {
        return currentSpeed;
    }

    protected Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    protected abstract void incrementSpeed(double amount);

    protected abstract void decrementSpeed(double amount);
//Abstract används för att car vet inte hur hastigheten
// ska minskas bara att den måste minskas.

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }


    public void gas(double amount) {
        if (amount < 0) {
            amount = 0;
        } else if (amount > 1) {
            amount = 1;
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        amount = Math.max(0, Math.min(1, amount));
        decrementSpeed(amount);
    }


    @Override
    public void move() {
        switch (direction) {
            case NORTH:
                y += currentSpeed;
                break;
            case SOUTH:
                y -= currentSpeed;
                break;
            case EAST:
                x += currentSpeed;
                break;
            case WEST:
                x -= currentSpeed;
                break;
        }
    }

    @Override
    public void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }

    }

    @Override
    public void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }
}