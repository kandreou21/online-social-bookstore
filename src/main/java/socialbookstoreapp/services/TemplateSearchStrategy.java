package socialbookstoreapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import socialbookstoreapp.domainmodel.Book;
import socialbookstoreapp.formsdata.BookFormData;
import socialbookstoreapp.formsdata.SearchFormData;
import socialbookstoreapp.mappers.BookMapper;

@Component
public abstract class TemplateSearchStrategy implements SearchStrategy {
	@Autowired 
	BookMapper bookMapper;
	
	public List<BookFormData> search(SearchFormData searchFormData, BookMapper bookMapper){
		return null;
	}
	
	public abstract List<Book> makeInitialListOfBooks(SearchFormData searchDto);
	
	public abstract boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book);
}
