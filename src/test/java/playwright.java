import Utility.baseDriver;
import org.testng.annotations.Test;

public class playwright extends baseDriver {

    @Test
    public void Test1(){
        page.navigate("https://playwright.dev/");
        System.out.println(page.title());
    }
}
