package Dersler;

import static utilities.Hooks.page;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Actions{

    @Test
    public void textInput(){
        page.navigate("https://www.ebay.com/");

        // Text Input
        Locator searchBox=page.getByPlaceholder("Search for anything");
        searchBox.fill("bicycle");

        // Keys and Shortcuts
        searchBox.press("Enter");

        //2. yöntem
        //page.keyboard().press("Enter"); // 16. satırdaki kod ile 19 satırdaki kod arasında ki herhangi bir farklılık yok 2 siylede enter a tıklama işlemi yapılıyor
    }

    @Test
    public void checkBox(){
        page.navigate("https://demoqa.com/checkbox");

        Locator checkBox =page.locator("[class='rct-checkbox']");
        checkBox.check();
        assertThat(checkBox).isChecked();

        Locator controlCheckBox= page.locator("//div[@id='result']/span").first();
        assertThat(controlCheckBox).containsText("selected");
    }

    @Test
    public void dropDown(){
        page.navigate("https://www.ebay.com/");

        //Select options
        Locator allCategories=page.getByLabel("Select a category for search").first();

        // Select by value
        allCategories.selectOption("2984");

        // Select by label
        allCategories.selectOption(new SelectOption().setLabel("Cameras & Photo"));
    }

    @Test
    public void mouseClick(){
        page.navigate("https://demoqa.com/buttons");

        // Generic click
        Locator clickMeBtn= page.getByText("Click me").nth(2);
        clickMeBtn.click();

        // Double click
        Locator doubleClickMeBtn=page.getByText("Double Click Me");
        doubleClickMeBtn.dblclick();

        // Hover over element and right click
       Locator rightClickBtn = page.getByText("Right Click Me");
       rightClickBtn.hover();
       //rightClickBtn.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        // şu anlık right click playwright da hata veriyor kullanımı böyle
    }

    @Test
    public void dragAndDrop(){
        page.navigate("https://demoqa.com/droppable");

        //1. yöntem hazır method ile sürükleme işlemi yapıyor
        page.getByText("Drag me").first().dragTo(page.getByText("Drop here").first());

        // 2.yöntem
//        page.getByText("Drag me").first().hover();
//        page.mouse().down();
//        page.getByText("Drop here").first().hover();
//        page.mouse().up();
    }

    @Test
    public void fileUpload(){
        page.navigate("https://the-internet.herokuapp.com/upload");

       Locator fileSelect= page.locator("[id='file-upload']");
       String filePath= "C:\\Users\\relax\\IdeaProjects\\Playwright\\src\\test\\java\\Utility\\6118a4664ef4d824cdf122b3f208e203.jpg";

       fileSelect.setInputFiles(Paths.get(filePath));
       page.locator("[id='file-submit']").click();
    }

    @Test
    public void multipleFiles(){
        page.navigate("https://demo.automationtesting.in/FileUpload.html");

        Locator fileSelectBtn = page.locator("[id='input-4']");

        String filePath= "C:\\Users\\relax\\IdeaProjects\\Playwright\\src\\test\\java\\Utility\\6118a4664ef4d824cdf122b3f208e203.jpg";
        String filePath2= "C:\\Users\\relax\\IdeaProjects\\Playwright\\src\\test\\java\\Utility\\image.png";

        fileSelectBtn.setInputFiles(new Path[] {Paths.get(filePath), Paths.get(filePath2)});

        Locator uploadBtn=page.getByTitle("Upload selected files");
        uploadBtn.click();
    }

    @Test
    public void uploadFiles(){
        // Uploaded single file path with file chooser (without input tag)
        //Dosya seçici ile yüklenen tek dosya yolu (giriş etiketi olmadan)
        page.navigate("https://autopract.com/selenium/upload2/");

        Locator fileSelect=page.locator("[id='pickfiles']");
        String filePath= "C:\\Users\\relax\\IdeaProjects\\Playwright\\src\\test\\java\\Utility\\6118a4664ef4d824cdf122b3f208e203.jpg";

        // resmi sitesibnden koda ulaşılabilir
        FileChooser fileChooser =page.waitForFileChooser(() -> {
            fileSelect.click();
        });
        fileChooser.setFiles(Paths.get(filePath));
    }
}
