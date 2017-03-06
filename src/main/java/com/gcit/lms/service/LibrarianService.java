package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.LibraryBranch;

public class LibrarianService {

	@Autowired
	BookCopyDAO bookCopiesDAO;

	@Autowired
	LibraryBranchDAO branchDAO;

	@Transactional
	public List<BookCopy> readBookCopies() throws SQLException {

		try {
			return bookCopiesDAO.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Transactional
	public void addBookCopies(BookCopy bc, int NoOfCopie) throws SQLException {

		try {
			bookCopiesDAO.addBookCopies(bc, NoOfCopie);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public void udpateLibraryBranch(LibraryBranch lbranch) throws ClassNotFoundException, Exception {

		if (branchDAO.readLibraryBranchByPk(lbranch.getBranchId()) == null) {
			throw new Exception("Invalid Input.");
		} else {
			branchDAO.updateLibraryBranch(lbranch);
		}

	}
}
