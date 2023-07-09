package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestEnum {
    TEST_DATA_PATH("testData.json"),
    POST_ID_TEST("/postIdWithImage"),
    TEST_ID_IMAGE("/testIdImage"),
    ID_URL("/idUrl"),
    MEDIA_ID_FORMAT("/mediaIdFormat"),
    POST_TYPE("/postType"),
    TEST_USER_ID("/userId"),
    FIRST_SYMBOL_CODE("/firstSymbolCode"),
    LAST_SYMBOL_CODE("/lastSymbolCode"),
    COUNT_SYMBOLS("/countSymbols"),
    TYPE_PHOTO("/typePhoto"),
    PATH_TO_DIRECTORY_IMAGE("src/test/resources/checkedImage"),
    IMAGE_NAME_FROM_POST("/postImage.png"),
    IMAGE_NAME_TEST("/testImage.png");

    public final String label;
}
