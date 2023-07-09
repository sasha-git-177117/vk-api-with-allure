package utils.api;

import api_utils.ApiUtils;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.common.base.Joiner;
import consts.ConfigEnum;
import consts.Endpoints;
import consts.Params;
import consts.ResponseAnswer;
import models.api.LikeInfo;
import java.util.HashMap;
import java.util.List;

public class VKApiUtil {

    public static String createPost(Endpoints endpoints, String token, String ownerId, String message) {
        HashMap<String ,String> query = new HashMap<>();
        query.put(Params.OWNER_ID.label, ownerId);
        query.put(Params.ACCESS_TOKEN.label, token);
        query.put(Params.MESSAGE.label, message);
        query.put(Params.VERSION.label, new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.API_VERSION.label).toString());

        return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.BASE_URI.label).toString() + endpoints.label,query)
                .path(ResponseAnswer.GET_POST_ID.label).toString();
    }

    public static List<HashMap<String,Object>> getPostPhotoAttachments(Endpoints endpoints,String token, String postId) {
        HashMap<String ,String> query = new HashMap<>();
        query.put(Params.ACCESS_TOKEN.label, token);
        query.put(Params.POSTS.label, postId);
        query.put(Params.VERSION.label, new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.API_VERSION.label).toString());

        List<HashMap<String,Object>> photoInfo = ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.BASE_URI.label).toString() + endpoints.label,query)
                .path(ResponseAnswer.GET_PHOTO_DATA_FROM_POST.label);

        return photoInfo;
    }

    public static String editPost(Endpoints endpoints,String token, String ownerId, String postId, String message, List<String> attachments) {
        String attachmentsParam = Joiner.on(",").join(attachments);
        HashMap<String ,String> query = new HashMap<>();
        query.put(Params.OWNER_ID.label, ownerId);
        query.put(Params.ACCESS_TOKEN.label, token);
        query.put(Params.POST_ID.label, postId);
        query.put(Params.MESSAGE.label, message);
        query.put(Params.ATTACHMENTS.label, attachmentsParam);
        query.put(Params.VERSION.label, new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.API_VERSION.label).toString());

        return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.BASE_URI.label).toString() + endpoints.label,query)
                .path(ResponseAnswer.GET_POST_ID.label).toString();
    }

    public static String createComment(Endpoints endpoints,String token, String ownerId, String postId, String message) {
        HashMap<String ,String> query = new HashMap<>();
        query.put(Params.OWNER_ID.label, ownerId);
        query.put(Params.ACCESS_TOKEN.label, token);
        query.put(Params.POST_ID.label, postId);
        query.put(Params.MESSAGE.label,message);
        query.put(Params.VERSION.label, new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.API_VERSION.label).toString());

        return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.BASE_URI.label).toString() + endpoints.label,query)
                .path(ResponseAnswer.GET_COMMENT_ID.label).toString();
    }

    public static LikeInfo getLikeInfoPost(Endpoints endpoints, String token, String ownerId, String userId, String type, String itemId) {
        HashMap<String ,String> query = new HashMap<>();
        query.put(Params.OWNER_ID.label, ownerId);
        query.put(Params.ACCESS_TOKEN.label, token);
        query.put(Params.ITEM_ID.label, itemId);
        query.put(Params.USER_ID.label, userId);
        query.put(Params.TYPE.label, type);
        query.put(Params.VERSION.label, new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.API_VERSION.label).toString());

        return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.BASE_URI.label).toString() + endpoints.label,query)
                .jsonPath().getObject(ResponseAnswer.GET_RESPONSE.label,LikeInfo.class);
    }

    public static Integer deletePost(Endpoints endpoints, String token, String ownerId, String postId) {
        HashMap<String ,String> query = new HashMap<>();
        query.put(Params.OWNER_ID.label, ownerId);
        query.put(Params.ACCESS_TOKEN.label, token);
        query.put(Params.POST_ID.label, postId);
        query.put(Params.VERSION.label, new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.API_VERSION.label).toString());

        return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
                .getValue(ConfigEnum.BASE_URI.label).toString() + endpoints.label,query)
                .jsonPath().getInt(ResponseAnswer.GET_RESPONSE.label);
    }

}
