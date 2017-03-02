package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class LibraryBranchDAO extends BaseDAO {
	
	
	
	
	
	public LibraryBranchDAO (Connection conn) {
		super(conn);
	}
	
	public LibraryBranch readLibraryBranchByPk(Integer branchId) throws ClassNotFoundException, SQLException{
		List<LibraryBranch> libraryBranches = readAll("select * from tbl_library_branch where branchId = ?", new Object[]{branchId});
		if(libraryBranches!=null && !libraryBranches.isEmpty()){
			return libraryBranches.get(0);
		}
		return null;
	}
	public Integer addLibraryBranchDAOWithID(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		return saveWithID("INSERT INTO tbl_library_branch  (branchName,branchAddress)  VALUES(?,?)", 
				new Object[] { libraryBranch.getBranchName(),libraryBranch.getBranchAddress() });
	}
	public void addLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_library_branch  (branchName,branchAddress)  VALUES(?,?)",
				new Object[] { libraryBranch.getBranchName(),libraryBranch.getBranchAddress() });
	}
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		save("UPDATE  tbl_library_branch  SET branchName =? "
				+ ",branchAddress = ?  WHERE branchId = ? ", 
				new Object[] {libraryBranch.getBranchName(),libraryBranch.getBranchAddress(),libraryBranch.getBranchId()});
	}
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		save("delete  from tbl_library_branch where branchId = ?", new Object[] {libraryBranch.getBranchId()});
	}
	
	public List<LibraryBranch> readAllLibraryBranches() throws ClassNotFoundException, SQLException{
		return readAll("select * from tbl_library_branch", null);
	}
	
	public List<LibraryBranch> readAllLibraryBranchByName(String branchName) throws ClassNotFoundException, SQLException{
		return readAll("select * from tbl_library_branch where branchName like ?", new Object[]{"%"+branchName+"%"});
	}
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<LibraryBranch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
	
		return null;
	}

	
	

}
