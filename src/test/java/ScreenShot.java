import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utility.BaseDriver;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ScreenShot extends BaseDriver {
    String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
    String filePath ="src/test/java/utility/screenShot/screenShot" + date +".jpg";

    @Test
    public void pageScreenShot(){
        page.navigate("https://www.ebay.com/");
        // Sayfanın resmini yakalama
        // Fotoğraf çekme
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(filePath)));
    }

    @Test
    public void pageFullScreenShot(){
        page.navigate("https://www.ebay.com/");

        // Tüm sayfanın fotoğrafını çekme
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(filePath)).setFullPage(true));
    }

    @Test
    public void pageElementScreenShot(){
        page.navigate("https://www.ebay.com/");

        // Elementin fotoğrafını çekme
        Locator searchBox= page.getByPlaceholder("Search for anything");
        searchBox.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath))); // ilgili elementin olduğu yerin fotoğrafını çekiyor
    }

    @Test
    public void setMaskColor(){
        page.navigate("https://www.ebay.com/");

        // ilgili elementin pembe renk ile kapatılmasını bekleyeceğiz (default olarak pembe kullanıyor)
        Locator searchBox= page.getByPlaceholder("Search for anything");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setMask(Arrays.asList(searchBox)));
    }
}
