package com.acme.learningcenterbacksw55.learning.domain.model.commands;



public record CreateStudentCommand(String firstName, String lastName, String email, String street, String number, String city, String state, String zipCode) {
}
