package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public Integer addGenreDAOWithID(Genre genre) throws ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}
	
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException {
		return readAll("SELECT * FROM tbl_genre", null);
	}

	public void addGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("delete  from tbl_genre where genre_id = ?", new Object[] { genre.getGenreId() });
	}
	public Genre readGenreByPk(Integer genreId) throws ClassNotFoundException, SQLException{
		List<Genre> genres = readAll("select * from tbl_genre where genre_id = ?", new Object[]{genreId});
		if(genres!=null && !genres.isEmpty()){
			return genres.get(0);
		}
		return null;
	}
	
	public void deleteBookGenre( Integer genreId) throws ClassNotFoundException, SQLException {
		save("delete  from tbl_book_genres where genre_id = ?", new Object[] {genreId});
	}
	
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			BookDAO bkdao = new BookDAO(conn);
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genre.setBooks(
					bkdao.readAllFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_genres"
							+ " where genre_id = ?)", new Object[] { genre.getGenreId() }));

			genres.add(genre);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {

		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			BookDAO bkdao = new BookDAO(conn);
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;

	}

	public List<Genre> readGenreByName(String genreName) throws ClassNotFoundException, SQLException {
		return readAll("select * from tbl_genre where genre_name like ?", new Object[] { "%" + genreName + "%" });
	}

}
