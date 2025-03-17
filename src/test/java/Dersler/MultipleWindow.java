package Dersler;

import static utilities.Hooks.page;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.util.List;

public class MultipleWindow{

    @Test
    public void newTab(){
        page.navigate("https://the-internet.herokuapp.com/windows");

        // Get page after a specific action (e.g. clicking a link)
        Page newPage = page.context().waitForPage(()-> {
            page.getByText("Click Here").click(); // yeni pencere açılacak yerin locator ı
        });

        newPage.waitForLoadState(); // yeni sayfa yüklenene kadar bekle
        // yeni sayfa ya geçtiğinde sayfa küçülüyor bunun için base driver daki ilgili kodu burada kullanabiliriz
        System.out.println(newPage.title());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Eski sekmeye geri dön
        page.bringToFront();
    }

    @Test
    public void newWindow(){
        page.navigate("https://demoqa.com/browser-windows");

        // Get popup after a specific action (e.g. click)
        Page popup = page.waitForPopup(()-> {
            page.getByText("New Window").first().click();
        });

        popup.waitForLoadState();
        System.out.println(popup.title());
    }

    @Test
    public void multipleWindow(){
        page.navigate("https://demoqa.com/browser-windows");

        Page popup = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate( // setTimeOut da verilebilir size da ise kaç sayfa açılacağı belirtiliyor
                p-> p.context().pages().size() == 2),() -> {
            page.getByText("New Window").first().click();
        });
        List<Page> pages =popup.context().pages();
        System.out.println("sayfa sayısı : "+pages.size());

        pages.forEach(tab -> {
            tab.waitForLoadState();
            System.out.println(tab.title());
        });
/*
        System.out.println("ilk sayfanın url ' i : "+pages.get(0).url());
        System.out.println("ikinci sayfanın url ' i : "+pages.get(1).url());

        Page fPage=null;
        Page sPage=null;

        if (pages.get(0).url().equals("https://demoqa.com/browser-windows")){
            fPage = pages.get(0);
        }else {
            sPage = pages.get(1);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fPage.bringToFront();

 */
        // title ile sayfa değişimi

        Page fPage=null;
        Page sPage=null;

        if (pages.get(0).url().equals("DEMOQA")){
            fPage=pages.get(0);
        }else {
            sPage=pages.get(1);
        }
    }
}
