package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Lote;
import com.kmv.goforrest.repository.EventoRepository;
import com.kmv.goforrest.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "lote")
public class LoteController {
    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public String lotes(Model model) {
        model.addAttribute("lotes", loteRepository.findAll());
        return "lote/listarLotes";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar lote");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar lote");
        model.addAttribute("eventos", eventoRepository.findAll());
        return "lote/formLotes";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Lote lote) {
        loteRepository.save(lote);
        return "redirect:/lote";
    }

    @GetMapping(value = "/edit/{codLote}")
    public String getEdit(Model model, @PathVariable Long codLote) {
        Optional<Lote> lote = loteRepository.findById(codLote);
        model.addAttribute("tittle", "Editar lote");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Lote");
        model.addAttribute("eventos", eventoRepository.findAll());
        if (lote.isPresent()) {
            model.addAttribute("lote", lote.get());
        }
        return "lote/formLotes";
    }

    @PostMapping(value = "/edit/{codLote}")
    public String postEdit(@ModelAttribute Lote lote, Model model,
                           @PathVariable Long codLote) throws Exception {

        loteRepository.save(lote);
        return "redirect:/lote";
    }

    @GetMapping(value = "/delete/{codLote}")
    public String getDelete(Model model, @PathVariable Long codLote) {
        Optional<Lote> lote = loteRepository.findById(codLote);

        if (lote.isPresent()) {
            model.addAttribute("lote", lote.get());
        }
        model.addAttribute("tittle", "Excluir lote");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Lote");
        model.addAttribute("eventos", eventoRepository.findAll());
        return "lote/formLotes";
    }

    @PostMapping(value = "/delete/{codLote}")
    public String postDelete(@PathVariable Long codLote, @ModelAttribute Lote lote) {
        loteRepository.delete(lote);
        return "redirect:/lote";
    }
}

