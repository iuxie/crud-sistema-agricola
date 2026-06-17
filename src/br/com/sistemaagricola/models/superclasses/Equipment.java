package br.com.sistemaagricola.models.superclasses;

import br.com.sistemaagricola.interfaces.Taxable;
import br.com.sistemaagricola.models.helpers.Supplier;

public abstract class Equipment extends Product implements Taxable {

    private final String equipmentType;

    public Equipment(String name, double basePrice, int quantity, Supplier supplier, String equipmentType) {
        super(name, basePrice, quantity, supplier);
        this.equipmentType = equipmentType;
    }

    @Override
    public double calculateTax() {
        return getBasePrice() * 0.1;
    }

    @Override
    public double getPriceWithTax() {
        return getBasePrice() + calculateTax();
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo do Equipamento: " + equipmentType;
    }

}
