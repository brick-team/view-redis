/*
 *
 * Copyright 2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.huifer.view.redis.model.info;

public class RedisCliInfoKeyspace {

	private String dbIndex;

	private KeyInfo keyInfo;

	public String getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(String dbIndex) {
		this.dbIndex = dbIndex;
	}

	public KeyInfo getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	public static class KeyInfo {
		private String keys;

		private String expires;

		private String avgTtl;

		public String getKeys() {
			return keys;
		}

		public void setKeys(String keys) {
			this.keys = keys;
		}

		public String getExpires() {
			return expires;
		}

		public void setExpires(String expires) {
			this.expires = expires;
		}

		public String getAvgTtl() {
			return avgTtl;
		}

		public void setAvgTtl(String avgTtl) {
			this.avgTtl = avgTtl;
		}
	}
}
