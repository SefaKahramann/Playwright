import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class TraceViewer {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        BrowserContext context=browser.newContext();

        // Start tracing before creating / navigate a page
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        );

        Page page = context.newPage();
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
        // Stop tracing and export it into a zip archive
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")) // Dosyayı açma yolu https://trace.playwright.dev/
        );

        context.close();
        page.close();
        playwright.close();
    }
}
