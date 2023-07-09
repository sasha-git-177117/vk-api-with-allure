package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class Post extends Form {
    private static final By CHECK_ELEMENT = By.id("feed_wall");
    private final String postsFormat = "post%s_%s";
    private final By byTextInPost = By.xpath("//*[contains(@class,'wall_post_text')]");
    private final String photoLinkInPostFormat = "//*[contains(@href,'%s')]";
    private final By byAuthorId = By.xpath("//*[contains(@class,'PostHeader')]//*[contains(@class,'AvatarRich') and @href]");
    private final By byCommentatorId = By.xpath("//*[contains(@class,'AvatarRich') and @href]");
    private static final By BY_SHOW_COMMENTS = By.xpath("//*[contains(@class,'js-replies_next_label')]");
    private static final String LIKE_BUTTON_FORMAT = "(//*[contains(@class,'_like_wall%s_%s')]//*[contains(@class,'like_btns')]//*[contains(@role,'button')])[1]";
    private final By urlImagePost = By.xpath("//*[@href and @aria-label]");

    public Post() {
        super(CHECK_ELEMENT, "checkElementPosts");
    }

    public String getText(String ownerId, String postId) {

        ITextBox textBox = AqualityServices.getElementFactory().
                findChildElement(getPost(ownerId, postId), byTextInPost,"text", ElementType.TEXTBOX);
        return textBox.getText();
    }

    public ILabel getPost(String ownerId, String postId) {
        return AqualityServices.getElementFactory().getLabel(By.id(String.format(postsFormat,ownerId,postId)),"post");
    }

    public void clickButtonShowComments(String ownerId, String postId) {
        getPost(ownerId, postId).findChildElement(BY_SHOW_COMMENTS,ElementType.BUTTON).click();
    }

    public void clickButtonLike(String ownerId, String postId) {
        AqualityServices.getElementFactory().getButton(By.xpath(String.format(LIKE_BUTTON_FORMAT,ownerId,postId)),"likeButton").click();
    }
}
