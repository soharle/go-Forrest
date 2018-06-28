package com.kmv.goforrest.controller;

import com.kmv.goforrest.model.*;
import com.kmv.goforrest.repository.ChipRepository;
import com.kmv.goforrest.repository.InscricaoRepository;
import com.kmv.goforrest.repository.PagamentoRepository;
import com.kmv.goforrest.repository.PercursoRepository;
import com.kmv.goforrest.repository.AtletaRepository;
import com.kmv.goforrest.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "inscricao")
public class InscricaoController {
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private ChipRepository chipRepository;
    @Autowired
    private PercursoRepository percursoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private KitRepository kitRepository;
    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("inscricoes", inscricaoRepository.findAll());
        return "inscricao/listarInscricoes";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("tittle", "Adicionar inscricao");
        model.addAttribute("operacao", "adicionar");
        model.addAttribute("botaoOperacao", "Cadastrar inscricao");
        model.addAttribute("percursos", percursoRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());

        //chips
        Iterable<Chip> chips = chipRepository.findAll();
        List<Chip> chipsCerto = new ArrayList<>();
        for (Chip chip : chips){
            if(chip.getInscricao() == null){
                chipsCerto.add(chip);
            }
        }
        model.addAttribute("chips", chipsCerto);

        //pagamentos
        Iterable<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<Pagamento> pagamentosCerto = new ArrayList<>();
        for(Pagamento pagamento : pagamentos){
            if(pagamento.getInscricao() == null){
                pagamentosCerto.add(pagamento);
            }
        }
        model.addAttribute("pagamentos", pagamentosCerto);

        //kits
        Iterable<Kit> kits = kitRepository.findAll();
        List<Kit> kitsCerto = new ArrayList<>();
        for(Kit kit : kits){
            if(kit.getInscricao() == null){
                kitsCerto.add(kit);
            }
        }
        model.addAttribute("kits", kitsCerto);

