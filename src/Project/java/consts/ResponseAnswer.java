package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseAnswer {
    GET_POST_ID("response.post_id"),
    GET_PHOTO_DATA_FROM_POST("response[0].attachments.findAll{it.type=='photo'}.photo"),
    GET_COMMENT_ID("response.comment_id"),
    GET_RESPONSE("response");

    public final String label;
}
