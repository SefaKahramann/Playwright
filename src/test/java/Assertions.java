import Utility.BaseDriver;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Assertions extends BaseDriver {

    @Test
    public void pageAssertions(){
        page.navigate("https://www.ebay.com/");

        //Page Assertions

        //hasURL
        assertThat(page).hasURL("https://www.ebay.com/");

        //hasTitle
        assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");

        //not ()
        assertThat(page).not().hasTitle("test");
    }

    @Test
    public void locatorAssertions(){
        page.navigate("https://www.ebay.com/");

        //Locator Assertions
        // containsText
        Locator singin=page.getByText("Sign in").first();
        assertThat(singin).containsText("Sign");

        //hasAttribute
        Locator searchBox=page.getByPlaceholder("Search for anything");
        assertThat(searchBox).hasAttribute("type","text");

        //hasText
        Locator register = page.getByText("register").first();
        assertThat(register).hasText("register");

        //isEditable
        assertThat(searchBox).isEditable();

        //isEmpty
        assertThat(searchBox).isEmpty();

        //isVisible
        assertThat(searchBox).isVisible();
    }
}
