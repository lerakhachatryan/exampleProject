import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTests {

    @Test
    public void testGithub() {
        open("https://github.com/");

        //выбираем элемент
        //$(".button-label");
    }
}
