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

package com.liferay.so.hook.upgrade.v2_0_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

import java.io.File;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * <a href="UpgradeDocumentLibrary.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 */
public class UpgradeDocumentLibrary extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			if (!group.isCommunity() ||
				group.getName().equals(GroupConstants.CONTROL_PANEL) ||
				group.getName().equals(GroupConstants.GUEST)) {

				continue;
			}

			Layout layout = LayoutLocalServiceUtil.getLayout(
				group.getGroupId(), group.hasPrivateLayouts(), 3);

			PortletPreferences portletSetup =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					layout, PortletKeys.DOCUMENT_LIBRARY);

			long rootFolderId = GetterUtil.getLong(
				portletSetup.getValue("rootFolderId", null),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

			if (rootFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				continue;
			}

			portletSetup.setValue(
				"rootFolderId",
				String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));

			portletSetup.store();

			moveDocumentsAndFolders(group.getGroupId(), rootFolderId);
		}
	}

	protected void moveDocumentsAndFolders(long groupId, long rootFolderId)
		throws Exception {

		boolean deleteFolder = true;

		List<DLFileEntry> fileEntries =
			DLFileEntryLocalServiceUtil.getFileEntries(groupId, rootFolderId);

		for (DLFileEntry fileEntry : fileEntries) {
			ServiceContext serviceContext = new ServiceContext();

			try {
				DLFileEntryLocalServiceUtil.updateFileEntry(
					fileEntry.getUserId(), fileEntry.getGroupId(),
					fileEntry.getFolderId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					fileEntry.getName(), StringPool.BLANK, fileEntry.getTitle(),
					fileEntry.getDescription(), StringPool.BLANK, false,
					fileEntry.getExtraSettings(), (File)null, serviceContext);
			}
			catch (Exception e) {
				deleteFolder = false;
			}
		}

		List<DLFolder> folders = DLFolderLocalServiceUtil.getFolders(
			groupId, rootFolderId);

		for (DLFolder folder : folders) {
			ServiceContext serviceContext = new ServiceContext();

			try {
				DLFolderLocalServiceUtil.updateFolder(
					folder.getFolderId(),
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
					folder.getName(), folder.getDescription(), serviceContext);
			}
			catch (Exception e) {
				deleteFolder = false;
			}
		}

		if (deleteFolder) {
			DLFolderLocalServiceUtil.deleteFolder(rootFolderId);
		}
	}

}