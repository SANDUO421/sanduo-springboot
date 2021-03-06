/**
 * Copyright 2018 行知 http://www.xz.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.job.server.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.job.server.common.LayuiPage;
import com.job.server.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

	LayuiPage queryPage(Map<String, Object> params);

	/**
	 * 保存定时任务
	 */
	boolean save(ScheduleJobEntity scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobEntity scheduleJob);

	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
}
