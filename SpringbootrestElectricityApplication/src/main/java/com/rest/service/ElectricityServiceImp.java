package com.rest.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.model.Electricity;
import com.rest.repo.ElectricityRepo;

@Service
public class ElectricityServiceImp implements ElectricityService {
	@Autowired
	private ElectricityRepo electricityRepo; 
	@Override
	public Electricity saveRecord(Electricity electricity) {
		int uts=0;
		double amount=0.0;
		uts=electricity.getCurrent_reading()-electricity.getPrevious_reading();
		electricity.setUnits(uts);
		if (uts<300) {
			amount=uts*1.75;
		}
		else if (uts>=300&&uts<500) {
			amount=uts*3.75;
		}
		else if (uts>=500) {
			amount=uts*7.35;
		}
		electricity.setTotalBill(amount);
		Electricity e=electricityRepo.save(electricity);
		return e;
	}
	
	@Override
	public Electricity getOneRecord(int cid) {
		Electricity get=electricityRepo.findById(cid).get();
		return get;
	}
	
	@Override
	public List<Electricity> getAllRecords() {
		List<Electricity>getAll=electricityRepo.findAll();
		return getAll;
	}
	
	@Override
	public void deleteRecord(int cid) {
		electricityRepo.deleteById(cid);
	}
	
	@Override
	public Electricity updateRecord(Electricity electricity,int cid) {
		Electricity oldRecord=electricityRepo.findById(cid).get();
		oldRecord.setCname(electricity.getCname());
		oldRecord.setCurrent_reading(electricity.getCurrent_reading());
		oldRecord.setPrevious_reading(electricity.getPrevious_reading());
		int uts=0;
		double amount=0.0;
		uts=electricity.getCurrent_reading()-electricity.getPrevious_reading();
		electricity.setUnits(uts);
		if (uts<300) {
			amount=uts*1.75;
		}
		else if (uts>=300&&uts<500) {
			amount=uts*3.75;
		}
		else if (uts>=500) {
			amount=uts*7.35;
		}
		oldRecord.setUnits(uts);
		oldRecord.setTotalBill(amount);
		Electricity e=electricityRepo.save(oldRecord);
		return e;
	}

	

}
