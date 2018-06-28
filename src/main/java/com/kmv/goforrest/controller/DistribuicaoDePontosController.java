package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Endereco;
import com.kmv.goforrest.model.DistribuicaoDePontos;
import com.kmv.goforrest.repository.DistribuicaoDePontosRepository;

import com.kmv.goforrest.repository.RankingRepository;
import com.kmv.goforrest.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "distribuicaodepontos")
public class DistribuicaoDePontosController {
    @Autowired
    private DistribuicaoDePontosRepository distribuicaoDePontosRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private RankingRepository rankingRepository;


    @GetMapping(value = "")
    public String get(Model model) {
        model.addAttribute("distribuicoesDePontos", distribuicaoDePontosRepository.findAll());
        return "distribuicaoDePontos/listarDistribuicoesDePontos";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar distribuicaoDePontos");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar distribuicaoDePontos");
        model.addAttribute("enderecos", enderecoRepository.findAll());
        model.addAttribute("rankings", rankingRepository.findAll());


        return "distribuicaoDePontos/formDistribuicoesDePontos";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute DistribuicaoDePontos distribuicaoDePontos) {

        distribuicaoDePontosRepository.save(distribuicaoDePontos);
        return "redirect:/distribuicaodepontos";
    }

    @GetMapping(value = "/edit/{codDistribuicaoDePontos}")
    public String getEdit(Model model, @PathVariable Long codDistribuicaoDePontos) {
        Optional<DistribuicaoDePontos> distribuicaoDePontos = distribuicaoDePontosRepository.findById(codDistribuicaoDePontos);
        model.addAttribute("tittle", "Editar distribuicaoDePontos");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar distribuicaoDePontos");
        model.addAttribute("distribuicoesDePontos", distribuicaoDePontosRepository.findAll());
        model.addAttribute("rankings", rankingRepository.findAll());


        if (distribuicaoDePontos.isPresent()) {
            model.addAttribute("distribuicaoDePontos", distribuicaoDePontos.get());
        }
        return "distribuicaoDePontos/formDistribuicoesDePontos";
    }

    @PostMapping(value = "/edit/{codDistribuicaoDePontos}")
    public String postEdit(@ModelAttribute DistribuicaoDePontos distribuicaoDePontos, Model model,
                           @PathVariable Long codDistribuicaoDePontos) throws Exception {

        distribuicaoDePontosRepository.save(distribuicaoDePontos);
        return "redirect:/distribuicaodepontos";
    }

    @GetMapping(value = "/delete/{codDistribuicaoDePontos}")
    public String getDelete(Model model, @PathVariable Long codDistribuicaoDePontos) {
        Optional<DistribuicaoDePontos> distribuicaoDePontos = distribuicaoDePontosRepository.findById(codDistribuicaoDePontos);

        if (distribuicaoDePontos.isPresent()) {
            model.addAttribute("distribuicaoDePontos", distribuicaoDePontos.get());
        }
        model.addAttribute("tittle", "Excluir distribuicaoDePontos");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir distribuicaoDePontos");
        return "distribuicaoDePontos/formDistribuicoesDePontos";
    }

    @PostMapping(value = "/delete/{codDistribuicaoDePontos}")
    public String postDelete(@PathVariable Long codDistribuicaoDePontos, @ModelAttribute DistribuicaoDePontos distribuicaoDePontos) {
        distribuicaoDePontosRepository.delete(distribuicaoDePontos);
        return "redirect:/distribuicaodepontos";
    }
}



