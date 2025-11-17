package M32.TestCases;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;
import static M32.setup.SetUp.logger;

public class CRMRegistration {
    Page page;
    public CRMRegistration(Page page){
        this.page = page;
    }

    public void signUpCRM(){
        try{
            String[] options = {"VP / Director", "CRM", "Instagram"};
            for (int i = 0; i < options.length; i++) {
                logger.info("Selecting dropdown option: " + options[i]);
                page.locator("button.go-dropdown-trigger").nth(i).click();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(options[i])).click();
            }
            page.locator("button:has-text('Continue')").click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(page.title()+".png")));
            Login logout = new Login(page);
            logger.info("Log out the CRM Board");
            logout.logoutDashboard();
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }

    }
}
