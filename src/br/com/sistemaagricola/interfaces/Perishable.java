package br.com.sistemaagricola.interfaces;

import java.time.LocalDate;

public interface Perishable {

    LocalDate getExpirationDate();
    long getDaysUntilExpirationDate();
    boolean isExpired();

}
