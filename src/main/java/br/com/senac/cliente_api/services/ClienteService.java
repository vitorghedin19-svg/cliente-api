package br.com.senac.cliente_api.services;

import br.com.senac.cliente_api.dtos.ClienteRequestDTO;
import br.com.senac.cliente_api.entidades.Cliente;
import br.com.senac.cliente_api.repositorios.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente criar(ClienteRequestDTO cliente) {

        Cliente clientePersist = this.clienteRequestDtoParaClientes(cliente);

        return clienteRepository.save(clientePersist);

    }

    public Cliente atualizar(Long id, ClienteRequestDTO cliente){
       if (clienteRepository.existsById(id)){
           Cliente clientePersist = this.clienteRequestDtoParaClientes(cliente);

           clientePersist.setId(id);

           return clienteRepository.save(clientePersist);
       }

       throw new RuntimeException("Cliente não encontrado!");

    }

    public Cliente deletar(Long id){

        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }

        throw new RuntimeException("Cliente não encontrado!");

    }

    private Cliente clienteRequestDtoParaClientes(ClienteRequestDTO entrada) {
        Cliente saida = new Cliente();
        saida.setNome(entrada.getNome());
        saida.setDocumento(entrada.getDocumento());
        saida.setIdade(entrada.getIdade());
        saida.setEmail(entrada.getEmail());

        return saida;
    }

}
