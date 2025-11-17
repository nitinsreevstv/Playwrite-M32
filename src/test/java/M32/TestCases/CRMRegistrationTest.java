package M32.TestCases;

import M32.Page.Login;
import M32.Page.NewAccount;
import M32.Page.Registration;
import M32.setup.SetUp;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static M32.setup.SetUp.logger;

public class CRMRegistrationTest {
    SetUp setup;
    Page page;

    @BeforeClass
    public void setUp() {
        setup = new SetUp();
        page = setup.launchBrowser();
    }

    @Test
    public void crmRegistrationFlow() {
        Registration registration = new Registration(page);
        Login login = new Login(page);
        login.launchUrl();
        NewAccount account = new NewAccount(page);
        account.signUpButtonClick();
        account.fillingDetails();
        registration.crmRegistration();
        logger.info("CRM Registration Test Passed");
    }

    @AfterClass
    public void tearDown() {
        setup.closeBrowser();
    }
}
