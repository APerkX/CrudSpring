<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO8859-1">
	<title>HomeRuolo</title>
</head>
<body>
     <c:url var="link" value="/preparaInserimentoRuolo" />
     <script type="text/javascript">
     </script>



    <h1 align="center" color="#10B0FF"> RUOLI </h1>
    <h2 align="center"> Gestione dei Ruoli</h2>
    
    <c:if test="${not empty esito }">
       <p style="text-align: center; color: red; text-transformer: uppercase;">
       <b><c:out value="${esito}"></c:out></b>
       </p>
    </c:if>
    
    <table border="0" style="margin:0 auto" bgcolor="#E0F0FF" cellspacing="0" frame="box" cellpadding="0">
        <tr>
           <td align=" center" width="5%" bgcolor="#70C0FF"><b>ID</b></td>
           <td align=" center" width=auto bgcolor="#70C0FF"><b>DESCRIZIONE</b></td>
           <td align=" center" width="15%" bgcolor="#70C0FF"><b>MODIFICA</b></td>
           <td align=" center" width="15%" bgcolor="#70C0FF"><b>CANCELLA</b></td>
        </tr>
        
        <c:forEach items="${listaRuoli}" var="ruoli">
             <tr>
                 <td align="center"><c:out value="${ruoli.id }"/></td>
                 <td align="center" width=auto><c:out value="${ruoli.descrizione}"/></td>
                 <td align="center">
                     <form:form action="preparaModificaRuolo" method="post" modelAttribute="formRuolo">
                              <form:hidden path="id" value="${ruoli.id}"/>
                              <input type="submit" value="Modifica">
                     </form:form>
                 </td>
                 <td align="center">
                     <form:form action="cancellaRuolo" method="post" modelAttribute="formRuolo">
                              <form:hidden path="id" value="${ruoli.id}"/>
                              <input type="submit" value="Cancella">
                     </form:form>
                 </td>    
                 
        </c:forEach>  
    </table>
    
   <!--   <p align="center">
		<a href="<c:url value='/preparaInserimentoRuolo' />">Inserisci un nuovo ruolo</a>
	</p>
    
    <p align="center">
       <a href="<c:url value='/' />">Ritorna a GestioneAnagrafiche</a>
    </p>-->
      <hr  width="50%">

      <table border="0" style="margin:0 auto;">
	    <tr>
	        <td align="center">
	            <form:form action="preparaInserimentoRuolo" method="get" modelAttribute="formAnagrafica">
	                         <input type="submit" value="INSERIMENTO NUOVO RUOLO">
	            </form:form>             
	        </td>
	        
	        <td align="center">
	            <form:form action="/spring/" method="post" modelAttribute="formAnagrafica">
	                         <input type="submit" value="GESTIONE ANAGRAFICHE">
	            </form:form>             
	        </td>
	        
	    </tr>
	
	</table>


</body>
</html>
