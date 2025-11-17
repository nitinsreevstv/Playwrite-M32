package M32.TestCases;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;
import static M32.setup.SetUp.logger;

public class AddContact {
    Page page;
    public AddContact(Page page){
        this.page = page;
    }
    public void newContact() {
        try {
            logger.info("Clicking on Sidebar");
            page.locator("div.sidebar button:has(svg.lucide-menu)").first().click();
            logger.info("Now clicking on CRM in Sidebar");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CRM").setExact(true)).first().click();
            logger.info("Clicking Actions menu");
            page.locator("button[aria-label='Actions']").first().click();
            logger.info("Waiting for Edit button to appear");
            page.locator("xpath = //button[contains(text(),'Edit')]").click();
            logger.info("Handling Email field");
            Locator emailInput = page.getByPlaceholder("Set Email Addresses");
            Locator closeButton = page.locator("xpath = //button[@type='button' and @style='margin-left: 4px; color: rgb(40, 158, 253);']");
            if (emailInput.count() > 0) {   // Element exists
                logger.info("Filling Email");
                emailInput.fill(Config.UpdateEmail);
            } else {
                logger.info("Email field not found, clicking × button");
                closeButton.click();
            }
            logger.info("Filling Location now");
            page.getByPlaceholder("Search for a city, state, country, or address...").fill(Config.Location);
            logger.info("Now Saving the data");
            page.locator("button:has-text('Save Changes')").click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(page.title() + ".png")));
        } catch (Exception e) {
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
