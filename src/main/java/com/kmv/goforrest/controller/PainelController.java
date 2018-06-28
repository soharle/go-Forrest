package com.kmv.goforrest.controller;


import com.kmv.goforrest.model.Atleta;
import com.kmv.goforrest.repository.AtletaRepository;
import com.kmv.goforrest.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequestMapping(value = "painel")
public class PainelController {

    @Autowired
    private EventoRepository eventoRepository;
@Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public String get(Model model, @ModelAttribute String teste, HttpSession session) {

        if (session.getAttribute("tipoLogin") == null) {
           return "redirect:/";
        }
        if(session.getAttribute("tipoLogin") == "atleta"){
            //return "painel/painel";
             return "redirect:/painel/painelatleta";
        }
        return "painel/painel";
    }

    @GetMapping(value = "/painelatleta")
    public String getPainelAtleta(Model model, @ModelAttribute String teste, HttpSession session) {
        session.removeAttribute("codEvento");
        session.removeAttribute("codPercurso");
        Optional<Atleta> atleta = atletaRepository.findById((Long) session.getAttribute("idAtleta"));
        model.addAttribute("inscricoes", atleta.get().getListaInscricoes());
        model.addAttribute("eventos", eventoRepository.findAll());
        return "painel/painelatleta";
    }


}