package org.example;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class ChatAgentRegistration {
    Page page;
    private final Logger logger;
    public ChatAgentRegistration(Page page, Logger logger){
        this.page = page;
        this.logger = logger;
    }
    public void signUpChatAgent(){
        try{
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
            Helper help = new Helper();
            String phoneNumber = help.generateRandomPhone(10);
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

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(page.title()+".png")));
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }
    }
}
