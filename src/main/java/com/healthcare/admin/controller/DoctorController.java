package com.healthcare.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.admin.domain.Doctor;
import com.healthcare.admin.domain.DoctorFreeTime;
import com.healthcare.admin.service.DoctorFreeTimeService;
import com.healthcare.admin.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private DoctorFreeTimeService doctorFreeTimeService;

	@GetMapping("/addDoctor")
	private String addDoctor(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		return "addDoctor";
	}

	@PostMapping("/addDoctor")
	private String addDoctorPost(@ModelAttribute("Doctor") Doctor doctor, Model model) throws IOException {

		if (doctorService.findByPhone(doctor.getPhone()) != null) {
			model.addAttribute("phoneExists", true);
			model.addAttribute("doctor", doctor);
			return "addDoctor";
		}

		if (doctorService.findByNrc(doctor.getNrc()) != null) {
			model.addAttribute("nrcExists", true);
			model.addAttribute("doctor", doctor);
			return "addDoctor";
		}

		MultipartFile doctorImage = doctor.getDoctorImage();

		byte[] bytes = doctorImage.getBytes();

		String imageFormat = doctor.getImageName();
		int index = imageFormat.indexOf(".");

		String nameFormat = imageFormat.substring(index);

		doctor.setImageName(nameFormat);
		doctorService.save(doctor);

		String name = doctor.getDoctorId() + nameFormat;
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("src/main/resources/static/image/doctor/" + name)));

		stream.write(bytes);
		stream.close();

		return "redirect:doctorList";
	}

	@RequestMapping("/doctorList")
	private String doctorList(Model model) {
		List<Doctor> doctorList = doctorService.findAll();
		model.addAttribute("doctorList", doctorList);
		return "doctorList";

	}

	@RequestMapping("/doctorInfo")
	private String doctorInfo(@RequestParam("id") Long id, Model model) {
		Doctor doctor = doctorService.findById(id);
		model.addAttribute("doctor", doctor);
		return "doctorInfo";

	}

	@RequestMapping("/updateDoctor")
	private String updateDoctor(@RequestParam("id") Long doctorId, Model model) {
		Doctor doctor = doctorService.findById(doctorId);
		model.addAttribute("doctor", doctor);
		return "updateDoctor";

	}

	@PostMapping("/updateDoctor")
	private String updateBookPost(@ModelAttribute("doctor") Doctor doctor, Model model) throws IOException {
		String oldImage = doctorService.findById(doctor.getDoctorId()).getImageName();

		Doctor oldDoctor = doctorService.findById(doctor.getDoctorId());

		if (doctorService.findByPhone(doctor.getPhone()) != null) {
			if (doctorService.findByPhone(doctor.getPhone()).getDoctorId() != oldDoctor.getDoctorId()) {
				model.addAttribute("phoneExists", true);
				model.addAttribute("doctor", doctor);
				return "updateDoctor";
			}
		}

		if (doctorService.findByNrc(doctor.getNrc()) != null) {
			if (doctorService.findByNrc(doctor.getNrc()).getDoctorId() != oldDoctor.getDoctorId()) {
				model.addAttribute("nrcExists", true);
				model.addAttribute("doctor", doctor);
				return "updateDoctor";
			}
		}

		if (!doctor.getDoctorImage().isEmpty()) {
			String imageFormat = doctor.getImageName();
			int index = imageFormat.indexOf(".");
			String name = imageFormat.substring(index);
			doctor.setImageName(name);
		}
		doctorService.save(doctor);
		// photo empty or not? empty so original photo or not so updated new photo
		MultipartFile doctorImage = doctor.getDoctorImage();
		if (!doctorImage.isEmpty()) {
			String docImgName = doctor.getDoctorId() + oldImage;
			String newImgName = doctor.getDoctorId() + doctor.getImageName();
			byte[] bytes = doctorImage.getBytes();
			// old photo ko delete ml
			Files.delete(Paths.get("src/main/resources/static/image/doctor/" + docImgName));
			// rewrite updated photo to save
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/doctor/" + newImgName)));
			stream.write(bytes);
			stream.close();
		}
		return "redirect:/doctor/doctorInfo?id=" + doctor.getDoctorId();
	}

	@RequestMapping("/remove")
	private String remove(@RequestParam("doctorId") Long doctorId, Model model) throws IOException {
		String name = doctorId + doctorService.findById(doctorId).getImageName();
		doctorService.removeOne(doctorId);
		Files.delete(Paths.get("src/main/resources/static/image/doctor/" + name));

		List<Doctor> doctorList = doctorService.findAll();
		model.addAttribute("doctorList", doctorList);
		return "redirect:/doctor/doctorList";

	}

	@RequestMapping("/addSchedule")
	private String addSchedule(@RequestParam("id") Long id, Model model) {
		Doctor doctor = doctorService.findById(id);

		DoctorFreeTime doctorFreeTime = new DoctorFreeTime();
		model.addAttribute("doctor", doctor);

		model.addAttribute("doctorFreeTime", doctorFreeTime);

		List<DoctorFreeTime> doctorFreeTimeList = doctor.getDoctorFreeTime();
		model.addAttribute("doctorFreeTimeList", doctorFreeTimeList);

		return "addSchedule";
	}

	@PostMapping("/addSchedule")
	private String addSchedulePost(@ModelAttribute("DoctorFreeTime") DoctorFreeTime doctorFreeTime,
			@ModelAttribute("id") Long doctorId, Model model) throws IOException {

		Doctor doctor = doctorService.findById(doctorId);

		doctorService.updateDoctorFreeTime(doctor, doctorFreeTime);

		doctorFreeTimeService.save(doctorFreeTime);

		return "redirect:/doctor/addSchedule?id=" + doctorId;

	}
	
	@RequestMapping("/updateSchedule")
	private String updateDoctorFreeTime(@RequestParam("id") Long doctorFreeTimeId, Model model) {
		DoctorFreeTime doctorFreeTime = doctorFreeTimeService.findById(doctorFreeTimeId);
		Doctor doctor = doctorFreeTime.getDoctor();
		model.addAttribute("doctor", doctor);
		model.addAttribute("doctorFreeTime", doctorFreeTime);
		List<DoctorFreeTime> doctorFreeTimeList = doctor.getDoctorFreeTime();
		model.addAttribute("doctorFreeTimeList", doctorFreeTimeList);

		return "updateSchedule";

	}

	@PostMapping("/updateSchedule")
	private String updateSchedulePost(@ModelAttribute("doctorFreeTime") DoctorFreeTime doctorFreeTime,
			@ModelAttribute("id") Long doctorId, Model model) throws IOException {
		Doctor doctor = doctorService.findById(doctorId);

		doctorService.updateDoctorFreeTime(doctor, doctorFreeTime);
		doctorFreeTimeService.save(doctorFreeTime);
		model.addAttribute("doctorFreeTimeList", doctor.getDoctorFreeTime());
		return "redirect:/doctor/addSchedule?id=" + doctorId;

	}
	
	@RequestMapping("/deleteSchedule")
	private String deleteSchedule(@RequestParam("sid") String sId, Model model) throws IOException {
		
		int index =  sId.indexOf(" ");
		
		Long drId = Long.parseLong(sId.substring(0, index));		

		Long freeTimeId = Long.parseLong(sId.substring(index+1));
		
		Doctor doctor = doctorService.findById(drId);
		
		DoctorFreeTime freeTime = doctorFreeTimeService.findById(freeTimeId);
		
		doctorFreeTimeService.delete(freeTime);
		
		List<DoctorFreeTime> doctorFreeTimeList = doctor.getDoctorFreeTime();
		model.addAttribute("doctorFreeTimeList", doctorFreeTimeList);
		
		return "redirect:/doctor/addSchedule?id=" + drId;

	}
	
}
