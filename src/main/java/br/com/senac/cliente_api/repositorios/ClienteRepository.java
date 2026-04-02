package br.com.senac.cliente_api.repositorios;

import br.com.senac.cliente_api.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
