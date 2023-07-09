package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseFields {
    ID("id"),
    OWNER_ID("owner_id"),
    SIZES("sizes"),
    TYPE("type"),
    URL("url");

    public final String label;
}
