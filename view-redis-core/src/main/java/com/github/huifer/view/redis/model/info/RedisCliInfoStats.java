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

public class RedisCliInfoStats {
	private String totalConnectionsReceived;

	private String totalCommandsProcessed;

	private String instantaneousOpsPerSec;

	private String totalNetInputBytes;

	private String totalNetOutputBytes;

	private String instantaneousInputKbps;

	private String instantaneousOutputKbps;

	private String rejectedConnections;

	private String syncFull;

	private String syncPartialOk;

	private String syncPartialErr;

	private String expiredKeys;

	private String evictedKeys;

	private String keyspaceHits;

	private String keyspaceMisses;

	private String pubsubChannels;

	private String pubsubPatterns;

	private String latestForkUsec;

	private String migrateCachedSockets;

	public String getTotalConnectionsReceived() {
		return totalConnectionsReceived;
	}

	public void setTotalConnectionsReceived(String totalConnectionsReceived) {
		this.totalConnectionsReceived = totalConnectionsReceived;
	}

	public String getTotalCommandsProcessed() {
		return totalCommandsProcessed;
	}

	public void setTotalCommandsProcessed(String totalCommandsProcessed) {
		this.totalCommandsProcessed = totalCommandsProcessed;
	}

	public String getInstantaneousOpsPerSec() {
		return instantaneousOpsPerSec;
	}

	public void setInstantaneousOpsPerSec(String instantaneousOpsPerSec) {
		this.instantaneousOpsPerSec = instantaneousOpsPerSec;
	}

	public String getTotalNetInputBytes() {
		return totalNetInputBytes;
	}

	public void setTotalNetInputBytes(String totalNetInputBytes) {
		this.totalNetInputBytes = totalNetInputBytes;
	}

	public String getTotalNetOutputBytes() {
		return totalNetOutputBytes;
	}

	public void setTotalNetOutputBytes(String totalNetOutputBytes) {
		this.totalNetOutputBytes = totalNetOutputBytes;
	}

	public String getInstantaneousInputKbps() {
		return instantaneousInputKbps;
	}

	public void setInstantaneousInputKbps(String instantaneousInputKbps) {
		this.instantaneousInputKbps = instantaneousInputKbps;
	}

	public String getInstantaneousOutputKbps() {
		return instantaneousOutputKbps;
	}

	public void setInstantaneousOutputKbps(String instantaneousOutputKbps) {
		this.instantaneousOutputKbps = instantaneousOutputKbps;
	}

	public String getRejectedConnections() {
		return rejectedConnections;
	}

	public void setRejectedConnections(String rejectedConnections) {
		this.rejectedConnections = rejectedConnections;
	}

	public String getSyncFull() {
		return syncFull;
	}

	public void setSyncFull(String syncFull) {
		this.syncFull = syncFull;
	}

	public String getSyncPartialOk() {
		return syncPartialOk;
	}

	public void setSyncPartialOk(String syncPartialOk) {
		this.syncPartialOk = syncPartialOk;
	}

	public String getSyncPartialErr() {
		return syncPartialErr;
	}

	public void setSyncPartialErr(String syncPartialErr) {
		this.syncPartialErr = syncPartialErr;
	}

	public String getExpiredKeys() {
		return expiredKeys;
	}

	public void setExpiredKeys(String expiredKeys) {
		this.expiredKeys = expiredKeys;
	}

	public String getEvictedKeys() {
		return evictedKeys;
	}

	public void setEvictedKeys(String evictedKeys) {
		this.evictedKeys = evictedKeys;
	}

	public String getKeyspaceHits() {
		return keyspaceHits;
	}

	public void setKeyspaceHits(String keyspaceHits) {
		this.keyspaceHits = keyspaceHits;
	}

	public String getKeyspaceMisses() {
		return keyspaceMisses;
	}

	public void setKeyspaceMisses(String keyspaceMisses) {
		this.keyspaceMisses = keyspaceMisses;
	}

	public String getPubsubChannels() {
		return pubsubChannels;
	}

	public void setPubsubChannels(String pubsubChannels) {
		this.pubsubChannels = pubsubChannels;
	}

	public String getPubsubPatterns() {
		return pubsubPatterns;
	}

	public void setPubsubPatterns(String pubsubPatterns) {
		this.pubsubPatterns = pubsubPatterns;
	}

	public String getLatestForkUsec() {
		return latestForkUsec;
	}

	public void setLatestForkUsec(String latestForkUsec) {
		this.latestForkUsec = latestForkUsec;
	}

	public String getMigrateCachedSockets() {
		return migrateCachedSockets;
	}

	public void setMigrateCachedSockets(String migrateCachedSockets) {
		this.migrateCachedSockets = migrateCachedSockets;
	}
}
