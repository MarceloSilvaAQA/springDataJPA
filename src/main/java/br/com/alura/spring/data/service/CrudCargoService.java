package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;


    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual Acao de cargo deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar os cargos");
            System.out.println("4 - Deletar Cargo");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizarCargo(scanner);
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
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
    }

    private void atualizarCargo(Scanner scanner){
        System.out.println("Atualizacao  do cargo");
        System.out.println("Informe Id do cargo");
        int id = scanner.nextInt();
        System.out.println("Informe a nova descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    private void  visualizar() {
       Iterable<Cargo> cargos =  cargoRepository.findAll();
       cargos.forEach(cargo -> System.out.println(cargo.toString()));
    }

    private void excluir(Scanner scanner) {
        System.out.println("Excluir  cargo");
        System.out.println("Informe Id do cargo");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Cargo Deletado");
    }
}
