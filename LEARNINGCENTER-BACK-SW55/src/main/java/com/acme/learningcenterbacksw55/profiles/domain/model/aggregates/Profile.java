package com.acme.learningcenterbacksw55.profiles.domain.model.aggregates;


import com.acme.learningcenterbacksw55.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learningcenterbacksw55.profiles.domain.model.valueobjects.PersonName;
import com.acme.learningcenterbacksw55.profiles.domain.model.valueobjects.StreetAddress;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city" , column = @Column(name= "address_city")),
            @AttributeOverride(name = "state", column = @Column(name = "address_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code")),
            @AttributeOverride(name = "country" , column = @Column(name = "address_country"))
    })
    private StreetAddress address;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Profile(){

    }

    public Profile(String firstName, String lastName, String email, String streetAddress,
                   String number, String city, String state, String zipCode, String country){
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.address = new StreetAddress(streetAddress, number, city, zipCode, state, country);
    }

    public void updateName(String firstName, String LastName) {
        this.name = new PersonName(firstName, name.lastName());
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updateAddress(String streetAddress, String number, String city, String zipcode, String state, String country){
        this.address = new StreetAddress(streetAddress, number, city, zipcode, state, country);
    }

    public String getFullName(){
        return this.name.getFullName();
    }

    public String getStreetAddress(){
        return  this.address.getStreetAddress();
    }

    public String getEmailAddress() {
        return this.email.address();
    }
}




















