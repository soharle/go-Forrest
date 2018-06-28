package com.kmv.goforrest.controller;


import com.kmv.goforrest.model.Administrador;
import com.kmv.goforrest.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "administrador")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping(value="")
    public String administradores(Model model) {
        model.addAttribute("administradores", administradorRepository.findAll());
        return "administrador/listarAdministradores";
    }

    @GetMapping(value = "/add") // site.com/administrador/add
    public String displayAdministradorForm(Model model) {
        model.addAttribute("tittle", "Adicionar administrador");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Adicionar Administrador");
        return "administrador/formAdministradores";
    }

    @PostMapping(value = "/add")
    public String processAdministradorForm(@ModelAttribute Administrador administrador, Model model) {
        administradorRepository.save(administrador);

        return "redirect:/administrador";
    }

    @GetMapping(value = "/edit/{codAdministrador}") // site.com/administrador/edit/1/
    public String administradorEdit(Model model, @PathVariable Long codAdministrador) {
        Optional<Administrador> administrador = administradorRepository.findById(codAdministrador);
        model.addAttribute("tittle", "Editar administrador");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Administrador");
        if (administrador.isPresent()) {
            model.addAttribute("administrador", administrador.get());
        }
        return "administrador/formAdministradores";
    }

    @PostMapping(value = "/edit/{codAdministrador}") // site.com/administrador/edit/1/
    public String edit(@ModelAttribute Administrador administrador, Model model,
                       @PathVariable Long codAdministrador) throws Exception {
        if (codAdministrador.equals(administrador.getCodAdministrador())) {
            administradorRepository.save(administrador);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }

        return "redirect:/administrador";
    }

    @GetMapping(value = "/delete/{codAdministrador}") // site.com/administrador/delete/1
    public String administradorDelete(Model model, @PathVariable Long codAdministrador) {
        Optional<Administrador> administrador = administradorRepository.findById(codAdministrador);

        if (administrador.isPresent()) {
            model.addAttribute("administrador", administrador.get());
        }
        model.addAttribute("tittle", "Excluir administrador");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Administrador");
        return "administrador/formAdministradores";
    }

    @PostMapping(value = "/delete/{codAdministrador}") // site.com/administrador/delete/1
    public String delete(@PathVariable Long codAdministrador, @ModelAttribute Administrador administrador) {
        administradorRepository.delete(administrador);
        return "redirect:/administrador";
    }

}
