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

package com.liferay.portal.workflow.kaleo.service;


/**
 * <a href="KaleoTaskInstanceTokenLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoTaskInstanceTokenLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceTokenLocalService
 * @generated
 */
public class KaleoTaskInstanceTokenLocalServiceWrapper
	implements KaleoTaskInstanceTokenLocalService {
	public KaleoTaskInstanceTokenLocalServiceWrapper(
		KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService) {
		_kaleoTaskInstanceTokenLocalService = kaleoTaskInstanceTokenLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken createKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		return _kaleoTaskInstanceTokenLocalService.createKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public void deleteKaleoTaskInstanceToken(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceTokenLocalService.deleteKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public void deleteKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceTokenLocalService.deleteKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(start,
			end);
	}

	public int getKaleoTaskInstanceTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.updateKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.updateKaleoTaskInstanceToken(kaleoTaskInstanceToken,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskId,
		java.lang.String kaleoTaskName,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		java.util.Date dueDate,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(kaleoInstanceTokenId,
			kaleoTaskId, kaleoTaskName, kaleoTaskAssignment, dueDate,
			workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.assignKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK, workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	public void deleteKaleoDefinitionKaleoTaskInstanceTokens(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceTokenLocalService.deleteKaleoDefinitionKaleoTaskInstanceTokens(kaleoDefinitionId);
	}

	public void deleteKaleoInstanceKaleoTaskInstanceTokens(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceTokenLocalService.deleteKaleoInstanceKaleoTaskInstanceTokens(kaleoInstanceId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getCompanyKaleoTaskInstanceTokens(companyId,
			start, end);
	}

	public int getCompanyKaleoTaskInstanceTokensCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getCompanyKaleoTaskInstanceTokensCount(companyId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(completed,
			start, end, orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(roleIds,
			completed, start, end, orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		long kaleoInstanceId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(kaleoInstanceId,
			completed, start, end, orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(assigneeClassName,
			assigneeClassPK, completed, start, end, orderByComparator,
			serviceContext);
	}

	public int getKaleoTaskInstanceTokensCount(java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(completed,
			serviceContext);
	}

	public int getKaleoTaskInstanceTokensCount(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(roleIds,
			completed, serviceContext);
	}

	public int getKaleoTaskInstanceTokensCount(long kaleoInstanceId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(kaleoInstanceId,
			completed, serviceContext);
	}

	public int getKaleoTaskInstanceTokensCount(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(assigneeClassName,
			assigneeClassPK, completed, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getSubmittingUserKaleoTaskInstanceTokens(
		long userId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getSubmittingUserKaleoTaskInstanceTokens(userId,
			completed, start, end, orderByComparator, serviceContext);
	}

	public int getSubmittingUserKaleoTaskInstanceTokensCount(long userId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.getSubmittingUserKaleoTaskInstanceTokensCount(userId,
			completed, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.search(keywords, completed,
			searchByUserRoles, start, end, orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String assetType,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.search(taskName, assetType,
			dueDateGT, dueDateLT, completed, searchByUserRoles, andOperator,
			start, end, orderByComparator, serviceContext);
	}

	public int searchCount(java.lang.String keywords,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.searchCount(keywords,
			completed, searchByUserRoles, serviceContext);
	}

	public int searchCount(java.lang.String taskName,
		java.lang.String assetType, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.searchCount(taskName,
			assetType, dueDateGT, dueDateLT, completed, searchByUserRoles,
			andOperator, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateDueDate(
		long kaleoTaskInstanceTokenId, java.util.Date dueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceTokenLocalService.updateDueDate(kaleoTaskInstanceTokenId,
			dueDate, serviceContext);
	}

	public KaleoTaskInstanceTokenLocalService getWrappedKaleoTaskInstanceTokenLocalService() {
		return _kaleoTaskInstanceTokenLocalService;
	}

	private KaleoTaskInstanceTokenLocalService _kaleoTaskInstanceTokenLocalService;
}