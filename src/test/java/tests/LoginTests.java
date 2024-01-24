package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void loginSuccess() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'Yv030665@'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("alimych65@gmail.com", "Yv030665@");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert checks is element button 'Sing out' present");
    }

    @Test
    public void loginSuccess1() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'Yv030665@'");
        User user = new User().withEmail("alimych65@gmail.com").withPassword("Yv030665!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("alimych65@gmail.com", "Yv030665@");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert checks is element button 'Sing out' present");
    }


    @Test
    public void loginSuccessModel() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'Yv030665@'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("alimych65@gmail.com", "Yv030665@");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert checks is element button 'Sing out' present");
    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test data email: 'alimych65gmail.com' and password: 'Yv030665@'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("alimych65gmail.com", "Yv030665@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert checks is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test data email: 'alimych65@gmail.com' and password: 'v030665@'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("alimych65@gmail.com", "v030665@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert checks is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginUnregisteredUser() {
        logger.info("Test data email: 'ych65@gmail.com' and password: 'Uv030665@'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("ych65@gmail.com", "Uv030665@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert checks is alert present with error text 'Wrong email or password'");
    }
}
