package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Atleta;
import com.kmv.goforrest.model.Endereco;
import com.kmv.goforrest.repository.AtletaRepository;
import com.kmv.goforrest.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("enderecos", enderecoRepository.findAll());
        return "endereco/listarEnderecos";
    }

    @GetMapping(value = "edit/atleta")
    public String getEditAtleta(Model model, HttpSession session) {

        Long atletaId = (Long) session.getAttribute("idAtleta");
        Optional<Atleta> atleta = atletaRepository.findById(atletaId);
        if (atleta.isPresent()) {
            model.addAttribute("tittle", "Editar endereco");
            model.addAttribute("operacao", "editar");
            model.addAttribute("botaoOperacao", "Editar Endereco");
            model.addAttribute("endereco", atleta.get().getEndereco());
            return "endereco/formEnderecos";

        } else {
            return "redirect:/";
        }

    }
    @PostMapping(value = "edit/atleta")
    public String getEditAtleta(@ModelAttribute Endereco endereco, HttpSession session){
        Long atletaId = (Long) session.getAttribute("idAtleta");
        Optional<Atleta> atleta = atletaRepository.findById(atletaId);
        enderecoRepository.save(endereco);
        atleta.get().setEndereco(endereco);
        atletaRepository.save(atleta.get());

        return "redirect:painel/painelatleta";

    }


    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar endereco");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar endereco");
        return "endereco/formEnderecos";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Endereco endereco) {
        enderecoRepository.save(endereco);
        return "redirect:/endereco";
    }

    @GetMapping(value = "/edit/{codEndereco}")
    public String getEdit(Model model, @PathVariable Long codEndereco) {
        Optional<Endereco> endereco = enderecoRepository.findById(codEndereco);
        model.addAttribute("tittle", "Editar endereco");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Endereco");
        if (endereco.isPresent()) {
            model.addAttribute("endereco", endereco.get());
        }
        return "endereco/formEnderecos";
    }

    @PostMapping(value = "/edit/{codEndereco}")
    public String postEdit(@ModelAttribute Endereco endereco, Model model,
                           @PathVariable Long codEndereco) throws Exception {

        enderecoRepository.save(endereco);
        return "redirect:/endereco";
    }

    @GetMapping(value = "/delete/{codEndereco}")
    public String getDelete(Model model, @PathVariable Long codEndereco) {
        Optional<Endereco> endereco = enderecoRepository.findById(codEndereco);

        if (endereco.isPresent()) {
            model.addAttribute("endereco", endereco.get());
        }
        model.addAttribute("tittle", "Excluir endereco");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Endereco");
        return "endereco/formEnderecos";
    }

    @PostMapping(value = "/delete/{codEndereco}")
    public String postDelete(@PathVariable Long codEndereco, @ModelAttribute Endereco endereco) {
        enderecoRepository.delete(endereco);
        return "redirect:/endereco";
    }
}



