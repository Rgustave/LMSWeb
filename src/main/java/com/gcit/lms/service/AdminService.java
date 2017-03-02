package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService {

	ConnectionUtil util = new ConnectionUtil();

	public void addAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			Integer authorId = adao.addAuthorDAOWithID(author);
			if (author.getBooks() != null && !author.getBooks().isEmpty()) {
				for (Book b : author.getBooks()) {
					bdao.addBookAuthors(authorId, b.getBookId());		
				}
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	
	
	
	public void addLibraryBranch(LibraryBranch libraryBranch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			lbdao.addLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	

	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			PublisherDAO pdao = new PublisherDAO(conn);
			Integer publisherId = pdao.addPublisherWithID(publisher);
			if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
				for (Book b : publisher.getBooks()) {
					bdao.updateBookPublisher(publisherId, b.getBookId());	
				}
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void addGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			GenreDAO gdao = new GenreDAO(conn);
			Integer genreId = gdao.addGenreDAOWithID(genre);
			if (genre.getBooks() != null && !genre.getBooks().isEmpty()) {
				for (Book b : genre.getBooks()) {
					bdao.addBookGenres(genreId, b.getBookId());		
				}
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void udpateAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
		   adao.deleteBookAuthor(author.getAuthorId());	
			if (author.getBooks() != null && !author.getBooks().isEmpty()) {
				for (Book b : author.getBooks()) {
					
					adao.addBookAuthors(author.getAuthorId(), b.getBookId());
					
				}
			}
		  conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void udpatePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			BookDAO bdao = new BookDAO(conn);

			if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
				for (Book b : publisher.getBooks()) {
					bdao.updateBookPublisher(publisher.getPublisherId(), b.getBookId());
				}
			}
			
			pdao.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	
	public void udpateLibraryBranch(LibraryBranch lbranch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
		
			
			lbdao.updateLibraryBranch(lbranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public void udpateGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteBookGenre(genre.getGenreId());
			BookDAO bdao = new BookDAO(conn);

			if (genre.getBooks() != null && !genre.getBooks().isEmpty()) {
				for (Book b : genre.getBooks()) {
					
					bdao.addBookGenres(genre.getGenreId(), b.getBookId());		
				}
			}
			
			gdao.updateGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public List<Author> readAuthors(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);

			return adao.readAllAuthors(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	
	
	public List<Publisher> readPublisher() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			return pdao.readAllPublisher();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<LibraryBranch> readLibraryBranch() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

			return lbdao.readAllLibraryBranches();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Publisher> readPublishers() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			return pdao.readAllPublisher();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Genre> readGenres() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenres();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public void addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			Integer bookId = bdao.addBookWithID(book);
			if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
				for (Author a : book.getAuthors()) {
					bdao.addBookAuthors(a.getAuthorId(), bookId);
				}
			}
			if (book.getGenres() != null && !book.getGenres().isEmpty()) {
				for (Genre g : book.getGenres()) {
					bdao.addGenres(g.getGenreId(), bookId);
				}
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	
	public void updateBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			System.out.println("connection");
			BookDAO bdao = new BookDAO(conn);
			
			bdao.updateBook(book);
			System.out.println("Book UPDATED");

	        bdao.deleteBookAuthor(book.getBookId());
			System.out.println("Author deleted ");

	        bdao.deleteBookGenre(book.getBookId());
			System.out.println("genre deleted ");

			if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
				for (Author a : book.getAuthors()) {
					bdao.addBookAuthors(a.getAuthorId(), book.getBookId());
					System.out.println("authors  added ");

				}
			}
			if (book.getGenres() != null && !book.getGenres().isEmpty()) {
				for (Genre g : book.getGenres()) {
					bdao.addGenres(g.getGenreId(), book.getBookId());
					System.out.println("genre  added ");

				}
			}
			System.out.println("ready to commit   ");

			conn.commit();
			System.out.println("committed   ");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			//conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public List<Book> readBooks() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public Author readAuthorByPk(Integer authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorByPk(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}
	
	public Genre readGenreByPk(Integer genreId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenreByPk(genreId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}


	public Publisher readPublisherByPk(Integer publisherId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisherByPk(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}
	public List <Author> readAuthorsByName(String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthorsByName(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}
	public List <Author> readAuthorsByName(String searchString,Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthorsByName(searchString,pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}
	public LibraryBranch readLibraryBranchByPk(Integer branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			return lbdao.readLibraryBranchByPk(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}
	
	

	public Publisher  readPublisherByPK(Integer pubId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisherByPk(pubId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}

		return null;
	}


	public void deleteLibraryBranch(LibraryBranch lbranch) throws SQLException {
		
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			lbdao.deleteLibraryBranch(lbranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		
	}
	
	public Integer getAuthorsCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null)
				conn.close();
		}
		return null;
	}




	public Book readBookByPk(Integer bookId) throws SQLException  {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBookByPk(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}




	public List<Book> readBookByName(String bookTitle) throws SQLException {
		
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBookByName(bookTitle);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}




	public List<Genre> readGenreByName(String genreName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenreByName(genreName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}




	public List<Publisher> readPublisherbyName(String publisherName) throws SQLException {
		
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			
			return pdao.readPublisherByName(publisherName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}




	public Borrower readBorrowerByPK(Integer borrowerID) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowerByPk(borrowerID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			
			if (conn != null)
				conn.close();
		}

		return null;
	}
	
	
	public void checkOut(Integer cardNo, Integer branchId, Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			
			conn = util.getConnection();
			BookLoanDAO bkDAO = new BookLoanDAO(conn);
			bkDAO.checkOutBook(bookId, cardNo, branchId);;
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			
			if (conn != null)
				conn.close();
		}

	}
	
	
	public void checkIn(String checkIn) throws SQLException {
		Connection conn = null;
		try {
			
			conn = util.getConnection();
			BookLoanDAO bkDAO = new BookLoanDAO(conn);
			bkDAO.returnCheckedOutBook(checkIn);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			
			if (conn != null)
				conn.close();
		}

	}




}
