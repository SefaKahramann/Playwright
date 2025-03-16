import com.microsoft.playwright.Download;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadPageUrlTitle extends BaseDriver {

    @Test
    public void download() {
        page.navigate("https://demoqa.com/upload-download");

        // Wait for the download to start
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            page.getByText("Download").last().click();
        });
        System.out.println("page : "+download.page().title());
        System.out.println("URL : "+download.page().url());
        System.out.println("Path : "+download.path().toString());

        String filePath=System.getProperty("user.home")+"/Downloads/file.jpg";
        // Wait for the download process to complete and save the downloaded file somewhere
        download.saveAs(Paths.get(filePath));
    }

    @Test
    public void downloadSaveAs(){
        page.navigate("https://demoqa.com/upload-download");

        Download download = page.waitForDownload(() -> {
            page.getByText("Download").last().click();
        });
        String filePath=System.getProperty("user.home")+"/Downloads";
        download.saveAs(Paths.get(filePath,download.suggestedFilename()));
    }

    @Test
    public void downloadControl(){
        page.navigate("https://demoqa.com/upload-download");

        Download download = page.waitForDownload(() -> {
            page.getByText("Download").last().click();
        });

        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String filePath=System.getProperty("user.home")+"/Downloads/"+date+" file.jpg";
        download.saveAs(Paths.get(filePath));

        boolean isFileDownloaded = Files.exists(Paths.get(filePath));
        assert isFileDownloaded;

        System.out.println("is file downloaded : "+isFileDownloaded);

        download.delete(); // silme işlemini gerçekleştirmedi

        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void downloadDelete(){

        page.navigate("https://demoqa.com/upload-download");

        Download download = page.waitForDownload(() -> {
            page.getByText("Download").last().click();
        });

        String filePath=System.getProperty("user.home")+"/Downloads";
        download.saveAs(Paths.get(filePath,download.suggestedFilename()));

        boolean isFileDownloaded = Files.exists(Paths.get(filePath,download.suggestedFilename()));
        assert isFileDownloaded;

        System.out.println("is file downloaded : "+isFileDownloaded);

        try {
            Files.deleteIfExists(Paths.get(filePath,download.suggestedFilename())); // dosyayı kesin silme işlemi
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}