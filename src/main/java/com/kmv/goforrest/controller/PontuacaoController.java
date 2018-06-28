package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Pontuacao;
import com.kmv.goforrest.repository.AtletaRepository;
import com.kmv.goforrest.repository.EventoRepository;
import com.kmv.goforrest.repository.PontuacaoRepository;
import com.kmv.goforrest.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "pontuacao")
public class PontuacaoController {
    @Autowired
    private PontuacaoRepository pontuacaoRepository;
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public String pontuacoes(Model model) {
        model.addAttribute("pontuacoes", pontuacaoRepository.findAll());
        return "pontuacao/listarPontuacoes";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar pontuacao");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar pontuacao");
        model.addAttribute("rankings", rankingRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        return "pontuacao/formPontuacoes";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Pontuacao pontuacao) {
        pontuacaoRepository.save(pontuacao);
        return "redirect:/pontuacao";
    }

    @GetMapping(value = "/edit/{codPontuacao}")
    public String getEdit(Model model, @PathVariable Long codPontuacao) {
        Optional<Pontuacao> pontuacao = pontuacaoRepository.findById(codPontuacao);
        model.addAttribute("tittle", "Editar pontuacao");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Pontuacao");
        model.addAttribute("rankings", rankingRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        if (pontuacao.isPresent()) {
            model.addAttribute("pontuacao", pontuacao.get());
        }
        return "pontuacao/formPontuacoes";
    }

    @PostMapping(value = "/edit/{codPontuacao}")
    public String postEdit(@ModelAttribute Pontuacao pontuacao, Model model,
                           @PathVariable Long codPontuacao) throws Exception {

        pontuacaoRepository.save(pontuacao);
        return "redirect:/pontuacao";
    }

    @GetMapping(value = "/delete/{codPontuacao}")
    public String getDelete(Model model, @PathVariable Long codPontuacao) {
        Optional<Pontuacao> pontuacao = pontuacaoRepository.findById(codPontuacao);

        if (pontuacao.isPresent()) {
            model.addAttribute("pontuacao", pontuacao.get());
        }
        model.addAttribute("tittle", "Excluir pontuacao");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Pontuacao");
        model.addAttribute("rankings", rankingRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());
        return "pontuacao/formPontuacoes";
    }

    @PostMapping(value = "/delete/{codPontuacao}")
    public String postDelete(@PathVariable Long codPontuacao, @ModelAttribute Pontuacao pontuacao) {
        pontuacaoRepository.delete(pontuacao);
        return "redirect:/pontuacao";
    }
}

