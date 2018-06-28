package com.kmv.goforrest.controller;

import com.kmv.goforrest.repository.AtletaRepository;
import com.kmv.goforrest.repository.EventoRepository;
import com.kmv.goforrest.repository.InscricaoRepository;
import com.kmv.goforrest.repository.OrganizadorRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/relatorio")
public class RelatorioController {

    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private OrganizadorRepository organizadorRepository;
    @Autowired
    private EventoRepository eventoRepository;

    public JasperReport gerarJasper(String relatorio, String nome) throws JRException {
        InputStream relatorioStream = getClass().getResourceAsStream(relatorio);

        if (relatorioStream == null)
            return null;

        JasperReport jasperReport = JasperCompileManager.compileReport(relatorioStream);
        JRSaver.saveObject(jasperReport, nome + ".jasper");
        return jasperReport;
    }

    public Connection abrirConexao() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sccr2", "root", "");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JasperPrint exibirRelatorio(JasperReport relatorio, HashMap parametros, Connection conexao) throws JRException {
        // parametros.put("PAR_codCurso", Integer.parseInt(request.getParameter("txtCodCurso")));

        return JasperFillManager.fillReport(relatorio, parametros, conexao);
    }

    @GetMapping(value = "")
    public String get(Model model){
        model.addAttribute("atletas", atletaRepository.findAll());
        model.addAttribute("organizadores", organizadorRepository.findAll());
        model.addAttribute("eventos", eventoRepository.findAll());
                return "relatorio/pesquisar";
    }




    @GetMapping(value = "{report}")
    public void gerarRelatorio(HttpServletRequest request, HttpServletResponse response, @PathVariable String report) throws JRException, IOException, SQLException {
        String nomeRelatorio = report;

        System.out.printf(report);
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();
            this.exibirRelatorio(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();

    }

    @GetMapping(value = "/report_inscricao_atleta")
    public void gerarRelatorioInscricaoAtleta(HttpServletRequest request, HttpServletResponse response, @RequestParam Long codAtleta) throws JRException, IOException, SQLException {
        String nomeRelatorio = "report_inscricao_atleta";
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("codAtleta", codAtleta);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();
    }

    @GetMapping(value = "/report_evento_organizador")
    public void gerarRelatorioEventoOrganizador(HttpServletRequest request, HttpServletResponse response, @RequestParam Long codOrganizador) throws JRException, IOException, SQLException {
        String nomeRelatorio = "report_evento_organizador";
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("codOrganizador", codOrganizador);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();
    }



    @GetMapping(value = "/report_lote_evento")
    public void gerarRelatorioLoteEvento(HttpServletRequest request, HttpServletResponse response, @RequestParam Long codEvento) throws JRException, IOException, SQLException {
        String nomeRelatorio = "report_lote_evento";
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("codEvento", codEvento);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();
    }


    @GetMapping(value = "/report_pagamento_tipo")
    public void gerarRelatorioPagamentoTipo(HttpServletRequest request, HttpServletResponse response, @RequestParam String pagamentoPT) throws JRException, IOException, SQLException {
        String nomeRelatorio = "report_pagamento_tipo";
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("tipoPagamento", pagamentoPT);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();
    }


    @GetMapping(value = "/report_pagamento_status")
    public void gerarRelatorioPagamentoStatus(HttpServletRequest request, HttpServletResponse response, @RequestParam String pagamentoPS) throws JRException, IOException, SQLException {
        String nomeRelatorio = "report_pagamento_status";
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("quitado", pagamentoPS);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();
    }




}