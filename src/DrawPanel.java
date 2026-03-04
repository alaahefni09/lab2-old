import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {


    ArrayList<Car> cars;

    private Map<String, BufferedImage> spriteCache = new HashMap<>();
    // To keep track of a single car's position
    ArrayList<Point> carPoints = new ArrayList<>();
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(0, 465); // same Y as Volvo starting position


    void moveit(int index, int x, int y) {

        while (carPoints.size() <= index) {
            carPoints.add(new Point());
        }

        carPoints.get(index).x = x;
        carPoints.get(index).y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {

            spriteCache.put( "volvo240",
                    ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg")));

            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
            spriteCache.put("saab95",
                    ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg")));
            spriteCache.put("Scania",
                    ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg")));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself

    @Override
    protected void paintComponent(Graphics g){
    super.paintComponent(g);
    for (int i = 0; i < cars.size(); i++) {
        if (i >= carPoints.size()) continue;

        Car car = cars.get(i);

        if (car instanceof Volvo240 volvo && volvo.isLoadedInWorkshop()) {
            continue;
        }
        Point p = carPoints.get(i);

        BufferedImage sprite = spriteCache.get(car.getSpriteKey());

        if (sprite != null) {
            g.drawImage(sprite, p.x, p.y, null);
        }


        }

    g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

    }


}