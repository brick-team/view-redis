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

public class RedisCliInfoReplication {

	private String role;

	private String connectedSlaves;

	private String masterReplOffset;

	private String replBacklogActive;

	private String replBacklogSize;

	private String replBacklogFirstByteOffset;

	private String replBacklogHistlen;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConnectedSlaves() {
		return connectedSlaves;
	}

	public void setConnectedSlaves(String connectedSlaves) {
		this.connectedSlaves = connectedSlaves;
	}

	public String getMasterReplOffset() {
		return masterReplOffset;
	}

	public void setMasterReplOffset(String masterReplOffset) {
		this.masterReplOffset = masterReplOffset;
	}

	public String getReplBacklogActive() {
		return replBacklogActive;
	}

	public void setReplBacklogActive(String replBacklogActive) {
		this.replBacklogActive = replBacklogActive;
	}

	public String getReplBacklogSize() {
		return replBacklogSize;
	}

	public void setReplBacklogSize(String replBacklogSize) {
		this.replBacklogSize = replBacklogSize;
	}

	public String getReplBacklogFirstByteOffset() {
		return replBacklogFirstByteOffset;
	}

	public void setReplBacklogFirstByteOffset(String replBacklogFirstByteOffset) {
		this.replBacklogFirstByteOffset = replBacklogFirstByteOffset;
	}

	public String getReplBacklogHistlen() {
		return replBacklogHistlen;
	}

	public void setReplBacklogHistlen(String replBacklogHistlen) {
		this.replBacklogHistlen = replBacklogHistlen;
	}
}
