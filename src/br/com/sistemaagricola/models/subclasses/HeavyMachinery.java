package br.com.sistemaagricola.models.subclasses;

import br.com.sistemaagricola.models.Supplier;
import br.com.sistemaagricola.models.superclasses.Equipment;

public class HeavyMachinery extends Equipment {

    private final String chassi;

    public HeavyMachinery(String name, double basePrice, int quantity, Supplier supplier, String chassi) {
        super(name, basePrice, quantity, supplier, "Maquinário");
        validateChassi(chassi);
        this.chassi = chassi;
    }

    public String getChassi() {
        return chassi;
    }

    @Override
    public String toString() {
        return super.toString() + " | Chassi: " + chassi;
    }

    private void validateChassi(String chassi) {
        if (chassi == null || chassi.isBlank()) {
            throw new IllegalArgumentException("Chassi is a required field.");
        }
    }

}
