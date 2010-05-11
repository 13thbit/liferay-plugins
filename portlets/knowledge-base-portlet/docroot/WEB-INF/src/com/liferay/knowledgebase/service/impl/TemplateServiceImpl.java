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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.base.TemplateServiceBaseImpl;
import com.liferay.knowledgebase.service.permission.AdminPermission;
import com.liferay.knowledgebase.service.permission.TemplatePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="TemplateServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class TemplateServiceImpl extends TemplateServiceBaseImpl {

	public Template addTemplate(
			String title, String content, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_TEMPLATE);

		return templateLocalService.addTemplate(
			null, getUserId(), title, content, description, serviceContext);
	}

	public void deleteTemplate(long templateId)
		throws PortalException, SystemException {

		TemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.DELETE);

		templateLocalService.deleteTemplate(templateId);
	}

	public List<Template> getGroupTemplates(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Template> templates = templateLocalService.getGroupTemplates(
			groupId, start, end, orderByComparator);

		return filterTemplates(templates);
	}

	public int getGroupTemplatesCount(long groupId) throws SystemException {
		return templateLocalService.getGroupTemplatesCount(groupId);
	}

	public Template getTemplate(long templateId)
		throws PortalException, SystemException {

		TemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.VIEW);

		return templateLocalService.getTemplate(templateId);
	}

	public Template updateTemplate(
			long templateId, String title, String content, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.UPDATE);

		return templateLocalService.updateTemplate(
			templateId, title, content, description, serviceContext);
	}

	protected List<Template> filterTemplates(List<Template> templates)
		throws PortalException {

		templates = ListUtil.copy(templates);

		Iterator<Template> itr = templates.iterator();

		while (itr.hasNext()) {
			if (!TemplatePermission.contains(
					getPermissionChecker(), itr.next(), ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return templates;
	}

}