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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskInstanceTokenLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.ContextUtil;
import com.liferay.portal.workflow.kaleo.util.RoleRetrievalUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <a href="KaleoTaskInstanceTokenLocalServiceImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceTokenLocalServiceImpl
	extends KaleoTaskInstanceTokenLocalServiceBaseImpl {

	public KaleoTaskInstanceToken addKaleoTaskInstanceToken(
			long kaleoInstanceTokenId, long kaleoTaskId, String kaleoTaskName,
			KaleoTaskAssignment kaleoTaskAssignment, Date dueDate,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoInstanceTokenPersistence.findByPrimaryKey(
				kaleoInstanceTokenId);
		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoTaskInstanceTokenId = counterLocalService.increment();

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.create(kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setCompanyId(user.getCompanyId());
		kaleoTaskInstanceToken.setUserId(user.getUserId());
		kaleoTaskInstanceToken.setUserName(user.getFullName());
		kaleoTaskInstanceToken.setCreateDate(now);
		kaleoTaskInstanceToken.setModifiedDate(now);
		kaleoTaskInstanceToken.setDueDate(dueDate);
		kaleoTaskInstanceToken.setKaleoInstanceId(
			kaleoInstanceToken.getKaleoInstanceId());

		KaleoInstanceToken childKaleoInstanceToken =
			kaleoInstanceTokenLocalService.addKaleoInstanceToken(
				kaleoInstanceToken.getKaleoInstanceTokenId(), context,
				serviceContext);

		kaleoTaskInstanceToken.setKaleoInstanceTokenId(
			childKaleoInstanceToken.getKaleoInstanceTokenId());

		kaleoTaskInstanceToken.setKaleoTaskId(kaleoTaskId);
		kaleoTaskInstanceToken.setKaleoTaskName(kaleoTaskName);
		kaleoTaskInstanceToken.setAssigneeClassName(
			kaleoTaskAssignment.getAssigneeClassName());
		kaleoTaskInstanceToken.setAssigneeClassPK(
			kaleoTaskAssignment.getAssigneeClassPK());
		kaleoTaskInstanceToken.setCompleted(false);
		kaleoTaskInstanceToken.setContext(ContextUtil.convert(context));

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, String assigneeClassName,
			long assigneeClassPK, Map<String, Serializable> context,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setModifiedDate(new Date());
		kaleoTaskInstanceToken.setAssigneeClassName(assigneeClassName);
		kaleoTaskInstanceToken.setAssigneeClassPK(assigneeClassPK);
		kaleoTaskInstanceToken.setContext(ContextUtil.convert(context));

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		return kaleoTaskInstanceToken;
	}

	public KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo instance token

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstanceToken.setCompletionUserId(serviceContext.getUserId());
		kaleoTaskInstanceToken.setCompleted(true);
		kaleoTaskInstanceToken.setCompletionDate(new Date());

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstanceToken, false);

		// Kaleo instance

		kaleoInstanceTokenLocalService.completeKaleoInstanceToken(
			kaleoTaskInstanceToken.getKaleoInstanceTokenId());

		return kaleoTaskInstanceToken;
	}

	public List<KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
			long companyId, int start, int end)
		throws SystemException {

		return kaleoTaskInstanceTokenPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getCompanyKaleoTaskInstanceTokensCount(long companyId)
		throws SystemException {

		return kaleoTaskInstanceTokenPersistence.countByCompanyId(companyId);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			completed, serviceContext);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			List<Long> roleIds, Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			roleIds, completed, serviceContext);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			long kaleoInstanceId, Boolean completed, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoInstanceId, completed, serviceContext);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public List<KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
			String assigneeClassName, long assigneeClassPK, Boolean completed,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			assigneeClassName, assigneeClassPK, completed, serviceContext);

		List<Object> results = dynamicQuery(
			dynamicQuery, start, end, orderByComparator);

		return toKaleoTaskInstanceTokens(results);
	}

	public int getKaleoTaskInstanceTokensCount(
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			completed, serviceContext);

		return dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			List<Long> roleIds, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			roleIds, completed, serviceContext);

		return dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			long kaleoInstanceId, Boolean completed,
			ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoInstanceId, completed, serviceContext);

		return dynamicQueryCount(dynamicQuery);
	}

	public int getKaleoTaskInstanceTokensCount(
			String assigneeClassName, long assigneeClassPK,
			Boolean completed, ServiceContext serviceContext)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			assigneeClassName, assigneeClassPK, completed, serviceContext);

		return dynamicQueryCount(dynamicQuery);
	}

	public List<KaleoTaskInstanceToken> search(
			String keywords, Boolean completed, Boolean searchByUserRoles,
			int start, int end, OrderByComparator orderByComparator,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return search(
			keywords, keywords, null, null, completed, searchByUserRoles, false,
			start, end, orderByComparator, serviceContext);
	}

	public List<KaleoTaskInstanceToken> search(
			String taskName, String assetType, Date dueDateGT,
			Date dueDateLT, Boolean completed, Boolean searchByUserRoles,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery query = buildDynamicQuery(
			taskName, assetType, dueDateGT, dueDateLT, completed,
			searchByUserRoles, andOperator, serviceContext);

		return toKaleoTaskInstanceTokens(dynamicQuery(query));
	}

	public int searchCount(
			String keywords, Boolean completed, Boolean searchByUserRoles,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return searchCount(
			keywords, keywords, null, null, completed, searchByUserRoles, false,
			serviceContext);
	}

	public int searchCount(
			String taskName, String assetType, Date dueDateGT,
			Date dueDateLT, Boolean completed, Boolean searchByUserRoles,
			boolean andOperator, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			taskName, assetType, dueDateGT, dueDateLT, completed,
			searchByUserRoles, andOperator, serviceContext);

		return dynamicQueryCount(dynamicQuery);
	}

	public KaleoTaskInstanceToken updateDueDate(
			long kaleoTaskInstanceTokenId, Date dueDate,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoTaskInstanceToken kaleoTaskInstance =
			kaleoTaskInstanceTokenPersistence.findByPrimaryKey(
				kaleoTaskInstanceTokenId);

		kaleoTaskInstance.setModifiedDate(new Date());

		if (dueDate != null) {
			Calendar cal = CalendarFactoryUtil.getCalendar(
				LocaleUtil.getDefault());

			cal.setTime(dueDate);

			kaleoTaskInstance.setDueDate(cal.getTime());
		}

		kaleoTaskInstanceTokenPersistence.update(kaleoTaskInstance, false);

		return kaleoTaskInstance;
	}

	protected void addCompletedCriterion(
		DynamicQuery dynamicQuery, Boolean completed) {

		if (completed == null) {
			return;
		}

		dynamicQuery.add(
			PropertyFactoryUtil.forName("completed").eq(completed));
	}

	protected void addSearchByUserRolesCriterion(
			DynamicQuery dynamicQuery, Boolean searchByUserRoles,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (searchByUserRoles == null) {
			return;
		}

		if (!searchByUserRoles) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("assigneeClassName").eq(
					User.class.getName()));
			dynamicQuery.add(
				PropertyFactoryUtil.forName("assigneeClassPK").eq(
					serviceContext.getUserId()));
			return;
		}

		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				Role.class.getName()));

		List<Long> roleIds = RoleRetrievalUtil.getRoleIds(serviceContext);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").in(
				roleIds.toArray(new Long[roleIds.size()])));
	}

	protected DynamicQuery buildDynamicQuery(
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		List<Long> roleIds, Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				Role.class.getName()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").in(
				roleIds.toArray(new Long[roleIds.size()])));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		long kaleoInstanceId, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoInstanceId").eq(
				kaleoInstanceId));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		String assigneeClassName, long assigneeClassPK, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassName").eq(
				assigneeClassName));
		dynamicQuery.add(
			PropertyFactoryUtil.forName("assigneeClassPK").eq(assigneeClassPK));

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
			String taskName, String assetType, Date dueDateGT, Date dueDateLT,
			Boolean completed, Boolean searchByUserRoles, boolean andOperator,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoTaskInstanceToken.class);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(
				serviceContext.getCompanyId()));

		if (Validator.isNotNull(taskName) || Validator.isNotNull(assetType) ||
			(dueDateGT != null) || (dueDateLT != null)) {

			Junction junction = null;

			if (andOperator) {
				junction = RestrictionsFactoryUtil.conjunction();
			}
			else {
				junction = RestrictionsFactoryUtil.disjunction();
			}

			if (Validator.isNotNull(taskName)) {
				String[] taskNameKeywords = StringUtil.split(
					taskName, StringPool.SPACE);

				for (String taskNameKeyword : taskNameKeywords) {
					junction.add(
						PropertyFactoryUtil.forName("kaleoTaskName").like(
							taskNameKeyword));
				}
			}

			if (Validator.isNotNull(assetType)) {
				String[] assetTypeKeywords = StringUtil.split(
					assetType, StringPool.SPACE);

				for (String assetTypeKeyword : assetTypeKeywords) {
					junction.add(
						PropertyFactoryUtil.forName("context").like(
							assetTypeKeyword));
				}
			}

			if (dueDateGT != null) {
				junction.add(
					PropertyFactoryUtil.forName("dueDate").ge(dueDateGT));
			}

			if (dueDateLT != null) {
				junction.add(
					PropertyFactoryUtil.forName("dueDate").lt(dueDateLT));
			}

			dynamicQuery.add(junction);
		}

		addSearchByUserRolesCriterion(
			dynamicQuery, searchByUserRoles, serviceContext);

		addCompletedCriterion(dynamicQuery, completed);

		return dynamicQuery;
	}

	protected List<KaleoTaskInstanceToken> toKaleoTaskInstanceTokens(
		List<Object> results) {

		List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
			new ArrayList<KaleoTaskInstanceToken>(results.size());

		for (Object result : results) {
			kaleoTaskInstanceTokens.add((KaleoTaskInstanceToken)result);
		}

		return kaleoTaskInstanceTokens;
	}

}