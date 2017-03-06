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
import com.gcit.lms.entity.LibraryBranch;
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

	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String librarian(Locale locale, Model model) {
		return "librarian";
	}

	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String publisher(Locale locale, Model model) {
		return "publisher";
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
	public String editBook(Locale locale, Model model, @RequestParam(value = "bookId", required = false) Integer bookId,
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

	@RequestMapping(value = "/editGenre", method = RequestMethod.GET)
	public String prepareeditGenre(Locale locale, Model model,
			@RequestParam(value = "genreId", required = false) Integer id) {

		Genre genre = null;
		try {
			genre = adminService.readGenreByPk(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("genre", genre);

		return "editgenre2";
	}

	@RequestMapping(value = "/editGenre", method = { RequestMethod.POST })
	public String editGenre(Locale locale, Model model,
			@RequestParam(value = "genreId", required = false) Integer genreId,
			@RequestParam(value = "genreName", required = false) String genreName,
			@RequestParam(value = "bookIds", required = false) String[] bookIds)

	{

		Genre genre = new Genre();
		List<Book> books = new ArrayList<Book>();
		if (bookIds != null && bookIds.length > 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book b = new Book();
				b.setBookId(Integer.parseInt(bookIds[i]));
				books.add(b);
			}
		}

		genre.setBooks(books);
		genre.setGenreId(genreId);
		genre.setGenreName(genreName);

		try {
			adminService.udpateGenre(genre);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "genre";
	}

	@RequestMapping(value = "/addGenre", method = { RequestMethod.POST })
	public String prepareaddGenre(Locale locale, Model model,
			@RequestParam(value = "genreName", required = false) String genreName,
			@RequestParam(value = "bookIds", required = false) String[] bookIds)

	{

		Genre genre = new Genre();
		List<Book> books = new ArrayList<>();

		if (bookIds != null && bookIds.length > 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book b = new Book();
				b.setBookId(Integer.parseInt(bookIds[i]));
				books.add(b);
			}
		}

		genre.setBooks(books);
		genre.setGenreName(genreName);

		try {
			adminService.addGenre(genre);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "genre";
	}

	@RequestMapping(value = "/deleteGenre", method = RequestMethod.GET)
	public String preparedeleteGenre(Locale locale, Model model,
			@RequestParam(value = "genreId", required = false) Integer genreId) {

		Genre genre = new Genre();
		genre.setGenreId(genreId);
		try {
			adminService.deleteGenre(genre);
			;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "genre";
	}

	@RequestMapping(value = "/addPublisher", method = { RequestMethod.POST })
	public String prepareaddPublisher(Locale locale, Model model,
			@RequestParam(value = "publisherName", required = false) String publisherName,
			@RequestParam(value = "publisherAddress", required = false) String publisherAddress,
			@RequestParam(value = "publisherPhone", required = false) String publisherPhone,
			@RequestParam(value = "bookIds", required = false) String[] bookIds)

	{

		Publisher publisher = new Publisher();
		List<Book> books = new ArrayList<Book>();

		if (bookIds != null && bookIds.length > 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book b = new Book();
				System.out.println(Integer.parseInt(bookIds[i]));
				b.setBookId(Integer.parseInt(bookIds[i]));
				books.add(b);
			}
		}
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		publisher.setBooks(books);

		try {
			adminService.addPublisher(publisher);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "publisher";
	}

	@RequestMapping(value = "/editPublisher", method = RequestMethod.GET)
	public String prepareeditPublisher(Locale locale, Model model,
			@RequestParam(value = "pubId", required = false) Integer pubId) {

		Publisher publisher = null;
		try {
			publisher = adminService.readPublisherByPk(pubId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("publisher", publisher);

		return "editpublisher";
	}

	@RequestMapping(value = "/editPublisher", method = { RequestMethod.POST })
	public String editPublisher(Locale locale, Model model,
			@RequestParam(value = "publisherName", required = false) String publisherName,
			@RequestParam(value = "pubId", required = false) int publisherId,
			@RequestParam(value = "publisherAddress", required = false) String publisherAddress,
			@RequestParam(value = "publisherPhone", required = false) String publisherPhone,
			@RequestParam(value = "bookIds", required = false) String[] bookIds)

	{

		Publisher publisher = new Publisher();
		List<Book> books = new ArrayList<Book>();

		if (bookIds != null && bookIds.length > 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book b = new Book();
				b.setBookId(Integer.parseInt(bookIds[i]));
				books.add(b);
			}
		}

		publisher.setBooks(books);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherName(publisherName);
		publisher.setPublisherId(publisherId);
		publisher.setPublisherPhone(publisherPhone);

		try {
			adminService.udpatePublisher(publisher);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "publisher";
	}

	@RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
	public String preparedeletePublisher(Locale locale, Model model,
			@RequestParam(value = "pubId", required = false) Integer pubId) {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(pubId);
		try {
			adminService.deletePublisher(publisher);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "publisher";
	}

	@RequestMapping(value = "/addlBranch", method = { RequestMethod.POST })
	public String prepareaddlBranch(Locale locale, Model model,
			@RequestParam(value = "lbranchName", required = false) String lbranchName,
			@RequestParam(value = "lbranchAddress", required = false) String lbranchAddress)

	{
		LibraryBranch lbranch = new LibraryBranch();

		lbranch.setBranchAddress(lbranchAddress);
		lbranch.setBranchName(lbranchName);

		try {
			adminService.addLibraryBranch(lbranch);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "libraryBranch";
	}
	
	@RequestMapping(value = "/editlBranch", method = RequestMethod.GET)
	public String prepareeditlBranch(Locale locale, Model model,
			@RequestParam(value = "branchId", required = false) Integer branchId) {

		LibraryBranch lbranch = null;
		try {
			lbranch = adminService.readLibraryBranchByPk(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("lbranch", lbranch);

		return "editLibraryBranch";
	}

	@RequestMapping(value = "/editlBranch", method = { RequestMethod.POST })
	public String eeditlBranch(Locale locale, Model model,
			@RequestParam(value = "lbranchName", required = false) String lbranchName,
			@RequestParam(value = "branchId", required = false) int branchId,
			@RequestParam(value = "lbAddress", required = false) String lbAddress)

	{
		LibraryBranch lbranch = new LibraryBranch();
		lbranch.setBranchAddress(lbAddress);
		lbranch.setBranchName(lbranchName);
		lbranch.setBranchId(branchId);


		try {
			adminService.udpateLibraryBranch(lbranch);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "libraryBranch";
	}
	


	
	@RequestMapping(value = "/deletelBranch", method = RequestMethod.GET)
	public String preparedeletelBranch(Locale locale, Model model,
			@RequestParam(value = "branchId", required = false) Integer branchId) {
		LibraryBranch lbranch = new LibraryBranch();
		lbranch.setBranchId(branchId);
		try {
			adminService.deleteLibraryBranch(lbranch);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "libraryBranch";
	}
	
	
	//  This method is not working I will come back on it later
	@RequestMapping(value = "/searchBooks", method = { RequestMethod.POST })
	public String searchBooks(Locale locale, Model model,
			@RequestParam(value = "searchString", required = false) String bookTitle)

	{
		StringBuffer str = new StringBuffer();

		try {
	List<Book> Allbooks = adminService.readBookByName(bookTitle);
			
			str.append("<thead><tr><th>#</th><th>Title</th><th>Publisher</th><th>Genre</th><th>Authors"
					+ "</th><th>Update</th><th>Delete</th></tr></thead><tbody>");
			for (Book b : Allbooks) {
				str.append("<tr><td scope=\"row\">"+Allbooks.indexOf(b) + 1+"</td><td>"+b.getTitle()+"</td><td>");
			
				if(b.getPublisher()!=null){
					str.append(b.getPublisher().getPublisherName()+",");
				}
				str.append("</td><td>");
				
				if (b.getGenres() != null && !b.getGenres().isEmpty()) {
					List<Genre> genres = b.getGenres() ;
					for (Genre gr : genres) {
						str.append((gr.getGenreName())+",");
					}
				}
				str.append("</td><td>");
				if ( b.getAuthors()!= null && !b.getAuthors().isEmpty()) {
					List<Author> authors = b.getAuthors();
					for (Author a : authors) {
						str.append((a.getAuthorName())+",");
					}
				}
				str.append("</td>");

				str.append("</td>");
				str.append("<td><button type='button' class='btn btn-sm btn-warning' onclick='javascript:location.href='editBook?bookId="+b.getBookId()+"''>Edit</button></td>");
				str.append("<td><button type='button' class='btn btn-sm btn-danger' onclick='javascript:location.href='deleteBook?bookId="+b.getBookId()+"''>Delete</button></td><tr>");
			}
			str.append("</tbody>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "libraryBranch";
	}
	
	//checkIn
	
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET)
	public String prepareCheckIn(Locale locale, Model model,
			@RequestParam(value = "checkoutTime", required = false) String checkOutDate) {
		
		try {
			adminService.checkIn(checkOutDate);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "libraryBranch";
	}
	
	
	@RequestMapping(value = "/checkOutBook", method = { RequestMethod.POST })
	public String checkOutBook(Locale locale, Model model,
			@RequestParam(value = "bookId", required = false) int bookId,
			@RequestParam(value = "branchId", required = false) int branchId,
			@RequestParam(value = "cardNo", required = false) int cardNo)

	{
		try {
			adminService.checkOut(cardNo, branchId, bookId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
				
	}
	
	//LibrianeditlBranch
	
	@RequestMapping(value = "/LibrianeditlBranch", method = RequestMethod.GET)
	public String prepareLibrianeditlBranch(Locale locale, Model model,
			@RequestParam(value = "branchId", required = false) Integer branchId) {

		LibraryBranch liribrianbranch = null;
		try {
			liribrianbranch = adminService.readLibraryBranchByPk(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("lbranch", liribrianbranch);

		return "editLiribrianBranch";
	}

	@RequestMapping(value = "/LibrianeditlBranch", method = { RequestMethod.POST })
	public String LibrianeditlBranch(Locale locale, Model model,
			@RequestParam(value = "lbranchName", required = false) String lbranchName,
			@RequestParam(value = "branchId", required = false) int branchId,
			@RequestParam(value = "lbAddress", required = false) String lbAddress)

	{
		LibraryBranch lbranch = new LibraryBranch();
		lbranch.setBranchAddress(lbAddress);
		lbranch.setBranchName(lbranchName);
		lbranch.setBranchId(branchId);


		try {
			adminService.udpateLibraryBranch(lbranch);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "libririan";
	}
}
