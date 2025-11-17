package M32.TestCases;

import M32.Page.AddContact;
import M32.Page.Login;
import M32.setup.SetUp;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static M32.setup.SetUp.logger;

public class UpdateContactTest {

    SetUp setup;
    Page page;
    @BeforeClass
    public void setUp() {
        setup = new SetUp();
        page = setup.launchBrowser();
    }
    @Test
    public void updateContactFlow() {
        AddContact contact = new AddContact(page);
        Login login = new Login(page);
        login.loginDashboard();
        contact.newContact();
        logger.info("Update Contact Test Passed");
    }
    @AfterClass
    public void tearDown() {
        setup.closeBrowser();
    }
}
