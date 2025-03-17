package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static utilities.Hooks.page;

@Listeners(utilities.Hooks.class)
public class POMTest {

    @Test
    void shouldNavigate(){
        page.navigate("https://demoqa.com/text-box");

        page.getByPlaceholder("Full Name").fill("Jonah");
        page.getByPlaceholder("name@example.com").fill("test@test.com");
        page.getByPlaceholder("Current Address").fill("test current address");
        page.locator("[id='permanentAddres']").fill("test permanent address"); // locator yanlış traceViewer ın çalıştığını görmek için yapıldı
    }
}
