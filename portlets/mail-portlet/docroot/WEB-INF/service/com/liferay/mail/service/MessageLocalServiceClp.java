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

package com.liferay.mail.service;

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="MessageLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MessageLocalServiceClp implements MessageLocalService {
	public MessageLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.mail.model.Message addMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(message);

		if (message == null) {
			paramObj0 = new NullWrapper("com.liferay.mail.model.Message");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMessage",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.mail.model.Message createMessage(long messageId) {
		Object paramObj0 = new LongWrapper(messageId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createMessage",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(messageId);

		try {
			_classLoaderProxy.invoke("deleteMessage", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteMessage(com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(message);

		if (message == null) {
			paramObj0 = new NullWrapper("com.liferay.mail.model.Message");
		}

		try {
			_classLoaderProxy.invoke("deleteMessage", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object paramObj3 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public int dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQueryCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.mail.model.Message getMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(messageId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMessage",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.mail.model.Message> getMessages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMessages",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.mail.model.Message>)ClpSerializer.translateOutput(returnObj);
	}

	public int getMessagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMessagesCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(message);

		if (message == null) {
			paramObj0 = new NullWrapper("com.liferay.mail.model.Message");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMessage",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(message);

		if (message == null) {
			paramObj0 = new NullWrapper("com.liferay.mail.model.Message");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMessage",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.mail.model.Message addMessage(long userId,
		long folderId, java.lang.String sender, java.lang.String recipientsTo,
		java.lang.String recipientsCc, java.lang.String recipientsBcc,
		java.util.Date sentDate, java.lang.String subject,
		java.lang.String body, java.lang.String flags, long size,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(folderId);

		Object paramObj2 = ClpSerializer.translateInput(sender);

		if (sender == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(recipientsTo);

		if (recipientsTo == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(recipientsCc);

		if (recipientsCc == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(recipientsBcc);

		if (recipientsBcc == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(sentDate);

		if (sentDate == null) {
			paramObj6 = new NullWrapper("java.util.Date");
		}

		Object paramObj7 = ClpSerializer.translateInput(subject);

		if (subject == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(body);

		if (body == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = ClpSerializer.translateInput(flags);

		if (flags == null) {
			paramObj9 = new NullWrapper("java.lang.String");
		}

		Object paramObj10 = new LongWrapper(size);

		Object paramObj11 = new LongWrapper(remoteMessageId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMessage",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.mail.model.Message> getCompanyMessages(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyMessages",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.mail.model.Message>)ClpSerializer.translateOutput(returnObj);
	}

	public int getCompanyMessagesCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyMessagesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.mail.model.Message> getFolderMessages(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(folderId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFolderMessages",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.mail.model.Message>)ClpSerializer.translateOutput(returnObj);
	}

	public int getFolderMessagesCount(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(folderId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFolderMessagesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.mail.model.Message getMessage(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(folderId);

		Object paramObj1 = new LongWrapper(remoteMessageId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMessage",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.mail.model.Message updateMessage(long messageId,
		long folderId, java.lang.String sender, java.lang.String recipientsTo,
		java.lang.String recipientsCc, java.lang.String recipientsBcc,
		java.util.Date sentDate, java.lang.String subject,
		java.lang.String body, java.lang.String flags, long size,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(messageId);

		Object paramObj1 = new LongWrapper(folderId);

		Object paramObj2 = ClpSerializer.translateInput(sender);

		if (sender == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(recipientsTo);

		if (recipientsTo == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(recipientsCc);

		if (recipientsCc == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(recipientsBcc);

		if (recipientsBcc == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(sentDate);

		if (sentDate == null) {
			paramObj6 = new NullWrapper("java.util.Date");
		}

		Object paramObj7 = ClpSerializer.translateInput(subject);

		if (subject == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(body);

		if (body == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = ClpSerializer.translateInput(flags);

		if (flags == null) {
			paramObj9 = new NullWrapper("java.lang.String");
		}

		Object paramObj10 = new LongWrapper(size);

		Object paramObj11 = new LongWrapper(remoteMessageId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMessage",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.mail.model.Message)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
}