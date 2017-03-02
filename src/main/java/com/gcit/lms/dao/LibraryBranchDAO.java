package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO implements ResultSetExtractor<List<LibraryBranch>>  {
	
	
	public LibraryBranch readLibraryBranchByPk(Integer branchId) throws ClassNotFoundException, SQLException{
		List<LibraryBranch> libraryBranches = template.query("select * from tbl_library_branch where"
				+ " branchId = ?", new Object[]{branchId},this);
		if(libraryBranches!=null && !libraryBranches.isEmpty()){
			return libraryBranches.get(0);
		}
		return null;
	}
	public Integer addLibraryBranchDAOWithID(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		return template.update("INSERT INTO tbl_library_branch  (branchName,branchAddress)  VALUES(?,?)", 
				new Object[] { libraryBranch.getBranchName(),libraryBranch.getBranchAddress() });
	}
	public void addLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		template.update("INSERT INTO tbl_library_branch  (branchName,branchAddress)  VALUES(?,?)",
				new Object[] { libraryBranch.getBranchName(),libraryBranch.getBranchAddress() });
	}
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		template.update("UPDATE  tbl_library_branch  SET branchName =? "
				+ ",branchAddress = ?  WHERE branchId = ? ", 
				new Object[] {libraryBranch.getBranchName(),libraryBranch.getBranchAddress(),libraryBranch.getBranchId()});
	}
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		template.update("delete  from tbl_library_branch where branchId = ?", new Object[] {libraryBranch.getBranchId()});
	}
	
	public List<LibraryBranch> readAllLibraryBranches() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_library_branch", this);
	}
	
	public List<LibraryBranch> readAllLibraryBranchByName(String branchName) throws ClassNotFoundException, SQLException{
		return  template.query("select * from tbl_library_branch where branchName "
				+ "like ?", new Object[]{"%"+branchName+"%"},this);
	}
	
	@Override
	public  List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> libraryBranches = new ArrayList<>();
		while(rs.next()){
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchAddress(rs.getString("branchAddress"));
			lb.setBranchId(rs.getInt("branchId"));
			lb.setBranchName(rs.getString("branchName"));
			
		    libraryBranches.add(lb);
		}
		return libraryBranches;
	}

	
	
	

}
