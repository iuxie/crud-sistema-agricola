package br.com.sistemaagricola.models.helpers;

import java.util.Objects;

public class Supplier {
    
    private final String cnpj, enterpriseName;
    private int deliveryTimeDays;

    public Supplier(String cnpj, String enterpriseName, int deliveryTimeDays) {
        validateCnpj(cnpj);
        validateEnterpriseName(enterpriseName);
        validateDeliveryTimeDays(deliveryTimeDays);
        this.cnpj = cnpj;
        this.enterpriseName = enterpriseName;
        this.deliveryTimeDays = deliveryTimeDays;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public int getDeliveryTimeDays() {
        return deliveryTimeDays;
    }

    public void setDeliveryTimeDays(int deliveryTimeDays) {
        this.deliveryTimeDays = deliveryTimeDays;
    }

    @Override
    public String toString() {
        return "CNPJ: " + cnpj + " | Nome da empresa: " + enterpriseName + " | Tempo e entrega em dias: " + deliveryTimeDays;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(cnpj, supplier.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpj);
    }

    private void validateCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("Invalid CNPJ. A valid CNPJ must have 14 characters.");
        }
    }

    private void validateDeliveryTimeDays(int deliveryTimeDays) {
        if (deliveryTimeDays < 0) {
            throw new IllegalArgumentException("Number of days cannot be negative.");
        }
    }

    private void validateEnterpriseName(String enterpriseName) {
        if (enterpriseName == null || enterpriseName.isBlank()) {
            throw new IllegalArgumentException("Enterprise name is a required field.");
        }
    }

}
