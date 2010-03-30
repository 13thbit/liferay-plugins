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
MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "mbCategoryId", MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

boolean defaultShowCategories = false;

if (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
	defaultShowCategories = true;
}

boolean showCategories = ParamUtil.getBoolean(request, "showCategories", defaultShowCategories);

PortletURL tabs1URL = renderResponse.createRenderURL();
%>

<div class="sidebar">
	<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="searchURL"><portlet:param name="struts_action" value="/message_boards/search" /></liferay-portlet:renderURL>

	<form action="<%= searchURL %>" method="get" name="<portlet:namespace />sidebarFm" onSubmit="submitForm(this); return false;">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />

	<div class="search">
		<input class="search-input" id="<portlet:namespace />keywords1" name="<portlet:namespace />keywords" type="text" />

		<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	</form>

	<h2><liferay-ui:message key="categories" /></h2>

	<ul class="disc">

		<%
		List<MBCategory> categories = MBCategoryLocalServiceUtil.getCategories(scopeGroupId, categoryId, -1, -1);

		for(MBCategory curCategory : categories) {
			curCategory = curCategory.toEscapedModel();

			PortletURL viewCategoryURL = renderResponse.createRenderURL();

			viewCategoryURL.setParameter("struts_action", "/message_boards/view");
			viewCategoryURL.setParameter("mbCategoryId", String.valueOf(curCategory.getCategoryId()));
		%>

			<li>
				<a <%= (curCategory.getCategoryId() == categoryId) ? "class=\"category-current\"" : "" %> href="<%= viewCategoryURL %>"><%= curCategory.getName() %></a>
			</li>

		<%
		}
		%>

	</ul>

	<%
	boolean showAddCategoryButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
	boolean showPermissionsButton = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
	boolean defaultCategory = (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
	%>

	<c:if test="<%= !defaultCategory %>">
		<ul class="disc">
			<c:if test="<%= showAddCategoryButton %>">
				<li>
					<a href="javascript:;" onClick="<portlet:namespace />addCategory();"><liferay-ui:message key='<%= (category == null) ? "add-category" : "add-subcategory" %>' /></a>
				</li>
			</c:if>

			<li>

				<%
				PortletURL toggleCategoriesURL = renderResponse.createRenderURL();

				toggleCategoriesURL.setParameter("mbCategoryId", String.valueOf(categoryId));
				%>

				<c:choose>
					<c:when test="<%= showCategories %>">

						<%
						toggleCategoriesURL.setParameter("showCategories", String.valueOf(false));
						%>

						<a href="<%= toggleCategoriesURL.toString() %>"><liferay-ui:message key="hide-category-controls" /></a>
					</c:when>
					<c:otherwise>

						<%
						toggleCategoriesURL.setParameter("showCategories", String.valueOf(true));
						%>

						<a href="<%= toggleCategoriesURL.toString() %>"><liferay-ui:message key="show-category-controls" /></a>
					</c:otherwise>
				</c:choose>
			</li>

			<c:if test="<%= showPermissionsButton %>">

				<%
				String modelResource = "com.liferay.portlet.messageboards";
				String modelResourceDescription = themeDisplay.getScopeGroupName();
				String resourcePrimKey = String.valueOf(scopeGroupId);

				if (category != null) {
					modelResource = MBCategory.class.getName();
					modelResourceDescription = category.getName();
					resourcePrimKey = String.valueOf(category.getCategoryId());
				}
				%>

				<liferay-security:permissionsURL
					modelResource="<%= modelResource %>"
					modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
					resourcePrimKey="<%= resourcePrimKey %>"
					var="permissionsURL"
				/>

				<li>
					<a href="javascript:;" onClick="location.href = '<%= permissionsURL %>';" /><liferay-ui:message key="permissions" /></a>
				</li>
			</c:if>
		</ul>
	</c:if>

	<h2><liferay-ui:message key="quick-links" /></h2>

	<ul class="disc">
		<li>

			<%
			tabs1URL.setParameter("tabs1", "categories");
			%>

			<a href="<%= tabs1URL %>"><liferay-ui:message key="categories" /></a>
		</li>

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			<li>

				<%
				tabs1URL.setParameter("tabs1", "my_posts");
				%>

				<a href="<%= tabs1URL %>"><liferay-ui:message key="my-posts" /></a>
			</li>
			<li>

				<%
				tabs1URL.setParameter("tabs1", "my_subscriptions");
				%>

				<a href="<%= tabs1URL %>"><liferay-ui:message key="my-subscriptions" /></a>
			</li>
		</c:if>

		<li>

			<%
			tabs1URL.setParameter("tabs1", "recent_posts");
			%>

			<a href="<%= tabs1URL %>"><liferay-ui:message key="recent-posts" /></a>
		</li>
		<li>

			<%
			tabs1URL.setParameter("tabs1", "statistics");
			%>

			<a href="<%= tabs1URL %>"><liferay-ui:message key="statistics" /></a>
		</li>

		<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">
			<li>

				<%
				tabs1URL.setParameter("tabs1", "banned_users");
				%>

				<a href="<%= tabs1URL %>"><liferay-ui:message key="banned-users" /></a>
			</li>
		</c:if>

		<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, layout.getPlid(), portletDisplay.getResourcePK(), ActionKeys.CONFIGURATION) %>">
			<liferay-security:permissionsURL
				modelResource="com.liferay.portlet.messageboards"
				modelResourceDescription="<%= portletDisplay.getTitle() %>"
				redirect="<%= currentURL %>"
				resourcePrimKey="<%= portletDisplay.getResourcePK() %>"
				var="permissionsURL"
			/>

			<li>
				<a href="<%= permissionsURL %>"><liferay-ui:message key="permissions" /></a>
			</li>
		</c:if>
	</ul>
</div>