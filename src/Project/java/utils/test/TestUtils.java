package utils.test;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import consts.TestEnum;
import java.util.Random;

public class TestUtils {

    public static String getGeneratorText() {
        ISettingsFile testInformation = new JsonSettingsFile(TestEnum.TEST_DATA_PATH.label);
        Random random = new Random();

        String generatedString = random.ints((Integer) testInformation.getValue(TestEnum.FIRST_SYMBOL_CODE.label),
                (Integer)testInformation.getValue(TestEnum.LAST_SYMBOL_CODE.label) + 1)
                .limit((Integer)testInformation.getValue(TestEnum.COUNT_SYMBOLS.label))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
