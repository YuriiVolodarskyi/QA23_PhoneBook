package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContacts {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .phone("123456789000")
                .email("pupkin@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .description("addContactSuccess")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("Vasyaaa")
                .lastName("Pumpkin")
                .phone("1234567890600")
                .email("pumpkin@gmail.com")
                .address("29 Ha-Yarden str Haifa")
                .build()});
        return list.iterator();
    }
}
