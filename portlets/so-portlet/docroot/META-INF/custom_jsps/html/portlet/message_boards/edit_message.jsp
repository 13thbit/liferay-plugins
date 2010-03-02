<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
%>

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
MBMessage message = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

long categoryId = BeanParamUtil.getLong(message, request, "mbCategoryId");

if (message != null) {
	categoryId = message.getCategoryId();
}
%>

<liferay-util:include page="/html/portlet/message_boards/sidebar.jsp" />

<div class="breadcrumbs">
	<%= getCategoryBreadcrumb(categoryId, pageContext, renderResponse) %>

	<h6><liferay-ui:message key='<%= message == null ? "add-message" : "edit-message" %>' /></h6>
</div>

<liferay-util:include page="/html/portlet/message_boards/edit_message.portal.jsp" />