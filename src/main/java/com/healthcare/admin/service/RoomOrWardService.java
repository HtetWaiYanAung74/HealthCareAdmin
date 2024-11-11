package com.healthcare.admin.service;

import java.util.List;

import com.healthcare.admin.domain.RoomOrWard;

public interface RoomOrWardService {
	
	List<RoomOrWard> findByLeftSpace(Integer leftSpace);
	
	RoomOrWard save(RoomOrWard roomOrWard);
	
	List<RoomOrWard> findAll();
	
	RoomOrWard findById(Long roomId);
	
	List<RoomOrWard> findByLeftSpaceGreaterThan(Integer leftSpace);
	
	List<RoomOrWard> findByIdAndLeftSpace(Integer leftSpace, Long roomId);
	
	void removeOne(Long roomId);

}
