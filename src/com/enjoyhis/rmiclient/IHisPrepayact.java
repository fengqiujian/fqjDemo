package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPrepayact;

public interface IHisPrepayact {
	List<HisPrepayact> findAllJt(HisPrepayact hisPrepayact);

	int modifyStatus(HisPrepayact hisPrepayact);

	int addActivityInfo(HisPrepayact hisPrepayact);

	int updateStatus(HisPrepayact hisPrepayact);

	HisPrepayact findByPrepayactId(int parseInt);

	int updatePrepayactInfo(HisPrepayact hisPrepayact);
}
