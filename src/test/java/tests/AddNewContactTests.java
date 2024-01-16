package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().withEmail("alimych65@gmail.com").withPassword("Yv030665@"));
    }

    @Test
    public void addContactSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .phone("1234567890")
                .email("pupkin" + i + "@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("My old friend")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        //Написал два ассерта, по-моему, так более надежно.
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.className("contact-item_card__2SOIM")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.xpath("//h3[text()='" + contact.getPhone() + "']")));

    }

    @Test
    public void addContactSuccessRequiredFields(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .phone("123456789000")
                .email("pupkin" + i + "@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();

        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.className("contact-item_card__2SOIM")));
        Assert.assertTrue(app.getHelperContact().isElementPresent
                (By.xpath("//h3[text()='" + contact.getPhone() + "']")));
    }


}
