/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.so.hook.upgrade;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.so.util.PortletPropsKeys;
import com.liferay.util.portlet.PortletProps;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * <a href="UpgradeProcess_1_5_1.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 */
public class UpgradeProcess_1_5_1 extends UpgradeProcess {

	public int getThreshold() {
		return 151;
	}

	protected void doUpgrade() throws Exception {
		if (isFirstRun()) {
			return;
		}

		updateGroups();
	}

	protected boolean isFirstRun() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		if (companies.isEmpty() || companies.size() > 1) {
			return false;
		}

		Company company = companies.get(0);

		long companyId = company.getCompanyId();

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			group.getGroupId(), false, 1);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		if (!layoutTypePortlet.hasPortletId("47")) {
			return false;
		}

		return true;
	}

	protected void updateGroups() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			Layout layout = null;

			try {
				layout = LayoutLocalServiceUtil.getLayout(
					group.getGroupId(), group.hasPrivateLayouts(), 1);
			}
			catch (Exception e) {
				continue;
			}

			if (!layout.getFriendlyURL().equals("/home")) {
				continue;
			}

			updatePortlets(group, layout);
			updateLayouts(group);
		}
	}

	protected void updateLayouts(Group group) throws Exception {
		if (!group.isCommunity()) {
			return;
		}

		if ((group.getType() == GroupConstants.TYPE_COMMUNITY_PRIVATE) &&
			group.hasPrivateLayouts()) {

			return;
		}

		if ((group.getType() == GroupConstants.TYPE_COMMUNITY_OPEN) &&
			group.hasPublicLayouts()) {

			return;
		}

		long companyId = group.getCompanyId();

		boolean privateLayout = group.hasPrivateLayouts();

		List<Layout> sourceLayouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), privateLayout);

		for (Layout sourceLayout : sourceLayouts) {
			Layout targetLayout = LayoutLocalServiceUtil.addLayout(
				group.getCreatorUserId(), group.getGroupId(), !privateLayout,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				sourceLayout.getName(LocaleUtil.getDefault().toString()),
				StringPool.BLANK, StringPool.BLANK,
				LayoutConstants.TYPE_PORTLET, false,
				sourceLayout.getFriendlyURL());

			LayoutLocalServiceUtil.updateLayout(
				targetLayout.getGroupId(), targetLayout.isPrivateLayout(),
				targetLayout.getLayoutId(), sourceLayout.getTypeSettings());

			LayoutTypePortlet sourceLayoutTypePortlet =
				(LayoutTypePortlet)sourceLayout.getLayoutType();

			List<Portlet> sourcePortlets =
				sourceLayoutTypePortlet.getAllPortlets();

			for (Portlet sourcePortlet : sourcePortlets) {
				String sourcePortletId = sourcePortlet.getPortletId();

				PortletPreferencesLocalServiceUtil.getPreferences(
					companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
					sourcePortletId);

				PortletPreferences sourcePreferences =
					PortletPreferencesLocalServiceUtil.getPreferences(
						companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
						PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
						sourceLayout.getPlid(), sourcePortletId);

				PortletPreferencesLocalServiceUtil.updatePreferences(
					PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
					sourcePortletId, sourcePreferences);
			}

			LayoutLocalServiceUtil.deleteLayout(sourceLayout.getPlid());
		}
	}

	protected void updatePortlets(Group group, Layout layout) throws Exception {
		String prefix = PortletPropsKeys.SITE_LAYOUT_PORTLETS;

		if (group.isUser()) {
			prefix = PortletPropsKeys.USER_LAYOUT_PORTLETS;
		}

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		LayoutTemplate layoutTemplate = layoutTypePortlet.getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (String column : columns) {
			layoutTypePortlet.setPortletIds(
				column, PortletProps.get(prefix + column));
		}

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

}