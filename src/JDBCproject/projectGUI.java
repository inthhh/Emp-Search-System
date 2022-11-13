//package JDBCproject;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Vector;
//
//public class projectGUI implements ActionListener{
//    public static JFrame frame = new JFrame("Employee Search System");
//    public Statement s;
//    public ResultSet r;
//
//    final JComboBox Attribute;
//    static String[] DepartmentList = { "Research", "Administration", "Headquarters" };
//    static String[] SexList = {"F", "M"};
//    static String[] birthMonthList = {"1","2","3","4","5","6","7","8","9","10","11","12"};
//    static JComboBox Department = new JComboBox(DepartmentList);
//    static JComboBox Sex = new JComboBox(SexList);
//    static JTextField textSalary = new JTextField(12);
//    static JLabel salaryLable = new JLabel("(입력한 금액보다 높은 연봉의 직원을 검색합니다.)");
//    static JComboBox birthMonth = new JComboBox(birthMonthList);
//    static JTextField subOrdinate = new JTextField(20);
//    static JLabel subLable = new JLabel("(입력한 직원의 부하 직원을 검색합니다.)");
//
//    static JCheckBox selectName = new JCheckBox("Name", true);
//    static JCheckBox selectSsn = new JCheckBox("Ssn", true);
//    static JCheckBox selectBdate = new JCheckBox("Bdate", true);
//    static JCheckBox selectAddress = new JCheckBox("Address", true);
//    static JCheckBox selectSex = new JCheckBox("Sex", true);
//    static JCheckBox selectSalary = new JCheckBox("Salary", true);
//    static JCheckBox selectSuv = new JCheckBox("Supervisor", true);
//    static JCheckBox selectDep = new JCheckBox("Department", true);
//    Vector<String> tableHead = new Vector<String>();
//
//    JTable table;
//    DefaultTableModel model;
//    static final int selectColumn = 0;
//    int columnOfName = 0;
//    int columnOfSalary = 0;
//    String dShow;
//
//    JButton searchBT = new JButton("검색");
//    //Container me = this;
//
//    private JLabel totalEmp = new JLabel("인원수 : ");
//    final JLabel totalCount = new JLabel();
//    JPanel panel;
//    JScrollPane ScPane;
//    private JLabel Emplabel = new JLabel("선택한 직원: ");
//    JLabel ShowSelectedEmp = new JLabel();
//    private JLabel Setlabel = new JLabel("새로운 Salary: ");
//    private JTextField setSalary = new JTextField(10);
//    private JButton updateBT = new JButton("UPDATE");
//    private JButton deleteBT = new JButton("선택한 데이터 삭제");
//    int count = 0;
//
//    public projectGUI() {
//        JPanel attributePanel = new JPanel();
//        String[] Attributes = { "전체","부서","성별","연봉","생일","부하직원" };
//        this.Attribute = new JComboBox(Attributes);
//        attributePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        attributePanel.add(new JLabel("EMPLOYEE 검색 범위 "));
//        attributePanel.add(this.Attribute);
//
//        final int[] selectedIndex = {0};
//
//        this.Attribute.addActionListener(new ActionListener(){
//            // Category 선택 시 event : setVisible(true)
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JComboBox jcb =(JComboBox)e.getSource();
//                selectedIndex[0] = jcb.getSelectedIndex();
//                System.out.println(selectedIndex[0]); // 선택값 인덱스 확인
//                attributePanel.add(Department);
//                attributePanel.add(Sex);
//                attributePanel.add(textSalary);
//                attributePanel.add(salaryLable);
//                attributePanel.add(birthMonth);
//                attributePanel.add(subOrdinate);
//                attributePanel.add(subLable);
//
//                Department.setVisible(false);
//                Sex.setVisible(false);
//                textSalary.setVisible(false);
//                salaryLable.setVisible(false);
//                birthMonth.setVisible(false);
//                subOrdinate.setVisible(false);
//                subLable.setVisible(false);
//
//                if(selectedIndex[0] == 1){
//                    Department.setVisible(true);
//                }
//                if(selectedIndex[0] == 2){
//                    Sex.setVisible(true);
//                }
//                if(selectedIndex[0] == 3){
//                    textSalary.setVisible(true);
//                    salaryLable.setVisible(true);
//                }
//                if(selectedIndex[0] == 4){
//                    birthMonth.setVisible(true);
//                }
//                if(selectedIndex[0] == 5){
//                    subOrdinate.setVisible(true);
//                    subLable.setVisible(true);
//                }
//            }
//        });
//
//        // 체크박스 추가
//        JPanel CheckBoxPanel = new JPanel();
//        CheckBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        CheckBoxPanel.add(new JLabel("EMPLOYEE 검색 항목 "));
//        CheckBoxPanel.add(selectName);
//        CheckBoxPanel.add(selectSsn);
//        CheckBoxPanel.add(selectBdate);
//        CheckBoxPanel.add(selectAddress);
//        CheckBoxPanel.add(selectSex);
//        CheckBoxPanel.add(selectSalary);
//        CheckBoxPanel.add(selectSuv);
//        CheckBoxPanel.add(selectDep);
//        CheckBoxPanel.add(searchBT);
//        searchBT.addActionListener(this);
//
//        // 왼쪽 하단
//        JPanel ShowSelectedPanel = new JPanel();
//        ShowSelectedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        Emplabel.setFont(new Font("Dialog", Font.BOLD, 16));
//        ShowSelectedEmp.setFont(new Font("Dialog", Font.BOLD, 16));
//        dShow = "";
//        ShowSelectedPanel.add(Emplabel);
//        ShowSelectedPanel.add(ShowSelectedEmp);
//
//        JPanel TotalPanel = new JPanel();
//        TotalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        TotalPanel.add(totalEmp);
//        TotalPanel.add(totalCount);
//
//        // 중심 하단
//        JPanel UpdatePanel = new JPanel();
//        UpdatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        UpdatePanel.add(Setlabel);
//        UpdatePanel.add(setSalary);
//        UpdatePanel.add(updateBT);
//        updateBT.addActionListener(this);
//
//        // 오른쪽 하단
//        JPanel DeletePanel = new JPanel();
//        DeletePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        DeletePanel.add(deleteBT);
//        deleteBT.addActionListener(this);
//
//        JPanel Top = new JPanel();
//        Top.setLayout(new BoxLayout(Top, BoxLayout.Y_AXIS));
//        Top.add(attributePanel);
//        Top.add(CheckBoxPanel);
//
//        JPanel Halfway = new JPanel();
//        Halfway.setLayout(new BoxLayout(Halfway, BoxLayout.X_AXIS));
//        Halfway.add(ShowSelectedPanel);
//
//        JPanel Bottom = new JPanel();
//        Bottom.setLayout(new BoxLayout(Bottom, BoxLayout.X_AXIS));
//        Bottom.add(TotalPanel);
//        Bottom.add(UpdatePanel);
//        Bottom.add(DeletePanel);
//
//        JPanel ShowVertical = new JPanel();
//        ShowVertical.setLayout(new BoxLayout(ShowVertical, BoxLayout.Y_AXIS));
//        ShowVertical.add(Halfway);
//        ShowVertical.add(Bottom);
//
//        frame.add(Top, BorderLayout.NORTH);
//        frame.add(ShowVertical, BorderLayout.SOUTH);
//
//
//        frame.setSize(1200, 500);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
