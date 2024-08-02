package com.acme.learningcenterbacksw55.profiles.domain.model.commands;

public record CreateProfileCommand(String firstName, String LastName,String email, String street, String number,
                                   String city, String state, String zipCode, String country) {
}
