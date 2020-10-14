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

public class RedisCliInfoMemory {
	private String usedMemory;

	private String usedMemoryHuman;

	private String usedMemoryRss;

	private String usedMemoryRssHuman;

	private String usedMemoryPeak;

	private String usedMemoryPeakHuman;

	private String totalSystemMemory;

	private String totalSystemMemoryHuman;

	private String usedMemoryLua;

	private String usedMemoryLuaHuman;

	private String maxmemory;

	private String maxmemoryHuman;

	private String maxmemoryPolicy;

	private String memFragmentationRatio;

	private String memAllocator;

	public String getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(String usedMemory) {
		this.usedMemory = usedMemory;
	}

	public String getUsedMemoryHuman() {
		return usedMemoryHuman;
	}

	public void setUsedMemoryHuman(String usedMemoryHuman) {
		this.usedMemoryHuman = usedMemoryHuman;
	}

	public String getUsedMemoryRss() {
		return usedMemoryRss;
	}

	public void setUsedMemoryRss(String usedMemoryRss) {
		this.usedMemoryRss = usedMemoryRss;
	}

	public String getUsedMemoryRssHuman() {
		return usedMemoryRssHuman;
	}

	public void setUsedMemoryRssHuman(String usedMemoryRssHuman) {
		this.usedMemoryRssHuman = usedMemoryRssHuman;
	}

	public String getUsedMemoryPeak() {
		return usedMemoryPeak;
	}

	public void setUsedMemoryPeak(String usedMemoryPeak) {
		this.usedMemoryPeak = usedMemoryPeak;
	}

	public String getUsedMemoryPeakHuman() {
		return usedMemoryPeakHuman;
	}

	public void setUsedMemoryPeakHuman(String usedMemoryPeakHuman) {
		this.usedMemoryPeakHuman = usedMemoryPeakHuman;
	}

	public String getTotalSystemMemory() {
		return totalSystemMemory;
	}

	public void setTotalSystemMemory(String totalSystemMemory) {
		this.totalSystemMemory = totalSystemMemory;
	}

	public String getTotalSystemMemoryHuman() {
		return totalSystemMemoryHuman;
	}

	public void setTotalSystemMemoryHuman(String totalSystemMemoryHuman) {
		this.totalSystemMemoryHuman = totalSystemMemoryHuman;
	}

	public String getUsedMemoryLua() {
		return usedMemoryLua;
	}

	public void setUsedMemoryLua(String usedMemoryLua) {
		this.usedMemoryLua = usedMemoryLua;
	}

	public String getUsedMemoryLuaHuman() {
		return usedMemoryLuaHuman;
	}

	public void setUsedMemoryLuaHuman(String usedMemoryLuaHuman) {
		this.usedMemoryLuaHuman = usedMemoryLuaHuman;
	}

	public String getMaxmemory() {
		return maxmemory;
	}

	public void setMaxmemory(String maxmemory) {
		this.maxmemory = maxmemory;
	}

	public String getMaxmemoryHuman() {
		return maxmemoryHuman;
	}

	public void setMaxmemoryHuman(String maxmemoryHuman) {
		this.maxmemoryHuman = maxmemoryHuman;
	}

	public String getMaxmemoryPolicy() {
		return maxmemoryPolicy;
	}

	public void setMaxmemoryPolicy(String maxmemoryPolicy) {
		this.maxmemoryPolicy = maxmemoryPolicy;
	}

	public String getMemFragmentationRatio() {
		return memFragmentationRatio;
	}

	public void setMemFragmentationRatio(String memFragmentationRatio) {
		this.memFragmentationRatio = memFragmentationRatio;
	}

	public String getMemAllocator() {
		return memAllocator;
	}

	public void setMemAllocator(String memAllocator) {
		this.memAllocator = memAllocator;
	}
}
