package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO {
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}
	
	public Publisher readPublisherByPk(Integer publisherId) throws ClassNotFoundException, SQLException{
		List<Publisher> publishers = readAll("select * from tbl_publisher where publisherId = ?", new Object[]{publisherId});
		if(publishers!=null && !publishers.isEmpty()){
			return publishers.get(0);
		}
		return null;
	}
	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException {
		return readAll("SELECT * FROM tbl_publisher", null);
	}
	
	public Integer addPublisherWithID(Publisher publisher) throws ClassNotFoundException, SQLException {
		
				return saveWithID("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (? , ?,?)",
				new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() });
	}
	public List<Publisher>readAllPublisher() throws ClassNotFoundException,SQLException{
		
		return readAll("SELECT * FROM tbl_publisher",null);
	}
	public void addGenre(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone)"
				+ " values (?,?,?)", new Object[] {publisher.getPublisherName(),publisher.
						getPublisherAddress(),publisher.getPublisherPhone()});
	}
	public void updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("update tbl_publisher set publisherName = ? ,publisherAddress =? ,publisherPhone = ?"
				+ " where publisherId = ?", new Object[] 
				{publisher.getPublisherName(),publisher.
					getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});
	}
	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("delete  from tbl_publisher where publisherId = ?", new Object[] {publisher.getPublisherId()});
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher publisher = new Publisher();
			BookDAO bkdao = new BookDAO(conn);
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
		    publisher.setBooks(bkdao.readAllFirstLevel("select * from tbl_book where pubId =? ",
						     new Object[]{publisher.getPublisherId()}));
			
		    publishers.add(publisher);
		}
		return publishers;
	}

	@Override
	public  List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
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
		return readAll("select * from tbl_publisher where publisherName like ?", new Object[] { "%" + publisherName + "%" });
	}

}
