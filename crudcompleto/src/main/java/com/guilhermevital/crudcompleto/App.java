package com.guilhermevital.crudcompleto;
import java.util.Date;
import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static ClienteDAO ClienteDAO = new ClienteDAO();

    public static void main(String[] args) {
    	boolean continuar = true;
        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir Registro");
            System.out.println("2. Atualizar Registro");
            System.out.println("3. Deletar Registro");
            System.out.println("4. Listar Registros");
            System.out.println("5. Sair");
            System.out.println("\n");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    inserirRegistro();
                    break;
                case 2:
                    atualizarRegistro();
                    break;
                case 3:
                    deletarRegistro();
                    break;
                case 4:
                    listarRegistros();
                    break;
                case 5:
                	System.out.println("Programa encerrado!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void inserirRegistro() {
        Cliente cliente = new Cliente();
        System.out.println("Digite o nome: ");
        String nome = sc.next();
        System.out.println("Digite a idade: ");
        int idade = sc.nextInt();

        cliente.setNome(nome);
        cliente.setIdade(idade);
        cliente.setDataCadastro(new Date());

        ClienteDAO.save(cliente);
    }

    public static void atualizarRegistro() {
        System.out.println("Digite o id para alterar: ");
        int id = sc.nextInt();
        System.out.println("Digite o novo nome:");
        String novoNome = sc.next();
        System.out.println("Digite a idade: ");
        int novaIdade = sc.nextInt();

        Cliente c1 = new Cliente();
        c1.setNome(novoNome);
        c1.setIdade(novaIdade);
        c1.setDataCadastro(new Date());
        c1.setId(id);

        ClienteDAO.update(c1);
    }

    public static void deletarRegistro() {
        System.out.println("Digite o ID para excluir: ");
        int excluirId = sc.nextInt();
        ClienteDAO.deleteByID(excluirId);
    }

    public static void listarRegistros() {
        for(Cliente p : ClienteDAO.getClientes()) {
        	System.out.println("\n\n");
            System.out.println("Clientes: " + p.getNome());
            System.out.println("\n\n");
        }
    }
}
