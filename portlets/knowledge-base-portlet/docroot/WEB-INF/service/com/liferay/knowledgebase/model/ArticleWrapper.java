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

package com.liferay.knowledgebase.model;


/**
 * <a href="ArticleSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link Article}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Article
 * @generated
 */
public class ArticleWrapper implements Article {
	public ArticleWrapper(Article article) {
		_article = article;
	}

	public long getPrimaryKey() {
		return _article.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_article.setPrimaryKey(pk);
	}

	public java.lang.String getUuid() {
		return _article.getUuid();
	}

	public void setUuid(java.lang.String uuid) {
		_article.setUuid(uuid);
	}

	public long getArticleId() {
		return _article.getArticleId();
	}

	public void setArticleId(long articleId) {
		_article.setArticleId(articleId);
	}

	public long getResourcePrimKey() {
		return _article.getResourcePrimKey();
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		_article.setResourcePrimKey(resourcePrimKey);
	}

	public long getGroupId() {
		return _article.getGroupId();
	}

	public void setGroupId(long groupId) {
		_article.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _article.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_article.setCompanyId(companyId);
	}

	public long getUserId() {
		return _article.getUserId();
	}

	public void setUserId(long userId) {
		_article.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _article.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_article.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _article.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_article.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _article.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_article.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _article.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_article.setModifiedDate(modifiedDate);
	}

	public long getParentResourcePrimKey() {
		return _article.getParentResourcePrimKey();
	}

	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		_article.setParentResourcePrimKey(parentResourcePrimKey);
	}

	public double getVersion() {
		return _article.getVersion();
	}

	public void setVersion(double version) {
		_article.setVersion(version);
	}

	public java.lang.String getTitle() {
		return _article.getTitle();
	}

	public void setTitle(java.lang.String title) {
		_article.setTitle(title);
	}

	public java.lang.String getContent() {
		return _article.getContent();
	}

	public void setContent(java.lang.String content) {
		_article.setContent(content);
	}

	public java.lang.String getDescription() {
		return _article.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_article.setDescription(description);
	}

	public int getPriority() {
		return _article.getPriority();
	}

	public void setPriority(int priority) {
		_article.setPriority(priority);
	}

	public com.liferay.knowledgebase.model.Article toEscapedModel() {
		return _article.toEscapedModel();
	}

	public boolean isNew() {
		return _article.isNew();
	}

	public boolean setNew(boolean n) {
		return _article.setNew(n);
	}

	public boolean isCachedModel() {
		return _article.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_article.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _article.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_article.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _article.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _article.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_article.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _article.clone();
	}

	public int compareTo(com.liferay.knowledgebase.model.Article article) {
		return _article.compareTo(article);
	}

	public int hashCode() {
		return _article.hashCode();
	}

	public java.lang.String toString() {
		return _article.toString();
	}

	public java.lang.String toXmlString() {
		return _article.toXmlString();
	}

	public Article getWrappedArticle() {
		return _article;
	}

	private Article _article;
}