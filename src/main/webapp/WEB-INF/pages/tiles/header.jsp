<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.header.title" var="news_management" />
<fmt:message bundle="${loc}" key="local.header.login_and_password_for_admin" var="login_for_admin" />
<fmt:message bundle="${loc}" key="local.header.login_and_password_for_user" var="login_for_user" />
<fmt:message bundle="${loc}" key="local.header.welcome" var="welcome" />
<fmt:message bundle="${loc}" key="local.header.lang_english" var="lang_eng" />
<fmt:message bundle="${loc}" key="local.header.lang_russian" var="lang_rus" />
<fmt:message bundle="${loc}" key="local.header.enter_login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.header.enter_password" var="enter_password" />
<fmt:message bundle="${loc}" key="local.header.registration" var="registration" />
<fmt:message bundle="${loc}" key="local.header.sign_in" var="sign_in" />
<fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out" />
<fmt:message bundle="${loc}" key="local.header.status" var="status" />
<fmt:message bundle="${loc}" key="local.header.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.header.redactor" var="redactor" />
<fmt:message bundle="${loc}" key="local.header.user" var="user" />

<div class="wrapper">
	<div class="newstitle">
		<c:out value="${news_management}" /><br/>
		<div class="newstitle-admin">
			<c:out value="${login_for_admin}" /><br/>
			<c:out value="${login_for_user}" />
		</div>
	</div>
	
	<div class="welcome-title">	
		<c:if test="${not (sessionScope.welcome eq 'guest')}">
			<c:out value="${welcome}" />
			<c:out value="${sessionScope.welcome}" />!
			<c:out value="${status}"/>
				<c:if test="${(sessionScope.role eq 'admin')}">
					<c:out value="${admin}"/>
				</c:if>
				<c:if test="${(sessionScope.role eq 'redactor')}">
					<c:out value="${redactor}"/>
				</c:if>
				<c:if test="${(sessionScope.role eq 'user')}">
					<c:out value="${user}"/>
				</c:if>
			</c:if>
			<c:if test="${sessionScope.session eq 'guest'}">
		</c:if>
	</div>
	
	<div class="local-link">

		<div align="right">

			<a href="controller?command=do_change_local&local=en">
				<c:out value="${lang_eng}" /></a> &nbsp;&nbsp; 
			<a	href="controller?command=do_change_local&local=ru">
				<c:out value="${lang_rus}" /></a> <br /> <br />
		</div>
		
		<c:if test="${not (sessionScope.user eq 'active')}">
			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_in" />
					<c:out value="${enter_login}" /><input type="text" name="login" value="" /><br /> 
					<c:out value="${enter_password}" /><input type="password" name="password" value="" /><br />

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
							<fmt:message bundle="${loc}" key="${requestScope.AuthenticationError}" var="auth_error" />
						   <c:out value="${auth_error}" />
						</font> 
					</c:if>
					
					<a href="controller?command=go_to_registration_page">
					<c:out value="${registration}" /></a> <input type="submit" value="${sign_in}" /><br />
				</form>
					
			</div>

		</c:if>
		
		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
			
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="${sign_out}" /><br />
				</form>
			</div>
		</c:if>
	</div>
</div>