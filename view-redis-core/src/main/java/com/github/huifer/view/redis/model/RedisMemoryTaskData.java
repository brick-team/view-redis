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

/** redis 内存任务数据 */
public class RedisMemoryTaskData {

	private long usedMemory = -1;

	private long usedMemoryRss = -1;

	private long usedMemoryPeak = -1;

	private long usedMemoryLua = -1;

	public RedisMemoryTaskData(long usedMemory, long usedMemoryRss, long usedMemoryPeak, long usedMemoryLua) {
		this.usedMemory = usedMemory;
		this.usedMemoryRss = usedMemoryRss;
		this.usedMemoryPeak = usedMemoryPeak;
		this.usedMemoryLua = usedMemoryLua;
	}

	@Override
	public String toString() {
		return "{\"RedisMemoryTaskData\":{"
				+ "\"usedMemory\":"
				+ usedMemory
				+ ",\"usedMemoryRss\":"
				+ usedMemoryRss
				+ ",\"usedMemoryPeak\":"
				+ usedMemoryPeak
				+ ",\"usedMemoryLua\":"
				+ usedMemoryLua
				+ "}}";
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public long getUsedMemoryRss() {
		return usedMemoryRss;
	}

	public void setUsedMemoryRss(long usedMemoryRss) {
		this.usedMemoryRss = usedMemoryRss;
	}

	public long getUsedMemoryPeak() {
		return usedMemoryPeak;
	}

	public void setUsedMemoryPeak(long usedMemoryPeak) {
		this.usedMemoryPeak = usedMemoryPeak;
	}

	public long getUsedMemoryLua() {
		return usedMemoryLua;
	}

	public void setUsedMemoryLua(long usedMemoryLua) {
		this.usedMemoryLua = usedMemoryLua;
	}
}
