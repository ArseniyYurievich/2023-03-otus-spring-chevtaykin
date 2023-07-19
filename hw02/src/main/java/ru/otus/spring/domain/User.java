package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
