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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchLogException;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoLogPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLogPersistence
 * @see       KaleoLogUtil
 * @generated
 */
public class KaleoLogPersistenceImpl extends BasePersistenceImpl<KaleoLog>
	implements KaleoLogPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKaleoTaskInstanceTokenId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKITI_T",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKITI_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKITI_T",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KITI_KNI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKITI_KNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_KITI_KNI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKITI_KNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_KNI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKITI_KNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(KaleoLog kaleoLog) {
		EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey(), kaleoLog);
	}

	public void cacheResult(List<KaleoLog> kaleoLogs) {
		for (KaleoLog kaleoLog : kaleoLogs) {
			if (EntityCacheUtil.getResult(
						KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
						KaleoLogImpl.class, kaleoLog.getPrimaryKey(), this) == null) {
				cacheResult(kaleoLog);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoLogImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoLogImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public KaleoLog create(long kaleoLogId) {
		KaleoLog kaleoLog = new KaleoLogImpl();

		kaleoLog.setNew(true);
		kaleoLog.setPrimaryKey(kaleoLogId);

		return kaleoLog;
	}

	public KaleoLog remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoLog remove(long kaleoLogId)
		throws NoSuchLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoLog kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class,
					new Long(kaleoLogId));

			if (kaleoLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoLogId);
				}

				throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoLogId);
			}

			return remove(kaleoLog);
		}
		catch (NoSuchLogException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KaleoLog remove(KaleoLog kaleoLog) throws SystemException {
		for (ModelListener<KaleoLog> listener : listeners) {
			listener.onBeforeRemove(kaleoLog);
		}

		kaleoLog = removeImpl(kaleoLog);

		for (ModelListener<KaleoLog> listener : listeners) {
			listener.onAfterRemove(kaleoLog);
		}

		return kaleoLog;
	}

	protected KaleoLog removeImpl(KaleoLog kaleoLog) throws SystemException {
		kaleoLog = toUnwrappedModel(kaleoLog);

		Session session = null;

		try {
			session = openSession();

			if (kaleoLog.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoLogImpl.class,
						kaleoLog.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoLog);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey());

		return kaleoLog;
	}

	public KaleoLog updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws SystemException {
		kaleoLog = toUnwrappedModel(kaleoLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoLog, merge);

			kaleoLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey(), kaleoLog);

		return kaleoLog;
	}

	protected KaleoLog toUnwrappedModel(KaleoLog kaleoLog) {
		if (kaleoLog instanceof KaleoLogImpl) {
			return kaleoLog;
		}

		KaleoLogImpl kaleoLogImpl = new KaleoLogImpl();

		kaleoLogImpl.setNew(kaleoLog.isNew());
		kaleoLogImpl.setPrimaryKey(kaleoLog.getPrimaryKey());

		kaleoLogImpl.setKaleoLogId(kaleoLog.getKaleoLogId());
		kaleoLogImpl.setCompanyId(kaleoLog.getCompanyId());
		kaleoLogImpl.setUserId(kaleoLog.getUserId());
		kaleoLogImpl.setUserName(kaleoLog.getUserName());
		kaleoLogImpl.setCreateDate(kaleoLog.getCreateDate());
		kaleoLogImpl.setModifiedDate(kaleoLog.getModifiedDate());
		kaleoLogImpl.setKaleoInstanceId(kaleoLog.getKaleoInstanceId());
		kaleoLogImpl.setKaleoInstanceTokenId(kaleoLog.getKaleoInstanceTokenId());
		kaleoLogImpl.setKaleoTaskInstanceTokenId(kaleoLog.getKaleoTaskInstanceTokenId());
		kaleoLogImpl.setKaleoNodeId(kaleoLog.getKaleoNodeId());
		kaleoLogImpl.setKaleoNodeName(kaleoLog.getKaleoNodeName());
		kaleoLogImpl.setTerminalKaleoNode(kaleoLog.isTerminalKaleoNode());
		kaleoLogImpl.setKaleoActionId(kaleoLog.getKaleoActionId());
		kaleoLogImpl.setKaleoActionName(kaleoLog.getKaleoActionName());
		kaleoLogImpl.setKaleoActionDescription(kaleoLog.getKaleoActionDescription());
		kaleoLogImpl.setPreviousKaleoNodeId(kaleoLog.getPreviousKaleoNodeId());
		kaleoLogImpl.setPreviousKaleoNodeName(kaleoLog.getPreviousKaleoNodeName());
		kaleoLogImpl.setPreviousAssigneeClassName(kaleoLog.getPreviousAssigneeClassName());
		kaleoLogImpl.setPreviousAssigneeClassPK(kaleoLog.getPreviousAssigneeClassPK());
		kaleoLogImpl.setCurrentAssigneeClassName(kaleoLog.getCurrentAssigneeClassName());
		kaleoLogImpl.setCurrentAssigneeClassPK(kaleoLog.getCurrentAssigneeClassPK());
		kaleoLogImpl.setType(kaleoLog.getType());
		kaleoLogImpl.setComment(kaleoLog.getComment());
		kaleoLogImpl.setStartDate(kaleoLog.getStartDate());
		kaleoLogImpl.setEndDate(kaleoLog.getEndDate());
		kaleoLogImpl.setDuration(kaleoLog.getDuration());
		kaleoLogImpl.setContext(kaleoLog.getContext());

		return kaleoLogImpl;
	}

	public KaleoLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoLog findByPrimaryKey(long kaleoLogId)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = fetchByPrimaryKey(kaleoLogId);

		if (kaleoLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoLogId);
			}

			throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoLogId);
		}

		return kaleoLog;
	}

	public KaleoLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoLog fetchByPrimaryKey(long kaleoLogId)
		throws SystemException {
		KaleoLog kaleoLog = (KaleoLog)EntityCacheUtil.getResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
				KaleoLogImpl.class, kaleoLogId, this);

		if (kaleoLog == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class,
						new Long(kaleoLogId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoLog != null) {
					cacheResult(kaleoLog);
				}

				closeSession(session);
			}
		}

		return kaleoLog;
	}

	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoTaskInstanceTokenId) };

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws SystemException {
		return findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId, start,
			end, null);
	}

	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoTaskInstanceTokenId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_KALEOTASKINSTANCETOKENID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoLog findByKaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoLog findByKaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		List<KaleoLog> list = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoLog[] findByKaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoLogId, long kaleoTaskInstanceTokenId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		int count = countByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(kaleoTaskInstanceTokenId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count,
					orderByComparator, kaleoLog);

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = (KaleoLog)objArray[0];
			array[1] = (KaleoLog)objArray[1];
			array[2] = (KaleoLog)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoInstanceTokenId), type };

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KITI_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

				if (type == null) {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
				}
				else {
					if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
					}
				}

				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (type != null) {
					qPos.add(type);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KITI_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type,
		int start, int end) throws SystemException {
		return findByKITI_T(kaleoInstanceTokenId, type, start, end, null);
	}

	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoInstanceTokenId),
				
				type,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_KITI_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(4 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(4);
				}

				query.append(_SQL_SELECT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

				if (type == null) {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
				}
				else {
					if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
					}
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_KITI_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoLog findByKITI_T_First(long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKITI_T(kaleoInstanceTokenId, type, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoLog findByKITI_T_Last(long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKITI_T(kaleoInstanceTokenId, type);

		List<KaleoLog> list = findByKITI_T(kaleoInstanceTokenId, type,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoLog[] findByKITI_T_PrevAndNext(long kaleoLogId,
		long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		int count = countByKITI_T(kaleoInstanceTokenId, type);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(kaleoInstanceTokenId);

			if (type != null) {
				qPos.add(type);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count,
					orderByComparator, kaleoLog);

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = (KaleoLog)objArray[0];
			array[1] = (KaleoLog)objArray[1];
			array[2] = (KaleoLog)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoLog> findByKITI_KNI_T(long kaleoInstanceTokenId,
		long kaleoNodeId, String type) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoInstanceTokenId), new Long(kaleoNodeId),
				
				type
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KITI_KNI_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(5);

				query.append(_SQL_SELECT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

				query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

				if (type == null) {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
				}
				else {
					if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
					}
				}

				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoNodeId);

				if (type != null) {
					qPos.add(type);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KITI_KNI_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<KaleoLog> findByKITI_KNI_T(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, int start, int end)
		throws SystemException {
		return findByKITI_KNI_T(kaleoInstanceTokenId, kaleoNodeId, type, start,
			end, null);
	}

	public List<KaleoLog> findByKITI_KNI_T(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoInstanceTokenId), new Long(kaleoNodeId),
				
				type,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_KITI_KNI_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(5 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(5);
				}

				query.append(_SQL_SELECT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

				query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

				if (type == null) {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
				}
				else {
					if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
					}
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoNodeId);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_KITI_KNI_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoLog findByKITI_KNI_T_First(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKITI_KNI_T(kaleoInstanceTokenId,
				kaleoNodeId, type, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoLog findByKITI_KNI_T_Last(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKITI_KNI_T(kaleoInstanceTokenId, kaleoNodeId, type);

		List<KaleoLog> list = findByKITI_KNI_T(kaleoInstanceTokenId,
				kaleoNodeId, type, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoLog[] findByKITI_KNI_T_PrevAndNext(long kaleoLogId,
		long kaleoInstanceTokenId, long kaleoNodeId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		int count = countByKITI_KNI_T(kaleoInstanceTokenId, kaleoNodeId, type);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(kaleoInstanceTokenId);

			qPos.add(kaleoNodeId);

			if (type != null) {
				qPos.add(type);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count,
					orderByComparator, kaleoLog);

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = (KaleoLog)objArray[0];
			array[1] = (KaleoLog)objArray[1];
			array[2] = (KaleoLog)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KaleoLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoLog> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoLog> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_KALEOLOG);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEOLOG.concat(KaleoLogModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		for (KaleoLog kaleoLog : findByKaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId)) {
			remove(kaleoLog);
		}
	}

	public void removeByKITI_T(long kaleoInstanceTokenId, String type)
		throws SystemException {
		for (KaleoLog kaleoLog : findByKITI_T(kaleoInstanceTokenId, type)) {
			remove(kaleoLog);
		}
	}

	public void removeByKITI_KNI_T(long kaleoInstanceTokenId, long kaleoNodeId,
		String type) throws SystemException {
		for (KaleoLog kaleoLog : findByKITI_KNI_T(kaleoInstanceTokenId,
				kaleoNodeId, type)) {
			remove(kaleoLog);
		}
	}

	public void removeAll() throws SystemException {
		for (KaleoLog kaleoLog : findAll()) {
			remove(kaleoLog);
		}
	}

	public int countByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoTaskInstanceTokenId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKITI_T(long kaleoInstanceTokenId, String type)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoInstanceTokenId), type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_T,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

				if (type == null) {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
				}
				else {
					if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (type != null) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKITI_KNI_T(long kaleoInstanceTokenId, long kaleoNodeId,
		String type) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoInstanceTokenId), new Long(kaleoNodeId),
				
				type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_KNI_T,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_COUNT_KALEOLOG_WHERE);

				query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

				query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

				if (type == null) {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
				}
				else {
					if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoNodeId);

				if (type != null) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_KNI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOLOG);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoLog>> listenersList = new ArrayList<ModelListener<KaleoLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoLog>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceAssignmentPersistence.class)
	protected KaleoTaskInstanceAssignmentPersistence kaleoTaskInstanceAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOLOG = "SELECT kaleoLog FROM KaleoLog kaleoLog";
	private static final String _SQL_SELECT_KALEOLOG_WHERE = "SELECT kaleoLog FROM KaleoLog kaleoLog WHERE ";
	private static final String _SQL_COUNT_KALEOLOG = "SELECT COUNT(kaleoLog) FROM KaleoLog kaleoLog";
	private static final String _SQL_COUNT_KALEOLOG_WHERE = "SELECT COUNT(kaleoLog) FROM KaleoLog kaleoLog WHERE ";
	private static final String _FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2 =
		"kaleoLog.kaleoTaskInstanceTokenId = ?";
	private static final String _FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2 = "kaleoLog.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_1 = "kaleoLog.type IS NULL";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_2 = "kaleoLog.type = ?";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_3 = "(kaleoLog.type IS NULL OR kaleoLog.type = ?)";
	private static final String _FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2 =
		"kaleoLog.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2 = "kaleoLog.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_KNI_T_TYPE_1 = "kaleoLog.type IS NULL";
	private static final String _FINDER_COLUMN_KITI_KNI_T_TYPE_2 = "kaleoLog.type = ?";
	private static final String _FINDER_COLUMN_KITI_KNI_T_TYPE_3 = "(kaleoLog.type IS NULL OR kaleoLog.type = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoLog exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoLogPersistenceImpl.class);
}