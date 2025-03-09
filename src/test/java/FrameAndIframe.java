import Utility.BaseDriver;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import java.util.List;


public class FrameAndIframe extends BaseDriver {

    @Test
    public void iframe(){
        page.navigate("https://the-internet.herokuapp.com/iframe");

        Locator control= page.locator("//h3");
        System.out.println("1. Text : "+control.innerText());

        // Frame Locator
        FrameLocator frameLocator= page.frameLocator("#mce_0_ifr");

        Locator body=frameLocator.getByText("Your content goes here.");
        body.click();
//        body.clear();
//
//        Locator inputText = frameLocator.locator("[id='tinymce']");
//        inputText.fill("Hello World");
//
//         Playwright da İframe içine girdiğinizde selenium gibi çıkış için driver.switch kullanmamıza gerek yok

        Locator iframe =page.getByText("Elemental Selenium"); // iframe ile bi etkileşimi olmamasına rağmen iframe dışındaki text i alabiliyorum
        System.out.println(" iframeOutsideText : "+ iframe.innerText());
    }

    @Test
    public void frameURL(){
        page.navigate("https://the-internet.herokuapp.com/iframe");

        Locator control= page.locator("//h3");
        System.out.println("1. Text : "+control.innerText());

        List<Frame> frames=page.frames();
        System.out.println("Frame size : "+ frames.size());

        for (Frame frame : frames){
            System.out.println("URL : "+frame.url());
        }

        // Frames by url
        Frame frame=page.frameByUrl("about:blank");


        Locator body=frame.getByText("Your content goes here.");
        body.click();
//        body.clear();
//
//        Locator inputText = frameLocator.locator("[id='tinymce']");
//        inputText.fill("Hello World");
        // Url kullanarak da bu şekilde etkileşime geçebiliyoruz
    }
}
