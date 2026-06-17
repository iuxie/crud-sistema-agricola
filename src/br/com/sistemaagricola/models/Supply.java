package br.com.sistemaagricola.models;

import br.com.sistemaagricola.interfaces.Perishable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Supply extends Product implements Perishable {

    private final LocalDate manufacturingDate;

    public Supply(String name, double basePrice, int quantity, Supplier supplier, LocalDate manufacturingDate) {
        super(name, basePrice, quantity, supplier);
        validateManufacturingDate(manufacturingDate);
        this.manufacturingDate = manufacturingDate;
    }

    private void validateManufacturingDate(LocalDate manufacturingDate) {
        if (manufacturingDate == null || manufacturingDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Manufacturing date is a required field.");
        }
    }

    @Override
    public LocalDate getExpirationDate() {
        return manufacturingDate.plusDays(60);
    }

    @Override
    public long getDaysUntilExpirationDate() {
        return ChronoUnit.DAYS.between(LocalDate.now(), getExpirationDate());
    }

    @Override
    public boolean isExpired() {
        return getExpirationDate().isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return super.toString() + " | Data de fabricacao: " + manufacturingDate;
    }

}
