package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Endereco;
import com.kmv.goforrest.model.Evento;
import com.kmv.goforrest.model.Percurso;
import com.kmv.goforrest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "evento")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private OrganizadorRepository organizadorRepository;
    @Autowired
    private KitRepository kitRepository;
    @Autowired
    private PercursoRepository percursoRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("eventos", eventoRepository.findAll());
        return "evento/listarEventos";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar evento");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar evento");
        model.addAttribute("enderecos", enderecoRepository.findAll());
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());

        return "evento/formEventos";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Evento evento) {

        eventoRepository.save(evento);
        return "redirect:/evento";
    }

    @GetMapping(value = "/edit/{codEvento}")
    public String getEdit(Model model, @PathVariable Long codEvento) {
        Optional<Evento> evento = eventoRepository.findById(codEvento);
        model.addAttribute("tittle", "Editar evento");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar evento");
        model.addAttribute("enderecos", enderecoRepository.findAll());
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("kits", kitRepository.findAll());

        if (evento.isPresent()) {
            model.addAttribute("evento", evento.get());
        }
        return "evento/formEventos";
    }

    @PostMapping(value = "/edit/{codEvento}")
    public String postEdit(@ModelAttribute Evento evento, Model model,
                           @PathVariable Long codEvento) throws Exception {

        eventoRepository.save(evento);
        return "redirect:/evento";
    }

    @GetMapping(value = "/delete/{codEvento}")
    public String getDelete(Model model, @PathVariable Long codEvento) {
        Optional<Evento> evento = eventoRepository.findById(codEvento);

        if (evento.isPresent()) {
            model.addAttribute("evento", evento.get());
        }
        model.addAttribute("tittle", "Excluir evento");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir evento");
        return "evento/formEventos";
    }

    @PostMapping(value = "/delete/{codEvento}")
    public String postDelete(@PathVariable Long codEvento, @ModelAttribute Evento evento) {
        eventoRepository.delete(evento);
        return "redirect:/evento";
    }

    @GetMapping(value = "/percursos/{codEvento}")
    public String getPercursosEvento(Model model, @PathVariable Long codEvento, HttpSession session) {
        Optional<Evento> evento = eventoRepository.findById(codEvento);
        session.setAttribute("tituloEvento", evento.get().getTitulo());
        session.setAttribute("codEvento", codEvento);
        model.addAttribute("percursos", percursoRepository.getPercursosByEvento_CodEvento(codEvento) );

        return "evento/listarPercursosDoEvento";
    }
}



