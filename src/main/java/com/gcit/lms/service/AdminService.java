package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	AuthorDAO authorDAO;
	@Autowired
	BookDAO bookDAO;
	@Autowired
	PublisherDAO publisherDAO;
	@Autowired
	BorrowerDAO borrowerDAO;
	@Autowired
	LibraryBranchDAO branchDAO;
	@Autowired
	GenreDAO genreDAO;
	@Autowired
	BookLoanDAO bookLoanDAO;


	@Transactional
	public void addAuthor(Author author) throws Exception {

		Integer authorId = authorDAO.addAuthorDAOWithID(author);
	
		if (author.getBooks() != null && !author.getBooks().isEmpty()) {
			for (Book b : author.getBooks()) {
				authorDAO.addBookAuthors(authorId, b.getBookId());
			}
		}

	}

	@Transactional
	public void addLibraryBranch(LibraryBranch libraryBranch) throws Exception {

		if (libraryBranch == null || libraryBranch.getBranchName() == null
				|| libraryBranch.getBranchName().length() == 0 || libraryBranch.getBranchName().length() > 45) {
			throw new Exception("Invalid input");
		} else if (libraryBranch == null || libraryBranch.getBranchAddress() == null
				|| libraryBranch.getBranchAddress().length() == 0 || libraryBranch.getBranchAddress().length() > 45) {
			throw new Exception("Invalid input");
		} else {
			branchDAO.addLibraryBranch(libraryBranch);
		}
	}

	@Transactional
	public void addPublisher(Publisher publisher) throws Exception {

		Integer publisherId = 0;

		if (publisher == null || publisher.getPublisherName() == null || publisher.getPublisherName().length() == 0
				|| publisher.getPublisherName().length() > 45) {
			throw new Exception("Invalid input");
		} else if (publisher == null || publisher.getPublisherAddress() == null
				|| publisher.getPublisherAddress().length() == 0 || publisher.getPublisherAddress().length() > 45) {
			throw new Exception("Invalid input");
		} else if (publisher == null || publisher.getPublisherPhone() == null
				|| publisher.getPublisherPhone().length() == 0 || publisher.getPublisherPhone().length() > 45) {
			throw new Exception("Invalid input");
		} else {
			publisherId = publisherDAO.addPublisherWithID(publisher);
		}
		if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
			for (Book b : publisher.getBooks()) {
				bookDAO.updateBookPublisher(publisherId, b.getBookId());
			}
		}

	}

	@Transactional
	public void addGenre(Genre genre) throws Exception {
		Integer genreId = genreDAO.addGenreDAOWithID(genre);

			if (genre.getBooks() != null && !genre.getBooks().isEmpty() && genreId > 0) {
				for (Book b : genre.getBooks()) {
					bookDAO.addBookGenres(genreId, b.getBookId());
				}
			}
		}
	

	@Transactional
	public void addBook(Book book) throws Exception {
		Integer bookId =  bookDAO.addBookWithID(book);
		

		if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
			for (Author a : book.getAuthors()) {
				bookDAO.addBookAuthors(a.getAuthorId(), bookId);
			}
		}
		if (book.getGenres() != null && !book.getGenres().isEmpty()) {
			for (Genre g : book.getGenres()) {
				bookDAO.addBookGenres(g.getGenreId(), bookId);
			}
		}
	}

	@Transactional
	public void udpateAuthor(Author author) throws ClassNotFoundException, Exception {

		if (authorDAO.readAuthorByPk(author.getAuthorId()) == null) {
			throw new Exception("The author you are trying to update does not exist");
		}
		authorDAO.updateAuthor(author);
		authorDAO.deleteBookAuthor(author.getAuthorId());

		if (author.getBooks() != null && !author.getBooks().isEmpty()) {
			for (Book b : author.getBooks()) {
				authorDAO.addBookAuthors(author.getAuthorId(), b.getBookId());

			}
		}
	}

	@Transactional
	public void udpatePublisher(Publisher publisher) throws ClassNotFoundException, Exception {
		if (publisherDAO.readPublisherByPk(publisher.getPublisherId()) == null) {
			throw new Exception("Invalid input");
		}
		publisherDAO.updatePublisher(publisher);

		if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
			for (Book b : publisher.getBooks()) {
				bookDAO.updateBookPublisher(publisher.getPublisherId(), b.getBookId());
			}
		}

	}

	@Transactional
	public void udpateLibraryBranch(LibraryBranch lbranch) throws ClassNotFoundException, Exception {

		if (branchDAO.readLibraryBranchByPk(lbranch.getBranchId()) == null) {
			throw new Exception("Invalid Input.");
		} else {
			branchDAO.updateLibraryBranch(lbranch);
		}

	}

	@Transactional
	public void udpateGenre(Genre genre) throws ClassNotFoundException, Exception {

		if (genreDAO.readGenreByPk(genre.getGenreId()) == null) {
			throw new Exception("Invalid input");
		}

		genreDAO.updateGenre(genre);
		genreDAO.deleteBookGenre(genre.getGenreId());
		if (genre.getBooks() != null && !genre.getBooks().isEmpty()) {
			for (Book b : genre.getBooks()) {

				bookDAO.addBookGenres(genre.getGenreId(), b.getBookId());
			}
		}

	}

	@Transactional
	public void updateBook(Book book) throws ClassNotFoundException, Exception {

		if (bookDAO.readBookByPk(book.getBookId()) == null) {
			throw new Exception("Invalid input");
		}
		bookDAO.updateBook(book);
		bookDAO.deleteBookAuthor(book.getBookId());
		bookDAO.deleteBookGenre(book.getBookId());

		if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
			for (Author a : book.getAuthors()) {
				bookDAO.addBookAuthors(a.getAuthorId(), book.getBookId());

			}
		}
		if (book.getGenres() != null && !book.getGenres().isEmpty()) {
			for (Genre g : book.getGenres()) {
				bookDAO.addBookGenres(g.getGenreId(), book.getBookId());

			}
		}

	}

	@Transactional
	public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {

		if (author != null) {

			authorDAO.deleteAuthor(author);

		}

	}

	@Transactional
	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {

		if (publisher != null) {

			publisherDAO.deletePublisher(publisher);
		}
	}

	@Transactional
	public void deleteGenre(Genre genre) throws SQLException, ClassNotFoundException {

		if (genre != null) {

			genreDAO.deleteGenre(genre);
		}

	}

	@Transactional
	public void deleteBook(Book book) throws SQLException, ClassNotFoundException {

		if (book != null) {

			bookDAO.deleteBook(book);
		}
	}

	@Transactional
	public void deleteLibraryBranch(LibraryBranch lbranch) throws ClassNotFoundException, SQLException {

		if (lbranch != null) {

			branchDAO.deleteLibraryBranch(lbranch);
		}

	}

	@Transactional
	public List<Genre> readGenres(Integer pageNo) throws SQLException, ClassNotFoundException {

		List<Genre> allGenres = genreDAO.readAllGenres();

		if (allGenres != null && !allGenres.isEmpty()) {
			for (Genre g : allGenres) {
				g.setBooks(bookDAO.readBooksByGenres(g.getGenreId()));
			}
		}

		return allGenres;

	}

	@Transactional
	public List<Author> readAuthors(Integer pageNo) throws SQLException, ClassNotFoundException {

		List<Author> allAuthors = authorDAO.readAllAuthors();

		if (allAuthors != null && !allAuthors.isEmpty()) {
			for (Author a : allAuthors) {
				a.setBooks(bookDAO.readBooksByAuthors(a.getAuthorId()));
			}
		}
		return allAuthors;

	}

	@Transactional
	public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException {

		List<Publisher> allPublishers = publisherDAO.readAllPublishers();

		if (allPublishers != null && !allPublishers.isEmpty()) {
			for (Publisher p : allPublishers) {
				p.setBooks(bookDAO.readBooksByPublishers(p.getPublisherId()));
			}
		}

		return allPublishers;

	}

	@Transactional
	public List<LibraryBranch> readLibraryBranch() throws SQLException, ClassNotFoundException {

		List<LibraryBranch> allLibraryBranchs = branchDAO.readAllLibraryBranches();
		return allLibraryBranchs;

	}

	@Transactional
	public List<Book> readBooks() throws SQLException, ClassNotFoundException {

		List<Book> allBooks = bookDAO.readAllBooks();

		if (allBooks != null && !allBooks.isEmpty()) {
			for (Book b : allBooks) {
				b.setAuthors(authorDAO.readAuthorsByBooks(b.getBookId()));
			}
		}
		if (allBooks != null && !allBooks.isEmpty()) {
			for (Book b : allBooks) {
				b.setGenres(genreDAO.readGenresByBooks(b.getBookId()));
			}
		}

		if (allBooks != null && !allBooks.isEmpty()) {
			for (Book b : allBooks) {
				for (Publisher p : publisherDAO.readPublisherByBooks(b.getBookId())) {

					b.setPublisher(p);

				}

			}

		}

		return allBooks;
	}

	public Author readAuthorByPk(Integer authorId) throws SQLException, ClassNotFoundException {

		return authorDAO.readAuthorByPk(authorId);

	}

	@Transactional
	public Genre readGenreByPk(Integer genreId) throws SQLException, ClassNotFoundException {

		return genreDAO.readGenreByPk(genreId);

	}

	@Transactional
	public Publisher readPublisherByPk(Integer publisherId) throws ClassNotFoundException, SQLException {

		return publisherDAO.readPublisherByPk(publisherId);

	}

	@Transactional
	public Borrower readBorrowerByPK(Integer borrowerID) throws SQLException, ClassNotFoundException {

		return borrowerDAO.readBorrowerByPk(borrowerID);

	}

	@Transactional
	public LibraryBranch readLibraryBranchByPk(Integer branchId) throws ClassNotFoundException, SQLException {

		return branchDAO.readLibraryBranchByPk(branchId);

	}

	@Transactional
	public List<Author> readAuthorsByName(String searchString) throws ClassNotFoundException, SQLException {

		return authorDAO.readAllAuthorsByName(searchString);

	}

	@Transactional
	public Book readBookByPk(Integer bookId) throws SQLException, ClassNotFoundException {
		return bookDAO.readBookByPk(bookId);
	}

	@Transactional
	public List<Book> readBookByName(String bookTitle) throws SQLException, ClassNotFoundException {

		if (bookTitle != null) {
			return bookDAO.readAllBookByName(bookTitle);
		}
		return null;
	}

	@Transactional
	public List<Genre> readGenreByName(String genreName) throws SQLException, ClassNotFoundException {

		if (genreName != null) {
			return genreDAO.readGenreByName(genreName);
		} else {

			return null;
		}
	}

	@Transactional
	public List<Publisher> readPublisherbyName(String publisherName) throws ClassNotFoundException, SQLException {

		if (publisherName != null) {
			return publisherDAO.readPublisherByName(publisherName);

		} else {
			return null;
		}
	}

	@Transactional
	public void checkOut(Integer cardNo, Integer branchId, Integer bookId) throws SQLException, ClassNotFoundException {

		bookLoanDAO.checkOutBook(bookId, cardNo, branchId);
	}

	@Transactional
	public void checkIn(String checkIn) throws SQLException, ClassNotFoundException {

		bookLoanDAO.returnCheckedOutBook(checkIn);

	}

}
