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

List<Article> articles = (List<Article>)request.getAttribute("article_tree.jsp-articles");
long firstArticleId = GetterUtil.getLong((String)request.getAttribute("article_tree.jsp-firstArticleId"));
long lastArticleId = GetterUtil.getLong((String)request.getAttribute("article_tree.jsp-lastArticleId"));
%>

<c:choose>
	<c:when test="<%= articles == null %>">

		<%
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", scopeGroupId);
		params.put("parentResourcePrimKey", article.getResourcePrimKey());

		List<Article> childArticles = ArticleServiceUtil.getArticles(params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ArticlePriorityComparator(true));
		%>

		<c:if test="<%= !childArticles.isEmpty() %>">

			<%
			Article firstArticle = childArticles.get(0);
			Article lastArticle = childArticles.get(childArticles.size() - 1);

			request.setAttribute("article_tree.jsp-articles", childArticles);
			request.setAttribute("article_tree.jsp-firstArticleId", String.valueOf(firstArticle.getArticleId()));
			request.setAttribute("article_tree.jsp-lastArticleId", String.valueOf(lastArticle.getArticleId()));
			%>

			<liferay-util:include page="/admin/article_tree.jsp" servletContext="<%= application %>" />
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="<%= articles.get(0).getArticleId() == firstArticleId %>">
			<div class="kb-article-tree">
				<div class="kb-elements">
		</c:if>

		<%
		for(Article curArticle : articles) {
		%>

			<div class="kb-element-header">
				<portlet:renderURL var="viewArticleURL">
					<portlet:param name="jspPage" value="/admin/view_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="../trees/page"
					label="<%= true %>"
					message="<%= curArticle.getTitle() %>"
					url="<%= viewArticleURL %>"
				/>
			</div>
			<div class="kb-element-body">

				<%
				request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, curArticle);
				%>

				<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

				<c:choose>
					<c:when test="<%= Validator.isNotNull(curArticle.getDescription()) %>">
						<%= curArticle.getDescription() %>
					</c:when>
					<c:otherwise>
						<%= StringUtil.shorten(HtmlUtil.extractText(curArticle.getContent()), 500) %>
					</c:otherwise>
				</c:choose>

				<%
				Map<String, Object> params = new HashMap<String, Object>();

				params.put("groupId", scopeGroupId);
				params.put("parentResourcePrimKey", curArticle.getResourcePrimKey());

				List<Article> childArticles = ArticleServiceUtil.getArticles(params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ArticlePriorityComparator(true));
				%>

				<c:if test="<%= !childArticles.isEmpty() %>">

					<%
					request.setAttribute("article_tree.jsp-articles", childArticles);
					%>

					<liferay-util:include page="/admin/article_tree.jsp" servletContext="<%= application %>" />
				</c:if>
			</div>

		<%
		}
		%>

		<c:if test="<%= articles.get(articles.size() - 1).getArticleId() == lastArticleId %>">
				</div>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>