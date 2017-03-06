package com.gcit.lms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "index";
	}

	@RequestMapping(value = "/Admin", method = RequestMethod.GET)
	public String book(Locale locale, Model model) {
		model.addAttribute("adminService", adminService);
		return "book";
	}

	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String author(Locale locale, Model model) {
		return "author";
	}

	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String borrower(Locale locale, Model model) {
		return "borrowerFunctionality";
	}

	@RequestMapping(value = "/libraryBranch", method = RequestMethod.GET)
	public String lbraryBranch(Locale locale, Model model) {
		return "libraryBranch";
	}

	@RequestMapping(value = "/genre", method = RequestMethod.GET)
	public String genre(Locale locale, Model model) {
		return "genre";
	}

	@RequestMapping(value = "/editBook", method = RequestMethod.GET)
	public String editBook(Locale locale, Model model) {
		return "editBook";
	}

	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String librarian(Locale locale, Model model) {
		return "librarian";
	}

	@RequestMapping(value = "/editgenre", method = RequestMethod.GET)
	public String editgenre(Locale locale, Model model) {
		return "editgenre2";
	}

	@RequestMapping(value = "/editpublisher", method = RequestMethod.GET)
	public String editPublisher(Locale locale, Model model) {
		return "editpublisher";
	}

	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String publisher(Locale locale, Model model) {
		return "publisher";
	}

	@RequestMapping(value = "/editLibraryBranch", method = RequestMethod.GET)
	public String editLibraryBranch(Locale locale, Model model) {
		return "editLibraryBranch";
	}

	@RequestMapping(value = "/editLiribrianBranch", method = RequestMethod.GET)
	public String editLiribrianBranch(Locale locale, Model model) {
		return "editLiribrianBranch";
	}

	@RequestMapping(value = "/addBookCopies", method = RequestMethod.GET)
	public String addBookCopies(Locale locale, Model model) {
		return "addBookCopies";
	}

	@RequestMapping(value = "/editAuthor", method = RequestMethod.GET)
	public String prepareEditAuthor(Locale locale, Model model,
			@RequestParam(value = "authorId", required = false) Integer id) {

		Author author = null;
		try {
			author = adminService.readAuthorByPk(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("author", author);

		return "editauthor";
	}

	@RequestMapping(value = "/editAuthor", method = { RequestMethod.POST })
	public String editAuthor(Locale locale, Model model,
			@RequestParam(value = "authorName", required = false) String authorName,
			@RequestParam(value = "authorId", required = false) Integer authorId,
			@RequestParam(value = "bookIds", required = false) String[] bookIds) {
		List<Book> books = new ArrayList<Book>();

		if (bookIds != null && bookIds.length > 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book b = new Book();
				b.setBookId(Integer.parseInt(bookIds[i]));
				books.add(b);
			}
		}
		Author author = new Author();

		author.setBooks(books);
		author.setAuthorName(authorName);
		author.setAuthorId(authorId);

		try {
			adminService.udpateAuthor(author);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "author";
	}

	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String prepareDeleteAuthor(Locale locale, Model model,
			@RequestParam(value = "authorId", required = false) Integer id) {

		Author author = new Author();
		author.setAuthorId(id);
		try {
			adminService.deleteAuthor(author);
			;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "author";
	}

	@RequestMapping(value = "/addAuthor", method = { RequestMethod.POST })
	public String prepareAddAuthor(Locale locale, Model model,
			@RequestParam(value = "authorName", required = false) String authorName,
			@RequestParam(value = "bookIds", required = false) String[] bookIds) {

		Author author = new Author();

		List<Book> books = new ArrayList<Book>();
		if (bookIds != null && bookIds.length > 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book b = new Book();
				b.setBookId(Integer.parseInt(bookIds[i]));
				books.add(b);
			}
		}
		author.setBooks(books);
		author.setAuthorName(authorName);
		try {
			adminService.addAuthor(author);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "author";
	}

	@RequestMapping(value = "/addBook", method = { RequestMethod.POST })
	public String prepareAddBook(Locale locale, Model model,
			@RequestParam(value = "bookTitle", required = false) String bookTitle,
			@RequestParam(value = "pubId", required = false) int pubId,
			@RequestParam(value = "genreIds", required = false) String[] genreIds,
			@RequestParam(value = "authorIds", required = false) String[] authorIds)

	{
		Publisher publisher = null;
		try {
			publisher = adminService.readPublisherByPk(pubId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Book book = new Book();
		List<Genre> genres = new ArrayList<Genre>();
		List<Author> authors = new ArrayList<Author>();

		if (genreIds != null && genreIds.length > 0) {
			for (int i = 0; i < genreIds.length; i++) {
				Genre g = new Genre();
				g.setGenreId(Integer.parseInt(genreIds[i]));
				genres.add(g);
			}
		}

		if (authorIds != null && authorIds.length > 0) {
			for (int i = 0; i < authorIds.length; i++) {
				Author a = new Author();
				a.setAuthorId(Integer.parseInt(authorIds[i]));
				authors.add(a);
			}
		}
		book.setGenres(genres);
		book.setAuthors(authors);
		book.setPublisher(publisher);
		book.setTitle(bookTitle);

		try {
			adminService.addBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "book";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String prepareDeleteBook(Locale locale, Model model,
			@RequestParam(value = "bookId", required = false) Integer id) {

		Book book = new Book();
		book.setBookId(id);
		try {
			adminService.deleteBook(book);
			;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "author";
	}

	@RequestMapping(value = "/editBook", method = RequestMethod.GET)
	public String prepareeditBook(Locale locale, Model model,
			@RequestParam(value = "bookId", required = false) Integer id) {

		Book book = null;
		try {
			book = adminService.readBookByPk(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("book", book);

		return "editBook";
	}

	@RequestMapping(value = "/editBook", method = { RequestMethod.POST })
	public String editBook(Locale locale, Model model,
			@RequestParam(value = "bookId", required = false) Integer bookId,
			@RequestParam(value = "bookTitle", required = false) String bookTitle,
			@RequestParam(value = "pubId", required = false) int pubId,
			@RequestParam(value = "genreIds", required = false) String[] genreIds,
			@RequestParam(value = "authorIds", required = false) String[] authorIds)

	{

		Book book = new Book();
		List<Genre> genres = new ArrayList<Genre>();
		List<Author> authors = new ArrayList<Author>();

		Publisher publisher = null;
		try {
			publisher = adminService.readPublisherByPk(pubId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (genreIds != null && genreIds.length > 0) {
			for (int i = 0; i < genreIds.length; i++) {
				Genre g = new Genre();
				g.setGenreId(Integer.parseInt(genreIds[i]));
				genres.add(g);
			}
		}
		if (authorIds != null && authorIds.length > 0) {
			for (int i = 0; i < authorIds.length; i++) {
				Author a = new Author();
				a.setAuthorId(Integer.parseInt(authorIds[i]));
				authors.add(a);
			}
		}
		
		book.setBookId(bookId);
		book.setGenres(genres);	
		book.setAuthors(authors);
		book.setPublisher(publisher);
		book.setTitle(bookTitle);
		try {
			adminService.updateBook(book);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "book";
	}

}
