
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookLoanDAO;

import com.gcit.lms.entity.BookLoan;;

public class BorrowerService {

	@Autowired
	BookLoanDAO bookLoanDAO;

	@Transactional
	public ArrayList<BookLoan> readBookLoans(int CardNo) throws SQLException {

		try {
			return (ArrayList<BookLoan>) bookLoanDAO.readAllCheckOutBooksByCardNo(CardNo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void checkOut(Integer cardNo, Integer branchId, Integer bookId) throws SQLException, ClassNotFoundException {

		bookLoanDAO.checkOutBook(bookId, cardNo, branchId);
	}

	@Transactional
	public void checkIn(String checkIn) throws SQLException, ClassNotFoundException {

		bookLoanDAO.returnCheckedOutBook(checkIn);

	}
}
