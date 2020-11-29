package br.com.xavecoding.regesc.service;

import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudProfessorService {
    private ProfessorRepository professorRepository;  // dependência da classe CrudProfessorService

    // o Spring automaticamente cria um objeto com a interface `ProfessorRepository`,
    // e o injeta para nós no construtor da classe atual ==> Injeção de Depenência
    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");
            System.out.println("3 - Visualizar todos os Professores");
            System.out.println("4 - Deletar um Professor");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }


    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.next();  // le a próxima string até achar um enter ou um espaco

        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.next();  // le a próxima string até achar um enter ou um espaco

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no Banco!!!\n");
    }


    private void atualizar(Scanner scanner) {
        System.out.print("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);

        // se o hibernate conseguiu achar uma tupla/registro na tabela de professores com id igual ao passado pelo usuario
        if (optional.isPresent()) {
            System.out.print("Digite o nome do professor: ");
            String nome = scanner.next();  // le a próxima string até achar um enter ou um espaco

            System.out.print("Digite o prontuario do professor: ");
            String prontuario = scanner.next();  // le a próxima string até achar um enter ou um espaco

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            professorRepository.save(professor);  // atualiza (persiste) o objeto/registro/tupla no BD
            System.out.println("Professor atualizado com sucesso!!!\n");
        }
        else {
            System.out.println("O id do professor informado: " + id + " é inválido\n");
        }
    }

    private void atualizarSemFindById(Scanner scanner) {
        System.out.print("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        System.out.print("Digite o nome do professor: ");
        String nome = scanner.next();  // le a próxima string até achar um enter ou um espaco

        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.next();  // le a próxima string até achar um enter ou um espaco

        Professor professor = new Professor();
        professor.setId(id);
        professor.setNome(nome);
        professor.setProntuario(prontuario);

        professorRepository.save(professor);  // atualiza (persiste) o objeto/registro/tupla no BD
        System.out.println("Professor atualizado com sucesso!!!\n");
    }


    private void visualizar() {
        Iterable<Professor> professores = this.professorRepository.findAll();
        // ALTERNATIVA 1
        for (Professor professor : professores) {
            System.out.println(professor);
        }

        // ALTERNATIVA 2
//        professores.forEach(professor -> {
//            System.out.println(professor);
//        });

        // ALTERNATIVA 3
//        professores.forEach(System.out::println);
        System.out.println();
    }


    private void deletar(Scanner scanner) {
        System.out.print("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();
        this.professorRepository.deleteById(id);  // lançará uma exception se não achar o ID passado na tabela
        System.out.println("Professor Deletado!\n");
    }
}
