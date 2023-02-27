<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.registration_form.registration_form" var="registration_form" />
<fmt:message bundle="${loc}" key="local.registration_form.warning_fill_fields" var="warning_fill_fields" />
<fmt:message bundle="${loc}" key="local.registration_form.warning_user_exists" var="warning_user_exists" />
<fmt:message bundle="${loc}" key="local.registration_form.enter_details" var="enter_details" />
<fmt:message bundle="${loc}" key="local.registration_form.login" var="login" />
<fmt:message bundle="${loc}" key="local.registration_form.password" var="password" />
<fmt:message bundle="${loc}" key="local.registration_form.role" var="role" />
<fmt:message bundle="${loc}" key="local.registration_form.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.registration_form.name" var="name" />
<fmt:message bundle="${loc}" key="local.registration_form.phone" var="phone" />
<fmt:message bundle="${loc}" key="local.registration_form.email" var="email" />
<fmt:message bundle="${loc}" key="local.registration_form.birthday" var="birthday" />
<fmt:message bundle="${loc}" key="local.registration_form.confirm" var="confirm" />
<fmt:message bundle="${loc}" key="local.registration_form.go_to_main_page" var="go_to_main_page" />

<div class="registration">
	<div class=registration-title><c:out value="${registration_form}" /><br/>
	</div>
		<c:if test="${sessionScope.warningReg eq 'warning'}">
			<div class=registration-warning><c:out value="${warning_fill_fields}" /></div>
			<c:set var="warningReg" scope="session" value=""/>
		</c:if>
		<c:if test="${sessionScope.warningReg2 eq 'warning'}">
			<div class=registration-warning><c:out value="${warning_user_exists}" /></div>
			<c:set var="warningReg2" scope="session" value=""/>
		</c:if>
	<div class="registration-field" ><b><c:out value="${enter_details}" /></b></div>
	<div  align="left">
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_registration"/>
			<div class="registration-field"><c:out value="${login}" /> <input type="text" name="loginReg" value=""/><br/>
			</div> 
			<div class="registration-field"><c:out value="${password}" /> <input type="text" name="passwordReg" value=""/><br/>
			</div>
			<div class="registration-field"><c:out value="${role}" />
				<select name="role">
					<!-- <option>admin</option> -->
					<option>user</option>
				</select>
			</div>
			<div class="registration-field"><c:out value="${surname}" />: <input type="text" name="surname" value=""/><br/>
			</div>
			<div class="registration-field"><c:out value="${name}" /> <input type="text" name="name" value=""/><br/>
			</div>
			<div class="registration-field"><c:out value="${phone}" /><input type="text" name="phone" value=""/><br/>
			</div>
			<div class="registration-field"><c:out value="${email}" /> <input type="text" name="email" value=""/><br/>
			</div>
			<div class="registration-field"><c:out value="${birthday}" /> <input type="text" name="birthday" value=""/><br/>
			</div>
			<div class="registration-field">
				<input type="submit" value="${confirm}"/>
			</div>
		</form>

		<div class="registration-field">
			
				<form action="controller" method="post">
							<input type="hidden" name="command" value="return_to_base_page">
							<input type="submit" value="${go_to_main_page}"> 
				</form>			
		</div>
	</div>
</div>