package com.acme.learningcenterbacksw55.learning.interfaces.rest.resources;

public record CreateStudentResource(String firstName, String lastName, String email, String street, String number,
                                    String city, String zipCode, String country) {
}
