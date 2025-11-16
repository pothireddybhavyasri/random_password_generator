import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.security.SecureRandom;

public class passwordgenerator extends Frame implements ActionListener {

    TextField lengthField, outputField;
    Checkbox cbLower, cbUpper, cbDigits, cbSymbols;
    Button btnGenerate, btnCopy;

    public passwordgenerator() {

        setTitle("Password Generator");
        setSize(420, 320);
        setLayout(null);
        setBackground(Color.lightGray);

        Label title = new Label("Random Password Generator");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(80, 40, 260, 25);
        add(title);

        Label lblLength = new Label("Password Length:");
        lblLength.setBounds(50, 90, 120, 20);
        add(lblLength);

        lengthField = new TextField("12");
        lengthField.setBounds(180, 90, 60, 20);
        add(lengthField);

        cbLower = new Checkbox("Lowercase (a-z)", true);
        cbLower.setBounds(50, 120, 150, 20);
        add(cbLower);

        cbUpper = new Checkbox("Uppercase (A-Z)", true);
        cbUpper.setBounds(210, 120, 150, 20);
        add(cbUpper);

        cbDigits = new Checkbox("Digits (0-9)", true);
        cbDigits.setBounds(50, 145, 150, 20);
        add(cbDigits);

        cbSymbols = new Checkbox("Symbols (!@#$%)", true);
        cbSymbols.setBounds(210, 145, 150, 20);
        add(cbSymbols);

        btnGenerate = new Button("Generate");
        btnGenerate.setBounds(50, 185, 120, 30);
        btnGenerate.addActionListener(this);
        add(btnGenerate);

        btnCopy = new Button("Copy");
        btnCopy.setBounds(180, 185, 120, 30);
        btnCopy.addActionListener(this);
        add(btnCopy);

        outputField = new TextField();
        outputField.setBounds(50, 230, 300, 25);
        add(outputField);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnGenerate) {
            try {
                int length = Integer.parseInt(lengthField.getText());
                String pwd = generatePassword(length);
                outputField.setText(pwd);
            } catch (Exception ex) {
                outputField.setText("Invalid Input!");
            }
        }

        if (e.getSource() == btnCopy) {
            StringSelection selection = new StringSelection(outputField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
    }

    // SIMPLE PASSWORD GENERATION LOGIC
    private String generatePassword(int length) {

        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()_+=-?/{}";

        String allowed = "";

        if (cbLower.getState())
            allowed += lower;
        if (cbUpper.getState())
            allowed += upper;
        if (cbDigits.getState())
            allowed += digits;
        if (cbSymbols.getState())
            allowed += symbols;

        if (allowed.isEmpty())
            return "Select at least 1 option!";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowed.length());
            password.append(allowed.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        new passwordgenerator();
    }
}
