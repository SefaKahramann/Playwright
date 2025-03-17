package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestResult;

public class BrowserFactory {
    private Playwright playwright;

    public BrowserFactory() {
        playwright = Playwright.create();
    }

    public Browser getBrowser(String browser) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
        BrowserType browserType;

        switch (browser.toLowerCase()) {
            case "chromium":
                browserType = playwright.chromium();
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "edge":
                browserType = playwright.chromium();
                launchOptions.setChannel("msedge");
                break;
            case "chrome":
                browserType = playwright.chromium();
                launchOptions.setChannel("chrome");
                break;
            default:
                String message = "Browser name : " + browser + " Geçersiz olarak ayrıştırıldı.";
                message += " Lütfen desteklenen tarayıcılardan birini belirtin [Firefox, Edge, Chrome, Chromium]";
                throw new IllegalArgumentException(message);
        }
        return browserType.launch(launchOptions);
    }

    public BrowserContext createPageAndGetContext(Browser browser, ITestResult result) {

        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
                .setName(result.getMethod().getMethodName()) // Method ismini trace adı olarak ayarladık
        );
        return context;
    }
}
