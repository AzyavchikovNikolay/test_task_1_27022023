<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.editNews.news" var="news" />
<fmt:message bundle="${loc}" key="local.editNews.adding_news" var="adding_news" />
<fmt:message bundle="${loc}" key="local.editNews.warning" var="edit_news_warning" />
<fmt:message bundle="${loc}" key="local.editNews.news_title" var="news_title" />
<fmt:message bundle="${loc}" key="local.editNews.news_date" var="news_date" />
<fmt:message bundle="${loc}" key="local.editNews.news_brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.editNews.news_content" var="content" />
<fmt:message bundle="${loc}" key="local.editNews.edit_news" var="edit_news" />
<fmt:message bundle="${loc}" key="local.editNews.back_to_news_list" var="back" />
<fmt:message bundle="${loc}" key="local.addNews.news_status" var="news_status" />
<fmt:message bundle="${loc}" key="local.addNews.new_news" var="new_news" />
<fmt:message bundle="${loc}" key="local.addNews.published_news" var="published_news" />
<fmt:message bundle="${loc}" key="local.editNews.current_news_status" var="current_news_status" />
<fmt:message bundle="${loc}" key="local.editNews.new_news_status" var="new_news_status" />
<fmt:message bundle="${loc}" key="local.editNews.new_news" var="new_news" />
<fmt:message bundle="${loc}" key="local.editNews.published_news" var="published_news" />

<div class="body-title">
	<a href="controller?command=go_to_news_list"><c:out value="${news}" /></a> <c:out value="${adding_news}" />
</div>

	<c:if test="${sessionScope.edit eq 'warning'}">
		<div class=registration-warning><c:out value="${edit_news_warning}" /></div>
		<c:set var="edit" scope="session" value=""/>
	</c:if>

<form action="controller" method="post">
			<input type="hidden" name="command" value="do_edit_news"/>
			
			<div class="add-table-margin">
				<table class="news_text_format">
					<tr>
						<td class="space_around_title_text"><c:out value="${news_title}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="titleEdit" value="${sessionScope.newsView.title }"/>
							</div>
						</td>
					</tr>	
					
					<!--
					<tr>
						<td class="space_around_title_text"><c:out value="${news_date}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="newsDateEdit" value="${sessionScope.newsView.newsDate }"/>
							</div>
						</td>
					</tr>
					-->
						
					<tr>
						<td class="space_around_title_text"><c:out value="${news_brief}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="briefEdit" value="${sessionScope.newsView.briefNews }"/>
							</div>
						</td>
					</tr>	
					<tr>
						<td class="space_around_title_text"><c:out value="${content}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="contentEdit" value="${sessionScope.newsView.content }"/>
							</div>
						</td>
					</tr>	
					
					<tr>
						<td class="space_around_title_text"><c:out value="${current_news_status}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<c:if test="${sessionScope.newsView.newsStatus eq 'new'}">
									<c:out value="${new_news}" />
								</c:if>
								<c:if test="${sessionScope.newsView.newsStatus eq 'published'}">
									<c:out value="${published_news}" />
								</c:if>
							</div>
						</td>
					</tr>	
					
					
					<tr>
						<td class="space_around_title_text"><c:out value="${new_news_status}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<select name="statusNewsEdit">
									<option><c:out value="${new_news}" /></option>
									<option><c:out value="${published_news}" /></option>
								</select>
							</div>
						</td>
					</tr>	
				</table>
			</div>
				
			<div class="registration-field">
				<input type="submit" value="${edit_news}"/>
			</div>
		</form>
		
	<c:if test="${(sessionScope.for_submit_back eq 'view_news')}">	
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_view_news"/>
			<div class="registration-field">
				<input type="submit" value="${back}"/>
			</div>
		</form>
	</c:if>
	
		<c:if test="${(sessionScope.for_submit_back eq 'news_list')}">	
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_news_list"/>
			<div class="registration-field">
				<input type="submit" value="${back}"/>
			</div>
		</form>
	</c:if>