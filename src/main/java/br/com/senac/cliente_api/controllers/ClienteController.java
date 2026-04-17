package br.com.senac.cliente_api.controllers;

import br.com.senac.cliente_api.dtos.ClienteRequestDTO;
import br.com.senac.cliente_api.entidades.Cliente;
import br.com.senac.cliente_api.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
@CrossOrigin

public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
        public ResponseEntity<List<Cliente>> listar(){
            List<Cliente> clienteList = clienteService.listar();

            if (clienteList.isEmpty()){
                return ResponseEntity.status(204).body(null);
            }

            return ResponseEntity.ok(clienteList);
        }

        @PostMapping("/criar")
        public ResponseEntity<Cliente> criar (@RequestBody ClienteRequestDTO cliente){
            try {
                return ResponseEntity.ok(clienteService.criar(cliente));
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @PutMapping("/atualizar/{id}")
        public ResponseEntity<Cliente> atualizar(@RequestBody ClienteRequestDTO cliente, @PathVariable Long id){

            try {
                return ResponseEntity.ok(clienteService.atualizar(id, cliente));
            }
            catch (RuntimeException e){
                return ResponseEntity.badRequest().body(null);
            }
            catch (Exception e){
                return ResponseEntity.internalServerError().body(null);
            }
        }

        @DeleteMapping("/deletar/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id){

            try {
                clienteService.deletar(id);
                return ResponseEntity.ok().body(null);
            }
            catch (RuntimeException e){
                return ResponseEntity.badRequest().body(null);
            }
            catch (Exception e){
                return ResponseEntity.internalServerError().body(null);
            }
        }
}
