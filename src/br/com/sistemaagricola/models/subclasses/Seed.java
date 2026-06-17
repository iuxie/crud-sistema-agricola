package br.com.sistemaagricola.models.subclasses;

import br.com.sistemaagricola.models.Supplier;
import br.com.sistemaagricola.models.superclasses.Supply;

import java.time.LocalDate;

public class Seed extends Supply {

    private final int germinationRate;

    public Seed(String name, double basePrice, int quantity, Supplier supplier, LocalDate manufacturingDate, int germinationRate) {
        super(name, basePrice, quantity, supplier, manufacturingDate);
        validateGeminationRate(germinationRate);
        this.germinationRate = germinationRate;
    }

    public int getGerminationRate() {
        return germinationRate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Taxa de germinacao: " + germinationRate + "%";
    }

    private void validateGeminationRate(int germinationRate) {
        if (germinationRate < 0 || germinationRate > 100) {
            throw new IllegalArgumentException("Germination rate should be between 0 and 100.");
        }
    }

}
