package newsapi;

public class NewsApiException extends Exception{
    //v f20:
    /*public NewsApiException(){
        super();
    }*/

    public NewsApiException(String message){
        super(message);
    }
}
