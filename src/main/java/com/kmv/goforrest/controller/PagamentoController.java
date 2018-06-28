package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.Pagamento;
import com.kmv.goforrest.repository.InscricaoRepository;
import com.kmv.goforrest.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoRepository pagamentoRepository;


    @GetMapping
    public String get(Model model) {
        model.addAttribute("pagamentos", pagamentoRepository.findAll());
        return "pagamento/listarPagamento";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar pagamento");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar pagamento");
        return "pagamento/formPagamento";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Pagamento chip) {
        pagamentoRepository.save(chip);
        return "redirect:/pagamento";
    }

    @GetMapping(value = "/edit/{codPagamento}")
    public String getEdit(Model model, @PathVariable Long codPagamento) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(codPagamento);
        model.addAttribute("tittle", "Editar pagamento");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar Pagamento");
        if (pagamento.isPresent()) {
            model.addAttribute("pagamento", pagamento.get());
        }
        return "pagamento/formPagamento";
    }

    @PostMapping(value = "/edit/{codPagamento}")
    public String postEdit(@ModelAttribute Pagamento pagamento, Model model,
                           @PathVariable Long codPagamento) throws Exception {

        pagamentoRepository.save(pagamento);
        return "redirect:/pagamento";
    }

    @GetMapping(value = "/delete/{codPagamento}")
    public String getDelete(Model model, @PathVariable Long codPagamento) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(codPagamento);

        if (pagamento.isPresent()) {
            model.addAttribute("pagamento", pagamento.get());
        }
        model.addAttribute("tittle", "Excluir pagamento");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir Pagamento");
        return "pagamento/formPagamento";
    }

    @PostMapping(value = "/delete/{codPagamento}")
    public String postDelete(@PathVariable Long codPagamento, @ModelAttribute Pagamento pagamento) {
        pagamentoRepository.delete(pagamento);
        return "redirect:/pagamento";
    }

    @GetMapping(value = "/confirmarpagamento")
    public String getConfirmar(Model model) {

        model.addAttribute("tittle", "Confirmar Pagamento");
        model.addAttribute("operacao", "confirmar");
        model.addAttribute("botaoOperacao", "Confirmar Pagamento");

        return "pagamento/confirmarPagamento";
    }

    @PostMapping(value = "/confirmarpagamento")
    public String postConfirmar(Model model, @RequestParam Long codPagamento){
        Optional<Pagamento> pagamento = pagamentoRepository.findById(codPagamento);

        if (pagamento.isPresent()) {
            Pagamento pagamento1 = pagamento.get().setStatus(true);
            pagamentoRepository.save(pagamento1);
        }

        return "redirect:/painel";
    }
}


