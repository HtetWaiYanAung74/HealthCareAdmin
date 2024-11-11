package com.healthcare.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.admin.dao.RoomOrWardDAO;
import com.healthcare.admin.domain.RoomOrWard;
import com.healthcare.admin.service.RoomOrWardService;

@Service
public class RoomOrWardServiceImpl implements RoomOrWardService{

	@Autowired
	private RoomOrWardDAO roomOrWardDAO;
	
	@Override
	public List<RoomOrWard> findByLeftSpace(Integer leftSpace) {
		// TODO Auto-generated method stub
		return roomOrWardDAO.findByLeftSpace(leftSpace);
	}

	@Override
	public RoomOrWard save(RoomOrWard roomOrWard) {
		// TODO Auto-generated method stub
		return roomOrWardDAO.save(roomOrWard);
	}

	@Override
	public List<RoomOrWard> findAll() {
		// TODO Auto-generated method stub
		return (List<RoomOrWard>) roomOrWardDAO.findAll();
	}

	@Override
	public RoomOrWard findById(Long roomId) {
		// TODO Auto-generated method stub
		return roomOrWardDAO.findById(roomId).get();
	}

	@Override
	public List<RoomOrWard> findByLeftSpaceGreaterThan(Integer leftSpace) {
		// TODO Auto-generated method stub
		return roomOrWardDAO.findByLeftSpaceGreaterThan(leftSpace);
	}

	@Override
	public List<RoomOrWard> findByIdAndLeftSpace(Integer leftSpace, Long roomId) {
		// TODO Auto-generated method stub
		return roomOrWardDAO.findByIdAndLeftSpace(leftSpace, roomId);
	}

	@Override
	public void removeOne(Long roomId) {
		// TODO Auto-generated method stub
		roomOrWardDAO.deleteById(roomId);
	}

}
