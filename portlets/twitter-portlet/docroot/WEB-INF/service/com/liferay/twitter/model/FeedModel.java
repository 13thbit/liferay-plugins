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

package com.liferay.twitter.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <a href="FeedModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Twitter_Feed table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Feed
 * @see       com.liferay.twitter.model.impl.FeedImpl
 * @see       com.liferay.twitter.model.impl.FeedModelImpl
 * @generated
 */
public interface FeedModel extends BaseModel<Feed> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getFeedId();

	public void setFeedId(long feedId);

	public long getTwitterUserId();

	public void setTwitterUserId(long twitterUserId);

	public String getTwitterUserUuid() throws SystemException;

	public void setTwitterUserUuid(String twitterUserUuid);

	public String getTwitterScreenName();

	public void setTwitterScreenName(String twitterScreenName);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getLastStatusId();

	public void setLastStatusId(long lastStatusId);

	public Feed toEscapedModel();

	public boolean isNew();

	public boolean setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(Feed feed);

	public int hashCode();

	public String toString();

	public String toXmlString();
}