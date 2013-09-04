/**
 * Copyright (c) 2012-2013 Axelor. All Rights Reserved.
 *
 * The contents of this file are subject to the Common Public
 * Attribution License Version 1.0 (the “License”); you may not use
 * this file except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://license.axelor.com/.
 *
 * The License is based on the Mozilla Public License Version 1.1 but
 * Sections 14 and 15 have been added to cover use of software over a
 * computer network and provide for limited attribution for the
 * Original Developer. In addition, Exhibit A has been modified to be
 * consistent with Exhibit B.
 *
 * Software distributed under the License is distributed on an “AS IS”
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is part of "Axelor Business Suite", developed by
 * Axelor exclusively.
 *
 * The Original Developer is the Initial Developer. The Initial Developer of
 * the Original Code is Axelor.
 *
 * All portions of the code written by Axelor are
 * Copyright (c) 2012-2013 Axelor. All Rights Reserved.
 */
package com.axelor.web;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.meta.MetaLoader;

@Singleton
public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2493577642638670615L;
	
	private static final Logger LOG = LoggerFactory.getLogger(InitServlet.class);
	
	@Inject
	private AppSettings settings;
	
	@Inject
	private MetaLoader metaLoader;

	@Override
	public void init() throws ServletException {
		LOG.info("Initializing...");
		try {
			String output = settings.get("temp.dir");
			metaLoader.load(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.init();
	}
}
