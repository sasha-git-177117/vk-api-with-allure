package pages;

import aquality.selenium.forms.Form;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class NewsPage extends Form {
    private static final By CHECK_ELEMENT = By.id("main_feed");
    private final NavBar navBar = new NavBar();

    public NewsPage() {
        super(CHECK_ELEMENT, "checkElementNewsPage");
    }
}
