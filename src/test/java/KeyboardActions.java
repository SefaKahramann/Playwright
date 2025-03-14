import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;
import utility.BaseDriver;


public class KeyboardActions extends BaseDriver {

    @Test
    public void insertText(){
        page.navigate("https://demoqa.com/login");

        Locator username=page.getByPlaceholder("UserName");
        username.click();

        //insertText
        page.keyboard().insertText("Jonah");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // press
        page.keyboard().press("Control+a");
        page.keyboard().press("Backspace");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type
        page.keyboard().type("Hero");

        // down
        page.keyboard().down("Shift");
        page.keyboard().press("A");

        // up
        page.keyboard().up("Shift");
        page.keyboard().press("b");
    }
}
