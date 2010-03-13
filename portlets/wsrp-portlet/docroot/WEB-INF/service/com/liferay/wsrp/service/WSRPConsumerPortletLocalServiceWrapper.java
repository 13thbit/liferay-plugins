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

package com.liferay.wsrp.service;


/**
 * <a href="WSRPConsumerPortletLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link WSRPConsumerPortletLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPortletLocalService
 * @generated
 */
public class WSRPConsumerPortletLocalServiceWrapper
	implements WSRPConsumerPortletLocalService {
	public WSRPConsumerPortletLocalServiceWrapper(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		_wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return _wsrpConsumerPortletLocalService.createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public void deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(start,
			end);
	}

	public int getWSRPConsumerPortletsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletsCount();
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortlet,
			merge);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerId,
			name, portletHandle, userToken);
	}

	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	public void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerPortletLocalService.destroyWSRPConsumerPortlets();
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(wsrpConsumerId,
			start, end);
	}

	public int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	public void initWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerPortletLocalService.initWSRPConsumerPortlets();
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortletId,
			name);
	}

	public WSRPConsumerPortletLocalService getWrappedWSRPConsumerPortletLocalService() {
		return _wsrpConsumerPortletLocalService;
	}

	private WSRPConsumerPortletLocalService _wsrpConsumerPortletLocalService;
}