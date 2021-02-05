package br.ifpb.dac.projeto.adminServices;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import br.ifpb.dac.projeto.dao.AdministradorDAO;
import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AdministradorFiltro;
import br.ifpb.dac.projeto.utils.TransacionalCdi;

public class AdministradorServicesControl implements Serializable, AdministradorService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AdministradorDAO adminDAO;

	@Override
	@TransacionalCdi
	public void salvarAdmin(Administrador admin) throws DACExceptions {
		
		try {
			validarCPF(admin.getCpf());
			
			calcularHashDaSenha(admin);
			
			adminDAO.save(admin);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public Administrador atualizarAdmin(Administrador admin, boolean passwordChanged) throws DACExceptions {
		
		try {
			validarCPF(admin.getCpf());
			
			if (passwordChanged) {
				calcularHashDaSenha(admin);
			}
			
			return adminDAO.update(admin);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void deletarAdmin(Administrador admin) throws DACExceptions {
		
		try {
			adminDAO.delete(admin);
			
		} catch (DACExceptions pe) {
			pe.printStackTrace();
			throw new DACExceptions(pe.getMessage(), pe);
		}
	}
	
	@Override
	@TransacionalCdi
	public void atualizarEmail(String emailAntigo) throws DACExceptions {
		
		try {
			
			adminDAO.updateEmail(emailAntigo);
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
		
	}

	@Override
	public Administrador getAdminPeloID(int id) throws DACExceptions {
		
		try {
			return adminDAO.getAdminPeloID(id);

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public Administrador getAdminPeloCPF(String cpf) throws DACExceptions {
		
		try {
			return adminDAO.getByCPF(cpf);

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public List<Administrador> getTodosOsAdmins() throws DACExceptions {
		
		try {
			return adminDAO.getAllAdmins();

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}

	@Override
	public List<Administrador> encontrarPor(AdministradorFiltro adminFiltro) throws DACExceptions {
		
		try {
			return adminDAO.findBy(adminFiltro);

		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}
	}
	
	
	
	public void validarCPF(String cpf) throws DACExceptions {
		
		try {
			adminDAO.validarCPF(cpf);
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions("O CPF inserido � inv�lido!");
		}
	}

	
	
	private String calcularHashDaSenha(Administrador admin) throws DACExceptions {
		
		admin.getConta().setSenha(hash(admin.getConta().getSenha()));
		
		return admin.getConta().getSenha();
	}
	
	
	
	@Override
	public boolean conferirEmail(Administrador admin, String supostoEmail) throws DACExceptions {
		
		String email = null;
		
		try {
			
			email = adminDAO.getAdminPeloID(admin.getId()).getConta().getEmail();
			
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
	public boolean conferirSenha(Administrador admin, String supostaSenha) throws DACExceptions {
		
		// Recuperar verdadeira senha atual (hash)
		String senhaHash = null;
		
		try {
			
			senhaHash = adminDAO.getAdminPeloID(admin.getId()).getConta().getSenha();
			
		} catch (DACExceptions e) {
			e.printStackTrace();
			throw new DACExceptions(e.getMessage(), e);
		}

		// Programa��o defensiva contra NPE
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
	 * M�todo que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password
	 *            senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException
	 *             lan�ada caso ocorra algum erro durante o processo
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
