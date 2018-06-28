package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Ranking;
import com.kmv.goforrest.repository.EventoRepository;
import com.kmv.goforrest.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "ranking")
public class RankingController {
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public String rankings(Model model) {
        model.addAttribute("rankings", rankingRepository.findAll());
        return "ranking/listarRankings";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar ranking");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar ranking");
        model.addAttribute("ranking", new Ranking());
        model.addAttribute("eventos", eventoRepository.findAll());


        return "ranking/formRankings";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Ranking ranking) {
        rankingRepository.save(ranking);
        return "redirect:/ranking";
    }

    @GetMapping(value = "/edit/{codRanking}")
    public String getEdit(Model model, @PathVariable Long codRanking) {
        Optional<Ranking> ranking = rankingRepository.findById(codRanking);
        model.addAttribute("tittle", "Editar ranking");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Ranking");
        model.addAttribute("eventos", eventoRepository.findAll());
        if (ranking.isPresent()) {
            model.addAttribute("ranking", ranking.get());
        }
        return "ranking/formRankings";
    }

    @PostMapping(value = "/edit/{codRanking}")
    public String postEdit(@ModelAttribute Ranking ranking, Model model,
                           @PathVariable Long codRanking) throws Exception {

        rankingRepository.save(ranking);
        return "redirect:/ranking";
    }

    @GetMapping(value = "/delete/{codRanking}")
    public String getDelete(Model model, @PathVariable Long codRanking) {
        Optional<Ranking> ranking = rankingRepository.findById(codRanking);

        if (ranking.isPresent()) {
            model.addAttribute("ranking", ranking.get());
        }
        model.addAttribute("tittle", "Excluir ranking");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Ranking");
        model.addAttribute("eventos", eventoRepository.findAll());

        return "ranking/formRankings";
    }

    @PostMapping(value = "/delete/{codRanking}")
    public String postDelete(@PathVariable Long codRanking, @ModelAttribute Ranking ranking) {
        rankingRepository.delete(ranking);
        return "redirect:/ranking";
    }
}

