//
//package com.gcit.lms.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import com.gcit.lms.dao.BookLoanDAO;
//
//import com.gcit.lms.entity.BookLoan;
//;
//
//
//public class BorrowerService {
//	ConnectionUtil util = new ConnectionUtil();	
//	public ArrayList<BookLoan> readBookLoans(int CardNo) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			BookLoanDAO blDAO = new BookLoanDAO(conn);
//			
//			return (ArrayList<BookLoan>) blDAO.readAllCheckOutBooksByCardNo(CardNo);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null)
//				conn.close();
//		}
//		return null;
//	}
//}
