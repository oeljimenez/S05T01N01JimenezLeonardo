package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.services.SucursalService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    Logger logger = (Logger) LoggerFactory.getLogger(SucursalController.class);
    @Autowired
    SucursalService sucursalService;

    @PostMapping("/add")
    public ResponseEntity<SucursalDTO> add(@RequestBody SucursalDTO sucursalDTO) {
        logger.info("Calling add method");
        try {
            Sucursal sucursal = sucursalService.convertToEntity(sucursalDTO);
            sucursalService.add(sucursal);
            return new ResponseEntity<>(sucursalService.convertToDto(sucursal), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SucursalDTO> update(@PathVariable("id") long id, @RequestBody SucursalDTO sucursalDTO) {
        logger.info("Calling update method");
        try {
            Optional<Sucursal> sucursal = sucursalService.findById(id);
            if (sucursal.isPresent()) {
                sucursal.get().setNomSucursal(sucursalDTO.getNomSucursal());
                sucursal.get().setPaisSucursal(sucursalDTO.getPaisSucursal());
                sucursalService.update(sucursal.get());
                return new ResponseEntity<>(sucursalService.convertToDto(sucursalService.findById(id).get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SucursalDTO> delete(@PathVariable("id") long id) {
        logger.info("Calling delete method");
        try {
            Optional<Sucursal> sucursal = sucursalService.findById(id);
            if (sucursal.isPresent()) {
                sucursalService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<SucursalDTO> getOne(@PathVariable("id") long id) {
        logger.info("Calling getOne method");
        try {
            Optional<Sucursal> sucursal = sucursalService.findById(id);
            if (sucursal.isPresent()) {
                return new ResponseEntity<>(sucursalService.convertToDto(sucursal.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SucursalDTO>> getAll() {
        logger.info("Calling getAll method");
        try {
            List<SucursalDTO> sucursalDTOS = sucursalService.getAll().stream().map(
                    s -> sucursalService.convertToDto(s)).collect(Collectors.toList());
            return new ResponseEntity<>(sucursalDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
