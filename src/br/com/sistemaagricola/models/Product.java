package br.com.sistemaagricola.models;

import java.util.Objects;
import java.util.UUID;

public abstract class Product {

    private final UUID id;
    private final String name;
    private double basePrice;
    private int quantity;
    private final Supplier supplier;

    public Product(String name, double basePrice, int quantity, Supplier supplier) {
        validateName(name);
        validateBasePrice(basePrice);
        validateQuantity(quantity);
        validateSupplier(supplier);
        this.id = UUID.randomUUID();
        this.name = name;
        this.basePrice = basePrice;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Nome: " + name + " | Preco base: " + basePrice + " | Fornecedor: " + supplier.getEnterpriseName() + " | Quantidade: " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is a required field.");
        }
    }

    private void validateBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }

    private void validateSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier is a required field.");
        }
    }

}
