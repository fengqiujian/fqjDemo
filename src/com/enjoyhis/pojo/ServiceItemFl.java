package com.enjoyhis.pojo;

import com.enjoyhis.persistence.his.po.HisServiceItemFl;

/**
 * 
 * @Name:ServiceItemFl
 * @Description: 二级菜单POJO
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 * @Parameters: 参数
 * @Return: 返回的页面
 */
@SuppressWarnings("serial")
public class ServiceItemFl extends HisServiceItemFl {
	private String itemParentName;

	public String getItemParentName() {
		return itemParentName;
	}

	public void setItemParentName(String itemParentName) {
		this.itemParentName = itemParentName;
	}

}
