package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;

import java.util.List;

public class Controller {

	public static final String APIKEY = "0038b5ccc1124e94b01d19b0d5982697";  //apikey eintragn

	public void process(NewsApi newsApi) {
		System.out.println("Start process");

		//TODO load the news based on the parameters
		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}

		//TODO implement Error handling



		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData() {
		
		return null;
	}
}
