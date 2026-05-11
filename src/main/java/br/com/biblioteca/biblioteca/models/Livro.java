package br.com.biblioteca.biblioteca.models;

import jakarta.persistence.*;

@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 100  )
    private String autor;
    @Column(nullable = false, length = 100)
    private String genero;
    @Column(nullable = false, length = 600)
    private String urlCapa;



    public Livro(Long id, String nome, String autor, String genero, String urlCapa) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        this.urlCapa = urlCapa;
    }

    public Livro(){
    }

    public String getUrlCapa() {
        return urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
