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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.impl.ArticleImpl;
import com.liferay.knowledgebase.model.impl.ArticleModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="ArticlePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ArticlePersistence
 * @see       ArticleUtil
 * @generated
 */
public class ArticlePersistenceImpl extends BasePersistenceImpl<Article>
	implements ArticlePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ArticleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUuid", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_UUID = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUuid", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_RESOURCEPRIMKEY = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByResourcePrimKey", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_RESOURCEPRIMKEY = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByResourcePrimKey",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByResourcePrimKey", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_R_V = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByR_V",
			new String[] { Long.class.getName(), Double.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_R_V = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_V",
			new String[] { Long.class.getName(), Double.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Article article) {
		EntityCacheUtil.putResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey(), article);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { article.getUuid(), new Long(article.getGroupId()) },
			article);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				new Long(article.getResourcePrimKey()),
				new Double(article.getVersion())
			}, article);
	}

	public void cacheResult(List<Article> articles) {
		for (Article article : articles) {
			if (EntityCacheUtil.getResult(
						ArticleModelImpl.ENTITY_CACHE_ENABLED,
						ArticleImpl.class, article.getPrimaryKey(), this) == null) {
				cacheResult(article);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(ArticleImpl.class.getName());
		EntityCacheUtil.clearCache(ArticleImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Article create(long articleId) {
		Article article = new ArticleImpl();

		article.setNew(true);
		article.setPrimaryKey(articleId);

		String uuid = PortalUUIDUtil.generate();

		article.setUuid(uuid);

		return article;
	}

	public Article remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Article remove(long articleId)
		throws NoSuchArticleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Article article = (Article)session.get(ArticleImpl.class,
					new Long(articleId));

			if (article == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + articleId);
				}

				throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					articleId);
			}

			return remove(article);
		}
		catch (NoSuchArticleException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Article remove(Article article) throws SystemException {
		for (ModelListener<Article> listener : listeners) {
			listener.onBeforeRemove(article);
		}

		article = removeImpl(article);

		for (ModelListener<Article> listener : listeners) {
			listener.onAfterRemove(article);
		}

		return article;
	}

	protected Article removeImpl(Article article) throws SystemException {
		article = toUnwrappedModel(article);

		Session session = null;

		try {
			session = openSession();

			if (article.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ArticleImpl.class,
						article.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(article);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ArticleModelImpl articleModelImpl = (ArticleModelImpl)article;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				articleModelImpl.getOriginalUuid(),
				new Long(articleModelImpl.getOriginalGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] {
				new Long(articleModelImpl.getOriginalResourcePrimKey()),
				new Double(articleModelImpl.getOriginalVersion())
			});

		EntityCacheUtil.removeResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey());

		return article;
	}

	public Article updateImpl(com.liferay.knowledgebase.model.Article article,
		boolean merge) throws SystemException {
		article = toUnwrappedModel(article);

		boolean isNew = article.isNew();

		ArticleModelImpl articleModelImpl = (ArticleModelImpl)article;

		if (Validator.isNull(article.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			article.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, article, merge);

			article.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
			ArticleImpl.class, article.getPrimaryKey(), article);

		if (!isNew &&
				(!Validator.equals(article.getUuid(),
					articleModelImpl.getOriginalUuid()) ||
				(article.getGroupId() != articleModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					articleModelImpl.getOriginalUuid(),
					new Long(articleModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(article.getUuid(),
					articleModelImpl.getOriginalUuid()) ||
				(article.getGroupId() != articleModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] { article.getUuid(), new Long(article.getGroupId()) },
				article);
		}

		if (!isNew &&
				((article.getResourcePrimKey() != articleModelImpl.getOriginalResourcePrimKey()) ||
				(article.getVersion() != articleModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
				new Object[] {
					new Long(articleModelImpl.getOriginalResourcePrimKey()),
					new Double(articleModelImpl.getOriginalVersion())
				});
		}

		if (isNew ||
				((article.getResourcePrimKey() != articleModelImpl.getOriginalResourcePrimKey()) ||
				(article.getVersion() != articleModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
				new Object[] {
					new Long(article.getResourcePrimKey()),
					new Double(article.getVersion())
				}, article);
		}

		return article;
	}

	protected Article toUnwrappedModel(Article article) {
		if (article instanceof ArticleImpl) {
			return article;
		}

		ArticleImpl articleImpl = new ArticleImpl();

		articleImpl.setNew(article.isNew());
		articleImpl.setPrimaryKey(article.getPrimaryKey());

		articleImpl.setUuid(article.getUuid());
		articleImpl.setArticleId(article.getArticleId());
		articleImpl.setResourcePrimKey(article.getResourcePrimKey());
		articleImpl.setGroupId(article.getGroupId());
		articleImpl.setCompanyId(article.getCompanyId());
		articleImpl.setUserId(article.getUserId());
		articleImpl.setUserName(article.getUserName());
		articleImpl.setCreateDate(article.getCreateDate());
		articleImpl.setModifiedDate(article.getModifiedDate());
		articleImpl.setParentResourcePrimKey(article.getParentResourcePrimKey());
		articleImpl.setVersion(article.getVersion());
		articleImpl.setTitle(article.getTitle());
		articleImpl.setContent(article.getContent());
		articleImpl.setDescription(article.getDescription());
		articleImpl.setPriority(article.getPriority());

		return articleImpl;
	}

	public Article findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Article findByPrimaryKey(long articleId)
		throws NoSuchArticleException, SystemException {
		Article article = fetchByPrimaryKey(articleId);

		if (article == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + articleId);
			}

			throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				articleId);
		}

		return article;
	}

	public Article fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Article fetchByPrimaryKey(long articleId) throws SystemException {
		Article article = (Article)EntityCacheUtil.getResult(ArticleModelImpl.ENTITY_CACHE_ENABLED,
				ArticleImpl.class, articleId, this);

		if (article == null) {
			Session session = null;

			try {
				session = openSession();

				article = (Article)session.get(ArticleImpl.class,
						new Long(articleId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (article != null) {
					cacheResult(article);
				}

				closeSession(session);
			}
		}

		return article;
	}

	public List<Article> findByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_ARTICLE_WHERE);

				if (uuid == null) {
					query.append(_FINDER_COLUMN_UUID_UUID_1);
				}
				else {
					if (uuid.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_UUID_UUID_3);
					}
					else {
						query.append(_FINDER_COLUMN_UUID_UUID_2);
					}
				}

				query.append(ArticleModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Article>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Article> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	public List<Article> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_UUID,
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

				query.append(_SQL_SELECT_ARTICLE_WHERE);

				if (uuid == null) {
					query.append(_FINDER_COLUMN_UUID_UUID_1);
				}
				else {
					if (uuid.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_UUID_UUID_3);
					}
					else {
						query.append(_FINDER_COLUMN_UUID_UUID_2);
					}
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(ArticleModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Article>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_UUID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Article findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Article findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByUuid(uuid);

		List<Article> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Article[] findByUuid_PrevAndNext(long articleId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		int count = countByUuid(uuid);

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

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count,
					orderByComparator, article);

			Article[] array = new ArticleImpl[3];

			array[0] = (Article)objArray[0];
			array[1] = (Article)objArray[1];
			array[2] = (Article)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Article findByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		Article article = fetchByUUID_G(uuid, groupId);

		if (article == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return article;
	}

	public Article fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	public Article fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_ARTICLE_WHERE);

				if (uuid == null) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_1);
				}
				else {
					if (uuid.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_UUID_G_UUID_3);
					}
					else {
						query.append(_FINDER_COLUMN_UUID_G_UUID_2);
					}
				}

				query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

				query.append(ArticleModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Article> list = q.list();

				result = list;

				Article article = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					article = list.get(0);

					cacheResult(article);

					if ((article.getUuid() == null) ||
							!article.getUuid().equals(uuid) ||
							(article.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, article);
					}
				}

				return article;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, new ArrayList<Article>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Article)result;
			}
		}
	}

	public List<Article> findByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(resourcePrimKey) };

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RESOURCEPRIMKEY,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_ARTICLE_WHERE);

				query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

				query.append(ArticleModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Article>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RESOURCEPRIMKEY,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Article> findByResourcePrimKey(long resourcePrimKey, int start,
		int end) throws SystemException {
		return findByResourcePrimKey(resourcePrimKey, start, end, null);
	}

	public List<Article> findByResourcePrimKey(long resourcePrimKey, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_RESOURCEPRIMKEY,
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

				query.append(_SQL_SELECT_ARTICLE_WHERE);

				query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(ArticleModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				list = (List<Article>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Article>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_RESOURCEPRIMKEY,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Article findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		List<Article> list = findByResourcePrimKey(resourcePrimKey, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Article findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		int count = countByResourcePrimKey(resourcePrimKey);

		List<Article> list = findByResourcePrimKey(resourcePrimKey, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Article[] findByResourcePrimKey_PrevAndNext(long articleId,
		long resourcePrimKey, OrderByComparator orderByComparator)
		throws NoSuchArticleException, SystemException {
		Article article = findByPrimaryKey(articleId);

		int count = countByResourcePrimKey(resourcePrimKey);

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

			query.append(_SQL_SELECT_ARTICLE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count,
					orderByComparator, article);

			Article[] array = new ArticleImpl[3];

			array[0] = (Article)objArray[0];
			array[1] = (Article)objArray[1];
			array[2] = (Article)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Article findByR_V(long resourcePrimKey, double version)
		throws NoSuchArticleException, SystemException {
		Article article = fetchByR_V(resourcePrimKey, version);

		if (article == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return article;
	}

	public Article fetchByR_V(long resourcePrimKey, double version)
		throws SystemException {
		return fetchByR_V(resourcePrimKey, version, true);
	}

	public Article fetchByR_V(long resourcePrimKey, double version,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), new Double(version)
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_R_V,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_ARTICLE_WHERE);

				query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

				query.append(_FINDER_COLUMN_R_V_VERSION_2);

				query.append(ArticleModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				List<Article> list = q.list();

				result = list;

				Article article = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
						finderArgs, list);
				}
				else {
					article = list.get(0);

					cacheResult(article);

					if ((article.getResourcePrimKey() != resourcePrimKey) ||
							(article.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
							finderArgs, article);
					}
				}

				return article;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
						finderArgs, new ArrayList<Article>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Article)result;
			}
		}
	}

	public List<Article> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Article> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Article> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Article> list = (List<Article>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_ARTICLE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_ARTICLE.concat(ArticleModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Article>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Article>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Article>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUuid(String uuid) throws SystemException {
		for (Article article : findByUuid(uuid)) {
			remove(article);
		}
	}

	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		Article article = findByUUID_G(uuid, groupId);

		remove(article);
	}

	public void removeByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		for (Article article : findByResourcePrimKey(resourcePrimKey)) {
			remove(article);
		}
	}

	public void removeByR_V(long resourcePrimKey, double version)
		throws NoSuchArticleException, SystemException {
		Article article = findByR_V(resourcePrimKey, version);

		remove(article);
	}

	public void removeAll() throws SystemException {
		for (Article article : findAll()) {
			remove(article);
		}
	}

	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_ARTICLE_WHERE);

				if (uuid == null) {
					query.append(_FINDER_COLUMN_UUID_UUID_1);
				}
				else {
					if (uuid.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_UUID_UUID_3);
					}
					else {
						query.append(_FINDER_COLUMN_UUID_UUID_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_ARTICLE_WHERE);

				if (uuid == null) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_1);
				}
				else {
					if (uuid.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_UUID_G_UUID_3);
					}
					else {
						query.append(_FINDER_COLUMN_UUID_G_UUID_2);
					}
				}

				query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(resourcePrimKey) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_ARTICLE_WHERE);

				query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByR_V(long resourcePrimKey, double version)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), new Double(version)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_V,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_ARTICLE_WHERE);

				query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

				query.append(_FINDER_COLUMN_R_V_VERSION_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_V, finderArgs,
					count);

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

				Query q = session.createQuery(_SQL_COUNT_ARTICLE);

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
						"value.object.listener.com.liferay.knowledgebase.model.Article")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Article>> listenersList = new ArrayList<ModelListener<Article>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Article>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = ArticlePersistence.class)
	protected ArticlePersistence articlePersistence;
	@BeanReference(type = TemplatePersistence.class)
	protected TemplatePersistence templatePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_ARTICLE = "SELECT article FROM Article article";
	private static final String _SQL_SELECT_ARTICLE_WHERE = "SELECT article FROM Article article WHERE ";
	private static final String _SQL_COUNT_ARTICLE = "SELECT COUNT(article) FROM Article article";
	private static final String _SQL_COUNT_ARTICLE_WHERE = "SELECT COUNT(article) FROM Article article WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "article.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "article.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(article.uuid IS NULL OR article.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "article.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "article.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(article.uuid IS NULL OR article.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "article.groupId = ?";
	private static final String _FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2 =
		"article.resourcePrimKey = ?";
	private static final String _FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2 = "article.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_V_VERSION_2 = "article.version = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "article.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Article exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Article exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(ArticlePersistenceImpl.class);
}