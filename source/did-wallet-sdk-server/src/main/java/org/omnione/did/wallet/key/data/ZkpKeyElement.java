/*
 * Copyright 2025 OmniOne.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.omnione.did.wallet.key.data;

import org.omnione.did.wallet.util.json.JsonConverterUtils;
import java.util.HashMap;
import java.util.Map;

public class ZkpKeyElement {
	private String keyId;
	private String type;
	private String privateKey;

	private Map<String, Object> properties;

	public ZkpKeyElement() {
		properties = new HashMap<>();
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public Object getProperty(String propertyName) {
		return properties.get(propertyName);
	}

	public void setProperty(String propertyName, Object value) {
		properties.put(propertyName, value);
	}

	public Map<String, Object> getAllProperties() {
		return properties;
	}

	public void setAllProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public boolean hasProperty(String propertyName) {
		return properties.containsKey(propertyName);
	}

	public Object removeProperty(String propertyName) {
		return properties.remove(propertyName);
	}

	public String getStringProperty(String propertyName) {
		Object value = properties.get(propertyName);
		return (value instanceof String) ? (String) value : null;
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getMapProperty(String propertyName) {
		Object value = properties.get(propertyName);
		return (value instanceof Map) ? (Map<String, T>) value : null;
	}

	public String toJson() {
		JsonConverterUtils gson = new JsonConverterUtils();
		Map<String, Object> jsonMap = new HashMap<>();

		jsonMap.put("keyId", keyId);
		jsonMap.put("type", type);
		jsonMap.put("privateKey", privateKey);

		jsonMap.putAll(properties);

		return gson.toJson(jsonMap);
	}

	@SuppressWarnings("unchecked")
	public void fromJson(String val) {
		JsonConverterUtils gson = new JsonConverterUtils();
		Map<String, Object> jsonMap = gson.fromJson(val, Map.class);

		if (jsonMap.containsKey("keyId")) {
			this.keyId = (String) jsonMap.get("keyId");
		}

		if (jsonMap.containsKey("type")) {
			this.type = (String) jsonMap.get("type");
		}

		if (jsonMap.containsKey("privateKey")) {
			this.privateKey = (String) jsonMap.get("privateKey");
		}

		jsonMap.remove("keyId");
		jsonMap.remove("type");
		jsonMap.remove("privateKey");

		this.properties = jsonMap;
	}
}
