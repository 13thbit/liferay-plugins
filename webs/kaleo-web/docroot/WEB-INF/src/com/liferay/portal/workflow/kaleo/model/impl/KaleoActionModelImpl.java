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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoActionSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoActionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoAction table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoActionImpl
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoAction
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoActionModel
 * @generated
 */
public class KaleoActionModelImpl extends BaseModelImpl<KaleoAction> {
	public static final String TABLE_NAME = "Kaleo_KaleoAction";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoActionId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "kaleoDefinitionId", new Integer(Types.BIGINT) },
			{ "kaleoNodeId", new Integer(Types.BIGINT) },
			{ "kaleoNodeName", new Integer(Types.VARCHAR) },
			{ "name", new Integer(Types.VARCHAR) },
			{ "description", new Integer(Types.VARCHAR) },
			{ "language", new Integer(Types.VARCHAR) },
			{ "script", new Integer(Types.CLOB) },
			{ "executionType", new Integer(Types.VARCHAR) },
			{ "executionOrder", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table Kaleo_KaleoAction (kaleoActionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionId LONG,kaleoNodeId LONG,kaleoNodeName VARCHAR(200) null,name VARCHAR(200) null,description VARCHAR(75) null,language VARCHAR(75) null,script TEXT null,executionType VARCHAR(75) null,executionOrder INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table Kaleo_KaleoAction";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoAction.executionOrder ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Kaleo_KaleoAction.executionOrder ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoAction"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoAction"),
			true);

	public static KaleoAction toModel(KaleoActionSoap soapModel) {
		KaleoAction model = new KaleoActionImpl();

		model.setKaleoActionId(soapModel.getKaleoActionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKaleoDefinitionId(soapModel.getKaleoDefinitionId());
		model.setKaleoNodeId(soapModel.getKaleoNodeId());
		model.setKaleoNodeName(soapModel.getKaleoNodeName());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setLanguage(soapModel.getLanguage());
		model.setScript(soapModel.getScript());
		model.setExecutionType(soapModel.getExecutionType());
		model.setExecutionOrder(soapModel.getExecutionOrder());

		return model;
	}

	public static List<KaleoAction> toModels(KaleoActionSoap[] soapModels) {
		List<KaleoAction> models = new ArrayList<KaleoAction>(soapModels.length);

		for (KaleoActionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoAction"));

	public KaleoActionModelImpl() {
	}

	public long getPrimaryKey() {
		return _kaleoActionId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoActionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoActionId);
	}

	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
	}

	public String getKaleoNodeName() {
		return GetterUtil.getString(_kaleoNodeName);
	}

	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getLanguage() {
		return GetterUtil.getString(_language);
	}

	public void setLanguage(String language) {
		_language = language;
	}

	public String getScript() {
		return GetterUtil.getString(_script);
	}

	public void setScript(String script) {
		_script = script;
	}

	public String getExecutionType() {
		return GetterUtil.getString(_executionType);
	}

	public void setExecutionType(String executionType) {
		_executionType = executionType;
	}

	public int getExecutionOrder() {
		return _executionOrder;
	}

	public void setExecutionOrder(int executionOrder) {
		_executionOrder = executionOrder;
	}

	public KaleoAction toEscapedModel() {
		if (isEscapedModel()) {
			return (KaleoAction)this;
		}
		else {
			KaleoAction model = new KaleoActionImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setKaleoActionId(getKaleoActionId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setKaleoDefinitionId(getKaleoDefinitionId());
			model.setKaleoNodeId(getKaleoNodeId());
			model.setKaleoNodeName(HtmlUtil.escape(getKaleoNodeName()));
			model.setName(HtmlUtil.escape(getName()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setLanguage(HtmlUtil.escape(getLanguage()));
			model.setScript(HtmlUtil.escape(getScript()));
			model.setExecutionType(HtmlUtil.escape(getExecutionType()));
			model.setExecutionOrder(getExecutionOrder());

			model = (KaleoAction)Proxy.newProxyInstance(KaleoAction.class.getClassLoader(),
					new Class[] { KaleoAction.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KaleoAction.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		KaleoActionImpl clone = new KaleoActionImpl();

		clone.setKaleoActionId(getKaleoActionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeId(getKaleoNodeId());
		clone.setKaleoNodeName(getKaleoNodeName());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setLanguage(getLanguage());
		clone.setScript(getScript());
		clone.setExecutionType(getExecutionType());
		clone.setExecutionOrder(getExecutionOrder());

		return clone;
	}

	public int compareTo(KaleoAction kaleoAction) {
		int value = 0;

		if (getExecutionOrder() < kaleoAction.getExecutionOrder()) {
			value = -1;
		}
		else if (getExecutionOrder() > kaleoAction.getExecutionOrder()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KaleoAction kaleoAction = null;

		try {
			kaleoAction = (KaleoAction)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoAction.getPrimaryKey();

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
		StringBundler sb = new StringBundler(31);

		sb.append("{kaleoActionId=");
		sb.append(getKaleoActionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", kaleoNodeName=");
		sb.append(getKaleoNodeName());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", language=");
		sb.append(getLanguage());
		sb.append(", script=");
		sb.append(getScript());
		sb.append(", executionType=");
		sb.append(getExecutionType());
		sb.append(", executionOrder=");
		sb.append(getExecutionOrder());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoAction");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoActionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
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
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>language</column-name><column-value><![CDATA[");
		sb.append(getLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>script</column-name><column-value><![CDATA[");
		sb.append(getScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executionType</column-name><column-value><![CDATA[");
		sb.append(getExecutionType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executionOrder</column-name><column-value><![CDATA[");
		sb.append(getExecutionOrder());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoActionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _kaleoNodeName;
	private String _name;
	private String _description;
	private String _language;
	private String _script;
	private String _executionType;
	private int _executionOrder;
	private transient ExpandoBridge _expandoBridge;
}