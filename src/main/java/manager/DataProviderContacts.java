package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
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
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line != null){
            String [] all = line.split(",");
            list.add(new  Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()
            });
            line = reader.readLine();
        }

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

    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Peterson")
                .email("John@ gmail.com")
                .address("jkfgjhfgf")
                .phone("12345")
                .description("Wow!")
                .build()

        });
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Peterson")
                .email("John@ gmail.com")
                .address("jkfgjhfgf")
                .phone("123452352352354235235")
                .description("Wow!")
                .build()

        });
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Peterson")
                .email("John@ gmail.com")
                .address("jkfgjhfgf")
                .phone("fdjkvbhah")
                .description("Wow!")
                .build()

        });
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Peterson")
                .email("John@ gmail.com")
                .address("jkfgjhfgf")
                .phone("")
                .description("Wow!")
                .build()

        });


        return list.iterator();
    }
}
