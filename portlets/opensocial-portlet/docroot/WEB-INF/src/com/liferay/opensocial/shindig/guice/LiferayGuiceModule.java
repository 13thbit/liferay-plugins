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

package com.liferay.opensocial.shindig.guice;

import com.google.inject.name.Names;

import com.liferay.opensocial.shindig.service.LiferayPersonService;
import com.liferay.opensocial.shindig.util.ShindigUtil;

import org.apache.shindig.social.core.config.SocialApiGuiceModule;
import org.apache.shindig.social.opensocial.oauth.OAuthDataStore;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.MessageService;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.apache.shindig.social.sample.oauth.SampleOAuthDataStore;
import org.apache.shindig.social.sample.spi.JsonDbOpensocialService;

/**
 * <a href="LiferayGuiceModule.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class LiferayGuiceModule extends SocialApiGuiceModule {

	protected void configure() {
		super.configure();

		bind(String.class).annotatedWith(
			Names.named("shindig.canonical.json.db")).toInstance(
				"sampledata/canonicaldb.json");

		bind(ActivityService.class).to(JsonDbOpensocialService.class);
		bind(AppDataService.class).to(JsonDbOpensocialService.class);
		bind(MessageService.class).to(JsonDbOpensocialService.class);
		bind(OAuthDataStore.class).to(SampleOAuthDataStore.class);
		bind(PersonService.class).to(LiferayPersonService.class);

		requestStaticInjection(ShindigUtil.class);
	}

}