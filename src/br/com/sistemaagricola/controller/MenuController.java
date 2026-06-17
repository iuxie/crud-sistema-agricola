package br.com.sistemaagricola.controller;

import br.com.sistemaagricola.models.helpers.Supplier;
import br.com.sistemaagricola.services.InventoryManager;

import java.util.Scanner;

public class MenuController {

    private final InventoryManager manager;
    private boolean running;
    private Scanner input;

    public MenuController(InventoryManager manager) {
        validateManager(manager);
        this.manager = manager;
        this.running = true;
        this.input = new Scanner(System.in);
    }

    public void start() {
        System.out.println("🌾 Bem-vindo ao AgroTech CLI 🌾");

        while (running) {
            showMenu();
            String option = input.nextLine();

            try {
                processOption(option);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Registar Fornecedor");
        System.out.println("2. Adicionar Produto ao Catálogo");
        System.out.println("3. Listar Todos os Produtos");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void processOption(String option) {
        switch (option) {
            case "1":
                registerSupplier();
                break;
            case "2":
                registerProduct();
                break;
            case "3":
                listProducts();
                break;
            case "4":
                running = false;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    private void registerSupplier() {
        System.out.print("Digite o CNPJ da empresa: ");
        String cnpj = input.nextLine();

        System.out.print("Digite o nome da empresa: ");
        String name = input.nextLine();

        System.out.print("Digite o tempo de entrega em dias (somente número): ");
        int days = Integer.parseInt(input.nextLine());

        manager.registerSupplier(new Supplier(cnpj, name, days));
    }

    private void registerProduct() {

    }

    private void listProducts() {
        manager.listAllProducts();
    }

    private void validateManager(InventoryManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager is a required field.");
        }
    }

}
