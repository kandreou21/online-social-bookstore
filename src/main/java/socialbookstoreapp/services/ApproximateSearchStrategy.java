package socialbookstoreapp.services;

import java.util.List;

import org.springframework.stereotype.Component;

import socialbookstoreapp.domainmodel.Book;
import socialbookstoreapp.formsdata.SearchFormData;

@Component
public class ApproximateSearchStrategy extends TemplateSearchStrategy {

	@Override
	public List<Book> makeInitialListOfBooks(SearchFormData searchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

}
