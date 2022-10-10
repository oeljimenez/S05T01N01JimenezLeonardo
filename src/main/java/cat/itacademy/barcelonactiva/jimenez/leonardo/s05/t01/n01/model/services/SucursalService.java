package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.dto.SucursalDTO;

import java.util.List;
import java.util.Optional;

public interface SucursalService {
    public void save(Sucursal sucursal);

    public void deleteById(Integer id);

    public Optional<Sucursal> findById(Integer id);

    public List<Sucursal> getAll();

    public SucursalDTO convertToDto(Sucursal post);

    public Sucursal convertToEntity(SucursalDTO post);

}

