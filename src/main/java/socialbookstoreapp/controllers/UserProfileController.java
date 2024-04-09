package socialbookstoreapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import socialbookstoreapp.formsdata.BookFormData;
import socialbookstoreapp.formsdata.RecommendationsFormData;
import socialbookstoreapp.formsdata.SearchFormData;
import socialbookstoreapp.formsdata.UserProfileFormData;
import socialbookstoreapp.services.UserProfileService;
import socialbookstoreapp.services.UserService;

@Controller
public class UserProfileController {
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserProfileService userProfileService;
	
	public String getUserMainMenu() {
		return null;
	}
	
	public String retrieveProfile(Model model) {
		return null;
		
	}
	
	public String saveProfile(UserProfileFormData userProfileFormData, Model theModel) {
		return null;
	}
	
	public String listBookOffers(Model model) {
		return null;
	}
	
	public String showOfferForm(Model model) {
		return null;
	}
	
	public String saveOffer(BookFormData bookFormData, Model model) {
		return null;
	}
	
	public String showSearchForm(Model model) {
		return null;
	}
	
	public String search(SearchFormData searchFormData, Model model) {
		return null;
	}
	
	public String showRecommendationsForm(Model model) {
		return null;
	}
	
	public String recommendBooks(RecommendationsFormData recomFormData, Model model) {
		return null;
	}

	public String requestBook(int bookId, Model model) {
		return null;
	}
	
	public String showUserBookRequests(Model model) {
		return null;
	}
	
	public String showRequestingUsersForBookOffer(int bookId, Model model) {
		return null;
	}
	
	public String acceptRequest(String username, int bookld, Model model) {
		return username;
	}
	
	public String deleteBookOffer(String username, int bookId, Model model) {
		return username;
	}
	
	public String deleteBookRequest(String username, int bookId, Model model) {
		return username;
	}
}
