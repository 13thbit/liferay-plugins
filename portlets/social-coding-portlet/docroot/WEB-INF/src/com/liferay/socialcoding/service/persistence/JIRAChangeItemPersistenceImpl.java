/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchJIRAChangeItemException;
import com.liferay.socialcoding.model.JIRAChangeItem;
import com.liferay.socialcoding.model.impl.JIRAChangeItemImpl;
import com.liferay.socialcoding.model.impl.JIRAChangeItemModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="JIRAChangeItemPersistenceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeItemPersistenceImpl extends BasePersistenceImpl<JIRAChangeItem>
	implements JIRAChangeItemPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraChangeGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraChangeGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByJiraChangeGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(JIRAChangeItem jiraChangeItem) {
		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);
	}

	public void cacheResult(List<JIRAChangeItem> jiraChangeItems) {
		for (JIRAChangeItem jiraChangeItem : jiraChangeItems) {
			if (EntityCacheUtil.getResult(
						JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeItemImpl.class,
						jiraChangeItem.getPrimaryKey(), this) == null) {
				cacheResult(jiraChangeItem);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(JIRAChangeItemImpl.class.getName());
		EntityCacheUtil.clearCache(JIRAChangeItemImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public JIRAChangeItem create(long jiraChangeItemId) {
		JIRAChangeItem jiraChangeItem = new JIRAChangeItemImpl();

		jiraChangeItem.setNew(true);
		jiraChangeItem.setPrimaryKey(jiraChangeItemId);

		return jiraChangeItem;
	}

	public JIRAChangeItem remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public JIRAChangeItem remove(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeItem jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
					new Long(jiraChangeItemId));

			if (jiraChangeItem == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						jiraChangeItemId);
				}

				throw new NoSuchJIRAChangeItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					jiraChangeItemId);
			}

			return remove(jiraChangeItem);
		}
		catch (NoSuchJIRAChangeItemException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public JIRAChangeItem remove(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		for (ModelListener<JIRAChangeItem> listener : listeners) {
			listener.onBeforeRemove(jiraChangeItem);
		}

		jiraChangeItem = removeImpl(jiraChangeItem);

		for (ModelListener<JIRAChangeItem> listener : listeners) {
			listener.onAfterRemove(jiraChangeItem);
		}

		return jiraChangeItem;
	}

	protected JIRAChangeItem removeImpl(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem = toUnwrappedModel(jiraChangeItem);

		Session session = null;

		try {
			session = openSession();

			if (jiraChangeItem.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(JIRAChangeItemImpl.class,
						jiraChangeItem.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(jiraChangeItem);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey());

		return jiraChangeItem;
	}

	public JIRAChangeItem updateImpl(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge) throws SystemException {
		jiraChangeItem = toUnwrappedModel(jiraChangeItem);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraChangeItem, merge);

			jiraChangeItem.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);

		return jiraChangeItem;
	}

	protected JIRAChangeItem toUnwrappedModel(JIRAChangeItem jiraChangeItem) {
		if (jiraChangeItem instanceof JIRAChangeItemImpl) {
			return jiraChangeItem;
		}

		JIRAChangeItemImpl jiraChangeItemImpl = new JIRAChangeItemImpl();

		jiraChangeItemImpl.setNew(jiraChangeItem.isNew());
		jiraChangeItemImpl.setPrimaryKey(jiraChangeItem.getPrimaryKey());

		jiraChangeItemImpl.setJiraChangeItemId(jiraChangeItem.getJiraChangeItemId());
		jiraChangeItemImpl.setJiraChangeGroupId(jiraChangeItem.getJiraChangeGroupId());
		jiraChangeItemImpl.setField(jiraChangeItem.getField());
		jiraChangeItemImpl.setOldValue(jiraChangeItem.getOldValue());
		jiraChangeItemImpl.setOldString(jiraChangeItem.getOldString());
		jiraChangeItemImpl.setNewValue(jiraChangeItem.getNewValue());
		jiraChangeItemImpl.setNewString(jiraChangeItem.getNewString());

		return jiraChangeItemImpl;
	}

	public JIRAChangeItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public JIRAChangeItem findByPrimaryKey(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException, SystemException {
		JIRAChangeItem jiraChangeItem = fetchByPrimaryKey(jiraChangeItemId);

		if (jiraChangeItem == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + jiraChangeItemId);
			}

			throw new NoSuchJIRAChangeItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				jiraChangeItemId);
		}

		return jiraChangeItem;
	}

	public JIRAChangeItem fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public JIRAChangeItem fetchByPrimaryKey(long jiraChangeItemId)
		throws SystemException {
		JIRAChangeItem jiraChangeItem = (JIRAChangeItem)EntityCacheUtil.getResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeItemImpl.class, jiraChangeItemId, this);

		if (jiraChangeItem == null) {
			Session session = null;

			try {
				session = openSession();

				jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
						new Long(jiraChangeItemId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (jiraChangeItem != null) {
					cacheResult(jiraChangeItem);
				}

				closeSession(session);
			}
		}

		return jiraChangeItem;
	}

	public List<JIRAChangeItem> findByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(jiraChangeGroupId) };

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

				query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end) throws SystemException {
		return findByJiraChangeGroupId(jiraChangeGroupId, start, end, null);
	}

	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(jiraChangeGroupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_JIRACHANGEGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(2);
				}

				query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

				query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				list = (List<JIRAChangeItem>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_JIRACHANGEGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId, OrderByComparator obc)
		throws NoSuchJIRAChangeItemException, SystemException {
		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraChangeGroupId=");
			msg.append(jiraChangeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeItem findByJiraChangeGroupId_Last(long jiraChangeGroupId,
		OrderByComparator obc)
		throws NoSuchJIRAChangeItemException, SystemException {
		int count = countByJiraChangeGroupId(jiraChangeGroupId);

		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraChangeGroupId=");
			msg.append(jiraChangeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId, OrderByComparator obc)
		throws NoSuchJIRAChangeItemException, SystemException {
		JIRAChangeItem jiraChangeItem = findByPrimaryKey(jiraChangeItemId);

		int count = countByJiraChangeGroupId(jiraChangeGroupId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

			query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraChangeGroupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraChangeItem);

			JIRAChangeItem[] array = new JIRAChangeItemImpl[3];

			array[0] = (JIRAChangeItem)objArray[0];
			array[1] = (JIRAChangeItem)objArray[1];
			array[2] = (JIRAChangeItem)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAChangeItem> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<JIRAChangeItem> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<JIRAChangeItem> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (obc != null) {
					query = new StringBundler(2 +
							(obc.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_JIRACHANGEITEM);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				sql = _SQL_SELECT_JIRACHANGEITEM;

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		for (JIRAChangeItem jiraChangeItem : findByJiraChangeGroupId(
				jiraChangeGroupId)) {
			remove(jiraChangeItem);
		}
	}

	public void removeAll() throws SystemException {
		for (JIRAChangeItem jiraChangeItem : findAll()) {
			remove(jiraChangeItem);
		}
	}

	public int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(jiraChangeGroupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_JIRACHANGEITEM_WHERE);

				query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
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

				Query q = session.createQuery(_SQL_COUNT_JIRACHANGEITEM);

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
						"value.object.listener.com.liferay.socialcoding.model.JIRAChangeItem")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAChangeItem>> listenersList = new ArrayList<ModelListener<JIRAChangeItem>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAChangeItem>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAActionPersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAIssuePersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence")
	protected com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.SVNRevisionPersistence")
	protected com.liferay.socialcoding.service.persistence.SVNRevisionPersistence svnRevisionPersistence;
	private static final String _SQL_SELECT_JIRACHANGEITEM = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem";
	private static final String _SQL_SELECT_JIRACHANGEITEM_WHERE = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem WHERE ";
	private static final String _SQL_COUNT_JIRACHANGEITEM = "SELECT COUNT(jiraChangeItem) FROM JIRAChangeItem jiraChangeItem";
	private static final String _SQL_COUNT_JIRACHANGEITEM_WHERE = "SELECT COUNT(jiraChangeItem) FROM JIRAChangeItem jiraChangeItem WHERE ";
	private static final String _FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2 =
		"jiraChangeItem.jiraChangeGroupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraChangeItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAChangeItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAChangeItem exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(JIRAChangeItemPersistenceImpl.class);
}