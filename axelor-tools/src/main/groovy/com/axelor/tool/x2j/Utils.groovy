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
package com.axelor.tool.x2j

class Utils {

	/**
	 * Strip the extra leading white space from the code string.
	 * 
	 */
	public static String stripCode(String code, String joinWith) {
		if (code == null || code.trim().length() == 0) {
			return ""
		}
		String text = code.stripIndent().replaceAll("    ", "\t")
		text = text.trim().replaceAll("\n", joinWith).trim()
		return text
	}
	
	public static String firstUpper(String string) {
		string.substring(0, 1).toUpperCase() + string.substring(1)
	}
	
	public static String firstLower(String string) {
		string.substring(0, 1).toLowerCase() + string.substring(1)
	}
}
