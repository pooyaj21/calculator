import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui implements ActionListener {
    String problem = "";
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;
    JButton decButton;
    JButton equButton;
    JButton delButton;
    JButton acButton;
    JButton negButton;
    JButton percentButton;
    JPanel panel;
    Font myFont = new Font("Arial", Font.TRUETYPE_FONT, 40);
    double num1 = 0;
    double num2 = 0;
    double result = 0;
    char operator;
    boolean isHaveDec;
    boolean isFirstTime = true;

    Ui() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(232, 320);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0x2b2d2f));
        frame.setResizable(false);

        textField = new JTextField();
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textField.setBounds(-5, 0, frame.getWidth() + 10, 57);
        textField.setFont(myFont);
        textField.setBackground(new Color(0x2b2d2f));
        textField.setForeground(Color.white);
        textField.setText("0");
        textField.setEditable(false);


        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        acButton = new JButton("AC");
        negButton = new JButton("(-)");
        percentButton = new JButton("%");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = acButton;
        functionButtons[8] = negButton;
        functionButtons[9] = percentButton;

        for (int i = 0; i < functionButtons.length; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
        }

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            setButtonBackground(numberButtons[i], new Color(0x5f6062), 20);
        }


        panel = new JPanel();
        panel.setBounds(0, textField.getHeight(), frame.getWidth(), frame.getHeight() - textField.getHeight() - 20);
        panel.setLayout(new GridLayout(5, 4, 0, 0));

        panel.setBackground(Color.BLACK);


        setButtonBackground(acButton, new Color(0x3f4143), 10);
        setButtonBackground(negButton, new Color(0x3f4143), 12);
        setButtonBackground(delButton, new Color(0x3f4143), 7);

        setButtonBackground(decButton, new Color(0x5f6062), 40);
        setButtonBackground(percentButton, new Color(0x5f6062), 16);

        setButtonBackground(divButton, new Color(0xff9f0c), 26);
        setButtonBackground(mulButton, new Color(0xff9f0c), 26);
        setButtonBackground(subButton, new Color(0xff9f0c), 30);
        setButtonBackground(addButton, new Color(0xff9f0c), 24);
        setButtonBackground(equButton, new Color(0xff9f0c), 24);


        panel.add(acButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(divButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);


        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(percentButton);
        panel.add(equButton);


        frame.add(textField);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static void setButtonBackground(JButton button, Color color, int fontSize) {
        button.setUI(new BasicButtonUI());
        button.setBackground(color);
        button.setFont(new Font("Arial", Font.TRUETYPE_FONT, fontSize));
        button.setForeground(Color.white);
        button.setBorder(new LineBorder(Color.BLACK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (!isFirstTime) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                } else {
                    textField.setText(String.valueOf(i));
                    isFirstTime = false;
                }
            }
        }
        if (e.getSource() == decButton && !isHaveDec) {
            textField.setText(textField.getText().concat("."));
            problem += textField.getText();
            isHaveDec = true;
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            problem += textField.getText().concat(String.valueOf(operator));
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            problem += textField.getText().concat(String.valueOf(operator));
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            problem += textField.getText().concat(String.valueOf(operator));
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            problem += textField.getText().concat(String.valueOf(operator));
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());
            problem += (int) num2;
            result = new Calculation(problem).getResult();
            textField.setText(String.valueOf((int) result));
            System.out.println(problem);
            problem = "";
            isHaveDec = true;

        }
        if (e.getSource() == acButton) {
            textField.setText("0");
            problem = "0";
            isHaveDec = false;
            isFirstTime = true;
        }
        if (e.getSource() == delButton) {
            String string = textField.getText();
            if (string.charAt(string.length() - 1) == '.') {
                isHaveDec = false;
            }
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
            for (int i = 0; i < problem.length() - 1; i++) {
                problem += problem.charAt(i);
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }
        if (e.getSource() == percentButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= 0.01;
            textField.setText(String.valueOf(temp));
        }
    }
}

