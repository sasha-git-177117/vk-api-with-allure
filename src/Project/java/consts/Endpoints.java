package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Endpoints {
    CREATE_POST("/wall.post"),
    GET_POST_INFO("/wall.getById"),
    CREATE_COMMENT("/wall.createComment"),
    EDIT_POST("/wall.edit"),
    DELETE_POST("/wall.delete"),
    GET_LIKE_IS_LIKED("/likes.isLiked");

    public final String label;
}
