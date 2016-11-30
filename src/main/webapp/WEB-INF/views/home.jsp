<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1">
	<title>HomeCrud</title>
</head>
<body>
     <c:url var="link" value="/preparaIserimento" />
     <script type="text/javascript">
     </script>



    <h1 align="center">  CRUD  </h1>
    
    <h2 align="center"> (create,read,update,delete) </h2>
    
    <c:if test="${not empty esito }">
       <p style="text-align: center; color: red; text-transformer: uppercase;">
       <b><c:out value="${esito}"></c:out></b>
       </p>
    </c:if>
    
   
    <table border="0" style="margin:0 auto" bgcolor="#E0F0FF" cellspacing="0" frame="box" cellpadding="0">
        <tr>
           <td align=" center" width="5%" bgcolor="#70C0FF"><b>ID</b></td>
           <td align=" center"  bgcolor="#70C0FF"><b>LOGIN</b></td>
           <td align=" center"  bgcolor="#70C0FF"><b>PASSWORD</b></td>
           <td align=" center"  bgcolor="#70C0FF"><b>RUOLO</b></td>
           <td width="15%" bgcolor="#70C0FF"><b>MODIFICA</b></td>
           <td width="15%" bgcolor="#70C0FF"><b>CANCELLA</b></td>
        </tr>
        
        <c:forEach items="${listaAnagrafiche }" var="anagrafica">
             <tr frame="below">
                 <td align="center" frame="below"><c:out value="${anagrafica.id }"/></td>
                 <td align="center" width=auto><c:out value="${anagrafica.login}"/></td>
                 <td align="center" width=auto><c:out value="${anagrafica.password}"/></td>
                 <td align="center" width=auto><c:out value="${anagrafica.ruolo.descrizione}"/></td>
                 <td align="center" valign="middle">
                     <form:form action="preparaModifica" method="post" modelAttribute="formAnagrafica">
                              <form:hidden path="id" value="${anagrafica.id}"/>
                              <input type="submit" value="Modifica">
                     </form:form>
                 </td>
                 <td align="center" valign="middle" align="center">
                     <form:form action="cancellaAnagrafica" method="post" modelAttribute="formAnagrafica">
                              <form:hidden path="id" value="${anagrafica.id}"/>
                              <input type="submit" value="Cancella">
                     </form:form>
                 </td>    
                 
        </c:forEach>  
    </table>
    
    
   <!-- <p align="center">
       <a href="<c:url value='/preparaInserimento' />">Inserisci una nuova riga</a>
    </p>

     <p align="center">
		<a href="<c:url value='/homeRuoli' />">Vai alla gestione dei Ruoli</a>
	</p>-->
	
	      <hr  width="50%">
	
	
	<table border="0" style="margin:0 auto;">
	    <tr>
	        <td align="center">
	            <form:form action="preparaInserimento" method="get" modelAttribute="formAnagrafica">
	                         <input type="submit" value="INSERIMENTO NUOVA ANAGRAFICA">
	            </form:form>             
	        </td>
	        
	        <td align="center">
	            <form:form action="homeRuoli" method="get" modelAttribute="formAnagrafica">
	                         <input type="submit" value="GESTIONE RUOLI">
	            </form:form>             
	        </td>
	        
	    </tr>
	
	</table>


</body>
</html>
