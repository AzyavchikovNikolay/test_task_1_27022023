<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.menu.news" var="news" />
<fmt:message bundle="${loc}" key="local.menu.news_list" var="news_list" />
<fmt:message bundle="${loc}" key="local.menu.add_news" var="add_news" />

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
			<c:out value="${news}" />
		</div>
	</div>
	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 5px;">
				
				<a href="controller?command=go_to_news_list">
					<c:out value="${news_list}" />
				</a><br/>
				</li>
			
				<c:if test="${sessionScope.role eq 'admin'}">
					<li style="padding-left: 5px;">
					
					<a href="controller?command=go_to_add_news">
						<c:out value="${add_news}" /></a>
					
					<br/>
					</li>
				</c:if>
				
				<c:if test="${sessionScope.role eq 'redactor'}">
					<li style="padding-left: 5px;">
					
					<a href="controller?command=go_to_add_news">
						<c:out value="${add_news}" /></a>
					
					<br/>
					</li>
				</c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<div style="height: 25px;"></div>
</div>