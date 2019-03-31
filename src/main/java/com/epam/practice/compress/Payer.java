package com.epam.practice.compress;

import java.time.LocalDate;

// Пол - 1 бит
// Год - 9 бит
// Месяц - 4 бита
// День - 5 бит
// ИНН - 34 бита
public interface Payer {

    static Payer create(Gender gender, long identifier, LocalDate registrationDate) {
        throw new UnsupportedOperationException();
    }

    Gender getGender();

    Payer setGender(Gender gender);

    long getIdentifier();

    Payer setIdentifier(long identifier);

    LocalDate getRegistrationDate();

    Payer setRegistrationDate(LocalDate registrationDate);
}
