package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class GenreDAO extends BaseDAO implements ResultSetExtractor<List<Genre>> {

	

	public Integer addGenreDAOWithID(Genre genre) throws ClassNotFoundException, SQLException {
		return  template.update("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}
	
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException {
		return template.query("SELECT * FROM tbl_genre", this);
	}

	public void addGenre(Genre genre) throws SQLException, ClassNotFoundException {
		template.update("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		template.update("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException, ClassNotFoundException {
		template.update("delete  from tbl_genre where genre_id = ?", new Object[] { genre.getGenreId() });
	}
	public Genre readGenreByPk(Integer genreId) throws ClassNotFoundException, SQLException{
		List<Genre> genres = template.query ("select * from tbl_genre where genre_id = ?", new Object[]{genreId},this);
		if(genres!=null && !genres.isEmpty()){
			return genres.get(0);
		}
		return null;
	}
	
	

	public void deleteBookGenre( Integer genreId) throws ClassNotFoundException, SQLException {
		template.update("delete  from tbl_book_genres where genre_id = ?", new Object[] {genreId});
	}
	
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {

		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));	
			genres.add(genre);
			
		}
		return genres;
	}


	public List<Genre> readGenreByName(String genreName) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_genre where genre_name like ?", new Object[] { "%" + genreName + "%" },this);
	}

}
