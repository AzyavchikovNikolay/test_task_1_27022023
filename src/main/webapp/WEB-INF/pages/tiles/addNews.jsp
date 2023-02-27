<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.header.title" var="news_management" />
<fmt:message bundle="${loc}" key="local.addNews.news" var="news" />
<fmt:message bundle="${loc}" key="local.addNews.adding_news" var="adding_news" />
<fmt:message bundle="${loc}" key="local.addNews.warning" var="add_news_warning" />
<!--<fmt:message bundle="${loc}" key="local.addNews.news_id" var="news_id" />-->
<fmt:message bundle="${loc}" key="local.addNews.news_title" var="news_title" />
<fmt:message bundle="${loc}" key="local.addNews.news_date" var="news_date" />
<fmt:message bundle="${loc}" key="local.addNews.news_brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.addNews.news_content" var="content" />
<fmt:message bundle="${loc}" key="local.addNews.add_news" var="add_news" />
<fmt:message bundle="${loc}" key="local.addNews.back_to_news_list" var="back_to_news_list" />
<fmt:message bundle="${loc}" key="local.addNews.news_status" var="news_status" />
<fmt:message bundle="${loc}" key="local.addNews.new_news" var="new_news" />
<fmt:message bundle="${loc}" key="local.addNews.published_news" var="published_news" />


<div class="body-title">
	<a href="controller?command=go_to_news_list"><c:out value="${news}" /></a> <c:out value="${adding_news}" />
</div>

	<c:if test="${sessionScope.add eq 'warning'}">
		<div class=registration-warning><c:out value="${add_news_warning}" /></div>
		<c:set var="add" scope="session" value=""/>
	</c:if>

<form action="controller" method="post">
			<input type="hidden" name="command" value="do_add_news"/>
			
			<div class="add-table-margin">
				<table class="news_text_format">
					
					<!--
					<tr>
						<td class="space_around_title_text"><c:out value="${news_id}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="newsId" value=""/>
							</div>
						</td>
					</tr>
					-->
						
					<tr>
						<td class="space_around_title_text"><c:out value="${news_title}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<textarea rows="2" cols="50" name="titleEdit">
								</textarea>
							</div>
						</td>
					</tr>	
					
					<!--
					<tr>
						<td class="space_around_title_text"><c:out value="${news_date}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<input type="text" name="newsDateEdit" value=""/>
							</div>
						</td>
					</tr>	
					-->
					
					<tr>
						<td class="space_around_title_text"><c:out value="${news_brief}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<textarea rows="5" cols="50" name="briefEdit">
								</textarea>
							</div>
						</td>
					</tr>	
					<tr>
						<td class="space_around_title_text"><c:out value="${content}" /></td>
						<td class="space_around_view_text">
							<div class="word-breaker">
								<textarea rows="8" cols="50" name="contentEdit">
								</textarea>
							</div>
						</td>
					</tr>
						<tr>
						<td class="space_around_title_text"><c:out value="${news_status}" /></td>
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
				<input type="submit" value="${add_news}"/>
			</div>
		</form>

		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_news_list"/>
			<div class="registration-field">
				<input type="submit" value="${back_to_news_list}"/>
			</div>
		</form>