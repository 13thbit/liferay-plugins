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

package com.liferay.knowledgebase.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.ArticleLocalService;
import com.liferay.knowledgebase.service.ArticleService;
import com.liferay.knowledgebase.service.TemplateLocalService;
import com.liferay.knowledgebase.service.TemplateService;
import com.liferay.knowledgebase.service.persistence.ArticlePersistence;
import com.liferay.knowledgebase.service.persistence.TemplatePersistence;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.util.List;

/**
 * <a href="TemplateLocalServiceBaseImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class TemplateLocalServiceBaseImpl
	implements TemplateLocalService {
	public Template addTemplate(Template template) throws SystemException {
		template.setNew(true);

		return templatePersistence.update(template, false);
	}

	public Template createTemplate(long templateId) {
		return templatePersistence.create(templateId);
	}

	public void deleteTemplate(long templateId)
		throws PortalException, SystemException {
		templatePersistence.remove(templateId);
	}

	public void deleteTemplate(Template template) throws SystemException {
		templatePersistence.remove(template);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return templatePersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return templatePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		return templatePersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public int dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return templatePersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Template getTemplate(long templateId)
		throws PortalException, SystemException {
		return templatePersistence.findByPrimaryKey(templateId);
	}

	public List<Template> getTemplates(int start, int end)
		throws SystemException {
		return templatePersistence.findAll(start, end);
	}

	public int getTemplatesCount() throws SystemException {
		return templatePersistence.countAll();
	}

	public Template updateTemplate(Template template) throws SystemException {
		template.setNew(false);

		return templatePersistence.update(template, true);
	}

	public Template updateTemplate(Template template, boolean merge)
		throws SystemException {
		template.setNew(false);

		return templatePersistence.update(template, merge);
	}

	public ArticleLocalService getArticleLocalService() {
		return articleLocalService;
	}

	public void setArticleLocalService(ArticleLocalService articleLocalService) {
		this.articleLocalService = articleLocalService;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public ArticlePersistence getArticlePersistence() {
		return articlePersistence;
	}

	public void setArticlePersistence(ArticlePersistence articlePersistence) {
		this.articlePersistence = articlePersistence;
	}

	public TemplateLocalService getTemplateLocalService() {
		return templateLocalService;
	}

	public void setTemplateLocalService(
		TemplateLocalService templateLocalService) {
		this.templateLocalService = templateLocalService;
	}

	public TemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public TemplatePersistence getTemplatePersistence() {
		return templatePersistence;
	}

	public void setTemplatePersistence(TemplatePersistence templatePersistence) {
		this.templatePersistence = templatePersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = ArticleLocalService.class)
	protected ArticleLocalService articleLocalService;
	@BeanReference(type = ArticleService.class)
	protected ArticleService articleService;
	@BeanReference(type = ArticlePersistence.class)
	protected ArticlePersistence articlePersistence;
	@BeanReference(type = TemplateLocalService.class)
	protected TemplateLocalService templateLocalService;
	@BeanReference(type = TemplateService.class)
	protected TemplateService templateService;
	@BeanReference(type = TemplatePersistence.class)
	protected TemplatePersistence templatePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = CounterService.class)
	protected CounterService counterService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}