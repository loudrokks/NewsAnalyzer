package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiException;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "0038b5ccc1124e94b01d19b0d5982697";  //apikey eintragn

	public void process(NewsApi newsApi) throws NewsApiException {
		System.out.println("Start process");

		//TODO load the news based on the parameters
		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}

		//TODO implement Error handling
		//eigne klasse+

		//?a funkt net
		//TODO implement methods for analysis
		//anz d artikl
		List<Article> articles = newsResponse.getArticles();
		long count = articles.stream().count();
		System.out.println("anzahl: "+count);
		//public long getArticlesamount(List<Article> articles)
		//return articles.stream().count();
		//return articles.size();
		//count i immer 20 :?

		//provider md häufigstn artikln
		String Provider = articles.stream().collect(Collectors.groupingBy(article -> article.getSource().getName(), Collectors.counting()))
				.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).orElseThrow(NoSuchElementException::new).getKey();
		System.out.println("provider: "+Provider);

		//kürzester authorname
		Article author = articles.stream().filter(au -> au.getAuthor() != null).sorted(Comparator.comparingInt(value -> value.getAuthor().length())).findFirst().orElse(new Article());
		System.out.println("kürzester authorname: "+author);

		//sort titel nach länge / alphabet
		List sort = articles.stream().sorted(Comparator.comparingInt(ar -> ar.getTitle().length())).collect(Collectors.toList());
		System.out.println("titel sortiert nach länge/alphabet: "+sort);

		System.out.println("End process");
		}
	
	public Object getData() {
		return null;
	}
}
