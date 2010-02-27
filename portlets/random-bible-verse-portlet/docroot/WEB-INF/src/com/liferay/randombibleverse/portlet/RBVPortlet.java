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

package com.liferay.randombibleverse.portlet;

import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;

/**
 * <a href="RBVPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RBVPortlet extends MVCPortlet {

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		if (actionRequest.getPortletMode().equals(PortletMode.EDIT)) {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.UPDATE)) {
				String language = ParamUtil.getString(
					actionRequest, "language");

				PortletPreferences preferences = actionRequest.getPreferences();

				preferences.setValue("language", language);

				preferences.store();

				SessionMessages.add(
					actionRequest,
					getPortletConfig().getPortletName() + ".doEdit");
			}
		}
	}

}