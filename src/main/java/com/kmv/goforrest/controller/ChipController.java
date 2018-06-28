package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Chip;
import com.kmv.goforrest.repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "chip")
public class ChipController {
    @Autowired
    private ChipRepository chipRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("chips", chipRepository.findAll());
        return "chip/listarChips";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar chip");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar chip");
        return "chip/formChips";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Chip chip) {
        chipRepository.save(chip);
        return "redirect:/chip";
    }

    @GetMapping(value = "/edit/{codChip}")
    public String getEdit(Model model, @PathVariable Long codChip) {
        Optional<Chip> chip = chipRepository.findById(codChip);
        model.addAttribute("tittle", "Editar chip");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Chip");
        if (chip.isPresent()) {
            model.addAttribute("chip", chip.get());
        }
        return "chip/formChips";
    }

    @PostMapping(value = "/edit/{codChip}")
    public String postEdit(@ModelAttribute Chip chip, Model model,
                       @PathVariable Long codChip) throws Exception {

        chipRepository.save(chip);
        return "redirect:/chip";
    }

    @GetMapping(value = "/delete/{codChip}")
    public String getDelete(Model model, @PathVariable Long codChip) {
        Optional<Chip> chip = chipRepository.findById(codChip);

        if (chip.isPresent()) {
            model.addAttribute("chip", chip.get());
        }
        model.addAttribute("tittle", "Excluir chip");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Chip");
        return "chip/formChips";
    }

    @PostMapping(value = "/delete/{codChip}")
    public String postDelete(@PathVariable Long codChip, @ModelAttribute Chip chip) {
        chipRepository.delete(chip);
        return "redirect:/chip";
    }
}



