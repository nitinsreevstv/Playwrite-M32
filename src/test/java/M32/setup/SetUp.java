package M32.setup;

import M32.Page.Config;
import com.microsoft.playwright.*;
import java.util.logging.*;

public class SetUp {

    public static final Logger logger = Logger.getLogger(SetUp.class.getName());
    private static Playwright playwright;
    private static Browser browser;

    static {
        try {
            System.setProperty("java.util.logging.FileHandler.disableLocking", "true");
            logger.setUseParentHandlers(false); // avoid duplicate logs
            FileHandler fileHandler = new FileHandler("signup.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize logger", e);
        }
    }

    public Page launchBrowser() {
        try {
            if (playwright == null) {
                playwright = Playwright.create();
            }
            if (browser == null) {
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(Config.headless)
                );
            }
            return browser.newPage();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Browser Wakeup failed -------------------------------------> ", e);
            return null;
        }
    }
    public void closeBrowser() {
        try {
            if (browser != null) {
                browser.close();
                browser = null;
            }
            if (playwright != null) {
                playwright.close();
                playwright = null;
            }
            logger.info("Browser and Playwright closed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to close Playwright/Browser", e);
        }
    }

}
