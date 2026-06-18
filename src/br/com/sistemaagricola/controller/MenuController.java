package br.com.sistemaagricola.controller;

import br.com.sistemaagricola.models.helpers.Supplier;
import br.com.sistemaagricola.models.subclasses.Fertilizer;
import br.com.sistemaagricola.models.subclasses.HandTool;
import br.com.sistemaagricola.models.subclasses.HeavyMachinery;
import br.com.sistemaagricola.models.subclasses.Seed;
import br.com.sistemaagricola.models.superclasses.Product;
import br.com.sistemaagricola.services.InventoryManager;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuController {

    private final InventoryManager manager;
    private boolean running;
    private final Scanner input;

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
        System.out.println("Finalizando o programa...");
    }

    private void showMenu() {
        System.out.println("\n==== Menu Principal ====");
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
                break;
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
        System.out.println("Fornecedor de CNPJ: " + cnpj + " adicionado.");
    }

    private void registerProduct() {
        System.out.println("\nQual tipo de produto deseja cadastrar? ");
        System.out.println("1 - Insumo");
        System.out.println("2 - Equipamento");
        System.out.println("3 - Voltar");
        System.out.print("Escolha sua opção: ");

        String option = input.nextLine();

        switch (option) {
            case "1":
                chooseSupply();
                break;
            case "2":
                chooseEquipment();
                break;
            case "3":
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void listProducts() {
        System.out.println("\n==== Lista de Produtos ====");
        for (Product p : manager.listAllProducts()) {
            System.out.println(p.getId() + " - " + p.toString());
        }
    }

    private void chooseSupply() {
        System.out.println("\nQual o tipo de Insumo?");
        System.out.println("1 - Semente");
        System.out.println("2 - Fertilizante");
        System.out.println("3 - Voltar");
        System.out.print("Escolha sua opção: ");

        String option = input.nextLine();

        switch (option) {
            case "1":
                createSeed();
                break;
            case "2":
                createFertilizer();
                break;
            case "3":
                registerProduct();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void createSeed() {
        System.out.print("Digite o nome da Semente: ");
        String name = input.nextLine();

        System.out.print("Digite o preço base da Semente: ");
        double basePrice = Double.parseDouble(input.nextLine());

        System.out.print("Digite a quantidade de Sementes: ");
        int quantity = Integer.parseInt(input.nextLine());

        System.out.print("Informe o CNPJ do fornecedor: ");
        String cnpj = input.nextLine();

        Supplier localSupplier = manager.findSupplierByCnpj(cnpj);

        System.out.print("Digite a data de fabricação da Semente (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        System.out.print("Digite a taxa de germinação da Semente: ");
        int tax = Integer.parseInt(input.nextLine());

        manager.addProduct(new Seed(name, basePrice, quantity, localSupplier, date, tax));
    }

    private void createFertilizer() {
        System.out.print("Digite o nome do Fertilizante: ");
        String name = input.nextLine();

        System.out.print("Digite o preço base do Fertilizante: ");
        double basePrice = Double.parseDouble(input.nextLine());

        System.out.print("Digite a quantidade de Fertilizantes: ");
        int quantity = Integer.parseInt(input.nextLine());

        System.out.print("Informe o CNPJ do fornecedor: ");
        String cnpj = input.nextLine();

        Supplier localSupplier = manager.findSupplierByCnpj(cnpj);

        System.out.print("Digite a data de fabricação do Fertilizante (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        System.out.print("Digite o nível de toxicidade do Fertilizante: ");
        int toxicity = Integer.parseInt(input.nextLine());

        manager.addProduct(new Fertilizer(name, basePrice, quantity, localSupplier, date, toxicity));
    }

    private void chooseEquipment() {
        System.out.println("\nQual o tipo de Equipamento?");
        System.out.println("1 - Ferramenta Manual");
        System.out.println("2 - Maquinário Pesado");
        System.out.println("3 - Voltar");
        System.out.print("Escolha sua opção: ");

        String option = input.nextLine();

        switch (option) {
            case "1":
                createHandTool();
                break;
            case "2":
                createHeavyMachinery();
                break;
            case "3":
                registerProduct();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void createHandTool() {
        System.out.print("Digite o nome da Ferramenta Manual: ");
        String name = input.nextLine();

        System.out.print("Digite o preço base da Ferramenta Manual: ");
        double basePrice = Double.parseDouble(input.nextLine());

        System.out.print("Digite a quantidade de Ferramentas Manuais: ");
        int quantity = Integer.parseInt(input.nextLine());

        System.out.print("Informe o CNPJ do fornecedor: ");
        String cnpj = input.nextLine();

        Supplier localSupplier = manager.findSupplierByCnpj(cnpj);

        System.out.print("Digite o material da Ferramenta Manual:  ");
        String material = input.nextLine();

        manager.addProduct(new HandTool(name, basePrice, quantity, localSupplier, material));
    }

    private void createHeavyMachinery() {
        System.out.print("Digite o nome do Maquinário: ");
        String name = input.nextLine();

        System.out.print("Digite o preço base do Maquinário: ");
        double basePrice = Double.parseDouble(input.nextLine());

        System.out.print("Digite a quantidade de Maquinários: ");
        int quantity = Integer.parseInt(input.nextLine());

        System.out.print("Informe o CNPJ do fornecedor: ");
        String cnpj = input.nextLine();

        Supplier localSupplier = manager.findSupplierByCnpj(cnpj);

        System.out.print("Digite o Chassi do Maquinário:  ");
        String chassi = input.nextLine();

        manager.addProduct(new HeavyMachinery(name, basePrice, quantity, localSupplier, chassi));
    }

    private void validateManager(InventoryManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager is a required field.");
        }
    }

}
