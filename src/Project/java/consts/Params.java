package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Params {
    VERSION("v"),
    OWNER_ID("owner_id"),
    ACCESS_TOKEN("access_token"),
    MESSAGE("message"),
    POSTS("posts"),
    POST_ID("post_id"),
    ATTACHMENTS("attachments"),
    ITEM_ID("item_id"),
    USER_ID("user_id"),
    TYPE("type");

    public final String label;
}
