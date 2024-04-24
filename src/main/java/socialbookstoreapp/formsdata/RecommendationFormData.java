package socialbookstoreapp.formsdata;

import java.util.List;

public class RecommendationFormData {
	private String recommendationStrategy;
	
	private List<String> authors;
	
	private List<String> categories;

	public String getRecommendationStrategy() {
		return recommendationStrategy;
	}

	public void setRecommendationStrategy(String recommendationStrategy) {
		this.recommendationStrategy = recommendationStrategy;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
