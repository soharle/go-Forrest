package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Usuario;
import com.kmv.goforrest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuario/usuarios";
    }

    @GetMapping(value = "/add") // site.com/usuario/add
    public String displayUsuarioForm() {
        return "usuario/add";
    }

    @PostMapping(value = "/add")
    public String processUsuarioForm(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:";
    }

    @GetMapping(value = "/edit/{id}") // site.com/usuario/edit/1/
    public String usuarioEdit(Model model, @PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        model.addAttribute("title", "Editar usuario");
        model.addAttribute("usuario", usuario);
        return "usuario/edit";
    }

    @PostMapping(value = "/edit/{id}") // site.com/usuario/edit/1/
    public String edit(@RequestBody Usuario usuario, Model model,
                       @PathVariable Long id) throws Exception {
        if (id.equals(usuario.getCodUsuario())) {
            usuarioRepository.save(usuario);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:";
    }

    @GetMapping(value = "/delete/{id}") // site.com/usuario/delete/1
    public String usuarioDelete(Model model, @PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
        }
        model.addAttribute("tittle", "Excluir usuario");
        return "usuario/delete";
    }

    @PostMapping(value = "/delete/{id}") // site.com/usuario/delete/1
    public String delete(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuarioRepository.delete(usuario);
        return "redirect:";
    }
}

