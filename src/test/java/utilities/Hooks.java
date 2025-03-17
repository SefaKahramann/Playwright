package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks extends TestListenerAdapter {
    private Playwright playwright;
    private Browser browser;
    public static Page page;
    private BrowserContext context;

    @Override
    public void onTestStart(ITestResult result) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        BrowserFactory browserFactory=new BrowserFactory();
        this.browser = browserFactory.getBrowser(ConfigReader.getProperty("browser"));
        this.context = browserFactory.createPageAndGetContext(this.browser,result);
        this.page = context.newPage();
        page.setViewportSize(width,height);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Başarılı olduğu durumlarda Trace dosyasını sil
        cleanupOldTraces();

        // Trace kaydını durdur
        try {
            if (context != null){
                context.tracing().stop();
            }
        }finally {
            cleanup();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String tracePath =getTraceFilePath(result);

        // Başarısızlık durumunda da trace kaydını durdur ve kaydet
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(tracePath)) // Dosyayı açma yolu https://trace.playwright.dev/
        );

        cleanup();
    }

    public String getTraceFilePath(ITestResult result){
        String baseDir = "src/test/java/utilities/traceViewer/";
        String methodName=result.getMethod().getMethodName();
        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        return baseDir+methodName+date+"-trace.zip";
    }

    private void cleanup(){
        // kaynakları temizle
        if (context != null){
            context.close();
        }
        if (browser != null){
            browser.close();
        }
        if (playwright != null){
            playwright.close();
        }
    }

    private void cleanupOldTraces(){
        final long EXPIRATION_TIME = 86400000; // 24 saat 86400000
        File dir = new File("src/test/java/utilities/traceViewer/"); // hangi klöserde benim dosyalarım birikiyor onun uzantısı
        File[] files = dir.listFiles(); // files arrayine bu bilgileri aktarmış oluyoruz
        if (files != null){ // dizi var olup olmadığını içinde dosya olup olmadığını kontrol ediyor
            long now =System.currentTimeMillis(); // şu anki zamanı alıyoruz
            for (File file : files){ // dosyaları bir akışa sokuyoruz her dosyayı
                if (now - file.lastModified() > EXPIRATION_TIME){ // dosyaların ne kadar süre var olduğunu bulmak için kullanıyoruz
                    if (!file.delete()){ // dosyaları silme işlemi
                        System.err.println("Eski trace dosyaları siliniyor : "+file.getPath()); // hata alırsa bilgi vermesi için
                    }
                }
            }
        }
    }
}
