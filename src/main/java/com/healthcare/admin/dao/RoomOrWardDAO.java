package com.healthcare.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.healthcare.admin.domain.RoomOrWard;

public interface RoomOrWardDAO extends CrudRepository<RoomOrWard, Long>{
	
	List<RoomOrWard> findByLeftSpace(Integer leftSpace);
	
	List<RoomOrWard> findByLeftSpaceGreaterThan(Integer leftSpace);
	
	@Query("select r from RoomOrWard r where r.leftSpace > ?1 or r.roomId = ?2")
	List<RoomOrWard> findByIdAndLeftSpace(Integer leftSpace, Long roomId);

}
