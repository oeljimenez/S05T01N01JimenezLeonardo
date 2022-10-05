package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.services.SucursalService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    Logger logger = (Logger) LoggerFactory.getLogger(SucursalController.class);
    @Autowired
    SucursalService sucursalService;
    @PostMapping("/add")
    public ResponseEntity<Sucursal> add(@RequestBody Sucursal sucursal) {
        logger.info("Calling add method");
        try {
            sucursalService.add(sucursal);
            return new ResponseEntity<>(sucursal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable("id") long id, @RequestBody Sucursal fruitaDetails) {
        logger.info("Calling update method");
        try {
            Optional<Sucursal> sucursal = sucursalService.findById(id);
            if (sucursal.isPresent()) {
               // sucursal.get().setNom(fruitaDetails.getNom());
               // sucursal.get().setQuantitatQuilos(fruitaDetails.getQuantitatQuilos());
                sucursalService.update(sucursal.get());
                return new ResponseEntity<>(sucursalService.findById(id).get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Sucursal> delete(@PathVariable("id") long id) {
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
    public ResponseEntity<Sucursal> getOne(@PathVariable("id") long id) {
        logger.info("Calling getOne method");
        try {
            Optional<Sucursal> sucursal = sucursalService.findById(id);
            if (sucursal.isPresent()) {
                sucursalService.deleteById(id);
                return new ResponseEntity<>(sucursal.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Sucursal>> getAll() {
        logger.info("Calling getAll method");
        try {
            List<Sucursal> fruites = sucursalService.getAll();
            return new ResponseEntity<>(fruites, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
