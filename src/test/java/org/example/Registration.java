package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
        try{
            NewAccount register = new NewAccount(page,logger);
            register.fillingDetails();
            CRMRegistration crm = new CRMRegistration(page,logger);
            crm.signUpCRM();
            logger.info("Clicking on Sign up button...");
            register.fillingDetails();
            ChatAgentRegistration chatAgent = new ChatAgentRegistration(page,logger);
            chatAgent.signUpChatAgent();
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }
    }
}
