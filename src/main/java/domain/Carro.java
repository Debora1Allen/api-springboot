package domain;

import com.fasterxml.jackson.annotation.JsonTypeId;


public class Carro {

    private Long id;

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

    public Carro(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private String nome;
}
