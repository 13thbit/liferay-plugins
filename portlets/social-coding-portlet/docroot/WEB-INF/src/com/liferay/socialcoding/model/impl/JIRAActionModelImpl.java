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

package com.liferay.socialcoding.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.model.JIRAActionSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="JIRAActionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the jiraaction table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAActionImpl
 * @see       com.liferay.socialcoding.model.JIRAAction
 * @see       com.liferay.socialcoding.model.JIRAActionModel
 * @generated
 */
public class JIRAActionModelImpl extends BaseModelImpl<JIRAAction> {
	public static final String TABLE_NAME = "jiraaction";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", new Integer(Types.BIGINT) },
			{ "author", new Integer(Types.VARCHAR) },
			{ "created", new Integer(Types.TIMESTAMP) },
			{ "updated", new Integer(Types.TIMESTAMP) },
			{ "issueid", new Integer(Types.BIGINT) },
			{ "actiontype", new Integer(Types.VARCHAR) },
			{ "actionbody", new Integer(Types.VARCHAR) },
			{ "actionlevel", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table jiraaction (id LONG not null primary key,author VARCHAR(75) null,created DATE null,updated DATE null,issueid LONG,actiontype VARCHAR(75) null,actionbody VARCHAR(75) null,actionlevel VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table jiraaction";
	public static final String ORDER_BY_JPQL = " ORDER BY jiraAction.modifiedDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY jiraaction.updated DESC";
	public static final String DATA_SOURCE = "jiraDataSource";
	public static final String SESSION_FACTORY = "jiraSessionFactory";
	public static final String TX_MANAGER = "jiraTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.socialcoding.model.JIRAAction"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.socialcoding.model.JIRAAction"),
			true);

	public static JIRAAction toModel(JIRAActionSoap soapModel) {
		JIRAAction model = new JIRAActionImpl();

		model.setJiraActionId(soapModel.getJiraActionId());
		model.setJiraUserId(soapModel.getJiraUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setJiraIssueId(soapModel.getJiraIssueId());
		model.setType(soapModel.getType());
		model.setBody(soapModel.getBody());
		model.setJiraGroupName(soapModel.getJiraGroupName());

		return model;
	}

	public static List<JIRAAction> toModels(JIRAActionSoap[] soapModels) {
		List<JIRAAction> models = new ArrayList<JIRAAction>(soapModels.length);

		for (JIRAActionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.socialcoding.model.JIRAAction"));

	public JIRAActionModelImpl() {
	}

	public long getPrimaryKey() {
		return _jiraActionId;
	}

	public void setPrimaryKey(long pk) {
		setJiraActionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraActionId);
	}

	public long getJiraActionId() {
		return _jiraActionId;
	}

	public void setJiraActionId(long jiraActionId) {
		_jiraActionId = jiraActionId;
	}

	public String getJiraUserId() {
		if (_jiraUserId == null) {
			return StringPool.BLANK;
		}
		else {
			return _jiraUserId;
		}
	}

	public void setJiraUserId(String jiraUserId) {
		_jiraUserId = jiraUserId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
	}

	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	public void setType(String type) {
		_type = type;
	}

	public String getBody() {
		if (_body == null) {
			return StringPool.BLANK;
		}
		else {
			return _body;
		}
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getJiraGroupName() {
		if (_jiraGroupName == null) {
			return StringPool.BLANK;
		}
		else {
			return _jiraGroupName;
		}
	}

	public void setJiraGroupName(String jiraGroupName) {
		_jiraGroupName = jiraGroupName;
	}

	public JIRAAction toEscapedModel() {
		if (isEscapedModel()) {
			return (JIRAAction)this;
		}
		else {
			JIRAAction model = new JIRAActionImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setJiraActionId(getJiraActionId());
			model.setJiraUserId(HtmlUtil.escape(getJiraUserId()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setJiraIssueId(getJiraIssueId());
			model.setType(HtmlUtil.escape(getType()));
			model.setBody(HtmlUtil.escape(getBody()));
			model.setJiraGroupName(HtmlUtil.escape(getJiraGroupName()));

			model = (JIRAAction)Proxy.newProxyInstance(JIRAAction.class.getClassLoader(),
					new Class[] { JIRAAction.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					JIRAAction.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		JIRAActionImpl clone = new JIRAActionImpl();

		clone.setJiraActionId(getJiraActionId());
		clone.setJiraUserId(getJiraUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setJiraIssueId(getJiraIssueId());
		clone.setType(getType());
		clone.setBody(getBody());
		clone.setJiraGroupName(getJiraGroupName());

		return clone;
	}

	public int compareTo(JIRAAction jiraAction) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				jiraAction.getModifiedDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		JIRAAction jiraAction = null;

		try {
			jiraAction = (JIRAAction)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = jiraAction.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{jiraActionId=");
		sb.append(getJiraActionId());
		sb.append(", jiraUserId=");
		sb.append(getJiraUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", jiraIssueId=");
		sb.append(getJiraIssueId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", body=");
		sb.append(getBody());
		sb.append(", jiraGroupName=");
		sb.append(getJiraGroupName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAAction");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraActionId</column-name><column-value><![CDATA[");
		sb.append(getJiraActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraUserId</column-name><column-value><![CDATA[");
		sb.append(getJiraUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraIssueId</column-name><column-value><![CDATA[");
		sb.append(getJiraIssueId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraGroupName</column-name><column-value><![CDATA[");
		sb.append(getJiraGroupName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraActionId;
	private String _jiraUserId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _jiraIssueId;
	private String _type;
	private String _body;
	private String _jiraGroupName;
	private transient ExpandoBridge _expandoBridge;
}