package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;


import org.springframework.jdbc.core.ResultSetExtractor;


public class BookCopyDAO extends BaseDAO{

	
	
	
	public List<BookCopy> readAllBooks() throws ClassNotFoundException, SQLException {
		return template.query("SELECT*FROM tbl_book_copies", this);
	}
	
	
	@Override
	public List<BookCopy> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		
		List<BookCopy> bookCopies = new ArrayList<>();

		while (rs.next()) {
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
			BookDAO bDAO = new BookDAO(conn);
				
			BookCopy bcp =new BookCopy();
			bcp.setBookCopies(rs.getInt("noOfCopies"));
			
			if (rs.getInt("bookId") > 0) {
				bcp.setBook(bDAO.readBookByPk(rs.getInt("bookId")));
			}
			
			if (rs.getInt("branchId") > 0) {
				bcp.setLibraryBranch(lbDAO.readLibraryBranchByPk(rs.getInt("branchId")));
			}
			
		
			bookCopies.add(bcp);
		}

		return bookCopies;	}

	@Override
	public <T> List<T> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	//bookId branchId noOfCopies 
	
	public void SaveBookCopy(BookCopy bookCopy) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)", new Object[] {bookCopy.getBook().getBookId()
				, bookCopy.getLibraryBranch().getBranchId(), bookCopy.getBookCopies()});
	}
	public void addBookCopy(BookCopy bookCopy) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)", new Object[] {bookCopy.getBook().getBookId()
				, bookCopy.getLibraryBranch().getBranchId(), bookCopy.getBookCopies()});
	}
	
}
