package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Organizador;
import com.kmv.goforrest.repository.EnderecoRepository;
import com.kmv.goforrest.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "organizador")
public class OrganizadorController {
    @Autowired
    private OrganizadorRepository organizadorRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("organizadores", organizadorRepository.findAll());
        return "organizador/listarOrganizadores";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar organizador");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("enderecos", enderecoRepository.findAll());
        model.addAttribute("botaoOperacao", "Cadastrar organizador");
        return "organizador/formOrganizadores";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Organizador organizador) {
        organizadorRepository.save(organizador);
        return "redirect:/organizador";
    }

    @GetMapping(value = "/edit/{codUsuario}")
    public String getEdit(Model model, @PathVariable Long codUsuario) {
        Optional<Organizador> organizador = organizadorRepository.findById(codUsuario);
        model.addAttribute("tittle", "Editar organizador");
        model.addAttribute("operacao", "editar");
        model.addAttribute("enderecos", enderecoRepository.findAll());
        model.addAttribute("botaoOperacao", "Editar Organizador");
        if (organizador.isPresent()) {
            model.addAttribute("organizador", organizador.get());
        }
        return "organizador/formOrganizadores";
    }

    @PostMapping(value = "/edit/{codUsuario}")
    public String postEdit(@ModelAttribute Organizador organizador, Model model,
                           @PathVariable Long codUsuario) throws Exception {

        organizadorRepository.save(organizador);
        return "redirect:/organizador";
    }

    @GetMapping(value = "/delete/{codUsuario}")
    public String getDelete(Model model, @PathVariable Long codUsuario) {
        Optional<Organizador> organizador = organizadorRepository.findById(codUsuario);

        if (organizador.isPresent()) {
            model.addAttribute("organizador", organizador.get());
        }
        model.addAttribute("tittle", "Excluir organizador");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("enderecos", enderecoRepository.findAll());
        model.addAttribute("botaoOperacao", "Excluir Organizador");
        return "organizador/formOrganizadores";
    }

    @PostMapping(value = "/delete/{codUsuario}")
    public String postDelete(@PathVariable Long codUsuario, @ModelAttribute Organizador organizador) {
        organizadorRepository.delete(organizador);
        return "redirect:/organizador";
    }
}



