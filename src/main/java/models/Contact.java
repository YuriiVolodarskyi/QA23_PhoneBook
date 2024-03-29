package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class Contact {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;


}
