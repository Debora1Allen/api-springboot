package com.example.carros.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarrosRepository rep;
    public Iterable<Carro> getCarros(){
        return rep.findAll();
    }

    public Optional<Carro> getCarrosById(Long id) {
        return rep.findById(id);
    }



}
