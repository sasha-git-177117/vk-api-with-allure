package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavBar extends Form {
    private static final By CHECK_ELEMENT = By.id("side_bar");
    private static final By BY_BUTTON_MY_PAGE = By.id("l_pr");
    private static final IButton BUTTON_MY_PAGE = AqualityServices.getElementFactory()
            .getButton(BY_BUTTON_MY_PAGE,"buttonMyPage");

    public NavBar() {
        super(CHECK_ELEMENT, "checkElementNavBar");
    }

    public void clickMyPage() {
        BUTTON_MY_PAGE.click();
    }
}
