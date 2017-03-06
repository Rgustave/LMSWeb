package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.BookLoan;

public class BookCopyDAO extends BaseDAO implements ResultSetExtractor<List<BookCopy>> {

	public void create(BookCopy copies) throws Exception {
		template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values(?,?,?)",
				new Object[] { copies.getBookId(), copies.getBranchId(), copies.getNoOfCopies() });
	}

	public void update(BookCopy copies) throws Exception {
		template.update("update tbl_book_copies set noOfCopies = ? where bookId=? and branchId=?",
				new Object[] { copies.getNoOfCopies(), copies.getBookId(), copies.getBranchId() });

	}

	public void delete(BookCopy copy) throws Exception {
		template.update("delete from tbl_book_copies where bookId=? and branchId=?",
				new Object[] { copy.getBookId(), copy.getBranchId() });
	}

	public List<BookCopy> readAll() throws Exception {
		return template.query("select * from tbl_book_copies", this);

	}

	public BookCopy readbyPK(int bookId, int branchId) throws Exception {
		List<BookCopy> noOfCopies = (List<BookCopy>) template.query(
				"select * from tbl_book_copies" + " where bookId=? and branchId=?", new Object[] { bookId, branchId },
				this);
		if (noOfCopies != null && noOfCopies.size() > 0) {
			return noOfCopies.get(0);
		}
		return null;
	}

	@Override
	public List<BookCopy> extractData(ResultSet rs) throws SQLException {

		List<BookCopy> noOfCopies = new ArrayList<BookCopy>();
		while (rs.next()) {
			BookCopy copy = new BookCopy();
			copy.setBookId(rs.getInt("bookId"));
			copy.setBranchId(rs.getInt("branchId"));
			copy.setNoOfCopies(rs.getInt("noOfCopies"));

			noOfCopies.add(copy);
		}
		return noOfCopies;
	}

	public BookCopy updateNoOfCopies(BookLoan bl) {
		BookCopy bc = new BookCopy();
		bc.setBookId(bl.getBook().getBookId());
		bc.setBranchId(bl.getLibraryBranch().getBranchId());
		return bc;
	}

	public void addBookCopies(BookCopy bc, int NoOfCopies) {

		template.update("UPDATE tbl_book_copies  SET  noOfCopies  = (noOfCopies + ?) "
				+ "WHERE bookId =? AND branchId =?",
				new Object[] { NoOfCopies, bc.getBookId(), bc.getBranchId() });

	}

}