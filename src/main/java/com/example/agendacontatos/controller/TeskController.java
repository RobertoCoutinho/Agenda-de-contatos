package com.example.agendacontatos.controller;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.agendacontatos.model.Contato;
import com.example.agendacontatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("view/contatos")
public class TeskController {
    @Autowired 
    ContatoRepository repository;

    // ContatoDB contatoDB = new ContatoDB();
    @GetMapping("/")
    public String home(){
        return "redirect:/listar";
    }
    @GetMapping("/cadastrar")
    public String telaHome(){
        return "cadastroContato";
    }
    // @PostMapping("/cadastrar")
    // public String create(@RequestBody Contato contato){
    //     Long id = listaContatos.size() + 1L;
    //     repository.save(new Contato(id, contato.getNome(), contato.getNum_tel()));
    //     //contatoDB.salvarContato(new Contato(id, contato.getNome(), contato.getNum_tel()));
    //     //listaContatos.add(new Contato(id, contato.getNome(), contato.getNum_tel()));
    //     return "redirect:/cadastrar";
    // }

    // @GetMapping("/listar")
    // public ModelAndView listar(){
    //     ModelAndView mv = new ModelAndView("listarContatos");
    //     List<Contato> todos = new ArrayList<>();
    //     todos = contatoDB.visualizarContato();
    //     mv.addObject("listaContatos",todos);
    //     return mv;
    // }

    @PostMapping("/cadastrar")
    public String create(Contato contato){
        try{
            repository.save(contato);
        }
        catch(Exception e){
            System.out.println("ERRO");
            return "redirect:/cadastrar";
        }
        return "redirect:/";
    }

    @GetMapping("/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("listarContatos");
        List<Contato> todos = new ArrayList<>();
        todos = repository.findAll();
        mv.addObject("listaContatos",todos);
        return mv;
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable(name = "id") Long id){
        repository.deleteById(id);
        return "redirect:/";
    }
    Long idx;
    @GetMapping("/atualizarForm/{id}")
    public String povoarFormAtt(@PathVariable(name = "id") Long id, Model model){
        //ModelAndView mv = new ModelAndView("atualizarContato");
        Optional<Contato> contatoOp = repository.findById(id);
        Contato contato = contatoOp.get();
        model.addAttribute("atualizarContato", contato);
        //mv.addObject("atualizarContato",contato);
        idx = id;
        return "atualizarContato";
    }

    @PostMapping("/atualizar")
    public String atualizar(Contato contato){
        contato.setId(idx);
        repository.save(contato);
        return "redirect:/";
    }
}
