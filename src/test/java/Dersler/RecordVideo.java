package Dersler;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RecordVideo {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String filePath=System.getProperty("user.dir")+"/src/test/java/utility/video/"+date;

        BrowserContext context =browser.newContext(new Browser.NewContextOptions().
                setRecordVideoDir(Paths.get(filePath))); // video yu tam ekran almak istediğimizde bu kodu".setRecordVideoSize()"
        // setrecordvideodir den sonra kullanabiliriz

        Page page =context.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://demoqa.com/text-box");

        page.getByPlaceholder("Full Name").fill("Jonah");
        page.getByPlaceholder("name@example.com").fill("test@test.com");
        page.getByPlaceholder("Current Address").fill("test current address");
        page.locator("[id='permanentAddress']").fill("test permanent address");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Path path = page.video().path();
        System.out.println("path : "+path);

        page.close();
        //page.video().saveAs(Paths.get(filePath+" test")); adını değiştirebiliyoruz bu şekilde
        //page.video().delete(); testng den yaralanarak test kaldığında video kaydı olur elimizde geçtiğinde video kaydını silebiliriz
        browser.close();
        playwright.close();
    }
}
