package steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import consts.Endpoints;
import consts.ResponseFields;
import consts.TestEnum;
import io.qameta.allure.Allure;
import models.api.PostPhoto;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.api.VKApiUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StepsApi {
    private static final Integer postWasLikedByUser = 1;

    public static String getMediaIdPhoto(Endpoints endpoints, String token, String postId, String id) {
        ISettingsFile testDataApi = new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label);
        PostPhoto photoData = getPhotoInfo(endpoints, token, postId, id);
        return String.format(testDataApi.getValue(TestEnum.MEDIA_ID_FORMAT.label).toString(),photoData.getOwner_id(),photoData.getId());
    }

    public static Boolean isPostLiked(Endpoints endpoints, String token, String ownerId, String userId, String type, String itemId) {
        Integer status = VKApiUtil.getLikeInfoPost(endpoints,token,ownerId,userId,type,itemId).getLiked();
        return status.equals(postWasLikedByUser);
    }

    public static List<PostPhoto> getPhotosInfo(Endpoints endpoints, String token, String postId){
        List<PostPhoto> photosInformation=new ArrayList<>();
        List<HashMap<String,Object>> photoInfo = VKApiUtil.getPostPhotoAttachments(endpoints, token, postId);
        photoInfo.forEach(entry -> photosInformation.add(
                new PostPhoto(entry.get(ResponseFields.ID.label).toString(),
                        entry.get(ResponseFields.OWNER_ID.label).toString(),
                        ((List<HashMap<String, String>>)entry.get(ResponseFields.SIZES.label))
                                .stream()
                                .filter(w->w.get(ResponseFields.TYPE.label).equals(new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label)
                                        .getValue(TestEnum.TYPE_PHOTO.label).toString()))
                                .findFirst().orElse(new HashMap<>()).get(ResponseFields.URL.label))));

        return photosInformation;
    }

    public static PostPhoto getPhotoInfo(Endpoints endpoints, String token, String postId, String id) {
        return getPhotosInfo(endpoints, token, postId).stream().filter(v->v.getId().equals(id)).findFirst().orElse(null);
    }

    public static File getScreenshot() {
        return ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
    }

    public static void attachScreenshotWithDiscInAllure(File file, String disc) {
        try {
            Allure.addAttachment(disc, FileUtils.openInputStream(file));
        } catch (IOException e) {
            AqualityServices.getLogger().info(e.getMessage());
        }
    }

    public static void attachTextInAllure(String name, String content) {
        Allure.addAttachment(name, content);
    }
}
