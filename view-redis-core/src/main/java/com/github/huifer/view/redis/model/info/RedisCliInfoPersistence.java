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

public class RedisCliInfoPersistence {
	private String loading;

	private String rdbChangesSinceLastSave;

	private String rdbBgsaveInProgress;

	private String rdbLastSaveTime;

	private String rdbLastBgsaveStatus;

	private String rdbLastBgsaveTimeSec;

	private String rdbCurrentBgsaveTimeSec;

	private String aofEnabled;

	private String aofRewriteInProgress;

	private String aofRewriteScheduled;

	private String aofLastRewriteTimeSec;

	private String aofCurrentRewriteTimeSec;

	private String aofLastBgrewriteStatus;

	private String aofLastWriteStatus;

	public String getLoading() {
		return loading;
	}

	public void setLoading(String loading) {
		this.loading = loading;
	}

	public String getRdbChangesSinceLastSave() {
		return rdbChangesSinceLastSave;
	}

	public void setRdbChangesSinceLastSave(String rdbChangesSinceLastSave) {
		this.rdbChangesSinceLastSave = rdbChangesSinceLastSave;
	}

	public String getRdbBgsaveInProgress() {
		return rdbBgsaveInProgress;
	}

	public void setRdbBgsaveInProgress(String rdbBgsaveInProgress) {
		this.rdbBgsaveInProgress = rdbBgsaveInProgress;
	}

	public String getRdbLastSaveTime() {
		return rdbLastSaveTime;
	}

	public void setRdbLastSaveTime(String rdbLastSaveTime) {
		this.rdbLastSaveTime = rdbLastSaveTime;
	}

	public String getRdbLastBgsaveStatus() {
		return rdbLastBgsaveStatus;
	}

	public void setRdbLastBgsaveStatus(String rdbLastBgsaveStatus) {
		this.rdbLastBgsaveStatus = rdbLastBgsaveStatus;
	}

	public String getRdbLastBgsaveTimeSec() {
		return rdbLastBgsaveTimeSec;
	}

	public void setRdbLastBgsaveTimeSec(String rdbLastBgsaveTimeSec) {
		this.rdbLastBgsaveTimeSec = rdbLastBgsaveTimeSec;
	}

	public String getRdbCurrentBgsaveTimeSec() {
		return rdbCurrentBgsaveTimeSec;
	}

	public void setRdbCurrentBgsaveTimeSec(String rdbCurrentBgsaveTimeSec) {
		this.rdbCurrentBgsaveTimeSec = rdbCurrentBgsaveTimeSec;
	}

	public String getAofEnabled() {
		return aofEnabled;
	}

	public void setAofEnabled(String aofEnabled) {
		this.aofEnabled = aofEnabled;
	}

	public String getAofRewriteInProgress() {
		return aofRewriteInProgress;
	}

	public void setAofRewriteInProgress(String aofRewriteInProgress) {
		this.aofRewriteInProgress = aofRewriteInProgress;
	}

	public String getAofRewriteScheduled() {
		return aofRewriteScheduled;
	}

	public void setAofRewriteScheduled(String aofRewriteScheduled) {
		this.aofRewriteScheduled = aofRewriteScheduled;
	}

	public String getAofLastRewriteTimeSec() {
		return aofLastRewriteTimeSec;
	}

	public void setAofLastRewriteTimeSec(String aofLastRewriteTimeSec) {
		this.aofLastRewriteTimeSec = aofLastRewriteTimeSec;
	}

	public String getAofCurrentRewriteTimeSec() {
		return aofCurrentRewriteTimeSec;
	}

	public void setAofCurrentRewriteTimeSec(String aofCurrentRewriteTimeSec) {
		this.aofCurrentRewriteTimeSec = aofCurrentRewriteTimeSec;
	}

	public String getAofLastBgrewriteStatus() {
		return aofLastBgrewriteStatus;
	}

	public void setAofLastBgrewriteStatus(String aofLastBgrewriteStatus) {
		this.aofLastBgrewriteStatus = aofLastBgrewriteStatus;
	}

	public String getAofLastWriteStatus() {
		return aofLastWriteStatus;
	}

	public void setAofLastWriteStatus(String aofLastWriteStatus) {
		this.aofLastWriteStatus = aofLastWriteStatus;
	}
}
