package socialbookstoreapp.services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import socialbookstoreapp.domainmodel.BookAuthor;
import socialbookstoreapp.domainmodel.BookCategory;
import socialbookstoreapp.formsdata.BookFormData;
import socialbookstoreapp.formsdata.RecommendationFormData;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class CategoriesRecommendationStrategyTest {
	@Autowired
	private CategoriesRecommendationStrategy categoriesRecommendationStrategy;
	
	private RecommendationFormData recomFormData;

	@Test
	@Transactional
	@Sql(scripts="insert-book-data.sql", executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void testRecommend() {
		BookAuthor author = new BookAuthor(); 
		author.setName("author");
		
		BookCategory category = new BookCategory();
		category.setName("comedy");
		
		recomFormData = new RecommendationFormData();
		recomFormData.setRecommendationStrategy("categories");
		recomFormData.setAuthors(List.of(author));
		recomFormData.setCategories(List.of(category));
		
		List<BookFormData> books = categoriesRecommendationStrategy.recommend("user", recomFormData);
		Assertions.assertEquals(2, books.size());
	}
}
