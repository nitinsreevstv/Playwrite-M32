package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class NewAccount {
    Page page;
    public final Logger logger;
    public NewAccount(Page page, Logger logger){
        this.page = page;
        this.logger = logger;
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
