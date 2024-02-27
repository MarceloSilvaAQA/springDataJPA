package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private final CargoRepository cargoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.cargoRepository = cargoRepository;
    }


    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual Acao de funcionario deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar os funcionarios");
            System.out.println("4 - Deletar Funcionario");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizarFuncionario(scanner);
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
        System.out.println("Nome do Funcionario");
        String nome = scanner.next();
        System.out.println("CPF do Funcionario");
        String cpf = scanner.next();
        System.out.println("Salario do Funcionario");
        double salario = scanner.nextDouble();
        System.out.println("Codigo do cargo");
        Integer cargoId = scanner.nextInt();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.now());
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);
        funcionarioRepository.save(funcionario);
    }

    private void atualizarFuncionario(Scanner scanner){
        System.out.println("Atualizacao  do Funcionario");
        System.out.println("Informe Id do Funcionario");
        int id = scanner.nextInt();
        System.out.println("Informe o novo CPF");
        String cpf = scanner.next();
        System.out.println("Informe o novo Salario");
        double salario = scanner.nextDouble();
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionarioRepository.save(funcionario);
        System.out.println("Atualizado");
    }

    private void  visualizar() {
        Iterable<Funcionario> funcionarios =  funcionarioRepository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void excluir(Scanner scanner) {
        System.out.println("Excluir  funcionario");
        System.out.println("Informe Id do funcionario");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Funcionario Deletado");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
}
