package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Signin", "/signin", "/SIGNIN", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Login ctrl;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        this.ctrl = new Login();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cliente cli = new Cliente();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		cli.setMail(email);
		cli.setContraseña(password);
		cli = ctrl.validateCliente(cli);
		request.getSession().setAttribute("usuario", cli);
		
		response.getWriter().append("Bienvenido: ").append(cli.getNombre()).append(" ").append(cli.getApellido());
		
		String action = request.getServletPath();
		
		switch (action) {
		
		case "/new":
			showNewForm(request,response);
			break;
		
		case "/insert":
			insertClient(request, response);
			break;
			
		case "/update":
			updateClient(request, response);
			break;
			
		case "/delete":
			deleteClient(request, response);
			break;
			
		default:
			listClients(request, response);
			break;
		}
		
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/ClientForm.jsp").forward(request, response);
	}
	
	private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Cliente> clientes = ctrl.getAll();
		request.setAttribute("listaClientes", clientes);
		request.getRequestDispatcher("WEB-INF/ListaClientes.jsp").forward(request, response);
	}

	private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoDoc = request.getParameter("tipoDoc");
		String nroDoc = request.getParameter("nroDoc");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("telefono");
		String sexo = request.getParameter("sexo");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		Date myDate = convertDate(fechaNacimiento);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		String tipoTarjeta = request.getParameter("tipoTarjeta");
		String nroTarjeta = request.getParameter("nroTarjeta");
		
		Cliente newCliente = new Cliente();
		newCliente.setTipoDoc(tipoDoc);
		newCliente.setNumDoc(nroDoc);
		newCliente.setNombre(nombre);
		newCliente.setApellido(apellido);
		newCliente.setMail(email);
		newCliente.setContraseña(password);
		newCliente.setTelefono(telefono);
		newCliente.setSexo(sexo);
		newCliente.setFechaNacimiento(sqlDate);
		newCliente.setTipoTarjetaCredito(tipoTarjeta);
		newCliente.setNumTarjetaCredito(nroTarjeta);
		
		ctrl.addClient(newCliente);
		response.sendRedirect("list");
	}
	
	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = new Cliente();
		int id = Integer.parseInt(request.getParameter("id"));
		String tipoDoc = request.getParameter("tipoDoc");
		String nroDoc = request.getParameter("nroDoc");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("telefono");
		String sexo = request.getParameter("sexo");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		Date myDate = convertDate(fechaNacimiento);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		String tipoTarjeta = request.getParameter("tipoTarjeta");
		String nroTarjeta = request.getParameter("nroTarjeta");
		
		c.setIdCliente(id);
		c.setTipoDoc(tipoDoc);
		c.setNumDoc(nroDoc);
		c.setNombre(nombre);
		c.setApellido(apellido);
		c.setMail(email);
		c.setContraseña(password);
		c.setTelefono(telefono);
		c.setSexo(sexo);
		c.setFechaNacimiento(sqlDate);
		c.setTipoTarjetaCredito(tipoTarjeta);
		c.setNumTarjetaCredito(nroTarjeta);
		
		ctrl.updateClient(c);
		response.sendRedirect("list");
	}
	
	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = new Cliente();
		int id = Integer.parseInt(request.getParameter("id"));
		c.setIdCliente(id);
		ctrl.deleteClient(c);
		response.sendRedirect("list");
	}
	
	private Date convertDate(String date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = null;
		try {
			myDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate;
	}

}
