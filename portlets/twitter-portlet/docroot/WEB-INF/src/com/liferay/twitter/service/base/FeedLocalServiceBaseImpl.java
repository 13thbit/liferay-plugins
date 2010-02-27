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

package com.liferay.twitter.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.twitter.model.Feed;
import com.liferay.twitter.service.FeedLocalService;
import com.liferay.twitter.service.persistence.FeedPersistence;

import java.util.List;

/**
 * <a href="FeedLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class FeedLocalServiceBaseImpl implements FeedLocalService {
	public Feed addFeed(Feed feed) throws SystemException {
		feed.setNew(true);

		return feedPersistence.update(feed, false);
	}

	public Feed createFeed(long feedId) {
		return feedPersistence.create(feedId);
	}

	public void deleteFeed(long feedId) throws PortalException, SystemException {
		feedPersistence.remove(feedId);
	}

	public void deleteFeed(Feed feed) throws SystemException {
		feedPersistence.remove(feed);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return feedPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return feedPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public Feed getFeed(long feedId) throws PortalException, SystemException {
		return feedPersistence.findByPrimaryKey(feedId);
	}

	public List<Feed> getFeeds(int start, int end) throws SystemException {
		return feedPersistence.findAll(start, end);
	}

	public int getFeedsCount() throws SystemException {
		return feedPersistence.countAll();
	}

	public Feed updateFeed(Feed feed) throws SystemException {
		feed.setNew(false);

		return feedPersistence.update(feed, true);
	}

	public Feed updateFeed(Feed feed, boolean merge) throws SystemException {
		feed.setNew(false);

		return feedPersistence.update(feed, merge);
	}

	public FeedLocalService getFeedLocalService() {
		return feedLocalService;
	}

	public void setFeedLocalService(FeedLocalService feedLocalService) {
		this.feedLocalService = feedLocalService;
	}

	public FeedPersistence getFeedPersistence() {
		return feedPersistence;
	}

	public void setFeedPersistence(FeedPersistence feedPersistence) {
		this.feedPersistence = feedPersistence;
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

	@BeanReference(name = "com.liferay.twitter.service.FeedLocalService")
	protected FeedLocalService feedLocalService;
	@BeanReference(name = "com.liferay.twitter.service.persistence.FeedPersistence")
	protected FeedPersistence feedPersistence;
	@BeanReference(name = "com.liferay.counter.service.CounterLocalService")
	protected CounterLocalService counterLocalService;
	@BeanReference(name = "com.liferay.counter.service.CounterService")
	protected CounterService counterService;
	@BeanReference(name = "com.liferay.portal.service.ResourceLocalService")
	protected ResourceLocalService resourceLocalService;
	@BeanReference(name = "com.liferay.portal.service.ResourceService")
	protected ResourceService resourceService;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.UserLocalService")
	protected UserLocalService userLocalService;
	@BeanReference(name = "com.liferay.portal.service.UserService")
	protected UserService userService;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected UserPersistence userPersistence;
}