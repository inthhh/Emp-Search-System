package JDBCproject.insert;

import java.sql.*;

public class SearchFeature {

    private static final String user = "root";
    private static final String pwd = "0000"; // 각자 비밀번호
    private static final String dbname = "DB"; // 각자 db 이름
    private static final String url = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC";

    public void InsertEmployee(String F, String M, String L, String Ssn, String B,
                               String A, String Sex, String Sal, String Sup_ssn, String Dno) {
        try {
            Connection db = getConnection();
            String query = "INSERT INTO EMPLOYEE(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            assert db != null;
            PreparedStatement pstm = db.prepareStatement(query);
            pstm.setString(1, F);
            pstm.setString(2, M);
            pstm.setString(3, L);
            pstm.setString(4, Ssn);
            pstm.setString(5, B);
            pstm.setString(6, A);
            pstm.setString(7, Sex);
            pstm.setString(8, Sal);
            pstm.setString(9, Sup_ssn);
            pstm.setString(10, Dno);
            pstm.executeUpdate();

            // 수행 후 해제
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.close(); // 연결 해제
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("연결 실패");
            e.printStackTrace();
        }
    }

    public int isExistSsn(String ssn) {
        int exist = -1;

        try {
            Connection db = getConnection();

            String query = "SELECT COUNT(*) AS C FROM EMPLOYEE WHERE Ssn = ?";

            PreparedStatement pstm = db.prepareStatement(query);
            pstm.setString(1, ssn);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            exist = rs.getInt("C");

            // 수행 후 해제
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            db.close(); // 연결 해제

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("연결 실패");
            e.printStackTrace();
        }

        return exist;
    }

    public int isExistDnumber(String dnumber) {
        int exist = -1;

        try {
            Connection db = getConnection();
            String query = "SELECT COUNT(*) AS C FROM DEPARTMENT WHERE Dnumber = ?";
            assert db != null;
            PreparedStatement pstm = db.prepareStatement(query);
            pstm.setString(1, dnumber);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            exist = rs.getInt("C");

            // 수행 후 해제
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            db.close(); // 연결 해제

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("연결 실패");
            e.printStackTrace();
        }

        return exist;
    }

    private Connection getConnection() throws ClassNotFoundException {
        try {
            Connection db;
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 연결
            db = DriverManager.getConnection(url, user, pwd);
            return db;
        } catch (Exception e) {
            System.err.println("연결 실패");
            e.printStackTrace();
        }
        return null;
    }
}