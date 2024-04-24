package socialbookstoreapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import socialbookstoreapp.formsdata.BookFormData;
import socialbookstoreapp.formsdata.RecommendationFormData;
import socialbookstoreapp.mappers.BookAuthorMapper;
import socialbookstoreapp.mappers.BookMapper;

@Component
public class AuthorsRecommendationStrategy extends TemplateRecommendationStrategy {
	
	public List<BookFormData> recommend(RecommendationFormData recommendationDTO, BookAuthorMapper bookAuthorMapper ) {
		List<BookFormData> recommendations = new ArrayList<BookFormData>();
		
		//bookAuthorMapper.findAllByName(recommendationDTO.getAuthors());
		return recommendations;
	}
}
