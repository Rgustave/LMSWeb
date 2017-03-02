package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;


public class AuthorDAO extends BaseDAO implements ResultSetExtractor<List<Author>>{

	public void addAuthor(Author author) throws SQLException, ClassNotFoundException {
		template.update("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}

	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
		template.update("update tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}

	public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {
		template.update("delete from tbl_author where authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	public List<Author> readAllAuthors(Integer pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return template.query("select * from tbl_author", this);
	}
	
	public List<Author> readAllAuthorsByName(String authorName) throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_author where authorName like ?", new Object[]{"%"+authorName+"%"}, this);
	}
	
//	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException{
//		return getCount("tbl_author");
//	}
	
	public Author readAuthorByPk(Integer authorId) throws ClassNotFoundException, SQLException{
		List<Author> authors = template.query("select * from tbl_author where authorId = ?", new Object[]{authorId}, this);
		if(authors!=null && !authors.isEmpty()){
			return authors.get(0);
		}
		return null;
	}
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException{
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
}
