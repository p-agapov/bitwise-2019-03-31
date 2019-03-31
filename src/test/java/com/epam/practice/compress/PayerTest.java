package com.epam.practice.compress;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PayerTest {

    @Test
    void factoryMethod() {
        LocalDate now = LocalDate.now();

        Payer payer = Payer.create(Gender.MALE, 123_456_789_0, now);

        assertEquals(Gender.MALE, payer.getGender());
        assertEquals(123_456_789_0, payer.getIdentifier());
        assertEquals(now, payer.getRegistrationDate());
    }

    @Test
    void gender() {
        Payer payer = Payer.create(Gender.MALE, 1, LocalDate.now());

        assertEquals(Gender.MALE, payer.getGender());

        payer.setGender(Gender.FEMALE);

        assertEquals(Gender.FEMALE, payer.getGender());
    }

    @Test
    void identifier() {
        Payer payer = Payer.create(Gender.MALE, 1, LocalDate.now());

        assertEquals(1, payer.getIdentifier());

        payer.setIdentifier(2);

        assertEquals(2, payer.getIdentifier());
    }

    @Test
    void registrationDate() {
        LocalDate registrationDate = LocalDate.of(2014, 8, 29);
        Payer payer = Payer.create(Gender.MALE, 1, registrationDate);

        assertEquals(registrationDate, payer.getRegistrationDate());

        LocalDate shiftedByDay = registrationDate.plusDays(1);
        payer.setRegistrationDate(shiftedByDay);

        assertEquals(shiftedByDay, payer.getRegistrationDate());
    }
}