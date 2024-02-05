package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        logger.info("Test data email: random valid, password: 'Yv030665@' - valid");
        Random random = new Random();
        int z = (int) System.currentTimeMillis() / 1000 % 3600;
        User user = new User()
                .withEmail("volodar" + z + "@gmail.com")
                .withPassword("Vvolodar12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());
        logger.info("Assert checks is element 'No contacts here' present ");
        //Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

    }

    //@Test(description = "Bug report N ***", enabled = false)
    @Test(groups = {"smoke"})
    public void registrationWrongEmail() {
        logger.info("Test data email not valid - without '@', password: 'Yv030665@' - valid");
        User user = new User()
                .withEmail("volodargmail.com")
                .withPassword("Vvolodar12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert checks is alert present with error text 'Wrong email or password format'");

    }

    @Test
    public void registrationWrongPassword() {
        logger.info("Test data email valid - 'volodar@gmail.com', password not valid '2345$'");
        User user = new User()
                .withEmail("volodar@gmail.com")
                .withPassword("2345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert checks is alert present with error text 'Wrong email or password format'");

    }

    @Test
    public void registrationExistUser() {
        logger.info("Test data email existed - 'alimych65@gmail.com', password right - 'Yv030665'");
        User user = new User()
                .withEmail("alimych65@gmail.com")
                .withPassword("Yv030665@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        logger.info("Assert checks is alert present with error text 'User already exist'");

    }

}
