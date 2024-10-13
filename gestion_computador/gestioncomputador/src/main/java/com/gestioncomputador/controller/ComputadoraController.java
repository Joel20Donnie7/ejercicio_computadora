package com.gestioncomputador.controller;

import com.gestioncomputador.entity.Computadora;
import com.gestioncomputador.repository.ComputadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
@Controller
public class ComputadoraController {

    @Autowired
    private ComputadoraRepository computadoraRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/computadora";
    }

    @GetMapping("/computadora")
    public String listarComputadora(Model model) {
        List<Computadora> computadoras = computadoraRepository.findAll();
        model.addAttribute("computadoras", computadoras);
        return "computadora"; // Vista para listar computadoras
    }

    @GetMapping("/computadora/nuevo")
    public String agregarComputadora(Model model) {
        Computadora computadora = new Computadora();
        model.addAttribute("computadora", computadora);
        model.addAttribute("pageTitle", "Nueva Computadora");
        return "computadora_form"; // Vista para agregar nueva computadora
    }

    @PostMapping("/computadora/save")
    public String guardarComputadora(Computadora computadora, RedirectAttributes redirectAttributes) {
        try {
            computadoraRepository.save(computadora);
            redirectAttributes.addFlashAttribute("message", "El computador ha sido guardado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/computadora";
    }

    @GetMapping("/computadora/editar/{id}")
    public String editarComputadora(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Computadora> optionalComputadora = computadoraRepository.findById(id);
        if (optionalComputadora.isPresent()) {
            model.addAttribute("computadora", optionalComputadora.get());
            model.addAttribute("pageTitle", "Editar Computadora");
            return "computadora_form"; // Vista para editar
        } else {
            redirectAttributes.addFlashAttribute("message", "Computadora no encontrada");
            return "redirect:/computadora";
        }
    }

    @GetMapping("/computadora/eliminar/{id}")
    public String eliminarComputadora(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            computadoraRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Computadora eliminada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al eliminar la computadora: " + e.getMessage());
        }
        return "redirect:/computadora";
    }
}
