package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain.Sucursal;

import java.util.List;
import java.util.Optional;

public interface SucursalService {
    public void add(Sucursal sucursal);

    public void update(Sucursal sucursal);

    public void deleteById(Long id);

    public Optional<Sucursal> findById(Long id);

    public List<Sucursal> getAll();

}

