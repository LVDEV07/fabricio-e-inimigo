package br.com.biblioteca.biblioteca.controllers;

import br.com.biblioteca.biblioteca.Enums.Cargo;
import br.com.biblioteca.biblioteca.models.Livro;
import br.com.biblioteca.biblioteca.models.User;
import br.com.biblioteca.biblioteca.repository.LivroRepository;
import br.com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @GetMapping
    public String getLivro(Model model){
        List<Livro> livros = livroRepository.findAll();
        model.addAttribute("livros", livros);
        return "Livro";
    }

    @GetMapping( "/cadastroLivro")
    public String cadastroLivro(Model model){
        model.addAttribute("livro", new Livro());
        return "CadastroLivro";
    }

    @GetMapping("/editarLivro/{id}")
    public String livroEditar(@PathVariable Long id, Model model){

        Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        model.addAttribute("livro", livro);
        return "CadastroLivro";
    }

    @PostMapping("/salvarLivro")
    public String salvar(@ModelAttribute Livro livroCadastro, RedirectAttributes redirectAttributes){
        if (livroCadastro.getNome().trim().isEmpty() || livroCadastro.getAutor().trim().isEmpty() || livroCadastro.getGenero().trim().isEmpty() || livroCadastro.getUrlCapa().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Informe o nome, o autor e o genero do livro");

            return "redirect:/home/cadastroLivro";

        }

        if(livroCadastro.getId() == null) {
            livroRepository.save(livroCadastro);

        }
        else{
            Livro livroQueVeioDoBancoDeDados = livroRepository.findById(livroCadastro.getId()).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

            livroQueVeioDoBancoDeDados.setNome(livroCadastro.getNome());
            livroQueVeioDoBancoDeDados.setAutor(livroCadastro.getAutor());
            livroQueVeioDoBancoDeDados.setGenero(livroCadastro.getGenero());
            livroQueVeioDoBancoDeDados.setUrlCapa(livroCadastro.getUrlCapa());

            livroRepository.save(livroQueVeioDoBancoDeDados);
        }

        redirectAttributes.addFlashAttribute("mensagemSucesso","Livro cadastrado com sucesso! ");
        return "redirect:/home";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        livroRepository.deleteById(id);

        return "redirect:/home";
    }

}


