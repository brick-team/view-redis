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

package com.github.huifer.view.redis.model;

import java.util.Objects;

/** redis key的信息 */
public class RedisKeyInfo {

	/** key name */
	private String key;

	/** 数据类型 */
	private RedisDataType dataType;

	public RedisKeyInfo(String key, RedisDataType dataType) {
		this.key = key;
		this.dataType = dataType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RedisKeyInfo that = (RedisKeyInfo) o;
		return Objects.equals(key, that.key) && dataType == that.dataType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, dataType);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public RedisDataType getDataType() {
		return dataType;
	}

	public void setDataType(RedisDataType dataType) {
		this.dataType = dataType;
	}
}
