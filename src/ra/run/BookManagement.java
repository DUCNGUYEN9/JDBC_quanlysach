package ra.run;

import ra.bussiness.BookBussiness;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************MENU*************\n" +
                    "   1. Hiển thị các sách\n" +
                    "   2. Thêm mới sách\n" +
                    "   3. Cập nhật sách\n" +
                    "   4. Xóa sách\n" +
                    "   5. Tìm sách theo tên sách\n" +
                    "   6. Thống kê sách theo tác giả\n" +
                    "   7. Sắp xếp sách theo giá tăng dần (procedure)\n" +
                    "   8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListBook();
                    break;
                case 2:
                    createBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    searchBook(scanner);
                    break;
                case 6:
                    statisticsFromAuthor();
                    break;
                case 7:
                    sortFromPriceASC();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-8");
            }
        } while (true);
    }
    public static void displayListBook(){
        List<Book> bookList = BookBussiness.getAllBook();
        System.out.println("THÔNG TIN CÁC SÁCH:");
        bookList.forEach(Book::displayData);
    }
    public static void createBook(Scanner scanner){
        //Nhập dữ liệu cho 1 sach để thêm mới
        Book bookNew = new Book();
        bookNew.inputData(scanner);
        //Gọi createBook của BookBussiness để thực hiện thêm dữ liệu vào db
        boolean result = BookBussiness.createBook(bookNew);
        if (result){
            System.out.println("Thêm mới thành công");
        }else{
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
        }
    }

    public static void updateBook(Scanner scanner){
        System.out.println("Nhập mã sach cần cập nhật:");
        String bookIdUpdate = scanner.nextLine();
        //Kiểm tra mã sach này có tồn tại hay không
        Book bookUpdate = BookBussiness.getBookById(bookIdUpdate);
        if (bookUpdate!=null){
            //Mã sach có tồn tại trong CSDL
            System.out.println("Nhập vào tên sach cần cập nhật:");
            bookUpdate.setBookName(scanner.nextLine());
            System.out.println("Nhập vào giá sach:");
            bookUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.println("Nhập vào tên tác giả cần cập nhật:");
            bookUpdate.setAuthor(scanner.nextLine());
            System.out.println("Nhập vào trạng thái sach:");
            bookUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
            //Thực hiện cập nhật
            boolean result = BookBussiness.updateBook(bookUpdate);
            if (result){
                System.out.println("Cập nhật thành công");
            }else{
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
            }
        }else{
            //Mã sach không tồn tại trong CSDL
            System.err.println("Mã  không tồn tại");
        }
    }
    public static void deleteBook(Scanner scanner){
        System.out.println("Nhập vào mã sach cần xóa:");
        String bookIdDelete = scanner.nextLine();
        //Kiểm tra bookDelete có tồn tại trong DB
        Book bookDelete = BookBussiness.getBookById(bookIdDelete);
        if (bookDelete!=null){
            boolean result = BookBussiness.deleteBook(bookIdDelete);
            if (result){
                System.out.println("Xóa thành công");
            }else{
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
            }
        }else{
            System.err.println("Mã sach không tồn tại");
        }
    }
    public static void searchBook(Scanner scanner){
        System.out.println("Nhập vào ten sach cần tim:");
        String bookNameSearch = scanner.nextLine();
        //Kiểm tra bookSearch có tồn tại trong DB
        Book bookSearch = BookBussiness.getBookByName(bookNameSearch);
        if (bookSearch!=null){
            List<Book> bookList = BookBussiness.searchBook(bookNameSearch);
            System.out.println("THÔNG TIN CÁC SÁCH TIM KIEM:");
            bookList.forEach(Book::displayData);
        }else{
            System.err.println("ten sach không tồn tại");
        }
    }
    public static void statisticsFromAuthor(){
        System.out.println("** Thông Tin Thống kê ***");
        BookBussiness.statisticsAuthor();
    }
    public static void sortFromPriceASC(){
        List<Book> bookList = BookBussiness.sortBook();
        System.out.println("Sắp xếp theo giá: ");
        bookList.forEach(Book::displayData);
    }

}

