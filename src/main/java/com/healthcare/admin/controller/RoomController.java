package com.healthcare.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.admin.domain.RoomOrWard;
import com.healthcare.admin.service.RoomOrWardService;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomOrWardService roomOrWardService;
	
	@GetMapping("/addRoom")
	public String addRoom(Model model) {		
		
		RoomOrWard roomOrWard = new RoomOrWard(); 
		model.addAttribute("roomOrWard", roomOrWard);
		

		return "addRoom";
	}
	
	@PostMapping("/addRoom")
	public String addRoomPost(@ModelAttribute("roomOrWard") RoomOrWard roomOrWard, Model model) {
		
		System.out.println("type "+ roomOrWard.getType());
		
		roomOrWard.setLeftSpace(roomOrWard.getType());
		roomOrWardService.save(roomOrWard);
		
		
		return "redirect:roomList";
	}
	
	@RequestMapping("/roomList")
	public String roomList(Model model) {
		
		List<RoomOrWard> roomOrWardList = roomOrWardService.findAll();
		model.addAttribute("roomOrWardList", roomOrWardList);
		
		return "roomList";
	}
	
	@RequestMapping("/updateRoom")
	public String updateRoom(@RequestParam("id") Long roomId, Model model) {
		
		RoomOrWard roomOrWard = roomOrWardService.findById(roomId);
		
		model.addAttribute("roomOrWard", roomOrWard);
		
		
		return "updateRoom";
	}
	
	@PostMapping("/updateRoom")
	public String updateRoomPost(@ModelAttribute("roomOrWard") RoomOrWard roomOrWard,  Model model) {
		
		RoomOrWard roomOrWard1 = roomOrWardService.findById(roomOrWard.getRoomId());
		
		roomOrWard1.setPrice(roomOrWard.getPrice());
		roomOrWard1.setRoomName(roomOrWard.getRoomName());
		
		roomOrWardService.save(roomOrWard1);
		
		return "redirect:/room/roomList";
	}
	
	@RequestMapping("/removeRoom")
	public String removeRoom(@RequestParam("id") Long roomId, Model model) {
		
		roomOrWardService.removeOne(roomId);
		
		List<RoomOrWard> roomOrWardList = roomOrWardService.findAll();
		model.addAttribute("roomOrWardList", roomOrWardList);
		
		return "redirect:/room/roomList";
	}
	
	
}
