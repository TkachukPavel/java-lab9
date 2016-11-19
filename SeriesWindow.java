import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by tkachukp on 17.11.16.
 */
public class SeriesWindow extends JFrame {

    private JTextField textFieldBase;
    private JTextField textFieldIncrement;
    private JTextField textFieldAmount;

    private String stringSeries = "Series: ";
    private String stringBuffer = "";
    private String stringSum = "Sum: ";
    private String stringFileName = "Series.txt";

    private Series series;

    private JLabel labelElements;
    private JLabel labelSum;
    private JLabel labelBase = new JLabel("Base of series:");
    private JLabel labelIncrement = new JLabel("Increment of series:");
    private JLabel labelAmount = new JLabel("Amount of elements:");
    private JLabel labelSeriesKind = new JLabel("Kind of series:");

    private JRadioButton radioButtonLiner = new JRadioButton("Liner");
    private JRadioButton radioButtonExponential = new JRadioButton("Exponential");

    private JButton buttonCalculate = new JButton("Calculate");
    private JButton buttonSaveToFile = new JButton("Save to file");

    //private ButtonGroup groupKindSeries;


    public SeriesWindow() throws HeadlessException {

        super("Series Application");
        setBounds(400, 200, 450, 300);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(13, 0, 0, 2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textFieldAmount = new JTextField();
        textFieldBase = new JTextField();
        textFieldIncrement = new JTextField();
        labelElements = new JLabel();
        container.add(labelAmount);
        container.add(textFieldAmount);
        container.add(labelBase);
        container.add(textFieldBase);
        container.add(labelIncrement);
        container.add(textFieldIncrement);
        container.add(labelSeriesKind);
        ButtonGroup groupKindSeries = new ButtonGroup();
        groupKindSeries.add(radioButtonLiner);
        groupKindSeries.add(radioButtonExponential);
        container.add(radioButtonLiner);
        container.add(radioButtonExponential);
        container.add(buttonCalculate);
        labelElements = new JLabel(stringSeries + stringBuffer);
        container.add(labelElements);
        labelSum = new JLabel(stringSum + stringBuffer);
        container.add(labelSum);
        container.add(buttonSaveToFile);

        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double base = Double.valueOf(textFieldBase.getText());
                    double increment = Double.valueOf(textFieldIncrement.getText());
                    int amount = Integer.valueOf(textFieldAmount.getText());

                    if (radioButtonExponential.isSelected()){
                        series = new Exponential(base, increment);
                    } else if (radioButtonLiner.isSelected()){
                            series = new Liner(base, increment);
                            }
                            else throw new NoSuchFieldException();

                    stringBuffer = series.toString();
                    labelElements.setText(stringSeries + stringBuffer);
                    stringBuffer = Double.toString(series.sum());
                    labelSum.setText(stringSum + stringBuffer);


                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,
                            "Some fields are invalid, please check it");
                }
                catch (NoSuchFieldException e) {
                    JOptionPane.showMessageDialog(null,
                            "Choose kind of series");
                }
            }
        });

        buttonSaveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonCalculate.getActionListeners()[0].actionPerformed(actionEvent);
                try {
                    series.saveToFile(stringFileName);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Something wrong with output file");
                }
            }
        });

    }


    public static void main (String[] args){
        SeriesWindow app = new SeriesWindow();
        app.setVisible(true);
    }
}
