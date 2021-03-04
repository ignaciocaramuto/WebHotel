<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entities.Cliente" %>
<%@ page import="data.DataCliente" %>

<% 
DataCliente dc= new DataCliente();
Cliente c=new Cliente();
c.setNombre(request.getParameter("nombre"));
c.setApellido(request.getParameter("apellido"));
c.setNumDoc(request.getParameter("nroDoc"));
c.setUsuario(request.getParameter("usuario"));
c.setContraseña(request.getParameter("contraseña"));
c.setMail(request.getParameter("email"));
//c.setFechaNacimiento(dc.convertDate(request.getParameter("fechaNac")));
c.setTipoDoc(request.getParameter("tipoDoc"));
c.setNumDoc(request.getParameter("nroDoc"));
c.setSexo(request.getParameter("sexo"));
c.setTelefono(request.getParameter("tel"));





dc.add(c);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cliente Registrado</title>
</head>
<body>
<h2>Cliente <%=c.getNombre()%> <%=c.getApellido() %> registrado</h2>
<button>Volver a Inicio</button>

</body>
</html>