package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.carros.domain.dto.CarroDTo;

@Service
public class CarroService {
    @Autowired
    private CarrosRepository rep;

    public List<CarroDTo> getCarros() {

        return rep.findAll().stream().map(c -> CarroDTo.create(c)).collect(Collectors.toList());

    }

    public Optional<CarroDTo> getCarrosById(Long id) {
        return rep.findById(id).map(c -> CarroDTo.create(c));
    }

    public List<CarroDTo> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(c -> CarroDTo.create(c)).collect(Collectors.toList());
    }

    public CarroDTo insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
        return CarroDTo.create(rep.save(carro));
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possivel atualizar o registro");
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id" + db.getId());
            rep.save(db);
            return db;

        } else {
            throw new RuntimeException("Não foi possivel localizar registro");
        }
    }

    public void delete(Long id) {

        if (getCarrosById(id).isPresent()) {
            rep.deleteById(id);
        }
    }
}
