package proj_progsist2.proj_estagios.model;

public class Estudante {
    private Long id;
    private String nome;
    private String curso;
    private String matricula;

    public Estudante() {
    }

    public Estudante(Long id, String nome, String curso, String matricula) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.matricula = matricula;
    }
}
