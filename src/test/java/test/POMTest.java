package test;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static utilities.Hooks.page;

@Listeners(utilities.Hooks.class)
public class POMTest {

    @Epic("User Interface Tests")
    @Feature("Login Feature")
    @Story("Successful Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test for successful login with valid credentials")
    @Test
    void shouldNavigate(){
        Allure.step("Navigate to domain",() ->{
            page.navigate("https://demoqa.com/text-box");
        });
        page.getByPlaceholder("Full Name").fill("Jonah");
        page.getByPlaceholder("name@example.com").fill("test@test.com");
        page.getByPlaceholder("Current Address").fill("test current address");
        Allure.step("Invalid Locator",()-> {
            page.locator("[id='permanentAddres']").fill("test permanent address"); // locator yanlış traceViewer ın çalıştığını görmek için yapıldı
        });
    }
}
