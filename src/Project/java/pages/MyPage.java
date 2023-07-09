package pages;

import aquality.selenium.forms.Form;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class MyPage extends Form {
    private static final By CHECK_ELEMENT = By.id("react_rootprofile");
    private final Post post = new Post();

    public MyPage() {
        super(CHECK_ELEMENT, "checkElementMyPage");
    }

}
