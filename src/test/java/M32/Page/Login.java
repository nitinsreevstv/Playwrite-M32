package M32.Page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static M32.setup.SetUp.logger;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Login {
    Page page;
    public Login(Page page) {
        this.page = page;
    }
    public void loginDashboard(){
        try{
            launchUrl();
            assertThat(page).hasTitle(Pattern.compile("Central - Home"));
            Locator emailFields = page.getByPlaceholder("Email");
            emailFields.fill(Config.LoginEmail);
            Locator password = page.getByPlaceholder("Password");
            password.fill(Config.LoginPassword);
            Locator button = page.locator("xpath = //button[@type='submit']");
            logger.info("Logged In Successfully");
            button.click();
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }
    }
    public void logoutDashboard(){
        try{
            Locator usernameDropdown = page.locator("xpath = //div[@class='w-8 h-8 text-sm bg-blue-500 rounded-full flex items-center justify-center text-white font-medium ']");
            usernameDropdown.click();
            logger.info("Clicked on Logout Button");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(page.title()+ ".png")));
            logger.info("Screenshot Captured");
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }
    }
    public void launchUrl(){
        page.navigate(Config.URL);
        logger.info("Landed on Website");
    }
}
