package com.example.carros.api.carros;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class CarroDTo {
    private Long id;
    private String nome;
    private String tipo;

    public static CarroDTo create(Carro c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarroDTo.class);
    }
}
