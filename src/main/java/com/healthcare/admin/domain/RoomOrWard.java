package com.healthcare.admin.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RoomOrWard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomId;

	private String roomName;
	private Integer type;
	private Double price;
	private Integer leftSpace;
	
	@OneToMany(mappedBy = "roomOrWard")
	private List<Patient> patient;

	public RoomOrWard() {
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getLeftSpace() {
		return leftSpace;
	}

	public void setLeftSpace(Integer leftSpace) {
		this.leftSpace = leftSpace;
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

}
