package br.com.biblioteca.biblioteca.models;

import br.com.biblioteca.biblioteca.Enums.Cargo;
import br.com.biblioteca.biblioteca.Enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = true, unique = true)
    private Long idLivroAlugado;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public User() {
    }

    public User(Long id, String nome, String email, String senha, Long idLivroAlugado, Cargo cargo, Status status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idLivroAlugado = idLivroAlugado;
        this.cargo = cargo;
        this.status = status;
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getIdLivroAlugado() {
        return idLivroAlugado;
    }

    public void setIdLivroAlugado(Long idLivroAlugado) {
        this.idLivroAlugado = idLivroAlugado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
