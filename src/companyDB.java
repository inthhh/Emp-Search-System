//package JDBCproject;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class companyDB extends JFrame implements ActionListener {
    // GUI 선언
    public Connection conn;
    public Statement s;
    public ResultSet r;

    private JComboBox Category;
    private JComboBox Department;
    private JComboBox Sex;
    private JTextField textSalary;
    private JComboBox birthMonth;
    private JTextField subOrdinate;

    private JCheckBox c1 = new JCheckBox("Name", true);
    private JCheckBox c2 = new JCheckBox("Ssn", true);
    private JCheckBox c3 = new JCheckBox("Bdate", true);
    private JCheckBox c4 = new JCheckBox("Address", true);
    private JCheckBox c5 = new JCheckBox("Sex", true);
    private JCheckBox c6 = new JCheckBox("Salary", true);
    private JCheckBox c7 = new JCheckBox("Supervisor", true);
    private JCheckBox c8 = new JCheckBox("Department", true);
    private Vector<String> Head = new Vector<String>();

    private JTable table;
    private DefaultTableModel model;
    private static final int BOOLEAN_COLUMN = 0;
    private int NAME_COLUMN = 0;
    private int SALARY_COLUMN = 0;
    private String dShow;

    private JButton Search_Button = new JButton("검색");
    Container me = this;

    private JLabel totalEmp = new JLabel("인원수 : ");
    final JLabel totalCount = new JLabel();
    JPanel panel;
    JScrollPane ScPane;
    private JLabel Emplabel = new JLabel("선택한 직원: ");
    private JLabel ShowSelectedEmp = new JLabel();
    private JLabel Setlabel = new JLabel("새로운 Salary: ");
    private JTextField setSalary = new JTextField(10);
    private JButton Update_Button = new JButton("UPDATE");
    private JButton Delete_Button = new JButton("선택한 데이터 삭제");
    int count = 0;

    public companyDB() {
        // panel에 Category 콤보박스 추가
        JPanel ComboBoxPanel = new JPanel();
        String[] Category = { "전체","부서","성별","연봉","생일","부하직원" };
        this.Category = new JComboBox(Category);
        ComboBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ComboBoxPanel.add(new JLabel("EMPLOYEE 검색 범위 "));
        ComboBoxPanel.add(this.Category);

        final int[] selectedIndex = {0};
        this.Category.addActionListener(new ActionListener(){
            // Category 선택 시 event : setVisible(true)
            String[] DepartmentList = { "Research", "Administration", "Headquarters" };
            String[] SexList = {"F", "M"};
            String[] birthMonthList = {"1","2","3","4","5","6","7","8","9","10","11","12"};
            JComboBox Department = new JComboBox(DepartmentList);
            JComboBox Sex = new JComboBox(SexList);
            JTextField textSalary = new JTextField("입력하세요");
            JComboBox birthMonth = new JComboBox(birthMonthList);
            JTextField subOrdinate = new JTextField("입력하세요");

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb =(JComboBox)e.getSource();
                selectedIndex[0] = jcb.getSelectedIndex();
                System.out.println(selectedIndex[0]); // 선택값 인덱스 확인
                ComboBoxPanel.add(Department);
                ComboBoxPanel.add(Sex);
                ComboBoxPanel.add(textSalary);
                ComboBoxPanel.add(birthMonth);
                ComboBoxPanel.add(subOrdinate);

                Department.setVisible(false);
                Sex.setVisible(false);
                textSalary.setVisible(false);
                birthMonth.setVisible(false);
                subOrdinate.setVisible(false);

                if(selectedIndex[0] == 1){
                    Department.setVisible(true);
                }
                if(selectedIndex[0] == 2){
                    Sex.setVisible(true);
                }
                if(selectedIndex[0] == 3){
                    textSalary.setVisible(true);
                }
                if(selectedIndex[0] == 4){
                    birthMonth.setVisible(true);
                }
                if(selectedIndex[0] == 5){
                    subOrdinate.setVisible(true);
                }
            }
        });

        // 체크박스 추가
        JPanel CheckBoxPanel = new JPanel();
        CheckBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        CheckBoxPanel.add(new JLabel("EMPLOYEE 검색 항목 "));
        CheckBoxPanel.add(c1);
        CheckBoxPanel.add(c2);
        CheckBoxPanel.add(c3);
        CheckBoxPanel.add(c4);
        CheckBoxPanel.add(c5);
        CheckBoxPanel.add(c6);
        CheckBoxPanel.add(c7);
        CheckBoxPanel.add(c8);
        CheckBoxPanel.add(Search_Button);

        // 왼쪽 하단
        JPanel ShowSelectedPanel = new JPanel();
        ShowSelectedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Emplabel.setFont(new Font("Dialog", Font.BOLD, 16));
        ShowSelectedEmp.setFont(new Font("Dialog", Font.BOLD, 16));
        dShow = "";
        ShowSelectedPanel.add(Emplabel);
        ShowSelectedPanel.add(ShowSelectedEmp);

        JPanel TotalPanel = new JPanel();
        TotalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TotalPanel.add(totalEmp);
        TotalPanel.add(totalCount);

        // 중심 하단
        JPanel UpdatePanel = new JPanel();
        UpdatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        UpdatePanel.add(Setlabel);
        UpdatePanel.add(setSalary);
        UpdatePanel.add(Update_Button);

        // 오른쪽 하단
        JPanel DeletePanel = new JPanel();
        DeletePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        DeletePanel.add(Delete_Button);

        JPanel Top = new JPanel();
        Top.setLayout(new BoxLayout(Top, BoxLayout.Y_AXIS));
        Top.add(ComboBoxPanel);
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

        add(Top, BorderLayout.NORTH);
        add(ShowVertical, BorderLayout.SOUTH);

        Search_Button.addActionListener(this);
        Delete_Button.addActionListener(this);
        Update_Button.addActionListener(this);

        setTitle("Employee Search System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    private static final String user = "root";
    private static final String pwd = "6427"; // 각자 비밀번호
    private static final String dbname = "DB"; // 각자 db 이름
    private static final String url = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC";

    public void actionPerformed(ActionEvent e) {

        // DB 연결
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 연결
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("정상적으로 연결되었습니다.");

        } catch (SQLException e1) {
            System.err.println("연결할 수 없습니다.");
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            System.err.println("드라이버를 로드할 수 없습니다.");
            e1.printStackTrace();
        }

        // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        if (count == 1) {
            me.remove(panel);
            revalidate();
        }

        if (e.getSource() == Search_Button) {
            if (c1.isSelected() || c2.isSelected() || c3.isSelected() || c4.isSelected() || c5.isSelected()
                    || c6.isSelected() || c7.isSelected() || c8.isSelected()) {
                Head.clear();
                Head.add("선택");

                String stmt = "select";
                if (c1.isSelected()) {
                    stmt += " concat(e.fname,' ', e.minit,' ', e.lname,' ') as Name";
                    Head.add("NAME");
                }
                if (c2.isSelected()) {
                    if (!c1.isSelected())
                        stmt += " e.ssn";
                    else
                        stmt += ", e.ssn";
                    Head.add("SSN");
                }
                if (c3.isSelected()) {
                    if (!c1.isSelected() && !c2.isSelected())
                        stmt += " e.bdate";
                    else
                        stmt += ", e.bdate";
                    Head.add("BDATE");
                }
                if (c4.isSelected()) {
                    if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected())
                        stmt += " e.address";
                    else
                        stmt += ", e.address";
                    Head.add("ADDRESS");
                }
                if (c5.isSelected()) {
                    if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected() && !c4.isSelected())
                        stmt += " e.sex";
                    else
                        stmt += ", e.sex";
                    Head.add("SEX");
                }
                if (c6.isSelected()) {
                    if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected() && !c4.isSelected()
                            && !c5.isSelected())
                        stmt += " e.salary";
                    else
                        stmt += ", e.salary";
                    Head.add("SALARY");
                }
                if (c7.isSelected()) {
                    if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected() && !c4.isSelected() && !c5.isSelected()
                            && !c6.isSelected())
                        stmt += " concat(s.fname, ' ', s.minit, ' ',s.lname,' ') as Supervisor ";
                    else
                        stmt += ", concat(s.fname, ' ', s.minit, ' ',s.lname,' ') as Supervisor ";
                    Head.add("SUPERVISOR");
                }
                if (c8.isSelected()) {
                    if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected() && !c4.isSelected() && !c5.isSelected()
                            && !c6.isSelected() && !c7.isSelected())
                        stmt += " dname";
                    else
                        stmt += ", dname";
                    Head.add("DEPARTMENT");
                }
                stmt += " from employee e left outer join employee s on e.super_ssn=s.ssn, department where e.dno = dnumber";

                if (Category.getSelectedItem().toString() == "부서별") {
                    if (Department.getSelectedItem().toString() == "Research")
                        stmt += " and dname = \"Research\";";
                    else if (Department.getSelectedItem().toString() == "Administration")
                        stmt += " and dname = \"Administration\";";
                    else if (Department.getSelectedItem().toString() == "Headquarters")
                        stmt += " and dname = \"Headquarters\";";
                }

                model = new DefaultTableModel(Head, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if (column > 0) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                };
                for (int i = 0; i < Head.size(); i++) {
                    if (Head.get(i) == "NAME") {
                        NAME_COLUMN = i;
                    } else if (Head.get(i) == "SALARY") {
                        SALARY_COLUMN = i;
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
                    r = s.executeQuery(stmt);
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
                add(panel, BorderLayout.CENTER);
                revalidate();

            } else {
                JOptionPane.showMessageDialog(null, "검색 항목을 한개 이상 선택하세요.");
            }

        }
    }

    public class CheckBoxModelListener implements TableModelListener {
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == BOOLEAN_COLUMN) {
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(1);
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (columnName == "NAME") {
                    if (checked) {
                        dShow = "";
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, 0) == Boolean.TRUE) {
                                dShow += (String) table.getValueAt(i, NAME_COLUMN) + "    ";

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