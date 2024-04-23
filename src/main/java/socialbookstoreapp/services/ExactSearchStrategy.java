package socialbookstoreapp.services;

import java.util.List;

import org.springframework.stereotype.Component;

import socialbookstoreapp.domainmodel.Book;
import socialbookstoreapp.formsdata.SearchFormData;

@Component
public class ExactSearchStrategy extends TemplateSearchStrategy {

	@Override
	protected List<Book> makeInitialListOfBooks(SearchFormData searchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

}
