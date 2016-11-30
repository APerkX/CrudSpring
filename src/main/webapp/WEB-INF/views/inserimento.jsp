<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina Inserimento</title>
</head>
<body>
  <c:if test="${not empty esito }">
     <p style="text-align: center; color: red; text-transform: uppercase;">
         <c:out value= "${esito }"/>
     </p>
  </c:if>
  
  <form:form action="inserimentoAnagrafica" method="post" modelAttribute="formAnagrafica">
  
 
           <table border="0" align="center" bgcolor="#E0F0FF" cellspacing="0" frame="box" cellpadding="5">
               <form:hidden path="id"/>
               <tr>
                  <td>LOGIN</td>
                  <td><form:input path="login" /><form:errors path="login"/></td>
               </tr>   
               <tr>
				<td>PASSWORD</td>
				<td><form:input path="password" /> <form:errors
						path="password" /></td>
			</tr>
			<tr>
				<td>RUOLO</td>
				<td>
					<form:select path="idRuolo">
						<form:options items="${listaRuoli}" itemValue="id" itemLabel="descrizione" />
					</form:select>
				</td>
			</tr>
		</table>
		
		<table border="0" align="center">
		   <tr>
		      <td>
	            <form>
	               <input TYPE="button" VALUE="Indietro" onClick="history.go(-1);"> 
	            </form>
	          </td>
		      <td><input type="submit" value="Invia dati"></td>
		  </tr>
		</table> 
  
		<!-- <input type="submit" value="Invia dati"> -->
	</form:form>

	                         
	      
	        
</body>
</html>