package Utility;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.awt.*;

public class BaseDriver {
    public static Playwright playwright;
    public static Browser browser;
    public static Page page;

    @BeforeClass
    public static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize(); // Tam ekran moduna getirmek için kullanıyoruz
        int width = (int) dimension.getWidth(); // ekranın genişliğini alıyoruz
        int height = (int) dimension.getHeight(); // ekranın yüksekliğini alıyoruz
        page.setViewportSize(width,height); // ve setliyoruz
    }

    @AfterClass
    public static void tearDown(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        page.close();
        browser.close();
        playwright.close();
    }

}
