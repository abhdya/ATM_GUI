import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodSoft3 extends JFrame {
    private int balance;
    private JTextField balanceField;
    private JTextField amountField;
    private JTextArea outputArea;

    public CodSoft3(int initialBalance) {
        this.balance = initialBalance;

        setTitle("ATM Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents();
    }

    private void createComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel balanceLabel = new JLabel("Current Balance:");
        balanceField = new JTextField();
        balanceField.setEditable(false);
        balanceField.setText(String.valueOf(balance));

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(amountField.getText());
                withdraw(amount);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(amountField.getText());
                deposit(amount);
            }
        });
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            outputArea.append("Withdrawn: " + amount + "\n");
            updateBalanceField();
        } else {
            outputArea.append("Insufficient balance.\n");
        }
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            outputArea.append("Deposited: " + amount + "\n");
            updateBalanceField();
        } else {
            outputArea.append("Invalid deposit amount.\n");
        }
    }

    private void updateBalanceField() {
        balanceField.setText(String.valueOf(balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CodSoft3 atmGUI = new CodSoft3(1000);
                atmGUI.setVisible(true);
            }
        });
    }
}
