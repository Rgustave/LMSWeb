//package com.gcit.lms.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//import com.gcit.lms.dao.AuthorDAO;
//import com.gcit.lms.dao.BookCopyDAO;
//import com.gcit.lms.entity.Author;
//import com.gcit.lms.entity.BookCopy;
//
//public class LibrarianService {
//	ConnectionUtil util = new ConnectionUtil();	
//	
//	
//
//public List<BookCopy> readBookCopies() throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			BookCopyDAO bdao = new BookCopyDAO(conn);
//
//			return bdao.readAllBooks();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null)
//				conn.close();
//		}
//		return null;
//	}
//
//}
