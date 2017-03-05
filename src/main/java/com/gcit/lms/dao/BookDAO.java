package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;

public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>>{
	
	public void addBook(Book book) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_book (title, pubId) values (? , ?)", new Object[]{book.getTitle(), book.getPublisher().getPublisherId()});
	}
	
	public Integer addBookWithID(Book book) throws ClassNotFoundException, SQLException{
		return template.update("insert into tbl_book (title, pubId) values (? , ?)", new Object[]{book.getTitle(), book.getPublisher().getPublisherId()});
	}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException{
		template.update("update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[]{book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException{
		template.update("delete * from tbl_book where bookId= ? ", new Object[]{book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_book", this);
	}
	
	public void addBookAuthors(Integer authorId, Integer bookId) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_book_authors values (? , ?)", new Object[]{bookId, authorId}, this);
	}
	
	
	public void updateBookPublisher(Integer pubId, Integer bookId) throws ClassNotFoundException, SQLException {
		template.update("update tbl_book set  pubId =? where bookId =?", new Object[] { pubId,bookId });
	}
	
	
	public void addBookGenres(Integer genreId, Integer bookId) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_book_genres (genre_id,bookId ) values (? , ?)", new Object[] { genreId ,bookId});
	}
	
	public void deleteBookAuthor(Integer bookId) throws SQLException, ClassNotFoundException {
		template.update("delete  from tbl_book_authors where bookId = ?", new Object[] {bookId});
	}


	public void deleteBookGenre( Integer bookId) throws ClassNotFoundException, SQLException {
		template.update("delete  from tbl_book_genres where bookId = ?", new Object[] {bookId});
	}
	
	public List<Book> readAllBookByName(String bookTitle) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book where title like ?", new Object[] { "%" + bookTitle + "%" },this);
	}
	
	
	
	
	public Book readBookByPk(Integer bookId) throws ClassNotFoundException, SQLException{
		List<Book> books = template.query("select * from tbl_book where bookId = ?", new Object[]{bookId}, this);
		if(books!=null && !books.isEmpty()){
			return books.get(0);
		}
		return null;
	}
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book b = new Book();
			b.setTitle(rs.getString("title"));
			b.setBookId(rs.getInt("bookId"));
			books.add(b);
		}
		return books;
	}
	

}
