package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t01.n01.model.services.SucursalService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    Logger logger = (Logger) LoggerFactory.getLogger(SucursalController.class);
    @Autowired
    SucursalService sucursalService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        logger.info("Calling add method");
        SucursalDTO sucursalDTO = new SucursalDTO();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sucursalDTO", sucursalDTO);
        modelAndView.setViewName("views/addSucursal");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute SucursalDTO sucursalDTO, BindingResult result,
                             RedirectAttributes attribute) {
        logger.info("Calling save method");

        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("sucursalDTO", sucursalDTO);
            logger.info("Error when saving Sucursal save method");
            modelAndView.setViewName("views/addSucursal");
            return modelAndView;
        }

        sucursalService.save(sucursalService.convertToEntity(sucursalDTO));
        attribute.addFlashAttribute("success", "Sucursal afegida amb èxit");
        return new ModelAndView("redirect:" + "/sucursal/getAll");
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Integer id, RedirectAttributes attribute) {
        logger.info("Calling update method");
        ModelAndView modelAndView = new ModelAndView();
        Optional<Sucursal> sucursal = sucursalService.findById(id);
        if (id > 0 && sucursal.isPresent()) {
            modelAndView.addObject("sucursalDTO", sucursalService.convertToDto(sucursal.get()));
            modelAndView.setViewName("views/addSucursal");
            return modelAndView;
        } else {
            attribute.addFlashAttribute("error","Error when editing Sucursal, Id null o not exist");
            return new ModelAndView("redirect:" + "/sucursal/getAll");
        }

    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes attribute) {
        logger.info("Calling delete method");

        Optional<Sucursal> sucursal = sucursalService.findById(id);
        if(id > 0 && sucursal.isPresent()) {
            sucursalService.deleteById(id);
            attribute.addFlashAttribute("warning", "Sucursal eliminada amb èxit");
        } else {
            attribute.addFlashAttribute("error","Error when editing Sucursal, Id null o not exist");
        }
        return new ModelAndView("redirect:" + "/sucursal/getAll");
    }

    @GetMapping("/getOne/{id}")
    public ModelAndView getOne(@PathVariable("id") Integer id, RedirectAttributes attribute) {
        logger.info("Calling getOne method");
        ModelAndView modelAndView = new ModelAndView();

        List<SucursalDTO> sucursalDTOS = Arrays.asList(sucursalService.findById(id)).stream().map(
                s -> sucursalService.convertToDto(s.get())).collect(Collectors.toList());;
        if (id > 0 && sucursalDTOS.size()==1) {
            logger.info("SUCURSAL FOUND");
            modelAndView.addObject("sucursals", sucursalDTOS);
            modelAndView.setViewName("views/sucursals");
            return modelAndView;
        } else {
            attribute.addFlashAttribute("error","Error when editing Sucursal, Id null o not exist");
            return new ModelAndView("redirect:" + "/sucursal/getAll");
        }

    }

    @GetMapping("/getAll")
    public ModelAndView getAll(Model model) {
        logger.info("Calling getAll method");
        List<SucursalDTO> sucursalDTOS = sucursalService.getAll().stream().map(
                s -> sucursalService.convertToDto(s)).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sucursals", sucursalDTOS);
        modelAndView.setViewName("views/sucursals");
        return modelAndView;

    }
}
