package JDBCproject.insert;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class InsertGUI {

    public JFrame insertFrame;
    private JTextField fNameTextField;
    private JTextField mNameTextField;
    private JTextField lNameTextField;
    private JTextField ssnTextField;
    private JTextField bDateTextField;
    private JTextField addressTextField;
    private String[] sexComboName = {"M", "F"};
    private JComboBox sexCombo;
    private JTextField salaryTextField;
    private JTextField superSsnTextField;
    private JTextField dnoTextField;

    public InsertGUI() {
        initialize();

        sexCombo.setSelectedIndex(0); // 기본성별 M
    }

    private void initialize() {
        insertFrame = new JFrame("직원 정보 추가");
        insertFrame.setBounds(100, 100, 450, 620);
        insertFrame.setLocationRelativeTo(null);
        insertFrame.setResizable(false);

        Box verticalBox = Box.createVerticalBox();
        insertFrame.getContentPane().add(verticalBox, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        verticalBox.add(panel);

        JLabel fNameLabel = new JLabel("First Name (*) : ");
        panel.add(fNameLabel);

        fNameTextField = new JTextField();
        panel.add(fNameTextField);
        fNameTextField.setColumns(10);

        JPanel panel_1 = new JPanel();
        verticalBox.add(panel_1);

        JLabel mNameLabel = new JLabel("Middle Init. : ");
        panel_1.add(mNameLabel);

        mNameTextField = new JTextField();
        panel_1.add(mNameTextField);
        mNameTextField.setColumns(10);

        JPanel panel_2 = new JPanel();
        verticalBox.add(panel_2);

        JLabel lNameLabel = new JLabel("Last Name (*) : ");
        panel_2.add(lNameLabel);

        lNameTextField = new JTextField();
        panel_2.add(lNameTextField);
        lNameTextField.setColumns(10);

        JPanel panel_3 = new JPanel();
        verticalBox.add(panel_3);

        JLabel ssnLabel = new JLabel("Ssn (*) : ");
        panel_3.add(ssnLabel);

        ssnTextField = new JTextField();
        panel_3.add(ssnTextField);
        ssnTextField.setColumns(10);

        JPanel panel_4 = new JPanel();
        verticalBox.add(panel_4);

        JLabel bDateLabel = new JLabel("Birthdate : ");
        panel_4.add(bDateLabel);

        bDateTextField = new JTextField();
        panel_4.add(bDateTextField);
        bDateTextField.setColumns(10);

        JPanel panel_5 = new JPanel();
        verticalBox.add(panel_5);

        JLabel addressLabel = new JLabel("Address : ");
        panel_5.add(addressLabel);

        addressTextField = new JTextField();
        panel_5.add(addressTextField);
        addressTextField.setColumns(10);

        JPanel panel_6 = new JPanel();
        verticalBox.add(panel_6);

        JLabel sexLabel = new JLabel("Sex : ");
        panel_6.add(sexLabel);

        sexCombo = new JComboBox(sexComboName);
        panel_6.add(sexCombo);

        JPanel panel_7 = new JPanel();
        verticalBox.add(panel_7);

        JLabel salaryLabel = new JLabel("Salary : ");
        panel_7.add(salaryLabel);

        salaryTextField = new JTextField();
        panel_7.add(salaryTextField);
        salaryTextField.setColumns(10);

        JPanel panel_8 = new JPanel();
        verticalBox.add(panel_8);

        JLabel superSsnLabel = new JLabel("Super_ssn : ");
        panel_8.add(superSsnLabel);

        superSsnTextField = new JTextField();
        panel_8.add(superSsnTextField);
        superSsnTextField.setColumns(10);

        JPanel panel_9 = new JPanel();
        verticalBox.add(panel_9);

        JLabel dnoLabel = new JLabel("Dno (*) : ");
        panel_9.add(dnoLabel);

        dnoTextField = new JTextField();
        panel_9.add(dnoTextField);
        dnoTextField.setColumns(10);

        JPanel panel_10 = new JPanel();
        verticalBox.add(panel_10);

        JButton addButton = new JButton("추가");
        addButton.addActionListener(e -> {
            String fName = fNameTextField.getText();   // Fname VARCHAR(15) NOT NULL
            if (!Pattern.matches("^[a-zA-Z]{1,15}$", fName)) {
                JOptionPane.showMessageDialog(null, "First Name은 영어 1~15글자로 입력해주세요.");
                return;
            }

            String mName = mNameTextField.getText();   // Minit CHAR
            if (!Pattern.matches("^[a-zA-Z]{0,1}$", mName)) {
                JOptionPane.showMessageDialog(null, "Middle init은 영어 0~1글자로 입력해주세요.");
                return;
            }
            if (mName.equals("")) {
                mName = null;
            }

            String lName = lNameTextField.getText();   // Lname VARCHAR(15) NOT NULL
            if (!Pattern.matches("^[a-zA-Z]{1,15}$", lName)) {
                JOptionPane.showMessageDialog(null, "Last Name은 영어 1~15글자로 입력해주세요.");
                return;
            }

            SearchFeature searchFeature = new SearchFeature();

            String ssn = ssnTextField.getText();   // Ssn CHAR(9) NOT NULL PRIMARY KEY (Ssn)
            if (!Pattern.matches("^[0-9]{9}$", ssn)) {
                JOptionPane.showMessageDialog(null, "Ssn은 9글자여야 합니다.");
                return;
            }
            if (searchFeature.isExistSsn(ssn) > 0) {
                JOptionPane.showMessageDialog(null, "중복 Ssn 존재");
                return;
            }

            String bDate = bDateTextField.getText();   // Bdate DATE nls_date_format='YYYY-MM-DD'
            if (!Pattern.matches("^((19[0-9]{2})|(20([01][0-9]|20)))-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", bDate)) {
                if (bDate.equals("")) {
                    bDate = null;
                } else {
                    JOptionPane.showMessageDialog(null, "형식을 yyyy-mm-dd으로 입력해주세요.");
                    return;
                }
            }

            String address = addressTextField.getText();   // Address VARCHAR(30)
            if (!Pattern.matches("^.{0,30}$", address)) {
                JOptionPane.showMessageDialog(null, "Address는 30글자 이내로 입력해주세요.");
                return;
            }
            if (address.equals("")) {
                address = null;
            }

            String sex = (String) sexCombo.getSelectedItem();    // Sex CHAR

            String salary = salaryTextField.getText();   // Salary DECIMAL(10,2)
            if (!Pattern.matches("^[0-9]{1,10}(\\.[0-9]{1,2})?$", salary)) {
                if (salary.equals("")) {
                    salary = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Salary는 숫자 10자리 이하, 소숫점 2자리 이하로 입력해주세요.");
                    return;
                }
            }

            String superSsn = superSsnTextField.getText();   // Super_ssn CHAR(9) FOREIGN KEY (Super_ssn) REFERENCES EMPLOYEE (Ssn)
            if (!Pattern.matches("^[0-9]{9}$", superSsn)) {
                if (superSsn.equals("")) {
                    superSsn = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Super_ssn 9글자 이하입니다.");
                    return;
                }
            }
            if (superSsn != null) {
                if (searchFeature.isExistSsn(superSsn) < 1) {
                    JOptionPane.showMessageDialog(null, "Super_ssn이 존재하지 않습니다.");
                    return;
                }
            }

            String dno = dnoTextField.getText();   // Dno NUMBER DEFAULT 1 NOT NULL FOREIGN KEY (Dno) REFERENCES DEPARTMENT (Dnumber)
            if (!Pattern.matches("^[0-9]{1}$", dno)) {
                JOptionPane.showMessageDialog(null, "Dno는 숫자 1자리 입니다.");
                return;
            }
            if (searchFeature.isExistDnumber(dno) < 1) {
                JOptionPane.showMessageDialog(null, "존재하지 않는 Dno입니다.");
                return;
            }

            searchFeature.InsertEmployee(fName, mName, lName, ssn, bDate, address, sex, salary, superSsn, dno);

            JOptionPane.showMessageDialog(null, "추가 완료");
            insertFrame.dispose();
        });
        panel_10.add(addButton);
    }
}