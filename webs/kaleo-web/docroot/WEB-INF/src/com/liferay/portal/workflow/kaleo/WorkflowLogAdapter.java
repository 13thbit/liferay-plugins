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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.workflow.DefaultWorkflowLog;
import com.liferay.portal.model.Role;
import com.liferay.portal.workflow.kaleo.definition.LogType;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;

/**
 * <a href="WorkflowLogAdapter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowLogAdapter extends DefaultWorkflowLog {

	public WorkflowLogAdapter(KaleoLog kaleoLogEntry) {
		setComment(kaleoLogEntry.getComment());
		setCreateDate(kaleoLogEntry.getCreateDate());
		setPreviousState(kaleoLogEntry.getPreviousKaleoNodeName());

		long previousAssigneeClassPK =
			kaleoLogEntry.getCurrentAssigneeClassPK();

		if (previousAssigneeClassPK > 0) {
			String previousAssigneeClassName =
				kaleoLogEntry.getCurrentAssigneeClassName();

			if (previousAssigneeClassName.equals(Role.class.getName())) {
				setRoleId(previousAssigneeClassPK);
			}
			else {
				setUserId(previousAssigneeClassPK);
			}
		}

		long currentAssigneeClassPK = kaleoLogEntry.getCurrentAssigneeClassPK();

		if (currentAssigneeClassPK > 0) {
			String currentAssigneeClassName =
				kaleoLogEntry.getCurrentAssigneeClassName();

			if (currentAssigneeClassName.equals(Role.class.getName())) {
				setRoleId(currentAssigneeClassPK);
			}
			else {
				setUserId(currentAssigneeClassPK);
			}
		}

		setState(kaleoLogEntry.getKaleoNodeName());
		setType(convertType(kaleoLogEntry.getType()));
		setWorkflowLogId(kaleoLogEntry.getKaleoLogId());
		setWorkflowTaskId(kaleoLogEntry.getKaleoTaskInstanceTokenId());
	}

	protected int convertType(String type) {
		LogType logType = LogType.valueOf(type);

		if (logType.equals(LogType.NODE_EXIT)) {
			return TRANSITION;
		}

		if (logType.equals(LogType.TASK_ASSIGNMENT)) {
			return TASK_ASSIGN;
		}

		return -1;
	}

}