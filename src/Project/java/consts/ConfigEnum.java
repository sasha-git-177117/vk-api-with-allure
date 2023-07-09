package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConfigEnum {
    CONFIG_DATA_PATH("configData.json"),
    BASE_URI("/baseURI"),
    API_VERSION("/apiVersion"),
    OWNER_ID("/ownerId"),
    ACCESS_TOKEN("/accessToken"),
    LOGIN("/login"),
    PASSWORD("/password"),
    BASE_URL("/baseUrl");

    public final String label;
}
