package br.com.senac.cliente_api.controllers;

import br.com.senac.cliente_api.dtos.ClienteRequestDTO;
import br.com.senac.cliente_api.entidades.Cliente;
import br.com.senac.cliente_api.repositorios.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
@CrossOrigin

public class ClienteController {
        private ClienteRepository clienteRepository;

        public ClienteController (ClienteRepository clienteRepository){
            this.clienteRepository = clienteRepository;
        }

        @GetMapping("/listar")
        public ResponseEntity<List<Cliente>> listar(){
            List<Cliente> clienteList = clienteRepository.findAll();

            if (clienteList.isEmpty()){
                return ResponseEntity.status(204).body(null);
            }

            return ResponseEntity.ok(clienteList);
        }

        @PostMapping("/criar")
        public ResponseEntity<Cliente> criar (@RequestBody ClienteRequestDTO cliente){
            Cliente clientePersist = new Cliente();
            clientePersist.setNome(cliente.getNome());
            clientePersist.setEmail(cliente.getEmail());
            clientePersist.setIdade(cliente.getIdade());
            clientePersist.setDocumento(cliente.getDocumento());

            Cliente retorno = clienteRepository.save(clientePersist);

            return ResponseEntity.status(201).body(retorno);
        }

        @PutMapping("/atualizar/{id}")
        public ResponseEntity<Cliente> atualizar(@RequestBody ClienteRequestDTO cliente, @PathVariable Long id){

            if (clienteRepository.existsById(id)){
                Cliente clientePersist = new Cliente();
                clientePersist.setNome(cliente.getNome());
                clientePersist.setEmail(cliente.getEmail());
                clientePersist.setIdade(cliente.getIdade());
                clientePersist.setDocumento(cliente.getDocumento());
                clientePersist.setId(id);

                Cliente retorno = clienteRepository.save(clientePersist);

                return ResponseEntity.ok(retorno);
            }

            return ResponseEntity.badRequest().body(null);

        }

        @DeleteMapping("/deletar/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id){

            if (clienteRepository.existsById(id)){
                clienteRepository.deleteById(id);
                return ResponseEntity.ok(null);
            }

            return ResponseEntity.badRequest().body(null);
        }
}
