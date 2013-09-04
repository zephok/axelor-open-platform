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
package com.axelor.meta.schema.actions;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import com.axelor.db.JPA;
import com.axelor.meta.ActionHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

@XmlType
public class ActionValidate extends Action {
	
	public static class Validator extends Element {
		
		@XmlAttribute(name = "message")
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
	}

	@XmlType
	public static class Error extends Validator {
	}

	@XmlType
	public static class Alert extends Validator {
	}

	@JsonIgnore
	@XmlElements({
		@XmlElement(name = "error", type = Error.class),
		@XmlElement(name = "alert", type = Alert.class),
	})
	private List<Validator> validators;
	
	public List<Validator> getValidators() {
		return validators;
	}
	
	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

	@Override
	public Object evaluate(ActionHandler handler) {
		for(Validator validator : validators) {
			if (validator.test(handler)) {
				String key = validator.getClass().getSimpleName().toLowerCase();
				String val = JPA.translate(validator.getMessage());
				
				if (!Strings.isNullOrEmpty(val))
					val = handler.evaluate("eval: " + "\"\"\"" + val + "\"\"\"").toString();
				
				Map<String, Object> result = Maps.newHashMap();
				result.put(key, val);
				return result;
			}
		}
		return null;
	}
	
	@Override
	public Object wrap(ActionHandler handler) {
		return evaluate(handler);
	}
}