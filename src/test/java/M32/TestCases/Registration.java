package M32.TestCases;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;
import static M32.setup.SetUp.logger;

public class Registration {
    Page page;
    public Registration(Page page) {
        this.page = page;
    }
    public void signup(){
        try{
            NewAccount register = new NewAccount(page);
            register.fillingDetails();
            CRMRegistration crm = new CRMRegistration(page);
            crm.signUpCRM();
            logger.info("Clicking on Sign up button...");
            register.fillingDetails();
            ChatAgentRegistration chatAgent = new ChatAgentRegistration(page);
            chatAgent.signUpChatAgent();
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }
    }
}