        return "inscricao/formInscricoes";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Inscricao inscricao) {

        inscricaoRepository.save(inscricao);
        return "redirect:/inscricao";
    }

    @GetMapping(value = "/edit/atleta")
    public String getEditAtleta(Model model, HttpSession session) {
        Long atletaId = (Long) session.getAttribute("idAtleta");
        Optional<Atleta> atleta = atletaRepository.findById(atletaId);
        if(atleta.isPresent()){
            model.addAttribute("inscricoes", atleta.get().getListaInscricoes());
            return "inscricao/listarInscricoes";

        }else{
            return "redirect:/";
        }
    }


    @GetMapping(value = "/edit/{codInscricao}")
    public String getEdit(Model model, @PathVariable Long codInscricao) {
        Optional<Inscricao> inscricao = inscricaoRepository.findById(codInscricao);
        model.addAttribute("tittle", "Editar inscricao");
        model.addAttribute("operacao", "editar");
        model.addAttribute("botaoOperacao", "Editar inscricao");
        model.addAttribute("percursos", percursoRepository.findAll());
        model.addAttribute("atletas", atletaRepository.findAll());



        //chips
        Iterable<Chip> chips = chipRepository.findAll();
        List<Chip> chipsCerto = new ArrayList<>();
        for (Chip chip : chips){
            if(chip.getInscricao() == null){
                chipsCerto.add(chip);
            }
        }
        model.addAttribute("chips", chipsCerto);

        //pagamentos
        Iterable<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<Pagamento> pagamentosCerto = new ArrayList<>();
        for(Pagamento pagamento : pagamentos){
            if(pagamento.getInscricao() == null){
                pagamentosCerto.add(pagamento);
            }
        }
        model.addAttribute("pagamentos", pagamentosCerto);

        //kits
        Iterable<Kit> kits = kitRepository.findAll();
        List<Kit> kitsCerto = new ArrayList<>();
        for(Kit kit : kits){
            if(kit.getInscricao() == null){
                kitsCerto.add(kit);
            }
        }
        model.addAttribute("kits", kitsCerto);

        if (inscricao.isPresent()) {
            model.addAttribute("inscricao", inscricao.get());
        }
        return "inscricao/formInscricoes";
    }

    @PostMapping(value = "/edit/{codInscricao}")
    public String postEdit(@ModelAttribute Inscricao inscricao, Model model,
                           @PathVariable Long codInscricao) throws Exception {

        inscricaoRepository.save(inscricao);
        return "redirect:/inscricao";
    }

    @GetMapping(value = "/delete/{codInscricao}")
    public String getDelete(Model model, @PathVariable Long codInscricao) {
        Optional<Inscricao> inscricao = inscricaoRepository.findById(codInscricao);

        if (inscricao.isPresent()) {
            model.addAttribute("inscricao", inscricao.get());
        }
        model.addAttribute("tittle", "Excluir inscricao");
        model.addAttribute("operacao", "excluir");
        model.addAttribute("botaoOperacao", "Excluir inscricao");
        return "inscricao/formInscricoes";
    }

    @PostMapping(value = "/delete/{codInscricao}")
    public String postDelete(@PathVariable Long codInscricao, @ModelAttribute Inscricao inscricao) {
        inscricaoRepository.delete(inscricao);
        return "redirect:/inscricao";
    }

    @GetMapping(value = "/retirar")
    public String getRetirar(Model model) {

        model.addAttribute("tittle", "Retirar Kit");
        model.addAttribute("operacao", "retirar");
        model.addAttribute("botaoOperacao", "Confirmar Retirada");

        return "inscricao/confirmarRetirada";
    }

    @PostMapping(value = "/retirar")
    public String postRetirar(Model model, @RequestParam Long codInscricao){
        System.out.println(codInscricao);
        Optional<Inscricao> inscricao = inscricaoRepository.findById(codInscricao);

        if (inscricao.isPresent()) {
            Inscricao inscricao1 = inscricao.get().setStatusRetirada(true);
            inscricaoRepository.save(inscricao1);
        }

        return "redirect:/inscricao";
    }

    @GetMapping(value = "/inscrever/{codPercurso}")
    public String getInscreverAtleta(Model model, @PathVariable Long codPercurso, HttpSession session) {
        session.setAttribute("codPercurso", codPercurso);
        Optional<Atleta> atleta = atletaRepository.findById((Long) session.getAttribute("idAtleta"));
        Optional<Percurso> percurso = percursoRepository.findById(codPercurso);
        session.setAttribute("codPercurso", codPercurso);
        session.setAttribute("nomePercurso", percurso.get().getNome());
        Inscricao inscricao = new Inscricao();
        inscricao.setAtleta(atleta.get());
        inscricao.setPercurso(percurso.get());
        int numeroPeito = (int) (Math.random() * (percurso.get().getEvento().getMaxParticipantes()+1));
        model.addAttribute("numeroPeito", numeroPeito);
        model.addAttribute("inscricao", inscricao);

        //chips
        Iterable<Chip> chips = chipRepository.findAll();
        List<Chip> chipsCerto = new ArrayList<>();
        for (Chip chip : chips){
            if(chip.getInscricao() == null){
                chipsCerto.add(chip);
            }
        }
        model.addAttribute("chips", chipsCerto);


        //kits
        List<Kit> kitsCerto = new ArrayList<>();
        for(Kit kit :percurso.get().getEvento().getListaKits()){
            if(kit.getInscricao() == null){
                kitsCerto.add(kit);
            }
        }

        model.addAttribute("kits", kitsCerto);
        model.addAttribute("tittle", "Fazer Inscricao");

        model.addAttribute("operacao", "inscrever");
        model.addAttribute("botaoOperacao", "Concluir Inscrição");
        return "inscricao/inscricaoDoAtleta";
    }

    @PostMapping(value = "/inscrever/{codPercurso}")
    public String postInscreverAtleta(Model model, @ModelAttribute Inscricao inscricao, @PathVariable Long codPercurso, HttpSession session) {
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatador.format(data);
        inscricao.setDataInscricao(dataFormatada);
        Optional<Atleta> atleta = atletaRepository.findById((Long) session.getAttribute("idAtleta"));
        inscricao.setAtleta(atleta.get());
        Optional<Percurso> percurso = percursoRepository.findById((Long) session.getAttribute("codPercurso"));
        inscricao.setPercurso(percurso.get());
        inscricaoRepository.save(inscricao);
        session.setAttribute("nomeKit", inscricao.getKit().getDescricao());
        session.setAttribute("codInscricao", inscricao.getCodInscricao());

        return "redirect:/inscricao/inscricaoPagamento";

    }

    @GetMapping(value = "inscricaoPagamento")
    public String getInscricaoPagamento(Model model, HttpSession session) {

        Long codBarras = (long) (1000000000000l + Math.random() * 8999999999999l);
        Pagamento pagamento = new Pagamento();
        pagamento.setCodigoBarra(codBarras.toString());
        Optional<Inscricao> inscricao = inscricaoRepository.findById((Long) session.getAttribute("codInscricao"));
        pagamento.setValorTotal(inscricao.get().getKit().getValorKit() * 3);

        model.addAttribute("pagamento", pagamento);
        model.addAttribute("tittle", "Conferir Pagamento");
        model.addAttribute("botaoOperacao", "Confirmar Pagamento");

        return "inscricao/inscricaoPagamento";
    }

    @PostMapping(value = "inscricaoPagamento")
    public String setInscricaoPagamento(@ModelAttribute Pagamento pagamento, HttpSession session) throws Exception {
        Optional<Inscricao> inscricao = inscricaoRepository.findById((Long) session.getAttribute("codInscricao"));
        pagamento.setInscricao(inscricaoRepository.findById((Long) session.getAttribute("codInscricao")).get());
        pagamento.setValorTotal(inscricao.get().getKit().getValorKit() * 3);
        inscricaoRepository.findById((Long) session.getAttribute("codInscricao")).get().setPrecoTotal(pagamento.getValorTotal());
        pagamentoRepository.save(pagamento);
        inscricao.get().setPagamento(pagamento);
        inscricaoRepository.save(inscricao.get());
        return "redirect:/painel/painelatleta";
    }
}



