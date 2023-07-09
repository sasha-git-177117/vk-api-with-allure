package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {
    private static final By CHECK_ELEMENT = By.id("index_login");
    private static final By BY_INPUT_LOGIN = By.id("index_email");
    private static final By BY_SIGN_IN = By.xpath("//*[contains(@class,'VkIdForm__signInButton')]");
    private static final IButton BUTTON_SIGN_IN = AqualityServices.getElementFactory()
            .getButton(BY_SIGN_IN, "buttonSignIn");
    private static final ITextBox INPUT_LOGIN = AqualityServices.getElementFactory()
            .getTextBox(BY_INPUT_LOGIN, "inputLogin");

    public LoginPage() {
        super(CHECK_ELEMENT, "checkElementLoginPage");
    }

    public void enterLogin(String login) {
        INPUT_LOGIN.clearAndType(login);
    }

    public void clickSignIn() {
        BUTTON_SIGN_IN.click();
    }
}
