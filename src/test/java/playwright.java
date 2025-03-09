import Utility.GWD;
import org.testng.annotations.Test;

public class playwright extends GWD {

    @Test
    public void Test1(){
        page.navigate("https://playwright.dev/");
        System.out.println(page.title());
    }
}
