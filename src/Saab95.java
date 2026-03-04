import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn = false;

    public Saab95() {
        super(2, Color.red, 125, "Saab95");
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }


    public void setTurboOff() {

        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = turboOn ? 1.3 : 1.0;
        return enginePower * 0.01 * turbo;
    }

    @Override
    protected void incrementSpeed(double amount) {

        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    // Vi räknar ut en ny hastighet och väljer den minsta eftersom det kan aldrig överstiga enginePower.

    @Override
    protected void decrementSpeed(double amount) {
//Math.max() returnerar det största av 2 tal. , hastigheten här kan aldrig bli neg.
        currentSpeed = Math.max(
                getCurrentSpeed() - speedFactor() * amount,
                0
        );
    }

    @Override
    public String getSpriteKey() {
        return "saab95";
    }
}