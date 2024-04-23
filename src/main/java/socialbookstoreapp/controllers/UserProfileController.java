package socialbookstoreapp.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import socialbookstoreapp.domainmodel.BookAuthor;
import socialbookstoreapp.domainmodel.BookCategory;
import socialbookstoreapp.domainmodel.UserProfile;
import socialbookstoreapp.formsdata.BookFormData;
import socialbookstoreapp.formsdata.RecommendationsFormData;
import socialbookstoreapp.formsdata.SearchFormData;
import socialbookstoreapp.formsdata.UserProfileFormData;
import socialbookstoreapp.services.UserProfileService;
import socialbookstoreapp.services.UserService;

@Controller
@RequestMapping("/user")
public class UserProfileController {
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserProfileService userProfileService;
	
	@RequestMapping("/dashboard")
	public String getUserMainMenu() {
		return "user/dashboard";
	}
	
	@RequestMapping("/createProfile") 
	public String setProfileInfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); 
		
		UserProfileFormData userProfileDTO = userProfileService.retrieveProfile(username);
		model.addAttribute("userProfileDTO", userProfileDTO);
		return "user/user-form";
	}
	
	@RequestMapping("/retrieveProfile") 
	public String retrieveProfile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		UserProfileFormData userProfileDTO = userProfileService.retrieveProfile(username);
		model.addAttribute("userProfileDTO", userProfileDTO);
		return "user/retrieve-profile";	
	}
	
	@RequestMapping("/saveProfile") 
	public String saveProfile(@ModelAttribute("userProfileDTO") UserProfileFormData userProfileFormData, 
								@RequestParam("bookCategories") String bookCategories,
								@RequestParam("bookAuthors") String bookAuthors,
								Model theModel) {
			
		List<BookCategory> favouriteBookCategories = new ArrayList<BookCategory>();
		List<BookAuthor> favouriteBookAuthors = new ArrayList<BookAuthor>();
		List<String> bookCategoriesList = Arrays.asList(bookCategories.split("\\s*,\\s*"));
		List<String> bookAuthorsList = Arrays.asList(bookAuthors.split("\\s*,\\s*"));
		
		for (String favouriteBookCategory: bookCategoriesList) {
			BookCategory bookCategory = new BookCategory();
			bookCategory.setName(favouriteBookCategory);
			favouriteBookCategories.add(bookCategory);
		}
		
		for (String favouriteBookAuthor: bookAuthorsList) {
			BookAuthor bookAuthor = new BookAuthor();
			bookAuthor.setName(favouriteBookAuthor);
			favouriteBookAuthors.add(bookAuthor);
		}
		userProfileFormData.setFavouriteBookCategories(favouriteBookCategories);
		userProfileFormData.setFavouriteBookAuthors(favouriteBookAuthors);
		userProfileService.save(userProfileFormData);
		return "redirect:/user/dashboard";
	}
	
	@RequestMapping("/listBookOffers")
	public String listBookOffers(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BookFormData> bookOffersDTO = userProfileService.retrieveBookOffers(username);
				
		model.addAttribute("bookOffersDTO", bookOffersDTO);
		return "user/list-book-offers";
	}
	
	@RequestMapping("/showOfferForm")
	public String showOfferForm(Model model) {
		BookFormData bookDTO = new BookFormData();
		
		model.addAttribute("bookDTO", bookDTO);	
		return "user/book-offer-form";
	}
	
	@RequestMapping("/saveOffer")
	public String saveOffer(@ModelAttribute("bookDTO") BookFormData bookFormData, 
							@RequestParam("authors") String authors,
							Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BookAuthor> bookAuthors = new ArrayList<BookAuthor>();
		List<String> bookAuthorsList = Arrays.asList(authors.split("\\s*,\\s*"));

		for (String bookAuthorName: bookAuthorsList) {
			BookAuthor bookAuthor = new BookAuthor();
			bookAuthor.setName(bookAuthorName);
			bookAuthors.add(bookAuthor);
		}
		bookFormData.setBookAuthors(bookAuthors);
		userProfileService.addBookOffer(username, bookFormData);		
		return "redirect:/user/listBookOffers";
	}
	
	@RequestMapping("/deleteBookOffer")
	public String deleteBookOffer(@RequestParam("bookId") int bookId, String username, Model model) {
		username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		userProfileService.deleteBookOffer(username, bookId);
		return "redirect:/user/listBookOffers";
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
	
	public String deleteBookRequest(String username, int bookId, Model model) {
		return username;
	}
}
