package br.com.sistemaagricola.models.subclasses;

import br.com.sistemaagricola.models.Supplier;
import br.com.sistemaagricola.models.superclasses.Supply;

import java.time.LocalDate;

public class Fertilizer extends Supply {

    private final int toxicityLevel;

    public Fertilizer(String name, double basePrice, int quantity, Supplier supplier, LocalDate manufacturingDate, int toxicityLevel) {
        super(name, basePrice, quantity, supplier, manufacturingDate);
        validateToxicityLevel(toxicityLevel);
        this.toxicityLevel = toxicityLevel;
    }

    public int getToxicityLevel() {
        return toxicityLevel;
    }

    @Override
    public String toString() {
        return super.toString() + " | Nível de toxicidade: " + toxicityLevel + "%";
    }

    private void validateToxicityLevel(int toxicityLevel) {
        if (toxicityLevel < 0 || toxicityLevel > 100) {
            throw new IllegalArgumentException("Toxicity level should be between 0 and 100.");
        }
    }

}
