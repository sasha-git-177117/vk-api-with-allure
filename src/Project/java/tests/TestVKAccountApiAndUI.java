package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import consts.ConfigEnum;
import consts.Endpoints;
import consts.TestEnum;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyPage;
import pages.NewsPage;
import steps.StepsApi;
import steps.StepsUi;
import tests.base.BaseTest;
import utils.api.VKApiUtil;
import utils.test.FileUtil;
import utils.test.TestUtils;
import java.util.ArrayList;
import java.util.Collections;

public class TestVKAccountApiAndUI extends BaseTest {

    @Test
    public void TestVKApiAndUI() {
        Logger log = AqualityServices.getLogger();
        ISettingsFile configData = new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label);
        ISettingsFile testDataApi = new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label);

        String userId = testDataApi.getValue(TestEnum.TEST_USER_ID.label).toString();
        String postIdTest = testDataApi.getValue(TestEnum.POST_ID_TEST.label).toString();
        String accessToken = configData.getValue(ConfigEnum.ACCESS_TOKEN.label).toString();
        String ownerId = configData.getValue(ConfigEnum.OWNER_ID.label).toString();

        AqualityServices.getLogger().info("Шаг 2 - Авторизация");
        StepsUi.authorization();
        StepsApi.attachScreenshotWithDiscInAllure(StepsApi.getScreenshot(),"Выполняется авторизация");
        NewsPage newsPage = new NewsPage();
        Assert.assertTrue(newsPage.state().waitForDisplayed(),"Станица Новости не открылась");
        newsPage.getNavBar().clickMyPage();
        StepsApi.attachScreenshotWithDiscInAllure(StepsApi.getScreenshot(),"Нажимается кнопка 'Моя страница'");

        log.info("Шаг3 - Переход на 'Моя страница'");
        MyPage myPage = new MyPage();
        Assert.assertTrue(myPage.state().waitForDisplayed(), "Страница Моя страница не открылась");
        StepsApi.attachScreenshotWithDiscInAllure(StepsApi.getScreenshot(),"Открывается 'Моя страница'");

        log.info("Шаг4 - Добавление записи со случайном текстом");
        String createdText = TestUtils.getGeneratorText();
        String idCreatedPost = VKApiUtil.createPost(Endpoints.CREATE_POST, accessToken,ownerId, createdText);
        StepsApi.attachTextInAllure("Создается запись на стене со случайным текстом","");

        log.info("Шаг5 - Проверка добавленной записи с текстом");
        Assert.assertTrue(StepsUi.isPostDisplayed(ownerId, idCreatedPost),"пост с id=" + idCreatedPost + " не найден");
        Assert.assertEquals(myPage.getPost().getText(ownerId,idCreatedPost),createdText,"Сгенерированный текст не совпадает с текстом в посте");
        Assert.assertEquals(StepsUi.getAuthorIdPost(ownerId,idCreatedPost),userId,"Автор поста не соответствует правильному пользователю");

        log.info("Шаг 6 - Редактирование записи и добавление изображения");
        String textBeforeEdit = myPage.getPost().getText(ownerId,idCreatedPost);
        String photoMediaIdTest = StepsApi.getMediaIdPhoto(Endpoints.GET_POST_INFO, accessToken,
                postIdTest,testDataApi.getValue(TestEnum.TEST_ID_IMAGE.label).toString());

        VKApiUtil.editPost(Endpoints.EDIT_POST, accessToken, ownerId, idCreatedPost,
                TestUtils.getGeneratorText(), new ArrayList<>(Collections.singletonList(photoMediaIdTest)));
        StepsApi.attachTextInAllure("Изменяется текст записи, и добавлется картинка","");

        log.info("Шаг 7 - Проверка изменения текста записи и добавления изображения");
        String textAfterEdit = myPage.getPost().getText(ownerId, idCreatedPost);
        String postImageUrl = StepsUi.getUrlPostImage(ownerId,idCreatedPost);
        String testImageUrl = StepsApi.getPhotoInfo(Endpoints.GET_POST_INFO, accessToken,
                postIdTest,testDataApi.getValue(TestEnum.TEST_ID_IMAGE.label).toString()).getUrl();
        FileUtil.savePhotoFromVk(postImageUrl,TestEnum.PATH_TO_DIRECTORY_IMAGE.label+TestEnum.IMAGE_NAME_FROM_POST.label);
        FileUtil.savePhotoFromVk(testImageUrl,TestEnum.PATH_TO_DIRECTORY_IMAGE.label+TestEnum.IMAGE_NAME_TEST.label);

        Assert.assertNotEquals(textBeforeEdit,textAfterEdit, "Текст в посте не изменился");
        Assert.assertTrue(FileUtil.compareImage(TestEnum.PATH_TO_DIRECTORY_IMAGE.label+TestEnum.IMAGE_NAME_FROM_POST.label,
                TestEnum.PATH_TO_DIRECTORY_IMAGE.label+TestEnum.IMAGE_NAME_TEST.label),"Картинка в посте не соответствует тестовой");
        FileUtil.deleteDirectory(TestEnum.PATH_TO_DIRECTORY_IMAGE.label);

        log.info("Шаг 8 - Добавление комментария к записи");
        String commentId = VKApiUtil.createComment(Endpoints.CREATE_COMMENT,accessToken,ownerId
                ,idCreatedPost,TestUtils.getGeneratorText());
        StepsApi.attachTextInAllure("Добавлен комментарий к записи","");

        log.info("Шаг 9 - Проверка наличия добавленного комментария от правильного пользователя");
        Assert.assertEquals(userId,StepsUi.getAuthorIdComment(ownerId,commentId,idCreatedPost),"К записи добавился комментарий неправильного пользователя");

        log.info("Шаг 10 - Проставление лайка к записи");
        StepsUi.clickLikeIfPostWasNotLiked(Endpoints.GET_LIKE_IS_LIKED, accessToken, ownerId, userId,
                testDataApi.getValue(TestEnum.POST_TYPE.label).toString(), idCreatedPost);
        StepsApi.attachScreenshotWithDiscInAllure(StepsApi.getScreenshot(),"Поставлен лайк к записи");

        log.info("Шаг 11 - Проверка наличия лайка от правильного пользователя");
        Boolean statusLike = StepsApi.isPostLiked(
                Endpoints.GET_LIKE_IS_LIKED, accessToken, ownerId, userId,
                testDataApi.getValue(TestEnum.POST_TYPE.label).toString(), idCreatedPost);
        Assert.assertTrue(statusLike,"Лайк не поставлен");

        log.info("Шаг 12 - Удаление созданной записи");
        VKApiUtil.deletePost(Endpoints.DELETE_POST,accessToken,ownerId,idCreatedPost);
        StepsApi.attachTextInAllure("Созданная ранее запись удалена","");

        log.info("Шаг 13 - Проверка отсутствия удаленной записи");
        Assert.assertTrue(StepsUi.isPostNotDisplayed(ownerId,idCreatedPost),"Запись " + idCreatedPost +" не удалена");
    }
}
