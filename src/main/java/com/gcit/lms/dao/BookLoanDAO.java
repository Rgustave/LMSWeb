package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

public class BookLoanDAO extends BaseDAO {

	public BookLoanDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	
	
public void checkOutBook(int bookId, int cardNo,int branchId) throws ClassNotFoundException, SQLException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String checkOutDay = dateFormat.format(timestamp);
		Calendar cal = Calendar.getInstance();cal.setTime(timestamp);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		timestamp.setTime(cal.getTime().getTime());
		timestamp = new Timestamp(cal.getTime().getTime());
		String dueDate  = dateFormat.format(timestamp);
 	
		System.out.println(checkOutDay);
		System.out.println(dueDate);

			
			save("INSERT INTO tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) VALUES (?,?,?,?,?)", 
					new Object[] { bookId,branchId,cardNo,checkOutDay, dueDate});	
		
	

	}

public void returnCheckedOutBook(String checkOUt) throws ClassNotFoundException, SQLException{
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String chekInDate = dateFormat.format(timestamp);

			
		save("UPDATE   tbl_book_loans  SET  dateIn = ? WHERE  dateOut = ?  AND dateIn IS NULL", 
				new Object[] {chekInDate, checkOUt});	
	
	

}


public void returnCheckedOutBook(Book bk, Borrower br, LibraryBranch lb) throws ClassNotFoundException, SQLException{
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String chekInDate = dateFormat.format(timestamp);

	
	if(bk!=null && br!=null && lb !=null){
		
		save("UPDATE   tbl_book_loans  SET  dateIn = ? WHERE  bookId = ? "
			+ "AND  branchId = ? AND cardNo = ?  AND dateIn IS NULL", 
				new Object[] {chekInDate, bk.getBookId(),lb.getBranchId(),br.getCardNo()});	
	
	}

}

public List<BookLoan> readAllCheckOutBooksByCardNo(int CardNo) throws ClassNotFoundException, SQLException {
	return readAll("select * from tbl_book_loans where CardNo = ? AND dateIn IS NULL", new Object[] {CardNo});
}



@Override
public  List<BookLoan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
	
	
	List<BookLoan> bookLoans = new ArrayList<>();

	while (rs.next()) {
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
		BookDAO bDAO = new BookDAO(conn);
		BorrowerDAO  brDAO= new BorrowerDAO(conn);
			
		BookLoan bkl =new BookLoan();
		bkl.setCheckoutTime(rs.getString("dateOut"));
		bkl.setDueDate(rs.getString("dateOut"));
		bkl.setCheckin(rs.getString("dateIn"));
		
		if (rs.getInt("bookId") > 0) {
			bkl.setBook(bDAO.readBookByPk(rs.getInt("bookId")));
		}
		
		if (rs.getInt("branchId") > 0) {
			bkl.setLibraryBranch(lbDAO.readLibraryBranchByPk(rs.getInt("branchId")));
		}
		
		if (rs.getInt("branchId") > 0) {
			bkl.setBorrower(brDAO.readAuthorByPk(rs.getInt("cardNo")));
		}
		
	
		bookLoans.add(bkl);
	}

	return bookLoans;
	
	
}



@Override
public <T> List<T> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
	return null;
}
}
