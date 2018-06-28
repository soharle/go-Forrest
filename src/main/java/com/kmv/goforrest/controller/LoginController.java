package com.kmv.goforrest.controller;


import com.kmv.goforrest.model.Administrador;
import com.kmv.goforrest.model.Atleta;
import com.kmv.goforrest.model.Organizador;
import com.kmv.goforrest.repository.AdministradorRepository;
import com.kmv.goforrest.repository.AtletaRepository;
import com.kmv.goforrest.repository.EventoRepository;
import com.kmv.goforrest.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "")
public class LoginController {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private OrganizadorRepository organizadorRepository;


    @GetMapping
    public String get(Model model, HttpSession session) {
        model.addAttribute("msg", "");
        if(session.getAttribute("tipoLogin") != null){
            return "redirect:/painel";
        }
        if(session.getAttribute("tipoLogin") == "atleta"){
            return "redirect:/painelatleta";
        }
        return "index";

    }


    @PostMapping
    public String postAdd(Model model, HttpSession session, @RequestParam String login, @RequestParam String senha) {
        if(atletaRepository.existsAtletaByEmailAndSenha(login,senha)){
            Atleta atleta = atletaRepository.getAtletaByEmail(login);
            session.setAttribute("tipoLogin", "atleta");
            session.setAttribute("atleta", atleta);
            session.setAttribute("idAtleta", atleta.getCodUsuario());
            return "redirect:painel/painelatleta";
        }
        if(organizadorRepository.existsOrganizadorByEmailAndSenha(login,senha)){
            Organizador organizador = organizadorRepository.getOrganizadorByEmail(login);
            session.setAttribute("tipoLogin", "organizador");
            session.setAttribute("organizador", organizador);
            session.setAttribute("id", organizador.getCodUsuario());
            return "redirect:painel";
        }
        if(administradorRepository.existsByLoginAndSenha(login,senha)){
            Administrador administrador = administradorRepository.getAdministradorByLogin(login);
            session.setAttribute("tipoLogin", "administrador");
            session.setAttribute("administrador",administrador);
            session.setAttribute("id", administrador.getCodAdministrador());
            return "redirect:painel";
        }
        model.addAttribute("msg", "Erro inesperado!");
        return "index";
    }

    @GetMapping(value = "/logout")
    public String getLogout(Model model, HttpSession session) {
        model.addAttribute("msg", "");
        session.invalidate();
        return "redirect:/";
    }


}



