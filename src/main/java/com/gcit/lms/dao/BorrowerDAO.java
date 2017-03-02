package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Borrower;

public class BorrowerDAO  extends BaseDAO implements ResultSetExtractor<List<Borrower>> {
	
	public Integer addABorrowerDAOWithID(Borrower borrower) throws ClassNotFoundException, SQLException {
		return  template.update("INSERT INTO tbl_borrower  (address,name,phone)  VALUES(?,?,?)", 
				new Object[] { borrower.getAddress(),borrower.getName(),borrower.getPhone() });
	}
	public void addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		template.update("INSERT INTO tbl_borrower  (address,name,phone)  VALUES(?,?,?)", 
				new Object[] { borrower.getAddress(),borrower.getName(),borrower.getPhone()});
	}
	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		template.update("UPDATE    tbl_borrower  SET address =?,phone = ? ,name = ? WHERE  cardNo=?", 
				new Object[] {borrower.getAddress(),borrower.getPhone(),
						borrower.getName(),borrower.getCardNo()});
	}
	public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		template.update("delete from tbl_borrower   where cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_borrower", this);
	}
	
	public List<Borrower> readAllBorrowerByName(String borrowerName) throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_borrower where name like ?", new Object[]{"%"+borrowerName+"%"},this);
	}
	
	public Borrower readAuthorByPk(Integer borrowerId) throws ClassNotFoundException, SQLException{
		List<Borrower> borrowers = template.query("select * from tbl_borrower where cardNo = ?", new Object[]{borrowerId},this);
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
			b.setAddress(rs.getString("address"));
			b.setName(rs.getString("name"));
			b.setPhone(rs.getString("phone"));
			b.setCardNo(rs.getInt("cardNo"));
		   
			borrowers.add(b);
		}
		return borrowers;
	}


	public Borrower readBorrowerByPk(Integer borrowerID) throws ClassNotFoundException, SQLException {
		List<Borrower> borrowers = template.query("select * from tbl_borrower where cardNo = ?", new Object[] { borrowerID},this);
		if (borrowers != null && !borrowers.isEmpty()) {
			return borrowers.get(0);
		}
		return null;
	}
	



}
