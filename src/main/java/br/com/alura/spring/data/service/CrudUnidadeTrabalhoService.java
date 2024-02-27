package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }


    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual Acao de Unidade Trabalho deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar as Unidades");
            System.out.println("4 - Desativar Unidade");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizarUnidade(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    excluir(scanner);
                    break;

                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner){
        System.out.println("Descricao da Unidade");
        String descricao = scanner.next();
        System.out.println("Endereco da Unidade");
        String endereco = scanner.next();
        UnidadeTrabalho unidade = new UnidadeTrabalho();
        unidade.setDescricao(descricao);
        unidade.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidade);
    }

    private void atualizarUnidade(Scanner scanner){
        System.out.println("Atualizacao  da unidade");
        System.out.println("Informe Id da unidade");
        int id = scanner.nextInt();
        System.out.println("Informe a nova descricao da unidade");
        String descricao = scanner.next();
        System.out.println("Informe o novo endereco da unidade");
        String endereco = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void  visualizar() {
        Iterable<UnidadeTrabalho> unidades =  unidadeTrabalhoRepository.findAll();
        unidades.forEach(unidadeTrabalho -> System.out.println(unidades.toString()));
    }

    private void excluir(Scanner scanner) {
        System.out.println("Excluir  unidade");
        System.out.println("Informe Id da unidade");
        int id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Cargo Deletado");
    }
}
