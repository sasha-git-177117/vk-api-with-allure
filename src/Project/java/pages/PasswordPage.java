package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordPage extends Form {
    private static final By CHECK_ELEMENT = By.xpath("//*[contains(@class,'vkc__AuthRoot__contentIn')]");
    private static final By BY_INPUT_PASSWORD = By.xpath("//*[@name='password']");
    private static final By BY_BUTTON_CONTINUE = By.xpath("//*[@type='submit']//*[contains(@class,'vkuiButton__in')]");
    private static final ITextBox INPUT_PASSWORD = AqualityServices.getElementFactory()
            .getTextBox(BY_INPUT_PASSWORD, "inputPassword");
    private static final IButton BUTTON_CONTINUE = AqualityServices.getElementFactory()
            .getButton(BY_BUTTON_CONTINUE,"buttonContinue");

    public PasswordPage() {
        super(CHECK_ELEMENT, "checkElementPasswordPage");
    }

    public void enterPassword(String password) {
        INPUT_PASSWORD.clearAndType(password);
    }

    public void clickContinue() {
        BUTTON_CONTINUE.click();
    }
}
