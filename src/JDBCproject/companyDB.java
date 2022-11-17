package JDBCproject;

import JDBCproject.insert.InsertGUI;
import org.w3c.dom.Attr;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class companyDB implements ActionListener {
    // GUI 선언
    public static JFrame frame = new JFrame("Employee Search System");
    public Statement s;
    public ResultSet r;

    private final JComboBox Attribute;
    static String[] DepartmentList = {"Research", "Administration", "Headquarters"};
    static String[] SexList = {"F", "M"};
    static String[] birthMonthList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    static JComboBox Department = new JComboBox(DepartmentList);
    static JComboBox Sex = new JComboBox(SexList);
    static JTextField textSalary = new JTextField(12);
    static JLabel salaryLable = new JLabel("(입력한 금액보다 높은 연봉의 직원을 검색합니다.)");
    static JComboBox birthMonth = new JComboBox(birthMonthList);
    static JTextField subOrdinate = new JTextField(20);
    static JLabel subLable = new JLabel("(입력한 Ssn에 해당하는 직원의 부하 직원을 검색합니다.)");

    private static JCheckBox selectName = new JCheckBox("Name", true);
    private static JCheckBox selectSsn = new JCheckBox("Ssn", true);
    private static JCheckBox selectBdate = new JCheckBox("Bdate", true);
    private static JCheckBox selectAddress = new JCheckBox("Address", true);
    private static JCheckBox selectSex = new JCheckBox("Sex", true);
    private static JCheckBox selectSalary = new JCheckBox("Salary", true);
    private static JCheckBox selectSuv = new JCheckBox("Supervisor", true);
    private static JCheckBox selectDep = new JCheckBox("Department", true);
    private Vector<String> tableHead = new Vector<String>();

    private JTable table;
    private DefaultTableModel model;
    private static final int selectColumn = 0;
    private int columnOfName = 0;
    private int columnOfSalary = 0;
    private String dShow;

    private JButton searchBT = new JButton("검색");
    //Container me = this;

    private JLabel totalEmp = new JLabel("인원수 : ");
    final JLabel totalCount = new JLabel();
    JPanel panel;
    JScrollPane ScPane;
    private JLabel Emplabel = new JLabel("선택한 직원: ");
    private JLabel ShowSelectedEmp = new JLabel();
    private JLabel Setlabel = new JLabel("새로운 Salary: ");
    private JTextField setSalary = new JTextField(10);
    private JButton updateBT = new JButton("UPDATE");
    private JButton deleteBT = new JButton("선택한 데이터 삭제");
    private JButton insertBT = new JButton("데이터 추가");
    int count = 0;

    public companyDB() {
        // panel에 Category 콤보박스 추가
        JPanel attributePanel = new JPanel();
        String[] Attributes = {"전체", "부서", "성별", "연봉", "생일", "부하직원"};
        this.Attribute = new JComboBox(Attributes);
        attributePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        attributePanel.add(new JLabel("EMPLOYEE 검색 범위 "));
        attributePanel.add(this.Attribute);

        final int[] selectedIndex = {0};

        this.Attribute.addActionListener(new ActionListener() {
            // Category 선택 시 event : setVisible(true)
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                selectedIndex[0] = jcb.getSelectedIndex();
                System.out.println(selectedIndex[0]); // 선택값 인덱스 확인
                attributePanel.add(Department);
                attributePanel.add(Sex);
                attributePanel.add(textSalary);
                attributePanel.add(salaryLable);
                attributePanel.add(birthMonth);
                attributePanel.add(subOrdinate);
                attributePanel.add(subLable);

                Department.setVisible(false);
                Sex.setVisible(false);
                textSalary.setVisible(false);
                salaryLable.setVisible(false);
                birthMonth.setVisible(false);
                subOrdinate.setVisible(false);
                subLable.setVisible(false);

                if (selectedIndex[0] == 1) {
                    Department.setVisible(true);
                }
                if (selectedIndex[0] == 2) {
                    Sex.setVisible(true);
                }
                if (selectedIndex[0] == 3) {
                    textSalary.setVisible(true);
                    salaryLable.setVisible(true);
                }
                if (selectedIndex[0] == 4) {
                    birthMonth.setVisible(true);
                }
                if (selectedIndex[0] == 5) {
                    subOrdinate.setVisible(true);
                    subLable.setVisible(true);
                }
            }
        });

        // 체크박스 추가
        JPanel CheckBoxPanel = new JPanel();
        CheckBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        CheckBoxPanel.add(new JLabel("EMPLOYEE 검색 항목 "));
        CheckBoxPanel.add(selectName);
        CheckBoxPanel.add(selectSsn);
        CheckBoxPanel.add(selectBdate);
        CheckBoxPanel.add(selectAddress);
        CheckBoxPanel.add(selectSex);
        CheckBoxPanel.add(selectSalary);
        CheckBoxPanel.add(selectSuv);
        CheckBoxPanel.add(selectDep);
        CheckBoxPanel.add(searchBT);
        searchBT.addActionListener(this);

        // 왼쪽 하단

        JPanel TotalPanel = new JPanel();
        TotalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TotalPanel.add(totalEmp);
        TotalPanel.add(totalCount);

        JPanel ShowSelectedPanel = new JPanel();
        ShowSelectedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Emplabel.setFont(new Font("Dialog", Font.BOLD, 14));
        ShowSelectedEmp.setFont(new Font("Dialog", Font.BOLD, 14));
        dShow = "";
        ShowSelectedPanel.add(Emplabel);
        ShowSelectedPanel.add(ShowSelectedEmp);
        ShowSelectedPanel.setBackground(new Color(244, 250, 222));

        // 중심 하단
        JPanel UpdatePanel = new JPanel();
        UpdatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        UpdatePanel.add(Setlabel);
        UpdatePanel.add(setSalary);
        UpdatePanel.add(updateBT);
        updateBT.addActionListener(this);

        // 오른쪽 하단
        JPanel DeletePanel = new JPanel();
        DeletePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        DeletePanel.add(deleteBT);
        DeletePanel.add(insertBT);
        insertBT.addActionListener(e -> {
            InsertGUI insertGUI = new InsertGUI();
            insertGUI.insertFrame.setVisible(true);
        });
        deleteBT.addActionListener(this);

        JPanel Top = new JPanel();
        Top.setLayout(new BoxLayout(Top, BoxLayout.Y_AXIS));
        Top.add(attributePanel);
        Top.add(CheckBoxPanel);

        JPanel Halfway = new JPanel();
        Halfway.setLayout(new BoxLayout(Halfway, BoxLayout.X_AXIS));
        Halfway.add(ShowSelectedPanel);

        JPanel Bottom = new JPanel();
        Bottom.setLayout(new BoxLayout(Bottom, BoxLayout.X_AXIS));
        Bottom.add(TotalPanel);
        Bottom.add(UpdatePanel);
        Bottom.add(DeletePanel);

        JPanel ShowVertical = new JPanel();
        ShowVertical.setLayout(new BoxLayout(ShowVertical, BoxLayout.Y_AXIS));
        ShowVertical.add(Halfway);
        ShowVertical.add(Bottom);

        frame.add(Top, BorderLayout.NORTH);
        frame.add(ShowVertical, BorderLayout.SOUTH);


        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public Connection conn;
    private static final String user = "root";
    private static final String pwd = "4290514l@"; // 각자 비밀번호
    private static final String dbname = "mydb"; // 각자 db 이름
    private static final String url = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC";
    static String selectOn = "select";

    public void actionPerformed(ActionEvent e) {

        // DB 연결
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 연결
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("연결 성공.");

        } catch (SQLException e1) {
            System.err.println("연결 실패");
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            System.err.println("드라이버 로드 실패");
            e1.printStackTrace();
        }

        if (count == 1) {
            //me.remove(panel);
            frame.revalidate();
        }


        if (e.getSource() == searchBT) {
            if (selectName.isSelected() || selectSsn.isSelected() || selectBdate.isSelected()
                    || selectAddress.isSelected() || selectSex.isSelected() || selectSalary.isSelected()
                    || selectSuv.isSelected() || selectDep.isSelected()) {
                tableHead.clear();
                tableHead.add("선택");
                selectOn = "select";
                if (selectName.isSelected()) {
                    selectOn += " concat(e.fname,' ', e.minit,' ', e.lname,' ') as Name";
                    tableHead.add("NAME");
                }
                if (selectSsn.isSelected()) {
                    if (!selectName.isSelected())
                        selectOn += " e.ssn";
                    else
                        selectOn += ", e.ssn";
                    tableHead.add("SSN");
                }
                if (selectBdate.isSelected()) {
                    if (!selectName.isSelected() && !selectSsn.isSelected())
                        selectOn += " e.bdate";
                    else
                        selectOn += ", e.bdate";
                    tableHead.add("BDATE");
                }
                if (selectAddress.isSelected()) {
                    if (!selectName.isSelected() && !selectSsn.isSelected() && !selectBdate.isSelected())
                        selectOn += " e.address";
                    else
                        selectOn += ", e.address";
                    tableHead.add("ADDRESS");
                }
                if (selectSex.isSelected()) {
                    if (!selectName.isSelected() && !selectSsn.isSelected() && !selectBdate.isSelected() && !selectAddress.isSelected())
                        selectOn += " e.sex";
                    else
                        selectOn += ", e.sex";
                    tableHead.add("SEX");
                }
                if (selectSalary.isSelected()) {
                    if (!selectName.isSelected() && !selectSsn.isSelected() && !selectBdate.isSelected() && !selectAddress.isSelected()
                            && !selectSex.isSelected())
                        selectOn += " e.salary";
                    else
                        selectOn += ", e.salary";
                    tableHead.add("SALARY");
                }
                if (selectSuv.isSelected()) {
                    if (!selectName.isSelected() && !selectSsn.isSelected() && !selectBdate.isSelected() && !selectAddress.isSelected() && !selectSex.isSelected()
                            && !selectSalary.isSelected())
                        selectOn += " concat(s.fname, ' ', s.minit, ' ',s.lname,' ') as Supervisor ";
                    else
                        selectOn += ", concat(s.fname, ' ', s.minit, ' ',s.lname,' ') as Supervisor ";
                    tableHead.add("SUPERVISOR");
                }
                if (selectDep.isSelected()) {
                    if (!selectName.isSelected() && !selectSsn.isSelected() && !selectBdate.isSelected() && !selectAddress.isSelected() && !selectSex.isSelected()
                            && !selectSalary.isSelected() && !selectSuv.isSelected())
                        selectOn += " dname";
                    else
                        selectOn += ", dname";
                    tableHead.add("DEPARTMENT");
                }
                selectOn += " from employee e left outer join employee s on e.super_ssn=s.ssn, department where e.dno = dnumber";

                System.out.println("attribute : " + Attribute.getSelectedIndex());
                System.out.println("index : " + Department.getSelectedIndex());

                if (Attribute.getSelectedIndex() == 1) { // 부서 선택 시
                    System.out.println("부서별 선택");
                    if (Department.getSelectedIndex() == 0) {
                        selectOn += " and dname = \"Research\"";
                    } else if (Department.getSelectedIndex() == 1) {
                        selectOn += " and dname = \"Administration\"";
                    } else if (Department.getSelectedIndex() == 2) {
                        selectOn += " and dname = \"Headquarters\"";
                    }
                }

                if (Attribute.getSelectedIndex() == 2) { // 성별 선택 시
                    System.out.println("성별 선택");
                    if (Sex.getSelectedIndex() == 0) {
                        selectOn += " and e.sex = \"F\"";
                    } else {
                        selectOn += " and e.sex = \"M\"";
                    }
                }
                if (Attribute.getSelectedIndex() == 3) { // 연봉 선택 시
                    System.out.println("연봉 선택");
                    if (textSalary.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "입력된 값이 없습니다.");
                    } else if (Integer.parseInt(textSalary.getText()) >= 0) {
                        Integer isSalary = Integer.parseInt(textSalary.getText());
                        selectOn += (" and e.salary > " + isSalary);
                    } else {
                        JOptionPane.showMessageDialog(null, "0 이상의 숫자를 입력하세요.");
                    }
                }
                if (Attribute.getSelectedIndex() == 4) { // 생일 선택 시
                    System.out.println("생일 선택");
                    Integer BM = birthMonth.getSelectedIndex();
                    switch (BM) {
                        case 0:
                            selectOn += " and e.bdate like \"%-01-%\"";
                            break;
                        case 1:
                            selectOn += " and e.bdate like \"%-02-%\"";
                            break;
                        case 2:
                            selectOn += " and e.bdate like \"%-03-%\"";
                            break;
                        case 3:
                            selectOn += " and e.bdate like \"%-04-%\"";
                            break;
                        case 4:
                            selectOn += " and e.bdate like \"%-05-%\"";
                            break;
                        case 5:
                            selectOn += " and e.bdate like \"%-06-%\"";
                            break;
                        case 6:
                            selectOn += " and e.bdate like \"%-07-%\"";
                            break;
                        case 7:
                            selectOn += " and e.bdate like \"%-08-%\"";
                            break;
                        case 8:
                            selectOn += " and e.bdate like \"%-09-%\"";
                            break;
                        case 9:
                            selectOn += " and e.bdate like \"%-10-%\"";
                            break;
                        case 10:
                            selectOn += " and e.bdate like \"%-11-%\"";
                            break;
                        case 11:
                            selectOn += " and e.bdate like \"%-12-%\"";
                            break;
                    }
                }
                if (Attribute.getSelectedIndex() == 5) { // 부하직원 선택 시
                    System.out.println("부하직원 선택");
                    if (subOrdinate.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "입력된 내용이 없습니다.");
                    } else {
                        selectOn += (" and s.ssn = \"" + subOrdinate.getText() + "\"");
                    }
                }

                model = new DefaultTableModel(tableHead, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if (column > 0) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                };
                for (int i = 0; i < tableHead.size(); i++) {
                    if (tableHead.get(i) == "NAME") {
                        columnOfName = i;
                    } else if (tableHead.get(i) == "SALARY") {
                        columnOfSalary = i;
                    }
                }
                table = new JTable(model) {
                    @Override
                    public Class getColumnClass(int column) {
                        if (column == 0) {
                            return Boolean.class;
                        } else
                            return String.class;
                    }
                };

                ShowSelectedEmp.setText(" ");

                try {
                    count = 1;
                    s = conn.createStatement();
                    r = s.executeQuery(selectOn + ";");
                    ResultSetMetaData rsmd = r.getMetaData();
                    int columnCnt = rsmd.getColumnCount();
                    int rowCnt = table.getRowCount();

                    while (r.next()) {
                        Vector<Object> tuple = new Vector<Object>();
                        tuple.add(false);
                        for (int i = 1; i < columnCnt + 1; i++) {
                            tuple.add(r.getString(rsmd.getColumnName(i)));
                        }
                        model.addRow(tuple);
                        rowCnt++;
                    }
                    totalCount.setText(String.valueOf(rowCnt));

                } catch (SQLException ee) {
                    System.out.println("actionPerformed err : " + ee);
                    ee.printStackTrace();

                }
                panel = new JPanel();
                ScPane = new JScrollPane(table);
                table.getModel().addTableModelListener(new CheckBoxModelListener());
                ScPane.setPreferredSize(new Dimension(1100, 400));
                panel.add(ScPane);
                frame.add(panel, BorderLayout.CENTER);
                frame.revalidate();

            } else {
                JOptionPane.showMessageDialog(null, "검색 항목을 한개 이상 선택하세요.");
            }

        }
    }

    public class CheckBoxModelListener implements TableModelListener {
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == selectColumn) {
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(1);
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (columnName == "NAME") {
                    if (checked) {
                        dShow = "";
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, 0) == Boolean.TRUE) {
                                dShow += (String) table.getValueAt(i, columnOfName) + "    ";

                            }
                        }
                        ShowSelectedEmp.setText(dShow);
                    } else {
                        dShow = "";
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, 0) == Boolean.TRUE) {
                                dShow += (String) table.getValueAt(i, 1) + "    ";

                            }
                        }
                        ShowSelectedEmp.setText(dShow);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new companyDB();
    }
}