
import org.junit.jupiter.api.Test;


        import static com.codeborne.selenide.Selenide.$;
        import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepTest {


        @Test
        public void testGithub() {
            step("Открываем главную страницу", () -> {
                open("https://github.com/");

            });


        //выбираем элемент
        //$(".button-label");
        }
        }
