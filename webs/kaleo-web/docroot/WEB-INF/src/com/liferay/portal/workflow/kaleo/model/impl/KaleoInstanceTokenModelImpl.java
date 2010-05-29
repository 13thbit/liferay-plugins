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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceTokenSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoInstanceTokenModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoInstanceToken table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstanceTokenImpl
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoInstanceTokenModel
 * @generated
 */
public class KaleoInstanceTokenModelImpl extends BaseModelImpl<KaleoInstanceToken> {
	public static final String TABLE_NAME = "Kaleo_KaleoInstanceToken";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoInstanceTokenId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "groupId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "kaleoDefinitionId", new Integer(Types.BIGINT) },
			{ "kaleoInstanceId", new Integer(Types.BIGINT) },
			{ "parentKaleoInstanceTokenId", new Integer(Types.BIGINT) },
			{ "currentKaleoNodeId", new Integer(Types.BIGINT) },
			{ "currentKaleoNodeName", new Integer(Types.VARCHAR) },
			{ "completed", new Integer(Types.BOOLEAN) },
			{ "completionDate", new Integer(Types.TIMESTAMP) }
		};
	public static final String TABLE_SQL_CREATE = "create table Kaleo_KaleoInstanceToken (kaleoInstanceTokenId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionId LONG,kaleoInstanceId LONG,parentKaleoInstanceTokenId LONG,currentKaleoNodeId LONG,currentKaleoNodeName VARCHAR(200) null,completed BOOLEAN,completionDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Kaleo_KaleoInstanceToken";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoInstanceToken.kaleoInstanceTokenId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Kaleo_KaleoInstanceToken.kaleoInstanceTokenId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"),
			true);

	public static KaleoInstanceToken toModel(KaleoInstanceTokenSoap soapModel) {
		KaleoInstanceToken model = new KaleoInstanceTokenImpl();

		model.setKaleoInstanceTokenId(soapModel.getKaleoInstanceTokenId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKaleoDefinitionId(soapModel.getKaleoDefinitionId());
		model.setKaleoInstanceId(soapModel.getKaleoInstanceId());
		model.setParentKaleoInstanceTokenId(soapModel.getParentKaleoInstanceTokenId());
		model.setCurrentKaleoNodeId(soapModel.getCurrentKaleoNodeId());
		model.setCurrentKaleoNodeName(soapModel.getCurrentKaleoNodeName());
		model.setCompleted(soapModel.getCompleted());
		model.setCompletionDate(soapModel.getCompletionDate());

		return model;
	}

	public static List<KaleoInstanceToken> toModels(
		KaleoInstanceTokenSoap[] soapModels) {
		List<KaleoInstanceToken> models = new ArrayList<KaleoInstanceToken>(soapModels.length);

		for (KaleoInstanceTokenSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken"));

	public KaleoInstanceTokenModelImpl() {
	}

	public long getPrimaryKey() {
		return _kaleoInstanceTokenId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoInstanceTokenId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoInstanceTokenId);
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
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

	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getParentKaleoInstanceTokenId() {
		return _parentKaleoInstanceTokenId;
	}

	public void setParentKaleoInstanceTokenId(long parentKaleoInstanceTokenId) {
		_parentKaleoInstanceTokenId = parentKaleoInstanceTokenId;
	}

	public long getCurrentKaleoNodeId() {
		return _currentKaleoNodeId;
	}

	public void setCurrentKaleoNodeId(long currentKaleoNodeId) {
		_currentKaleoNodeId = currentKaleoNodeId;
	}

	public String getCurrentKaleoNodeName() {
		if (_currentKaleoNodeName == null) {
			return StringPool.BLANK;
		}
		else {
			return _currentKaleoNodeName;
		}
	}

	public void setCurrentKaleoNodeName(String currentKaleoNodeName) {
		_currentKaleoNodeName = currentKaleoNodeName;
	}

	public boolean getCompleted() {
		return _completed;
	}

	public boolean isCompleted() {
		return _completed;
	}

	public void setCompleted(boolean completed) {
		_completed = completed;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public KaleoInstanceToken toEscapedModel() {
		if (isEscapedModel()) {
			return (KaleoInstanceToken)this;
		}
		else {
			return (KaleoInstanceToken)Proxy.newProxyInstance(KaleoInstanceToken.class.getClassLoader(),
				new Class[] { KaleoInstanceToken.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KaleoInstanceToken.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		KaleoInstanceTokenImpl clone = new KaleoInstanceTokenImpl();

		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setParentKaleoInstanceTokenId(getParentKaleoInstanceTokenId());
		clone.setCurrentKaleoNodeId(getCurrentKaleoNodeId());
		clone.setCurrentKaleoNodeName(getCurrentKaleoNodeName());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());

		return clone;
	}

	public int compareTo(KaleoInstanceToken kaleoInstanceToken) {
		int value = 0;

		if (getKaleoInstanceTokenId() < kaleoInstanceToken.getKaleoInstanceTokenId()) {
			value = -1;
		}
		else if (getKaleoInstanceTokenId() > kaleoInstanceToken.getKaleoInstanceTokenId()) {
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

		KaleoInstanceToken kaleoInstanceToken = null;

		try {
			kaleoInstanceToken = (KaleoInstanceToken)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoInstanceToken.getPrimaryKey();

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
		StringBundler sb = new StringBundler(29);

		sb.append("{kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
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
		sb.append(", kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
		sb.append(", parentKaleoInstanceTokenId=");
		sb.append(getParentKaleoInstanceTokenId());
		sb.append(", currentKaleoNodeId=");
		sb.append(getCurrentKaleoNodeId());
		sb.append(", currentKaleoNodeName=");
		sb.append(getCurrentKaleoNodeName());
		sb.append(", completed=");
		sb.append(getCompleted());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentKaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getParentKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getCurrentKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getCurrentKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completed</column-name><column-value><![CDATA[");
		sb.append(getCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoInstanceTokenId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _parentKaleoInstanceTokenId;
	private long _currentKaleoNodeId;
	private String _currentKaleoNodeName;
	private boolean _completed;
	private Date _completionDate;
	private transient ExpandoBridge _expandoBridge;
}