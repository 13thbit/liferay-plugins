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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManager;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

/**
 * <a href="WorkflowLogManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowLogManagerImpl implements WorkflowLogManager {

	public List<WorkflowLog> getWorkflowLogs(
			long companyId, long workflowTaskId, int start,	int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowLogs(
			companyId, -1, workflowTaskId, start, end, orderByComparator);
	}

	public List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
			long companyId, long workflowInstanceId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowLogs(
			companyId, workflowInstanceId, -1, start, end, orderByComparator);
	}

	public int getWorkflowLogCount(long companyId, long workflowTaskId)
		throws WorkflowException {

		return getWorkflowLogsCount(companyId, -1, workflowTaskId);
	}

	public int getWorkflowLogCountByWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		return getWorkflowLogsCount(companyId, workflowInstanceId, -1);
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		_jbpmConfiguration = jbpmConfiguration;
	}

	protected void addJoin(
		Criteria criteria, long workflowInstanceId, long workflowTaskId) {

		if (workflowInstanceId > 0) {
			criteria.createAlias("taskInstance", "tskInst");
			criteria.createAlias(
				"tskInst.processInstance", "processInst");

			criteria.add(
				Restrictions.eq("processInst.id", workflowInstanceId));
		}
		else {
			criteria.add(
				Restrictions.eq("taskInstance.id", workflowTaskId));
		}
	}

	protected List<WorkflowLog> getWorkflowLogs(
			long companyId, long workflowInstanceId, long workflowTaskId,
			int start, int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Session session = jbpmContext.getSession();

			Criteria criteria = session.createCriteria(WorkflowLogImpl.class);

			addJoin(criteria, workflowInstanceId, workflowTaskId);

			List<WorkflowLog> workflowLogs = criteria.list();

			Collections.sort(workflowLogs, orderByComparator);

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				workflowLogs = ListUtil.subList(workflowLogs, start, end);
			}

			return workflowLogs;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	protected int getWorkflowLogsCount(
			long companyId, long workflowInstanceId, long workflowTaskId)
		throws WorkflowException {

		JbpmContext jbpmContext = _jbpmConfiguration.createJbpmContext();

		try {
			Session session = jbpmContext.getSession();

			Criteria criteria = session.createCriteria(WorkflowLogImpl.class);

			addJoin(criteria, workflowInstanceId, workflowTaskId);

			criteria.setProjection(Projections.rowCount());

			List<Long> results = criteria.list();

			if (results.isEmpty()) {
				return 0;
			}
			else {
				return (results.get(0)).intValue();
			}
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
		finally {
			jbpmContext.close();
		}
	}

	private JbpmConfiguration _jbpmConfiguration;

}