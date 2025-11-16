package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Login {
    private final Logger logger;
    Page page;
    public Login(Page page, Logger logger) {
        this.page = page;
        this.logger = logger;
    }
    public void loginDashboard(){
        try{
            page.navigate(Config.URL);
            logger.info("Landed on Website");
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
}
