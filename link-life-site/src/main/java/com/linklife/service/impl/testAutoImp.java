package com.linklife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linklife.domain.Nuist;

@Service
public class testAutoImp {

	/** 用户领域类 */
	@Autowired
	private Nuist nuist;
	
	public void test(){
		
		
		System.out.println("test");
		nuist.getNuistInfoModel().getAddCity();
	}
}
