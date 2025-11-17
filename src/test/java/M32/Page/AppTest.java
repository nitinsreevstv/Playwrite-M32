package M32.Page;
import com.microsoft.playwright.*;
import M32.setup.SetUp;
import java.nio.file.Paths;
import static M32.setup.SetUp.logger;

public class AppTest
{
    public static void main(String[] args){
        SetUp setup = new SetUp();
        Page page = setup.launchBrowser();
        try {
            Login login = new Login(page);
            login.loginDashboard();
            AddContact contact = new AddContact(page);
            contact.newContact();
            login.logoutDashboard();
            Registration register = new Registration(page);
            NewAccount account = new NewAccount(page);
            account.signUpButtonClick();
            register.crmRegistration();
            account.signUpButtonClick();
            register.signup();
            setup.closeBrowser();
        } catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("failureScreenshot.png")));
        }
    }
}

