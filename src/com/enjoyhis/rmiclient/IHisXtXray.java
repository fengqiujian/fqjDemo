package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisXtXray;

public interface IHisXtXray {

	List<HisXtXray> findXtXrayAll(HisXtXray hisXtXray);

}
