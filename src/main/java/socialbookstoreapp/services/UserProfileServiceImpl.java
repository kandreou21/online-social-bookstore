package socialbookstoreapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private SearchFactory searchFactory;
	
	@Autowired 
	private RecommendationsFactory recommendationsFactory;
	
	@Override
	public UserProfileFormData retrieveProfile(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserProfileFormData userProfileFormData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookFormData> retrieveBookOffers(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBookOffer(String username, BookFormData bookFormData) {
		// TODO Auto-generated method stub
		
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
	public void deleteBookOffer(String username, int bookid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookRequest(String username, int bookld) {
		// TODO Auto-generated method stub
		
	}

}
