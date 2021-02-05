package br.ifpb.dac.projeto.adminServices;
import java.util.List;

import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AdministradorFiltro;

public interface AdministradorService {
	
	public void salvarAdmin(Administrador admin) throws DACExceptions;
	
	public Administrador atualizarAdmin(Administrador admin, boolean passwordChanged) throws DACExceptions;
	
	public void atualizarEmail(String emailAntigo) throws DACExceptions;
	
	public void deletarAdmin(Administrador admin) throws DACExceptions;
	
	public Administrador getAdminPeloID(int id) throws DACExceptions;
	
	public Administrador getAdminPeloCPF(String cpf) throws DACExceptions;
	
	public List<Administrador> getTodosOsAdmins() throws DACExceptions;
	
	public List<Administrador> encontrarPor(AdministradorFiltro adminFiltro) throws DACExceptions;
	
	public boolean conferirEmail(Administrador admin, String supostoEmail) throws DACExceptions;
	
	public boolean conferirSenha(Administrador admin, String supostaSenha) throws DACExceptions;
	
	public void validarCPF(String cpf) throws DACExceptions;

}
