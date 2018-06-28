package com.kmv.goforrest.controller;


import com.kmv.goforrest.model.LocalDeRetirada;
import com.kmv.goforrest.repository.LocalDeRetiradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "localderetirada")
public class LocalDeRetiradaController {

    @Autowired
    private LocalDeRetiradaRepository localDeRetiradaRepository;

    @GetMapping(value="")
    public String locaisDeRetirada(Model model) {
        model.addAttribute("locaisDeRetirada", localDeRetiradaRepository.findAll());
        return "localDeRetirada/listarLocaisDeRetirada";
    }

    @GetMapping(value = "/add") // site.com/localDeRetirada/add
    public String displayLocalDeRetiradaForm(Model model) {
        model.addAttribute("tittle", "Adicionar Local de Retirada");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Adicionar Local de Retirada");
        return "localDeRetirada/formLocaisDeRetirada";
    }

    @PostMapping(value = "/add")
    public String processLocalDeRetiradaForm(@ModelAttribute LocalDeRetirada localDeRetirada, Model model) {
        localDeRetiradaRepository.save(localDeRetirada);

        return "redirect:/localderetirada";
    }

    @GetMapping(value = "/edit/{codLocalDeRetirada}") // site.com/localDeRetirada/edit/1/
    public String localDeRetiradaEdit(Model model, @PathVariable Long codLocalDeRetirada) {
        Optional<LocalDeRetirada> localDeRetirada = localDeRetiradaRepository.findById(codLocalDeRetirada);
        model.addAttribute("tittle", "Editar Local de Retirada");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Local de Retirada");
        if (localDeRetirada.isPresent()) {
            model.addAttribute("localDeRetirada", localDeRetirada.get());
        }
        return "localDeRetirada/formLocaisDeRetirada";
    }

    @PostMapping(value = "/edit/{codLocalDeRetirada}") // site.com/localDeRetirada/edit/1/
    public String edit(@ModelAttribute LocalDeRetirada localDeRetirada, Model model,
                       @PathVariable Long codLocalDeRetirada) throws Exception {
        if (codLocalDeRetirada.equals(localDeRetirada.getCodLocalDeRetirada())) {
            localDeRetiradaRepository.save(localDeRetirada);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }

        return "redirect:/localderetirada";
    }

    @GetMapping(value = "/delete/{codLocalDeRetirada}") // site.com/localDeRetirada/delete/1
    public String localDeRetiradaDelete(Model model, @PathVariable Long codLocalDeRetirada) {
        Optional<LocalDeRetirada> localDeRetirada = localDeRetiradaRepository.findById(codLocalDeRetirada);

        if (localDeRetirada.isPresent()) {
            model.addAttribute("localDeRetirada", localDeRetirada.get());
        }
        model.addAttribute("tittle", "Excluir Local de Retirada");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Local de Retirada");
        return "localDeRetirada/formLocaisDeRetirada";
    }

    @PostMapping(value = "/delete/{codLocalDeRetirada}") // site.com/localDeRetirada/delete/1
    public String delete(@PathVariable Long codLocalDeRetirada, @ModelAttribute LocalDeRetirada localDeRetirada) {
        localDeRetiradaRepository.delete(localDeRetirada);
        return "redirect:/localderetirada";
    }

}
