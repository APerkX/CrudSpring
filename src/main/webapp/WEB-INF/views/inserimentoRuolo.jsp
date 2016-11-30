<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina Inserimento Ruoli</title>
</head>
<body>
  <c:if test="${not empty esito }">
     <p style="text-align: center; color: red; text-transform: uppercase;">
         <c:out value= "${esito }"/>
     </p>
  </c:if>
  
  <form:form action="inserimentoRuolo" method="post" modelAttribute="formRuolo">
  
           <table border="0" align="center" bgcolor="#E0F0FF" cellspacing="0" frame="box" cellpadding="5">
               <form:hidden path="id"/>
               <tr>
                  <td>DESCRIZIONE</td>
                  <td><form:input path="descrizione" /> <form:errors path="descrizione"/></td>
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