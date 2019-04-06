package com.enjoyhis.rmiclient;

import java.util.List;

import com.enjoyhis.persistence.his.po.HisPayment;

public interface IHisPayment {

	List<HisPayment> findPaymentAll(HisPayment hisPayment);

}
