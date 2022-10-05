package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.S05T01N01JimenezLeonardoSpringConfig;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.repository.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiveImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    ApplicationContext ctx = new AnnotationConfigApplicationContext(S05T01N01JimenezLeonardoSpringConfig.class);

    @Override
    public void add(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    @Override
    public void update(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
    }

    @Override
    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }

    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    public List<Sucursal> getAll() {
        return sucursalRepository.findAll();
    }

    public SucursalDTO convertToDto(Sucursal sucursal) {
        SucursalDTO sucursalDTO = ctx.getBean(ModelMapper.class).map(sucursal, SucursalDTO.class);
        if(sucursalDTO.getCountries().stream().anyMatch(p -> sucursal.getPaisSucursal().equalsIgnoreCase(p))){
            sucursalDTO.setTipusSucursal("UE");
        }else{
            sucursalDTO.setTipusSucursal("Fora UE");
        }
        return sucursalDTO;
    }

    public Sucursal convertToEntity(SucursalDTO sucursalDTO) {
        Sucursal sucursal = ctx.getBean(ModelMapper.class).map(sucursalDTO, Sucursal.class);

        if (sucursalDTO.getPk_SucursalID() != null) {
            Optional<Sucursal> oldSucursal = sucursalRepository.findById(Long.valueOf(sucursalDTO.getPk_SucursalID()));
            sucursal.setNomSucursal(oldSucursal.get().getNomSucursal());
            sucursal.setPaisSucursal(oldSucursal.get().getPaisSucursal());
        }
        return sucursal;
    }
}
