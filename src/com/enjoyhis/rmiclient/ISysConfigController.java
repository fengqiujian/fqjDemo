package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.SysConfig;

public interface ISysConfigController {

	List<SysConfig> findAllJt(SysConfig sysConfig);

}
