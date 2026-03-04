import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching its controller in its state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of its components.
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;

    // The controller member
    CarController carC;

    DrawPanel drawPanel;

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JPanel brakePanel = new JPanel();
    JSpinner brakeSpinner = new JSpinner();
    int brake = 0;
    JLabel brakeLabel = new JLabel(" Brake ");

    JPanel anglePanel = new JPanel();
    JSpinner angleSpinner = new JSpinner();
    double angle = 0;
    JLabel angleLabel = new JLabel("Scania lower Bed");

    JPanel raiseAnglePanel = new JPanel();
    JSpinner raiseAngleSpinner = new JSpinner();
    double raiseAngle = 0;
    JLabel raiseAngleLabel = new JLabel("Scania raise Bed");


    // Buttons

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(String framename, CarController cc){
        this.carC = cc;// controller
        this.drawPanel = new DrawPanel(X, Y-240, cc.cars);
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        this.add(drawPanel);
        drawPanel.setBounds(0,0,800,560);
        this.add(Box.createHorizontalStrut(800));



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        SpinnerModel brakeModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        brakeSpinner = new JSpinner(brakeModel);
        brakeSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                brake = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        SpinnerModel angleRaiseModel =
                new SpinnerNumberModel(0,
                        0,
                        70,
                        1);
        angleSpinner = new JSpinner(angleRaiseModel);

        angleSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                angle = (int) ((JSpinner) e.getSource()).getValue();

            }
        });

        SpinnerModel angleLowerModel =
                new SpinnerNumberModel(0,
                        0,
                        70,
                        1);
        raiseAngleSpinner = new JSpinner(angleLowerModel);

        raiseAngleSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                raiseAngle = (int) ((JSpinner) e.getSource()).getValue();

            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        anglePanel.setLayout(new BorderLayout());
        anglePanel.add(angleLabel, BorderLayout.PAGE_START);
        anglePanel.add(angleSpinner, BorderLayout.PAGE_END);
        this.add(anglePanel);

        raiseAnglePanel.setLayout(new BorderLayout());
        raiseAnglePanel.add(raiseAngleLabel, BorderLayout.PAGE_START);
        raiseAnglePanel.add(raiseAngleSpinner, BorderLayout.PAGE_END);
        this.add(raiseAnglePanel);

        brakePanel.setLayout(new BorderLayout());
        brakePanel.add(brakeLabel, BorderLayout.PAGE_START);
        brakePanel.add(brakeSpinner, BorderLayout.PAGE_END);
        this.add(brakePanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.setTurboOn();
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.setTurboOff();
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                carC.raiseFlak(raiseAngle);
            }

        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.brake(brake);
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.lowerFlak(angle);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.startAllCars();

            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.stopAllCars();

            }
        });

        this.add(drawPanel);


        this.pack();
        this.setSize(800, 900);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}