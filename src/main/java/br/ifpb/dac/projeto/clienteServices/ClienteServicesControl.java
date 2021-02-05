package br.ifpb.dac.projeto.clienteServices;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import br.ifpb.dac.projeto.dao.ClienteDAO;
import br.ifpb.dac.projeto.entidades.Cliente;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.ClienteFiltro;
import br.ifpb.dac.projeto.utils.TransacionalCdi;

public class ClienteServicesControl implements Serializable, ClienteService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO clienteDAO;

	@Override
	@TransacionalCdi
	public void salvarCliente(Cliente cliente) throws DACExceptions {
		
		try {
			calcularHashDaSenha(cliente);
			
			clienteDAO.save(cliente);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}
	

	@Override
	@TransacionalCdi
	public Cliente atualizarCliente(Cliente client, boolean passwordChanged) throws DACExceptions {
		
		try {
			
			if (passwordChanged) {
				calcularHashDaSenha(client);
			}
			
			return clienteDAO.update(client);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	@TransacionalCdi
	public void deletarCliente(Cliente client) throws DACExceptions {
		
		try {
			clienteDAO.delete(client);
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
		
	}
	
	
	@Override
	@TransacionalCdi
	public void atualizarEmail(String emailAntigo) throws DACExceptions {
		
		try {
			
			clienteDAO.updateEmail(emailAntigo);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public Cliente getClientePeloID(int id) throws DACExceptions {
		
		try {
			return clienteDAO.getClientePeloID(id);

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public Cliente getClientePeloCPF(String cpf) throws DACExceptions {
		
		try {
			return clienteDAO.getByCPF(cpf);

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public List<Cliente> getTodosClientes() throws DACExceptions {
		
		try {
			return clienteDAO.getAllClients();

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	
	@Override
	public List<Cliente> encontrarPor(ClienteFiltro clientFilter) throws DACExceptions {
		
		try {
			return clienteDAO.findBy(clientFilter);

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}
	
	
	private String calcularHashDaSenha(Cliente cliente) throws DACExceptions {
		
		cliente.getConta().setSenha(hash(cliente.getConta().getSenha()));
		
		return cliente.getConta().getSenha();
	}
	
	
	
	@Override
	public boolean conferirEmail(Cliente cliente, String supostoEmail) throws DACExceptions {
		
		String email = null;
		
		try {
			
			email = clienteDAO.getClientePeloID(cliente.getId()).getConta().getEmail();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
		// Programação defensiva contra NPE
		if (email == null && supostoEmail == null) {
			return true;
		}

		if (email == null || supostoEmail == null) {
			return false;
		}
		
		return email.equals(supostoEmail);
	}
	
	
	
	@Override
	public boolean conferirSenha(Cliente cliente, String supostaSenha) throws DACExceptions {
		
		// Recuperar verdadeira senha atual (hash)
		String senhaHash = null;
		
		try {
			
			senhaHash = clienteDAO.getClientePeloID(cliente.getId()).getConta().getSenha();
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}

		// Programação defensiva contra NPE
		if (senhaHash == null && supostaSenha == null) {
			return true;
		}

		if (senhaHash == null || supostaSenha == null) {
			return false;
		}

		// Comparar hash da suposta senha com o verdadeiro hash da senha
		String supostaSenhaHash = hash(supostaSenha);

		return senhaHash.equals(supostaSenhaHash);
	}
	
	
	
	/**
	 * Metodo que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password
	 *            senha a ser calculada o hash
	 * @return hash da senha
	 * @throws DACExceptions
	 *             lancada caso ocorra algum erro durante o processo
	 */
	private String hash(String password) throws DACExceptions {
		
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			
			return output;
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DACExceptions("Could not calculate hash!", e);
		}
	}
}
