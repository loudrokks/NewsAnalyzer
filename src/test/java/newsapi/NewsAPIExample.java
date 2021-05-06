package newsapi;

import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import java.util.List;

public class NewsAPIExample {

    public static final String APIKEY = "0038b5ccc1124e94b01d19b0d5982697";                                     //2376bc7f1c5e493b916edeeecb03f0ec  //b084aa58809048bbac829f464a423a4f

    public static void main(String[] args){

        NewsApi newsApi = new NewsApiBuilder()  //builded url
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)
                .setSourceCountry(Country.at)
                .setSourceCategory(Category.health)
                .createNewsApi();
        NewsReponse newsResponse = null;
        try {
            newsResponse = newsApi.getNews();
        } catch (NewsApiException e) {
            e.printStackTrace();
        }
        if(newsResponse != null){
                List<Article> articles = newsResponse.getArticles();
                articles.stream().forEach(article -> System.out.println(article.toString()));
            }

        newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.EVERYTHING)
                .setFrom("2021-04-20")
                .setExcludeDomains("Lifehacker.com")
                .createNewsApi();
        try {
            newsResponse = newsApi.getNews();
        } catch (NewsApiException e) {
            e.printStackTrace();
        }
        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }
    }
}
