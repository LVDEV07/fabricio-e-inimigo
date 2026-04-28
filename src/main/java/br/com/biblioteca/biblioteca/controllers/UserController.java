package br.com.biblioteca.biblioteca.controllers;

import br.com.biblioteca.biblioteca.Enums.Cargo;
import br.com.biblioteca.biblioteca.models.User;
import br.com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")

public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String getUser(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "index";
    }

    @GetMapping( "/cadastro")
    public String cadastroUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("cargos", Cargo.values());
        return "Cadastro";
    }

    @GetMapping("/editar/{id}")
    public String userEditar(@PathVariable Long id, Model model){

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        model.addAttribute("user", user);
        model.addAttribute("cargos", Cargo.values());
        return "Cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute User userCadastro, RedirectAttributes redirectAttributes){
        if (userCadastro.getEmail().trim().isEmpty() || userCadastro.getNome().trim().isEmpty() || userCadastro.getSenha().trim().isEmpty() || userCadastro.getCargo() == null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Informe o e-mail e o nome do usuario");
            return "redirect:/cadastro";
        }

        Optional<User> userComMesmoEmail = userRepository.findByEmail(userCadastro.getEmail());

        if (userComMesmoEmail.isPresent()){
            redirectAttributes.addFlashAttribute("mensagemEmail", "E-mail já está cadastrado");
            return "redirect:/cadastro";
        }

        if(userCadastro.getId() == null) {
            userRepository.save(userCadastro);


        }
        else{
            User userQueVeioDoBancoDeDados = userRepository.findById(userCadastro.getId()).orElseThrow(() -> new RuntimeException("usuario não encontrado"));

            userQueVeioDoBancoDeDados.setNome(userCadastro.getNome());
            userQueVeioDoBancoDeDados.setEmail(userCadastro.getEmail());
            userQueVeioDoBancoDeDados.setSenha(userCadastro.getSenha());

            userRepository.save(userQueVeioDoBancoDeDados);
        }

        redirectAttributes.addFlashAttribute("mensagemSucesso","User cadastrado com sucesso! ");
        return "redirect:/";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        userRepository.deleteById(id);

        return "redirect:/";
    }

        }
