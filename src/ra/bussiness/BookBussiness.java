package ra.bussiness;

import ra.entity.Book;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBussiness {
    public static List<Book> getAllBook(){
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        List<Book> bookList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_display_book()}");
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            bookList = new ArrayList<>();
            while (rs.next()){
                Book book = new Book();
                book.setBookId(rs.getString("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                bookList.add(book);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return bookList;
    }
    public static boolean createBook(Book book){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_book_new(?,?,?,?,?)}");
            //set dữ liệu cho các tham số vào của procedure
            callSt.setString(1,book.getBookId());
            callSt.setString(2,book.getBookName());
            callSt.setFloat(3,book.getPrice());
            callSt.setString(4,book.getAuthor());
            callSt.setBoolean(5,book.isStatus());
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }
    public static Book getBookById(String bookId){

        Book book = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_book_by_id(?)}");
            //set giá trị tham số vào
            callSt.setString(1,bookId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng book trả về
            while (rs.next()){
                book = new Book();
                book.setBookId(rs.getString("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return book;
    }
    public static boolean updateBook(Book book){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_book(?,?,?,?,?)}");
            //set dữ liệu cho các tham số vào của procedure
            callSt.setString(1,book.getBookId());
            callSt.setString(2,book.getBookName());
            callSt.setFloat(3,book.getPrice());
            callSt.setString(4,book.getAuthor());
            callSt.setBoolean(5,book.isStatus());
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }
    public static boolean deleteBook(String bookId){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_book(?)}");
            //set giá trị tham số vào
            callSt.setString(1,bookId);
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }
    public static List<Book> searchBook(String bookName){
        List<Book> bookList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_book_from_name(?)}");
            callSt.setString(1,bookName);

            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            bookList = new ArrayList<>();
            while (rs.next()){
                Book book = new Book();
                book.setBookId(rs.getString("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                bookList.add(book);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return bookList;
    }
    public static Book getBookByName(String bookName){

        Book book = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_book_by_name(?)}");
            //set giá trị tham số vào
            callSt.setString(1,bookName);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng book trả về
            while (rs.next()){
                book = new Book();
                book.setBookId(rs.getString("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return book;
    }
    public static void statisticsAuthor() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statistics_book_to_author()}");
            // Thực hiện gọi procedure
            boolean hasResults = callSt.execute();
            // Lặp qua các dòng kết quả
            while (hasResults) {
                // Lấy thông tin từ kết quả
                // Sử dụng callSt.getResultSet() để lấy ResultSet (danh sách kết quả)
                ResultSet rs = callSt.getResultSet();
                // Sử dụng rs để duyệt và lấy thông tin từ kết quả
                // Đọc dữ liệu từ ResultSet và hiển thị
                 while (rs.next()) {
                     String authorName = rs.getString("author");
                     int bookCount = rs.getInt("count(bookid)");
                     System.out.println("Tên tác giả: " + authorName + ", Số lượng sách: " + bookCount);
                 }
                // Kiểm tra xem còn dòng kết quả nữa không
                hasResults = callSt.getMoreResults();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
    public static List<Book> sortBook(){
        List<Book> bookList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_book_to_price_asc()}");
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            bookList = new ArrayList<>();
            while (rs.next()){
                Book book = new Book();
                book.setBookId(rs.getString("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                bookList.add(book);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return bookList;
    }

}
