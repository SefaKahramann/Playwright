import Utility.baseDriver;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class playwrightAndLocators extends baseDriver {

    @Test
    public void playwrightStart(){
        page.navigate("https://playwright.dev/");
        System.out.println(page.title());
    }

    @Test
    public void builtinLocators(){
        page.navigate("https://www.saucedemo.com/");

        // 1- getByText
         Locator loginText= page.getByText("Swag Labs"); //Input kısmındaki texten direk bulabiliyor
         System.out.println("1. text ="+loginText.innerText()); //Text i alabiliyomuyuz diye kontrol ediyoruz

        // 2- Locate in Shadow DOM
        Locator shadowDom = page.locator("div",new Page.LocatorOptions().setHasText("Swag Labs")).last();
        //Burada hata verecektir birden fazla bu texte sahip locator varsa
        // biz buradan ilkini seçebiliriz ve ya sonuncusu seçebiliriz ve ya nth diyerek index verebiliriz.
        // TODO : Mümkünse tek e gidecek yolu seçmemiz lazım
        System.out.println("ShadowDom Text ="+shadowDom.innerText());

        // 3- getByRole
        Locator userName=page.getByRole(AriaRole.TEXTBOX , new Page.GetByRoleOptions().setName("Username"));
        System.out.println("Username = "+userName.innerText());
        userName.click();
        userName.fill("standard_user");

        // 4- getByPlaceHolder
        Locator password =page.getByPlaceholder("Password");
        System.out.println("usernamePlaceHolder = "+password.innerText());

        password.click();
        password.fill("secret_sauce");

        // 5- getByLabel
        //Locator login=page.getByLabel("xxxxxxxx"); // DOOM da aria-label varsa bu locator kullanılabilir!

        // 6- getByRole
        Locator login=page.getByRole(AriaRole.BUTTON ,new Page.GetByRoleOptions().setName("Login"));
        login.click();

        // 7- getByTestId
        // Locator xxxx=page.getByTestId("xxxxxx").getByPlaceHolder("xxxxxxxx"); şeklinde yazılabilir
        // genelde DOOM da data-testId şeklinde geçiyor buradan alınması gerekiyor

        // 8- getByAltText
        //page.getByAltText şeklinde alınıyor genelde img şeklinde olanların alt şeklinde bir locatırı varsa oradan direk tıklanabilir ve ya bu locator direk alınabilir

        // 9- getByTest and filter options
        // Locator xxxxx=page.getByTestId("text").filter(new Locator.FilterOptions().setHasText("xxxxxxxx"));
        // bu alanın içinde xxxxx adına sahip olan filtrelemeyi yap bana locator ı getir

        // TODO: Playwright’ta page.locator() metodu, hem CSS hem de XPath ile çalışır.
        // TODO:   page.locator("input[name='password']").fill("mypassword");
        // TODO:   page.locator("//input[@name='password']").fill("mypassword");  ikisi içinde de page.locator kullanılabilir
    }

    @Test
    public void cssAndXpath(){
        page.navigate("https://www.saucedemo.com/");

        Locator userName= page.locator("[id='user-name']");
        userName.fill("standard_user");

        Locator password= page.locator("[id='password']");
        password.fill("secret_sauce");

        Locator loginBtn=page.locator("//input[@id='login-button']");
        loginBtn.click();
    }
}
