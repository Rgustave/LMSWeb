package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>>   {
	
	
	public Publisher readPublisherByPk(Integer publisherId) throws ClassNotFoundException, SQLException{
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId = ?", new Object[]{publisherId},this);
		if(publishers!=null && !publishers.isEmpty()){
			return publishers.get(0);
		}
		return null;
	}
	
	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException {
		return template.query("SELECT * FROM tbl_publisher", this);
	}
	
	public List<Publisher> readPublisherByBooks(Integer bookId){
		return template.query("SELECT *FROM tbl_publisher WHERE publisherId "
				+ "IN( SELECT pubId FROM  tbl_book where bookId= ? ) ", new Object[]{bookId}, this);
	}
	public Integer addPublisherWithID(Publisher publisher) throws ClassNotFoundException, SQLException {
		
				return template.update("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (? , ?,?)",
				new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() });
	}
	public List<Publisher>readAllPublisher() throws ClassNotFoundException,SQLException{
		
		return template.query("SELECT * FROM tbl_publisher",this);
	}
	public void addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		template.update("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone)"
				+ " values (?,?,?)", new Object[] {publisher.getPublisherName(),publisher.
						getPublisherAddress(),publisher.getPublisherPhone()});
	}
	public void updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		template.update("update tbl_publisher set publisherName = ? ,publisherAddress =? ,publisherPhone = ?"
				+ " where publisherId = ?", new Object[] 
				{publisher.getPublisherName(),publisher.
					getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});
	}
	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		template.update("delete  from tbl_publisher where publisherId = ?", new Object[] {publisher.getPublisherId()});
	}

	
	

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
		    
			
		    publishers.add(publisher);
		}
		return publishers;
	}



	public List<Publisher> readPublisherByName(String publisherName) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_publisher where publisherName like ?", new Object[] { "%" + publisherName + "%" },this);
	}

}
