package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.NewsApiException;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Language;

public class UserInterface 
{
	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		NewsApi newsApi = new NewsApiBuilder()
                .setApiKey("0038b5ccc1124e94b01d19b0d5982697")
				.setQ("österreich: corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setFrom("2021-04-20")
				.setSourceCountry(Country.at)
				.setLanguage(Language.de)
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		} catch (NewsApiException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl2(){
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey("0038b5ccc1124e94b01d19b0d5982697")
				.setQ("motogp")
				.setEndPoint(Endpoint.EVERYTHING)
				.setFrom("2021-04-20")
				.setLanguage(Language.en)
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		} catch (NewsApiException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl3(){
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey("0038b5ccc1124e94b01d19b0d5982697")
				.setQ("zoo")
				.setEndPoint(Endpoint.EVERYTHING)
				.setFrom("2021-04-20")
				.setExcludeDomains("Lifehacker.com")
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		} catch (NewsApiException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void getDataForCustomInput() {
		String eing;
		Scanner scan = new Scanner(System.in);
		System.out.println("suchbegriff: ");
		eing = scan.nextLine();
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey("0038b5ccc1124e94b01d19b0d5982697")
				.setQ(eing)
				.setEndPoint(Endpoint.EVERYTHING)
				.setFrom("2021-04-20")
				.setExcludeDomains("Lifehacker.com")
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		} catch (NewsApiException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "last news: corona", this::getDataFromCtrl1);
		menu.insert("b", "last news: motogp", this::getDataFromCtrl2);
		menu.insert("c", "last news: zoo", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}

    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
