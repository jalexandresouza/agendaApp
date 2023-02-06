package com.agenda.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.app.model.TipoUsuario;
import com.agenda.app.repository.TipoUsuarioRepository;

@RestController //define que ira trabalahar com a API
public class TipoUsuarioController {

    @Autowired //cria a instancia da Interface
    private TipoUsuarioRepository tipoUsuarioRepository ;


//
@PostMapping(value = "/tipousuarios")
    public ResponseEntity<Object> criarNovoTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(tipoUsuarioRepository.save(tipoUsuario));
        } catch (DataIntegrityViolationException d) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tipo de usuário já existente." + d.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar tipo de usuário." + e.getMessage());
        }
    }
///




        @GetMapping(value = "/tipousuarios") //rota Tipousuarios
        public List <TipoUsuario> obterTipoUsuarios(){
                return tipoUsuarioRepository.findAll();
        }

        @GetMapping(value = "/tipousuarios/{id}") 
        public Optional<TipoUsuario> obterTipoUsuarioPeloId(@PathVariable("id") int id){
                return tipoUsuarioRepository.findById(id);
                //return tipoUsuarioRepository.findById(id).get(); usando esse tirar o "Optional" 
        }

        @DeleteMapping(value = "/tipousuarios/{id}") 
        public String deletarTipoUsuarioPeloId(@PathVariable("id") int id){
            tipoUsuarioRepository.deleteById(id);
            return "Tipo deletado com Sucesso !" ;
               
        }

        @PutMapping(value = "/tipousuarios") //rota usuarios
        public TipoUsuario AtualizarTipoUsuario(@RequestBody TipoUsuario tipoUsuario){
                TipoUsuario tipoUsuarioBD= tipoUsuarioRepository.findById(tipoUsuario.getIdTipoUsuario()).get();


                tipoUsuarioBD.setNome(tipoUsuario.getNome()); //usuario a ser alterado
                return tipoUsuarioRepository.save(tipoUsuarioBD);
        }


    //fazer novas rotas    @PostMapping(value = "/tipousuarios")
   
    @GetMapping(value = "/tipousuarios/nome/{nome}")
    public TipoUsuario obterTipoUsuarioPeloNome(@PathVariable("nome") String nome) {
        return tipoUsuarioRepository.findByNomeLike(nome + "%");
    }



 
}
