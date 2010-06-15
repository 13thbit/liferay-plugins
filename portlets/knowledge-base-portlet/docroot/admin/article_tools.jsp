<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
%>

<div class="kb-article-tools">
	<table class="lfr-table">
	<tr>
		<td>
			<portlet:renderURL var="historyURL">
				<portlet:param name="jspPage" value="/admin/history.jsp" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="recent_changes"
				label="<%= true %>"
				message="history"
				url="<%= historyURL %>"
			/>
		</td>
		<td>
			<portlet:renderURL var="printURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="jspPage" value="/admin/print_article.jsp" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			</portlet:renderURL>

			<%
			String taglibURL = "javascript:var printArticleWindow = window.open('" + printURL + "', 'printArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printArticleWindow.focus();";
			%>

			<liferay-ui:icon
				image="print"
				label="<%= true %>"
				url="<%= taglibURL %>"
			/>
		</td>
	</tr>
	</table>
</div>