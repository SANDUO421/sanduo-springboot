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

package com.job.server.job.controller;


import com.job.server.common.ApiResponse;
import com.job.server.common.LayuiPage;
import com.job.server.job.entity.ScheduleJobEntity;
import com.job.server.job.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 定时任务
 */
@Api(description = "定时任务")
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Resource
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@ApiOperation(value = "定时任务列表",notes = "查询列表")
	@PostMapping("/list")
	public ApiResponse<LayuiPage> list(@RequestParam Map<String, Object> params){
		LayuiPage layuiPage = scheduleJobService.queryPage(params);

		return ApiResponse.ofSuccess(layuiPage);
	}
	
	/**
	 * 定时任务信息
	 */
	@ApiOperation(value = "定时任务详情",notes = "查询详情")
	@GetMapping("/info/{jobId}")
	public ApiResponse<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
		return ApiResponse.ofSuccess(schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@ApiOperation(value = "保存定时任务",notes = "保存定时")
	@PostMapping("/save")
	public ApiResponse save(@RequestBody ScheduleJobEntity scheduleJob){

		scheduleJobService.save(scheduleJob);
		return ApiResponse.ofSuccess();
	}
	
	/**
	 * 修改定时任务
	 */
	@ApiOperation(value = "修改定时任务",notes = "修改定时任务")
	@PostMapping("/update")
	public ApiResponse update(@RequestBody ScheduleJobEntity scheduleJob){

		scheduleJobService.update(scheduleJob);
		
		return ApiResponse.ofSuccess();
	}
	
	/**
	 * 删除定时任务
	 */
	@ApiOperation(value = "删除定时任务",notes = "删除任务")
	@DeleteMapping("/delete")
	public ApiResponse delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return ApiResponse.ofSuccess();
	}
	
	/**
	 * 立即执行任务
	 */
	@ApiOperation(value = "立即执行定时任务",notes = "立即执行")
	@PostMapping("/run")
	public ApiResponse run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return ApiResponse.ofSuccess();
	}
	
	/**
	 * 暂停定时任务
	 */
	@ApiOperation(value = "暂停定时任务",notes = "暂停定时")
	@PostMapping("/pause")
	public ApiResponse pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return ApiResponse.ofSuccess();
	}
	
	/**
	 * 恢复定时任务
	 */
	@ApiOperation(value = "恢复定时任务",notes = "恢复定时")
	@PostMapping("/resume")
	public ApiResponse resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return ApiResponse.ofSuccess();
	}

}
