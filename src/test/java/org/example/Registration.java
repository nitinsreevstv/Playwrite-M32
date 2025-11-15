package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.util.Random;
import java.util.logging.Logger;
import java.nio.file.Paths;

public class Registration {
    Page page;
    private final Logger logger;

    public Registration(Page page, Logger logger) {
        this.page = page;
        this.logger = logger;
    }

    public void signup(){
        logger.info("Clicking on Sign up button...");
        Locator signUpButton = page.getByText("Sign up");
        signUpButton.click();
        logger.info("Filling basic info...");
        page.locator("#fullName").fill("Test User");
        String emailGenerator = generateRandomPhone(3);
        page.locator("#email").fill(emailGenerator + Config.BaseEmail);
        logger.info("Using " + emailGenerator + Config.BaseEmail + " for SignUp");
        page.locator("#password").fill(Config.DefaultPassword);
        logger.info("Submitting signup form...");
        page.locator("xpath = //button[@type='submit']").click();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Creating Profile.png")));
        logger.info("Screenshot after creating profile taken.");
        String[] options = {"Manager / Team Lead", "Chat Agents", "Google"};
        for (int i = 0; i < options.length; i++) {
            logger.info("Selecting dropdown option: " + options[i]);
            page.locator("button.go-dropdown-trigger").nth(i).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(options[i])).click();
        }
        page.locator("button:has-text('Continue')").click();
        page.getByPlaceholder("e.g., Acme Inc").fill("Test Agent");
        logger.info("Selecting country and filling phone number...");
        page.locator("xpath = //div[@title='United States: + 1']").click();
        page.locator("xpath = //input[@placeholder='Search']").fill("India");
        logger.info("Selecting country: India");
        page.locator("li.country[data-country-code='in']").click();
        logger.info("Selected country: India");
        String phoneNumber = generateRandomPhone(10);
        page.fill("input.form-control.op-phone-input", "+91" + phoneNumber);
        logger.info("Phone number filled: +91" + phoneNumber);
        logger.info("Clicking Let's Go button...");
        page.getByText("Let's Go", new Page.GetByTextOptions().setExact(true)).click();
        page.locator("xpath = //input[@placeholder='yourwebsite.com']").fill("wikipedia.com");
        page.getByText("Continue", new Page.GetByTextOptions().setExact(true)).click();
        page.getByText("Save Popup", new Page.GetByTextOptions().setExact(true)).click();

        page.fill("input#name", "Agentic");
        page.getByText("Save Identity", new Page.GetByTextOptions().setExact(true)).click();

        page.getByText("Save Greeting", new Page.GetByTextOptions().setExact(true)).click();

        page.locator("img[alt='WhatsApp']").click();
        page.locator("img[alt='Instagram']").click();
        page.getByText("Launch My Chat Agent", new Page.GetByTextOptions().setExact(true)).click();

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("SignUpCompleted.png")));
    }

    private String generateRandomPhone(int digits) {
        Random rand = new Random();
        StringBuilder phone = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            phone.append(rand.nextInt(10));
        }
        return phone.toString();
    }
}
