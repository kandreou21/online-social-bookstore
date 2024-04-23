package socialbookstoreapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialbookstoreapp.domainmodel.Book;
import socialbookstoreapp.domainmodel.BookAuthor;
import socialbookstoreapp.domainmodel.BookCategory;
import socialbookstoreapp.domainmodel.UserProfile;
import socialbookstoreapp.formsdata.BookFormData;
import socialbookstoreapp.formsdata.RecommendationsFormData;
import socialbookstoreapp.formsdata.SearchFormData;
import socialbookstoreapp.formsdata.UserProfileFormData;
import socialbookstoreapp.mappers.BookAuthorMapper;
import socialbookstoreapp.mappers.BookCategoryMapper;
import socialbookstoreapp.mappers.BookMapper;
import socialbookstoreapp.mappers.UserProfileMapper;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired 
	private UserProfileMapper userProfileMapper;
	
	@Autowired 
	private BookAuthorMapper bookAuthorMapper;
	
	@Autowired 
	private BookCategoryMapper bookCategoryMapper;
	
	@Autowired 
	private BookMapper bookMapper;
	
	@Autowired
	private SearchStrategyFactory searchStrategyFactory;
	
	@Autowired 
	private RecommendationStrategyFactory recommendationStrategyFactory;
	
	private UserProfileFormData convertToDTO(UserProfile userProfile) {
		UserProfileFormData userProfileDTO = new UserProfileFormData();
        return userProfileDTO;
    }

    private UserProfile convertToEntity(UserProfileFormData userProfileFormData) {
    	UserProfile userProfile = new UserProfile();
    	return userProfile;
    }
    
	@Override
	public UserProfileFormData retrieveProfile(String username) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		UserProfileFormData userProfileFormData = new UserProfileFormData();
		
		userProfileFormData.setUsername(username);
		userProfileFormData.setFullName(userProfile.getFullName());
		userProfileFormData.setAge(userProfile.getAge());
		userProfileFormData.setAddress(userProfile.getAddress());
		userProfileFormData.setPhonenumber(userProfile.getPhonenumber());
		userProfileFormData.setBookOffers(userProfile.getBookOffers());
		userProfileFormData.setFavouriteBookAuthors(userProfile.getFavouriteBookAuthors());
		userProfileFormData.setFavouriteBookCategories(userProfile.getFavouriteBookCategories());
		userProfileFormData.setRequestedBooks(userProfile.getRequestedBooks());

		return userProfileFormData;
	}

	@Override
	@Transactional
	public void save(UserProfileFormData userProfileFormData) {
		UserProfile userProfile;
		if (userProfileMapper.findByUsername(userProfileFormData.getUsername()) == null) {
			userProfile = new UserProfile();
			userProfile.setUsername(userProfileFormData.getUsername());
		} else {
			userProfile = userProfileMapper.findByUsername(userProfileFormData.getUsername());
		}
		
		userProfile.setFullName(userProfileFormData.getFullName());
		userProfile.setAge(userProfileFormData.getAge());
		userProfile.setAddress(userProfileFormData.getAddress());
		userProfile.setPhonenumber(userProfileFormData.getPhonenumber());
		userProfile.setBookOffers(userProfileFormData.getBookOffers());
		userProfile.setRequestedBooks(userProfileFormData.getRequestedBooks());
		List<BookAuthor> favouriteAuthors = new ArrayList<>();
	    List<BookCategory> favouriteCategories = new ArrayList<>();
	    
	    if (userProfileFormData.getFavouriteBookAuthors() != null) {
		    for (BookAuthor author: userProfileFormData.getFavouriteBookAuthors()) {
		        BookAuthor existingAuthor = bookAuthorMapper.findByName(author.getName());
		        if (existingAuthor == null) {
		            bookAuthorMapper.save(author);
		            favouriteAuthors.add(author);
		        } else {
		            favouriteAuthors.add(existingAuthor);
		        }
		    }
	    }
	    if (userProfileFormData.getFavouriteBookCategories() != null) {
		    for (BookCategory category: userProfileFormData.getFavouriteBookCategories()) {
		        BookCategory existingCategory = bookCategoryMapper.findByName(category.getName());
		        if (existingCategory == null) {
		            bookCategoryMapper.save(category);
		            favouriteCategories.add(category);
		        } else {
		            favouriteCategories.add(existingCategory);
		        }
		    }
	    }
	    userProfile.setFavouriteBookAuthors(favouriteAuthors);
	    userProfile.setFavouriteBookCategories(favouriteCategories);
	    userProfileMapper.save(userProfile);
	}
	

	@Override
	public List<BookFormData> retrieveBookOffers(String username) {
		List<Book> bookOffers = userProfileMapper.findByUsername(username).getBookOffers();
		
		List<BookFormData> bookOffersDTO = new ArrayList<>();
	    for (Book book: bookOffers) {
	        BookFormData bookFormData = convertToBookFormData(book);
	        bookOffersDTO.add(bookFormData);
	    }
	    return bookOffersDTO;
	}
	
	private BookFormData convertToBookFormData(Book book) {
	    BookFormData bookFormData = new BookFormData();
	    bookFormData.setBookId(book.getBookId());
	    bookFormData.setTitle(book.getTitle());
	    bookFormData.setSummary(book.getSummary());
	    bookFormData.setBookAuthors(book.getBookAuthors());
	    bookFormData.setBookCategory(book.getBookCategory());
	    bookFormData.setRequestingUsers(book.getRequestingUsers());
	    
	    return bookFormData;
	}

	@Override
	public void addBookOffer(String username, BookFormData bookFormData) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		List<BookAuthor> bookAuthors = new ArrayList<BookAuthor>();
		for (BookAuthor author: bookFormData.getBookAuthors()) {
	        BookAuthor existingAuthor = bookAuthorMapper.findByName(author.getName());
	        if (existingAuthor == null) {
	            bookAuthorMapper.save(author);
	            bookAuthors.add(author);
	        } else {
	        	bookAuthors.add(existingAuthor);
	        }
	    }
		bookFormData.setBookAuthors(bookAuthors);
		BookCategory bookCategory = bookCategoryMapper.findByName(bookFormData.getBookCategory().getName());
		if (bookCategory == null) {
			bookCategoryMapper.save(bookFormData.getBookCategory());
		} else {
			bookFormData.setBookCategory(bookCategory);
		}
		Book bookOffer = convertToBook(bookFormData);
		userProfile.addBookOffer(bookOffer);
		userProfileMapper.save(userProfile);
	}

	private Book convertToBook(BookFormData bookFormData) {
	    Book book = new Book();
	    book.setTitle(bookFormData.getTitle());
	    book.setSummary(bookFormData.getSummary());
	    book.setBookAuthors(bookFormData.getBookAuthors());
	    book.setBookCategory(bookFormData.getBookCategory());
	    book.setRequestingUsers(bookFormData.getRequestingUsers());
	    
	    return book;
	}
	
	@Override
	public List<BookFormData> searchBooks(SearchFormData searchFormData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookFormData> recommendBooks(String username, RecommendationsFormData recomFormData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void requestBook(int bookid, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookFormData> retrieveBookRequests(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserProfileFormData> retrieveRequestingUsers(int bookld) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookOffer(String username, int bookId) {
		UserProfile userProfile = userProfileMapper.findByUsername(username);
		//Book bookOffer = bookMapper.findById(bookId).get();
		
		//userProfile.deleteBookOffer(bookOffer);
		bookMapper.deleteById(bookId);
		//userProfileMapper.save(userProfile);
	}

	@Override
	public void deleteBookRequest(String username, int bookld) {
		// TODO Auto-generated method stub
		
	}
	
}
