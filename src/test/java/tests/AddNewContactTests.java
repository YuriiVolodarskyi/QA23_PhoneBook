package tests;

import manager.DataProviderContacts;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().withEmail("alimych65@gmail.com").withPassword("Yv030665@"));
    }

    @Test(dataProvider = "contactCSV", dataProviderClass = DataProviderContacts.class)
    public void addContactSuccess(Contact contact) {
        logger.info("Test run with data" + contact.toString());
        int i = new Random().nextInt(1000) + 1000;
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" + i + ".png");
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submit();
        //Написал два ассерта, по-моему, так более надежно.
        /*Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.className("contact-item_card__2SOIM")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.xpath("//h3[text()='" + contact.getPhone() + "']")));
                */
        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));
        logger.info("Asserts check is new contact present");


    }

    @Test(groups = {"smoke", "regress", "retest"})
    public void addContactSuccessRequiredFields() {
        logger.info("add contacts with valid data - only required fields");
        int i = new Random().nextInt(1000) + 1000;
        // Другой способ: int i = (int)(System.currentTimeMillis())/1000 % 3600;
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .phone("123456789000")
                .email("pupkin" + i + "@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("addContactSuccessRequiredFields")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(1000);
        app.getHelperContact().submit();

        /*Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.className("contact-item_card__2SOIM")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.xpath("//h3[text()='" + contact.getPhone() + "']")));
        */
        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));
        logger.info("Asserts check is new contact present");

    }

    @Test
    public void addNewContactWrongName() {
        logger.info("add contacts with empty field 'name'");
        Contact contact = Contact.builder()
                .name("")
                .lastName("Pupkin")
                .phone("1234567890")
                .email("pupkin@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("addNewContactWrongName")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(1000);
        app.getHelperContact().submit();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert check is button 'Add' present");
    }

    @Test
    public void addNewContactWrongAddress() {
        logger.info("add contacts with empty field 'address'");
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .phone("1234567890")
                .email("pupkin@gmail.com")
                .address("")
                .description("addNewContactWrongAddress")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submit();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert checks is button 'Add' present");
    }

    @Test
    public void addNewContactWrongLastName() {
        logger.info("add contacts with empty field 'lastName'");
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("")
                .phone("1234567890")
                .email("pupkin@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("addNewContactWrongLastName")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submit();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert checks is button 'Add' present");
    }

    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContacts.class)
    public void addNewContactWrongPhone(Contact contact) {
        logger.info("add contacts with empty field 'phone'");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submit();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert checks is button 'Add' present");
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        logger.info("Assert check is alert 'Phone not valid' present");
    }

    @Test
    public void addNewContactWrongEmail() {
        logger.info("add contacts with wrong email 'pupkingmail.com'");
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .phone("1234567890")
                .email("pupkingmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("addNewContactWrongEmail")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submit();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert checks is button 'Add' present");
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
        logger.info("Assert check is alert 'Email not valid' present");
    }


}
