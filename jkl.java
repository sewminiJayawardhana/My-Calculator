import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame {
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JButton[] btns;
    private static final String[] btnNames = {"7", "8", "9", "-", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "*"};

    private JTextField txt1;
    private StringBuilder currentInput = new StringBuilder();
    private double result;
    private String operator;

    Calculator() {
        setSize(650, 800);
        setTitle("My Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        addActionListeners();
    }

    private void initComponents() {
        displayPanel = new JPanel();

        txt1 = new JTextField();
        txt1.setPreferredSize(new Dimension(620, 90));
        
        Font textFieldFont = new Font("Arial", Font.PLAIN, 30); 
		txt1.setFont(textFieldFont);

        displayPanel.add(txt1);

        buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        btns = new JButton[16];
        
        Font buttonFont = new Font("Arial", Font.PLAIN, 35);
        

        for (int i = 0; i < btns.length; i++) {
            btns[i] = new JButton(btnNames[i]);
            btns[i].setFont(buttonFont);
        }

        for (int i = 0; i < btns.length; i++) {
            buttonPanel.add(btns[i]);
        }

        add(displayPanel, BorderLayout.PAGE_START);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        for (JButton btn : btns) {
            btn.addActionListener(new CalculatorButtonListener());
        }
    }

    private class CalculatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if ("0123456789.".contains(buttonText)) {
                currentInput.append(buttonText);
                txt1.setText(currentInput.toString());
            } else if ("+-*/".contains(buttonText)) {
                if (currentInput.length() > 0) {
                    result = Double.parseDouble(currentInput.toString());
                    operator = buttonText;
                    currentInput.setLength(0);
                }
            } else if ("=".equals(buttonText)) {
                if (currentInput.length() > 0 && operator != null) {
                    double secondOperand = Double.parseDouble(currentInput.toString());
                    switch (operator) {
                        case "+":
                            result += secondOperand;
                            break;
                        case "-":
                            result -= secondOperand;
                            break;
                        case "*":
                            result *= secondOperand;
                            break;
                        case "/":
                            if (secondOperand != 0) {
                                result /= secondOperand;
                            } else {
                                txt1.setText("Error: Division by zero");
                                return;
                            }
                            break;
                    }
                    txt1.setText(String.valueOf(result));
                    currentInput.setLength(0);
                    operator = null;
                }
            }
        }
    }
}

class jkl {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Calculator c1 = new Calculator();
            c1.setVisible(true);
        });
    }
}
