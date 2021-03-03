<%@ page import="entities.Cliente" %>
<%@ page import="java.util.LinkedList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style/signin.css">

<%
	Cliente c = (Cliente)session.getAttribute("usuario");
	LinkedList<Cliente> lc = (LinkedList<Cliente>)request.getAttribute("listaClientes");
%>

</head>
<body>
	<div class="container">
		<div class="row">
			<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Agregar nuevo cliente</a>
			<h4>Clientes</h4>
			<div class="col-12 col-sm col-lg-12">
				<div class="table-responsive">
					<div class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Email</th>
								<th>Telefono</th>
								<th>Sexo</th>
								<th>Fecha Nacimiento</th>
							</tr>
						</thead>
						<tbody>
						<% for (Cliente cli : lc) { %>
							<tr>
								<td><%=cli.getIdCliente() %></td>
								<td><%=cli.getNombre() %></td>
								<td><%=cli.getApellido() %></td>
								<td><%=cli.getMail() %></td>
								<td><%=cli.getTelefono() %></td>
								<td><%=cli.getSexo() %></td>
								<td><%=cli.getFechaNacimiento() %></td>
								<td></td><!-- editar -->
								<td></td><!-- borrar -->
							</tr>
						<% } %>	
						</tbody>
					</div><!-- /container -->
				</div>	
			</div>
		</div>
	</div>
</body>
</html>