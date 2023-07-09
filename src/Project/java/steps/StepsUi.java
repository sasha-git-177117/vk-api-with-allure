package steps;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.ElementType;
import consts.AttributeAndProperty;
import consts.ConfigEnum;
import consts.Endpoints;
import consts.TestEnum;
import dev.failsafe.internal.util.Assert;
import org.apache.commons.lang3.StringUtils;
import pages.LoginPage;
import pages.PasswordPage;
import pages.Post;

public class StepsUi {
    private static final Post POST = new Post();

    public static Boolean isPostDisplayed(String ownerId, String postId) {
        return POST.getPost(ownerId, postId).state().waitForDisplayed();
    }

    public static Boolean isPostNotDisplayed(String ownerId, String postId) {
        return POST.getPost(ownerId, postId).state().waitForNotDisplayed();
    }

    public static String getUrlPostImage(String ownerId, String postId) {
        return POST.getPost(ownerId, postId).findChildElement(POST.getUrlImagePost(),ElementType.LABEL)
                .getCssValue(AttributeAndProperty.BACKGROUND_IMAGE.label).split("\"")[1].split("\"")[0];
    }

    public static String getAuthorIdPost(String ownerId, String postId) {
        ISettingsFile testDataApi = new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label);
        return  StringUtils.replace(POST.getPost(ownerId, postId)
                        .findChildElement(POST.getByAuthorId(),ElementType.LABEL).getAttribute(AttributeAndProperty.HREF.label),
                testDataApi.getValue(TestEnum.ID_URL.label).toString(),"");

    }

    public static String getAuthorIdComment(String ownerId, String commentId, String postId) {
        if(isPostNotDisplayed(ownerId,commentId)) POST.clickButtonShowComments(ownerId,postId);
        ISettingsFile testDataApi = new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label);
        return  StringUtils.replace(POST.getPost(ownerId, commentId)
                        .findChildElement(POST.getByCommentatorId(),ElementType.LABEL).getAttribute(AttributeAndProperty.HREF.label),
                testDataApi.getValue(TestEnum.ID_URL.label).toString(),"");
    }

    public static void clickLikeIfPostWasNotLiked(Endpoints endpoints, String token, String ownerId, String userId, String type, String itemId) {
        if(isPostDisplayed(ownerId,itemId) && !StepsApi.isPostLiked(endpoints,token,ownerId,userId,type,itemId)) {
            POST.clickButtonLike(ownerId, itemId);
        }
    }

    public static void authorization() {
        LoginPage loginPage = new LoginPage();
        Assert.isTrue(loginPage.state().waitForDisplayed(),"Страница логина не открылась");
        loginPage.enterLogin(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label).getValue(ConfigEnum.LOGIN.label).toString());
        loginPage.clickSignIn();
        PasswordPage passwordPage = new PasswordPage();
        Assert.isTrue(passwordPage.state().waitForDisplayed(),"Страница пароля не открылась");
        passwordPage.enterPassword(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label).getValue(ConfigEnum.PASSWORD.label).toString());
        passwordPage.clickContinue();
    }
}
