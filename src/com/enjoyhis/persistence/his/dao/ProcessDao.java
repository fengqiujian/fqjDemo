package com.enjoyhis.persistence.his.dao;

import java.io.Serializable;
import java.util.List;
import com.enjoyhis.pojo.Process;

/** (REPORT_PROCESS) **/
public interface ProcessDao {

	public Process find(Serializable id);

	public List<Process> findAll();

	public void update(Process t);

	public int updateByPrimaryKeySelective(Process t);

	public void delete(Serializable id);

	public void deleteAll();

	/** codegen **/

	public Process findBiger();
	
	
}