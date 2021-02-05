package br.ifpb.dac.projeto.beans;

public class EnderecoDasPaginasWeb {
	
	public EnderecoDasPaginasWeb() {
		
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}
	
	private static final String REDIRECT_TRUE = "?faces-redirect=true";
	
	public static final String PAGINA_PRINCIPAL = "/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_CLIENTE = "/paginas/protegido/clientes/paginaPrincipalCliente.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_CADASTRAR_CLIENTE = "/paginas/publico/clientes/cadastroEdicaoDeCliente.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_CADASTRAR_ADMIN = "/paginas/publico/admin/cadastroEdicaoAdmin.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_CADASTRAR_CARRO = "/paginas/protegido/carros/cadastroEdicaoDeCarros.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_LISTAR_CARROS = "/paginas/protegido/carros/listarCarros.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_LISTAR_ADMINS = "/paginas/protegido/admin/listarAdministradores.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_LISTAR_AGENCIAS = "/paginas/protegido/agencias/listarAgencias.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_LISTAR_RESERVAS = "/paginas/protegido/alugueis/listarReservas.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_EDICAO_CLIENTE = "/paginas/protegido/clientes/paginaEdicaoCliente.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_RESERVAS_CLIENTE = "/paginas/protegido/clientes/reservasDoCliente.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_LOGIN = "/paginas/publico/login.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_ADMIN = "/paginas/protegido/admin/paginaPrincipalAdmin.xhtml" + REDIRECT_TRUE;
	
	public static final String FAKE_PAGE = "/paginas/protegido/fakePage.xhtml" + REDIRECT_TRUE;
	
}
