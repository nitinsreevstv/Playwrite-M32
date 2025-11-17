package M32.TestCases;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.logging.Logger;

import static M32.setup.SetUp.logger;

public class NewAccount {
    Page page;
    public NewAccount(Page page){
        this.page = page;
    }
    public void fillingDetails(){
        try{
            logger.info("Clicking on Sign up button...");
            Locator signUpButton = page.getByText("Sign up");
            signUpButton.click();
            Helper help = new Helper();
            logger.info("Filling basic info...");
            page.locator("#fullName").fill("Test User");
            String emailGenerator = help.generateRandomPhone(3);
            page.locator("#email").fill(emailGenerator + Config.BaseEmail);
            logger.info("Using " + emailGenerator + Config.BaseEmail + " for SignUp");
            page.locator("#password").fill(Config.DefaultPassword);
            logger.info("Submitting signup form...");
            page.locator("xpath = //button[@type='submit']").click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(page.title()+".png")));
            logger.info("Screenshot after creating profile taken.");
        }catch (Exception e) {
            logger.severe("Test failed: " + e.getMessage());
            try {
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
            } catch (Exception ex) {
                logger.severe("Failed to take screenshot: " + ex.getMessage());
            }
            throw e;
        }

    }
}
