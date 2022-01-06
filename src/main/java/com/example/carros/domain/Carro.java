package com.example.carros.domain;

import com.fasterxml.jackson.annotation.JsonTypeId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carro {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
public Carro(){

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

    public Carro(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private String nome;
}