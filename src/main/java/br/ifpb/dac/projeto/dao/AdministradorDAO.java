package br.ifpb.dac.projeto.dao;

import java.util.List;

import br.ifpb.dac.projeto.entidades.Administrador;
import br.ifpb.dac.projeto.exceptions.DACExceptions;
import br.ifpb.dac.projeto.filtros.AdministradorFiltro;

public interface AdministradorDAO {
	
	public void save(Administrador admin) throws DACExceptions;
	
	public Administrador update(Administrador admin) throws DACExceptions;
	
	public void updateEmail(String oldEmail) throws DACExceptions;
	
	public void delete(Administrador admin) throws DACExceptions;
	
	public Administrador getAdminPeloID(int id) throws DACExceptions;
	
	public Administrador getByCPF(String cpf) throws DACExceptions;
	
	public List<Administrador> getAllAdmins() throws DACExceptions;
	
	public List<Administrador> findBy(AdministradorFiltro adminFilter) throws DACExceptions;
	
	public boolean validarCPF(String cpf) throws DACExceptions;

}
