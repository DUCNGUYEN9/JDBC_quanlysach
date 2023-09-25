package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    /*
     * Các bước để tạo kết nối với database
     * B1: Download mysql jdbc jar và giải nén
     * B2: add gói jar vào project
     * B3: Cung cấp các thông tin driver, url, username, password
     * B4: set Driver cho DriverManager để làm việc với CSDL mysql
     * B5: Tạo đối tượng connection để tạo phiên làm việc với mysql
     * */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/quanlysach";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1996";

    //1. Phương thức mở connection đến DB và trả về đối tượng connection để làm việc
    public static Connection openConnection(){
        //Khai báo đối tượng connection
        Connection conn = null;
        //Set driver cho Driver Manager
        try {
            Class.forName(DRIVER);
            //Khởi tạo đối tượng conn từ Driver Manager
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //2. Đóng kết nối connection
    public static void closeConnection(Connection conn, CallableStatement callSt){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (callSt!=null){
            try {
                callSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
