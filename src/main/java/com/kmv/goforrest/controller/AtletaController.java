package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Atleta;
import com.kmv.goforrest.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "atleta")
public class AtletaController {
    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("atletas", atletaRepository.findAll());
        return "atleta/listarAtletas";
    }

    @GetMapping(value = "edit/atleta")
    public String getEditAtleta(Model model, HttpSession session) {

        Long atletaId = (Long) session.getAttribute("idAtleta");
        Optional<Atleta> atleta = atletaRepository.findById(atletaId);
        if (atleta.isPresent()) {
            model.addAttribute("tittle", "Editar cadastro");
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar cadastro");
            model.addAttribute("atleta", atleta.get());
            return "atleta/formAtletas";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar atleta");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar atleta");
        return "atleta/formAtletas";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Atleta atleta) {
        atletaRepository.save(atleta);
        return "redirect:/atleta";
    }

    @GetMapping(value = "/edit/{codUsuario}")
    public String getEdit(Model model, @PathVariable Long codUsuario) {
        Optional<Atleta> atleta = atletaRepository.findById(codUsuario);
        model.addAttribute("tittle", "Editar atleta");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar atleta");
        if (atleta.isPresent()) {
            model.addAttribute("atleta", atleta.get());
        }
        return "atleta/formAtletas";
    }

    @PostMapping(value = "/edit/{codUsuario}")
    public String postEdit(@ModelAttribute Atleta atleta, Model model,
                           @PathVariable Long codUsuario) throws Exception {

        atletaRepository.save(atleta);
        return "redirect:/atleta";
    }

    @GetMapping(value = "/delete/{codUsuario}")
    public String getDelete(Model model, @PathVariable Long codUsuario) {
        Optional<Atleta> atleta = atletaRepository.findById(codUsuario);

        if (atleta.isPresent()) {
            model.addAttribute("atleta", atleta.get());
        }
        model.addAttribute("tittle", "Excluir atleta");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir atleta");
        return "atleta/formAtletas";
    }

    @PostMapping(value = "/delete/{codUsuario}")
    public String postDelete(@PathVariable Long codUsuario, @ModelAttribute Atleta atleta) {
        atletaRepository.delete(atleta);
        return "redirect:/atleta";
    }
    @GetMapping(value = "/cadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("tittle", "Cadastrar atleta");
        model.addAttribute("operacao", "cadastrar");
        model.addAttribute("botaoOperacao", "Cadastrar atleta");
        return "atleta/formCadastroAtletas";
    }

    @PostMapping(value = "/cadastrar")
    public String postCadastrar(@ModelAttribute Atleta atleta) {
        atletaRepository.save(atleta);
        return "redirect:/";
    }
}



