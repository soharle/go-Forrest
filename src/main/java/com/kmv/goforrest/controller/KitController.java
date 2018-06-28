package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Kit;
import com.kmv.goforrest.repository.ChipRepository;
import com.kmv.goforrest.repository.KitRepository;
import com.kmv.goforrest.repository.LocalDeRetiradaRepository;
import com.kmv.goforrest.repository.EventoRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "kit")

public class KitController {
    @Autowired
    private KitRepository kitRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private LocalDeRetiradaRepository localDeRetiradaRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("kits", kitRepository.findAll());
        return "kit/listarKits";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar kit");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar kit");
        model.addAttribute("eventos", eventoRepository.findAll());
        model.addAttribute("locaisDeRetirada", localDeRetiradaRepository.findAll());
        return "kit/formKits";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Kit kit) {
        if (kit.getCamisa()== null) {
            kit.setCamisa(false);
        }
        kitRepository.save(kit);
        return "redirect:/kit";
    }

    @GetMapping(value = "/edit/{codKit}")
    public String getEdit(Model model, @PathVariable Long codKit) {
        Optional<Kit> kit = kitRepository.findById(codKit);
        model.addAttribute("tittle", "Editar kit");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar kit");
        model.addAttribute("eventos", eventoRepository.findAll());
        model.addAttribute("locaisDeRetirada", localDeRetiradaRepository.findAll());

        if (kit.isPresent()) {
            model.addAttribute("kit", kit.get());
        }
        return "kit/formKits";
    }

    @PostMapping(value = "/edit/{codKit}")
    public String postEdit(@ModelAttribute Kit kit, Model model,
                           @PathVariable Long codKit) throws Exception {

        if (kit.getCamisa()== null) {
            kit.setCamisa(false);
        }
        kitRepository.save(kit);
        return "redirect:/kit";
    }

    @GetMapping(value = "/delete/{codKit}")
    public String getDelete(Model model, @PathVariable Long codKit) {
        Optional<Kit> kit = kitRepository.findById(codKit);

        if (kit.isPresent()) {
            model.addAttribute("kit", kit.get());
        }
        model.addAttribute("tittle", "Excluir kit");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir kit");
        return "kit/formKits";
    }

    @PostMapping(value = "/delete/{codKit}")
    public String postDelete(@PathVariable Long codKit, @ModelAttribute Kit kit) {
        kitRepository.delete(kit);
        return "redirect:/kit";
    }
}



