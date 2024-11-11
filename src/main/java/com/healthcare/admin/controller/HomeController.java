package com.healthcare.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthcare.admin.domain.PatientAppointment;
import com.healthcare.admin.service.AppointmentService;
import com.healthcare.admin.service.DoctorService;
import com.healthcare.admin.service.PatientService;
import com.healthcare.admin.service.RoomOrWardService;

@Controller
public class HomeController {
		
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private RoomOrWardService roomOrWardService;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {		
		
		int doctorCount = doctorService.findAll().size();
		
		int patientCount = patientService.findAll().size();
		
		int roomCount = roomOrWardService.findAll().size();
		
		List<PatientAppointment> appointmentList = appointmentService.findAll();
		
		model.addAttribute("doctorCount", doctorCount);
		model.addAttribute("patientCount", patientCount);
		model.addAttribute("roomCount", roomCount);
		model.addAttribute("appointmentList", appointmentList);
				
		return "home";
		
	} 
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
}
