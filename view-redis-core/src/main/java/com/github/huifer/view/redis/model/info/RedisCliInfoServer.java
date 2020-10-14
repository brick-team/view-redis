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

/** info server */
public class RedisCliInfoServer {

	private String redisVersion;

	private String redisGitSha1;

	private String redisGitDirty;

	private String redisBuildId;

	private String redisMode;

	private String os;

	private String archBits;

	private String multiplexingApi;

	private String processId;

	private String runId;

	private String tcpPort;

	private String uptimeInSeconds;

	private String uptimeInDays;

	private String hz;

	private String lruClock;

	private String executable;

	private String configFile;

	public String getRedisVersion() {
		return redisVersion;
	}

	public void setRedisVersion(String redisVersion) {
		this.redisVersion = redisVersion;
	}

	public String getRedisGitSha1() {
		return redisGitSha1;
	}

	public void setRedisGitSha1(String redisGitSha1) {
		this.redisGitSha1 = redisGitSha1;
	}

	public String getRedisGitDirty() {
		return redisGitDirty;
	}

	public void setRedisGitDirty(String redisGitDirty) {
		this.redisGitDirty = redisGitDirty;
	}

	public String getRedisBuildId() {
		return redisBuildId;
	}

	public void setRedisBuildId(String redisBuildId) {
		this.redisBuildId = redisBuildId;
	}

	public String getRedisMode() {
		return redisMode;
	}

	public void setRedisMode(String redisMode) {
		this.redisMode = redisMode;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getArchBits() {
		return archBits;
	}

	public void setArchBits(String archBits) {
		this.archBits = archBits;
	}

	public String getMultiplexingApi() {
		return multiplexingApi;
	}

	public void setMultiplexingApi(String multiplexingApi) {
		this.multiplexingApi = multiplexingApi;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRunId() {
		return runId;
	}

	public void setRunId(String runId) {
		this.runId = runId;
	}

	public String getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}

	public String getUptimeInSeconds() {
		return uptimeInSeconds;
	}

	public void setUptimeInSeconds(String uptimeInSeconds) {
		this.uptimeInSeconds = uptimeInSeconds;
	}

	public String getUptimeInDays() {
		return uptimeInDays;
	}

	public void setUptimeInDays(String uptimeInDays) {
		this.uptimeInDays = uptimeInDays;
	}

	public String getHz() {
		return hz;
	}

	public void setHz(String hz) {
		this.hz = hz;
	}

	public String getLruClock() {
		return lruClock;
	}

	public void setLruClock(String lruClock) {
		this.lruClock = lruClock;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
}
