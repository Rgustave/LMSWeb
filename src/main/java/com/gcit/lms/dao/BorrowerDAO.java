package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;

public class BorrowerDAO  extends BaseDAO {
	public BorrowerDAO(Connection conn) {
		super(conn);
	}
	public Integer addABorrowerDAOWithID(Borrower borrower) throws ClassNotFoundException, SQLException {
		return saveWithID("INSERT INTO tbl_borrower  (address,name,phone)  VALUES(?,?,?)", 
				new Object[] { borrower.getAddress(),borrower.getName(),borrower.getPhone() });
	}
	public void addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_borrower  (address,name,phone)  VALUES(?,?,?)", 
				new Object[] { borrower.getAddress(),borrower.getName(),borrower.getPhone()});
	}
	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("UPDATE    tbl_borrower  SET address =?,phone = ? ,name = ? WHERE  cardNo=?", 
				new Object[] {borrower.getAddress(),borrower.getPhone(),
						borrower.getName(),borrower.getCardNo()});
	}
	public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("delete from tbl_borrower   where cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException{
		return readAll("select * from tbl_borrower", null);
	}
	
	public List<Borrower> readAllBorrowerByName(String borrowerName) throws ClassNotFoundException, SQLException{
		return readAll("select * from tbl_borrower where name like ?", new Object[]{"%"+borrowerName+"%"});
	}
	
	public Borrower readAuthorByPk(Integer borrowerId) throws ClassNotFoundException, SQLException{
		List<Borrower> borrowers = readAll("select * from tbl_borrower where cardNo = ?", new Object[]{borrowerId});
		if(borrowers!=null && !borrowers.isEmpty()){
			return borrowers.get(0);
		}
		return null;
	}
	
	@Override
	public  List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower b= new Borrower();
			BookDAO bkdao = new BookDAO(conn);
			
			b.setAddress(rs.getString("address"));
			b.setName(rs.getString("name"));
			b.setPhone(rs.getString("phone"));
			b.setCardNo(rs.getInt("cardNo"));
		   
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
	
		return null;
	}
	public Borrower readBorrowerByPk(Integer borrowerID) throws ClassNotFoundException, SQLException {
		List<Borrower> borrowers = readAll("select * from tbl_borrower where cardNo = ?", new Object[] { borrowerID });
		if (borrowers != null && !borrowers.isEmpty()) {
			return borrowers.get(0);
		}
		return null;
	}
	



}
