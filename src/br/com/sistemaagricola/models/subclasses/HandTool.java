package br.com.sistemaagricola.models.subclasses;

import br.com.sistemaagricola.models.helpers.Supplier;
import br.com.sistemaagricola.models.superclasses.Equipment;

public class HandTool extends Equipment {

    private final String material;

    public HandTool(String name, double basePrice, int quantity, Supplier supplier, String material) {
        super(name, basePrice, quantity, supplier, "Ferramenta");
        validateMaterial(material);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString() + " | Material: " + material;
    }

    private void validateMaterial(String material) {
        if (material == null || material.isBlank()) {
            throw new IllegalArgumentException("Material is a required field.");
        }
    }

}
