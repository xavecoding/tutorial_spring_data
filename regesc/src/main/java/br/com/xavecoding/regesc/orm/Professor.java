package br.com.xavecoding.regesc.orm;


import javax.persistence.*;

@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String prontuario;

    @Deprecated  // só para indicar que nós não a usaremos muito, ou que ela será usada por outras bibliotecas externas
    public Professor() { }

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public String toString() {
        return "Professor{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", prontuario='" + prontuario + '\'' +
               '}';
    }
}
