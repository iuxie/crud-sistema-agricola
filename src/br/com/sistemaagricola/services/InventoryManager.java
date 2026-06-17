package br.com.sistemaagricola.services;

import br.com.sistemaagricola.models.helpers.Supplier;
import br.com.sistemaagricola.models.superclasses.Product;

import java.util.*;

public class InventoryManager {

    private final Map<UUID, Product> catalog;
    private final Set<Supplier> suppliers;
    private final LinkedList<String> history;

    public InventoryManager() {
        this.catalog = new HashMap<>();
        this.suppliers = new HashSet<>();
        this.history = new LinkedList<>();
    }

    public void registerSupplier(Supplier supplier) {
        if (!suppliers.add(supplier)) {
            throw new IllegalArgumentException("O Fornecedor com este CNPJ já existe no sistema.");
        }
        registerAction("Registrado novo Fornecedor: " + supplier.getEnterpriseName());
    }

    public void addProduct(Product product) {
        if (!suppliers.contains(product.getSupplier())) {
            throw new IllegalArgumentException("Fornecedor não cadastrado. Cadastre o fornecedor antes de adicionar o produto.");
        }
        catalog.put(product.getId(), product);
        registerAction("Adicionado produto: " + product.getName() + " ao catálogo");
    }

    public Product getProductById(UUID id) {
        Product product = catalog.get(id);
        if (product != null) {
            registerAction("Busca produto " + product.getName() + " com UUID " + id);
        }
        return product;
    }

    public List<Product> listAllProducts() {
        if (catalog.isEmpty()) {
            return Collections.emptyList();
        }
        registerAction("Lista todos os produtos");
        return new ArrayList<>(catalog.values());
    }

    private void registerAction(String action) {
        if (history.size() == 10) {
            history.poll();
        }
        history.add(action);
    }

    public Map<UUID, Product> getCatalog() {
        return catalog;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public LinkedList<String> getHistory() {
        return history;
    }

}