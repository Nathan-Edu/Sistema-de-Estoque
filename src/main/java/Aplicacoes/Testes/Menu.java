package Aplicacoes.Testes;

import Aplicacoes.Dao.*;
import Aplicacoes.Modelos.*;

import java.sql.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        MaterialDAO materialDAO = new MaterialDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LoteDAO loteDAO = new LoteDAO();
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal;

        do {
            System.out.println("\n----- Sistema de Gestão de Estoque -----");
            System.out.println("1. Gerenciar Materiais");
            System.out.println("2. Gerenciar Usuários");
            System.out.println("3. Gerenciar Lotes");
            System.out.println("4. Gerenciar Estoques");
            System.out.println("5. Gerenciar Requisições");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    gerenciarMateriais(materialDAO, scanner);
                    break;
                case 2:
                    gerenciarUsuarios(usuarioDAO, scanner);
                    break;
                case 3:
                    gerenciarLotes(loteDAO, scanner);
                    break;
                case 4:
                    gerenciarEstoques(estoqueDAO, scanner);
                    break;
                case 5:
                    gerenciarRequisicoes(requisicaoDAO, scanner);
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoPrincipal != 6);

        scanner.close();
    }

    private static void gerenciarMateriais(MaterialDAO materialDAO, Scanner scanner) {
        int opcaoMateriais;
        do {
            System.out.println("\n--- Gerenciar Materiais ---");
            System.out.println("1. Cadastrar novo material");
            System.out.println("2. Modificar material");
            System.out.println("3. Listar todos os materiais");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoMateriais = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoMateriais) {
                case 1:
                    System.out.print("Descrição Curta: ");
                    String descricaoCurta = scanner.nextLine();
                    System.out.print("Descrição Longa: ");
                    String descricaoLonga = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Unidade de Medida: ");
                    String unidadeMedida = scanner.nextLine();
                    System.out.print("Depósito: ");
                    String deposito = scanner.nextLine();
                    System.out.print("Origem do Material (Produzido Internamente / Comprado Externamente): ");
                    String origemMaterial = scanner.nextLine();
                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    BigDecimal quantidadeBigDecimal = BigDecimal.valueOf(quantidade);

                    Material novoMaterial = new Material(descricaoCurta, descricaoLonga, quantidadeBigDecimal, unidadeMedida, deposito, origemMaterial, status);
                    materialDAO.adicionaMaterial(novoMaterial);
                    System.out.println("Material cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("ID do Material a modificar: ");
                    int idModMaterial = scanner.nextInt();
                    scanner.nextLine();
                    Material materialMod = materialDAO.buscarMaterialPorId(idModMaterial);
                    if (materialMod != null) {
                        System.out.print("Nova Descrição Curta: ");
                        String novaDescricaoCurta = scanner.nextLine();
                        System.out.print("Nova Descrição Longa: ");
                        String novaDescricaoLonga = scanner.nextLine();
                        System.out.print("Nova Quantidade: ");
                        int novaQuantidade = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nova Unidade de Medida: ");
                        String novaUnidadeMedida = scanner.nextLine();
                        System.out.print("Novo Depósito: ");
                        String novoDeposito = scanner.nextLine();
                        System.out.print("Nova Origem do Material (Produzido Internamente / Comprado Externamente): ");
                        String novaOrigemMaterial = scanner.nextLine();
                        System.out.print("Novo Status: ");
                        String novoStatus = scanner.nextLine();

                        BigDecimal novaQuantidadeBigDecimal = BigDecimal.valueOf(novaQuantidade);

                        materialMod.setDescricao_curta(novaDescricaoCurta);
                        materialMod.setDescricao_longa(novaDescricaoLonga);
                        materialMod.setQuantidade(novaQuantidadeBigDecimal);
                        materialMod.setUnidade_medida(novaUnidadeMedida);
                        materialMod.setDeposito(novoDeposito);
                        materialMod.setOrigem_material(novaOrigemMaterial);
                        materialMod.setStatus(novoStatus);

                        materialDAO.atualizarMaterial(materialMod);
                        System.out.println("Material modificado com sucesso!");
                    } else {
                        System.out.println("Material não encontrado!");
                    }
                    break;

                case 3:
                    List<Material> materiais = materialDAO.listarMateriais();
                    for (Material mat : materiais) {
                        System.out.println(mat);
                    }
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoMateriais != 4);
    }
    private static void gerenciarUsuarios(UsuarioDAO usuarioDAO, Scanner scanner) {
        int opcaoUsuarios;
        do {
            System.out.println("\n--- Gerenciar Usuários ---");
            System.out.println("1. Cadastrar novo usuário");
            System.out.println("2. Modificar usuário");
            System.out.println("3. Listar todos os usuários");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoUsuarios = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoUsuarios) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    Usuario novoUsuario = new Usuario(nome, email, senha);
                    usuarioDAO.adicionaUsuario(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("ID do Usuário a modificar: ");
                    int idModUsuario = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioMod = usuarioDAO.buscarUsuarioPorId(idModUsuario);
                    if (usuarioMod != null) {
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo Email: ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Nova Senha: ");
                        String novaSenha = scanner.nextLine();

                        usuarioMod.setNome(novoNome);
                        usuarioMod.setEmail(novoEmail);
                        usuarioMod.setSenha(novaSenha);

                        usuarioDAO.atualizarUsuario(usuarioMod);
                        System.out.println("Usuário modificado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;
                case 3:
                    List<Usuario> usuarios = usuarioDAO.listarUsuarios();
                    for (Usuario usuario : usuarios) {
                        System.out.println(usuario);
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoUsuarios != 4);
    }

    private static void gerenciarLotes(LoteDAO loteDAO, Scanner scanner) {
        int opcaoLotes;
        do {
            System.out.println("\n--- Gerenciar Lotes ---");
            System.out.println("1. Cadastrar novo lote");
            System.out.println("2. Modificar lote");
            System.out.println("3. Listar todos os lotes");
            System.out.println("4. Deletar lote");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoLotes = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoLotes) {
                case 1:
                    System.out.print("ID do Lote: ");
                    int idLote = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do Material: ");
                    int idMaterialLote = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidadeLote = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo de Ação: ");
                    String tipoAcao = scanner.nextLine();
                    System.out.print("Data (yyyy-mm-dd): ");
                    String data = scanner.nextLine();

                    BigDecimal quantidadeLoteBigDecimal = BigDecimal.valueOf(quantidadeLote);

                    Lote novoLote = new Lote(idLote, idMaterialLote, quantidadeLoteBigDecimal, tipoAcao, Date.valueOf(data));
                    loteDAO.adicionaLote(novoLote);
                    System.out.println("Lote cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("ID do Lote a modificar: ");
                    int idModLote = scanner.nextInt();
                    scanner.nextLine();
                    Lote loteMod = loteDAO.buscarLotePorId(idModLote);
                    if (loteMod != null) {
                        System.out.print("Nova Quantidade: ");
                        int novaQuantidadeLote = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo Tipo de Ação: ");
                        String novoTipoAcao = scanner.nextLine();
                        System.out.print("Nova Data (yyyy-mm-dd): ");
                        String novaData = scanner.nextLine();

                        BigDecimal novaQuantidadeLoteBigDecimal = BigDecimal.valueOf(novaQuantidadeLote);

                        loteMod.setQuantidade(novaQuantidadeLoteBigDecimal);
                        loteMod.setTipoAcao(novoTipoAcao);
                        loteMod.setDataEntrada(Date.valueOf(novaData));

                        loteDAO.atualizarLote(loteMod);
                        System.out.println("Lote modificado com sucesso!");
                    } else {
                        System.out.println("Lote não encontrado!");
                    }
                    break;

                case 3:
                    List<Lote> lotes = loteDAO.listarLotes();
                    for (Lote lote : lotes) {
                        System.out.println(lote);
                    }
                    break;

                case 4:
                    System.out.print("ID do Lote a deletar: ");
                    int idDelLote = scanner.nextInt();
                    scanner.nextLine();
                    loteDAO.deletarLote(idDelLote);
                    System.out.println("Lote deletado com sucesso!");
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoLotes != 5);
    }

    private static void gerenciarEstoques(EstoqueDAO estoqueDAO, Scanner scanner) {
        int opcaoEstoques;
        do {
            System.out.println("\n--- Gerenciar Estoques ---");
            System.out.println("1. Cadastrar novo estoque");
            System.out.println("2. Modificar estoque");
            System.out.println("3. Listar todos os estoques");
            System.out.println("4. Deletar estoque");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoEstoques = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoEstoques) {
                case 1:
                    System.out.print("ID do Estoque: ");
                    int idEstoque = Integer.parseInt(scanner.nextLine());
                    scanner.nextLine();
                    System.out.print("ID do Material: ");
                    int idMaterialEstoque = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidadeEstoque = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Unidade de Medida: ");
                    String unidadeMedida = scanner.nextLine();
                    System.out.print("Data (yyyy-mm-dd): ");
                    String dataEntrada = scanner.nextLine();

                    BigDecimal quantidadeEstoqueBigDecimal = BigDecimal.valueOf(quantidadeEstoque);

                    Estoque novoEstoque = new Estoque(idEstoque, idMaterialEstoque, quantidadeEstoqueBigDecimal, unidadeMedida, Date.valueOf(dataEntrada));
                    estoqueDAO.adicionaEstoque(novoEstoque);
                    System.out.println("Estoque cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("ID do Estoque a modificar: ");
                    String idModEstoque = String.valueOf(scanner.nextInt());
                    scanner.nextLine();
                    Estoque estoqueMod = estoqueDAO.buscarEstoquePorId(Integer.parseInt(idModEstoque));
                    if (estoqueMod != null) {
                        System.out.print("Nova Quantidade: ");
                        int novaQuantidadeEstoque = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nova Unidade de Medida: ");
                        String novaUnidadeMedida = scanner.nextLine();
                        System.out.print("Nova Data (yyyy-mm-dd): ");
                        String novaDataEntrada = scanner.nextLine();

                        BigDecimal novaQuantidadeEstoqueBigDecimal = BigDecimal.valueOf(novaQuantidadeEstoque);

                        estoqueMod.setQuantidade(novaQuantidadeEstoqueBigDecimal);
                        estoqueMod.setUnidadeMedida(novaUnidadeMedida);
                        estoqueMod.setDataEntrada(Date.valueOf(novaDataEntrada));

                        estoqueDAO.atualizarEstoque(estoqueMod);
                        System.out.println("Estoque modificado com sucesso!");
                    } else {
                        System.out.println("Estoque não encontrado!");
                    }
                    break;

                case 3:
                    List<Estoque> estoques = estoqueDAO.listarEstoques();
                    for (Estoque estoque : estoques) {
                        System.out.println(estoque);
                    }
                    break;

                case 4:
                    System.out.print("ID do Estoque a deletar: ");
                    String idDelEstoque = scanner.nextLine();
                    scanner.nextLine();
                    estoqueDAO.deletarEstoque(Integer.parseInt(idDelEstoque));
                    System.out.println("Estoque deletado com sucesso!");
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoEstoques != 5);
    }

    private static void gerenciarRequisicoes(RequisicaoDAO requisicaoDAO, Scanner scanner) {
        int opcaoRequisicoes;
        do {
            System.out.println("\n--- Gerenciar Requisições ---");
            System.out.println("1. Cadastrar nova requisição");
            System.out.println("2. Modificar requisição");
            System.out.println("3. Listar todas as requisições");
            System.out.println("4. Deletar requisição");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoRequisicoes = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoRequisicoes) {
                case 1:
                    System.out.print("ID da Requisição: ");
                    int idRequisicao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do Usuário: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Solicitante: ");
                    String solicitante = scanner.nextLine();
                    System.out.print("ID do Material: ");
                    int idMaterialRequisicao = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Quantidade: ");
                    double quantidadeRequisicao = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Data (yyyy-mm-dd): ");
                    String dataRequisicao = scanner.nextLine();

                    Requisicao novaRequisicao = new Requisicao(idRequisicao, idUsuario, solicitante, idMaterialRequisicao, quantidadeRequisicao, dataRequisicao);
                    requisicaoDAO.adicionaRequisicao(novaRequisicao);
                    System.out.println("Requisição cadastrada com sucesso!");
                    break;
                case 2:
                    System.out.print("ID da Requisição a modificar: ");
                    int idModRequisicao = scanner.nextInt();
                    scanner.nextLine();
                    Requisicao requisicaoMod = requisicaoDAO.buscarRequisicaoPorId(idModRequisicao);
                    if (requisicaoMod != null) {
                        System.out.print("Novo ID do Usuário: ");
                        int novoIdUsuario = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo Solicitante: ");
                        String novoSolicitante = scanner.nextLine();
                        System.out.print("Novo ID do Material: ");
                        int novoIdMaterialRequisicao = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nova Quantidade: ");
                        double novaQuantidadeRequisicao = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Nova Data (yyyy-mm-dd): ");
                        String novaDataRequisicao = scanner.nextLine();

                        requisicaoMod.setIdUsuario(novoIdUsuario);
                        requisicaoMod.setSolicitante(novoSolicitante);
                        requisicaoMod.setIdMaterial(novoIdMaterialRequisicao);
                        requisicaoMod.setQuantidade(novaQuantidadeRequisicao);
                        requisicaoMod.setData(novaDataRequisicao);

                        requisicaoDAO.atualizarRequisicao(requisicaoMod);
                        System.out.println("Requisição modificada com sucesso!");
                    } else {
                        System.out.println("Requisição não encontrada!");
                    }
                    break;
                case 3:
                    List<Requisicao> requisicoes = requisicaoDAO.listarRequisicoes();
                    for (Requisicao requisicao : requisicoes) {
                        System.out.println(requisicao);
                    }
                    break;
                case 4:
                    System.out.print("ID da Requisição a deletar: ");
                    int idDelRequisicao = scanner.nextInt();
                    scanner.nextLine();
                    requisicaoDAO.deletarRequisicao(idDelRequisicao);
                    System.out.println("Requisição deletada com sucesso!");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoRequisicoes != 5);
    }
}
