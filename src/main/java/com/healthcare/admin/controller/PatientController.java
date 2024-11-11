package com.healthcare.admin.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.admin.domain.Doctor;
import com.healthcare.admin.domain.Patient;
import com.healthcare.admin.domain.Payment;
import com.healthcare.admin.domain.RoomOrWard;
import com.healthcare.admin.domain.Treatment;
import com.healthcare.admin.domain.User;
import com.healthcare.admin.domain.security.Role;
import com.healthcare.admin.domain.security.UserRole;
import com.healthcare.admin.service.DoctorService;
import com.healthcare.admin.service.PatientService;
import com.healthcare.admin.service.PaymentService;
import com.healthcare.admin.service.RoomOrWardService;
import com.healthcare.admin.service.TreatmentService;
import com.healthcare.admin.service.UserService;
import com.healthcare.admin.utility.SecurityUtility;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private RoomOrWardService roomOrWardService;

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private TreatmentService treatmentService;
	
	@Autowired
	private PaymentService paymentService;
	
	private Long patId;
	
	private Long pattid;
	
	private String oldDateCount = null;
	
	@RequestMapping("/addPatient")
	private String addPatient(Model model) {
		
		User user = new User();
		Patient patient = new Patient();
		
		List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
		
		model.addAttribute("patient",patient);
		model.addAttribute("user",user);
		model.addAttribute("roomOrWardList",roomOrWardList);
		return "patient-form";
	}
	
	@PostMapping("/addPatient")
	private String addPatientPost(@ModelAttribute("patient")Patient patient, @ModelAttribute("user") User user, Model model, @ModelAttribute("roomOrWardId") Long roomOrWardId) {
		
		if(userService.findByEmail(user.getEmail())!=null) {
			model.addAttribute("emailExists",true);
			model.addAttribute("patient",patient);
			model.addAttribute("user",user);
			List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
			model.addAttribute("roomOrWardList",roomOrWardList);
			
			if(patient.getType().equals("Out-Patient")) {
				System.out.println("ggwp :"+patient.getType());
				model.addAttribute("isOutPatient","out-patient");
			}
			return "patient-form";
		}
		
		if(patientService.findByPhone(patient.getPhone())!=null) {
			model.addAttribute("phoneExists",true);
			model.addAttribute("patient",patient);
			model.addAttribute("user",user);
			List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
			model.addAttribute("roomOrWardList",roomOrWardList);
			
			if(patient.getType().equals("Out-Patient")) {
				System.out.println("ggwp :"+patient.getType());
				model.addAttribute("isOutPatient","out-patient");
			}
			return "patient-form";
			
		}
		
		if(patientService.findByNRC(patient.getNrc())!=null) {
			model.addAttribute("nrcExists",true);
			model.addAttribute("patient",patient);
			model.addAttribute("user",user);
			List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
			model.addAttribute("roomOrWardList",roomOrWardList);
			
			if(patient.getType().equals("Out-Patient")) {
				System.out.println("ggwp :"+patient.getType());
				model.addAttribute("isOutPatient","out-patient");
			}
			return "patient-form";
			
		}
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode("default-password");
		user.setPassword(encryptedPassword);
		
		Integer index = user.getEmail().indexOf("@");
		
		String username = patient.getFirstName()+" "+patient.getLastName()+" "+user.getEmail().substring(0,index);
		
		user.setUsername(username);
		
		
		
		user.setPatient(patient);
		if(patient.getType().equals("In-Patient")) {
			RoomOrWard roomOrWard = roomOrWardService.findById(roomOrWardId);
			Integer spaceCount = roomOrWard.getLeftSpace();
			
			if(spaceCount>0) {
				
				roomOrWard.setLeftSpace(spaceCount-1);
				roomOrWardService.save(roomOrWard);
			}
			patient.setRoomOrWard(roomOrWard);
		}
		
		
		Role role = new Role();
		role.setRoleId(2);
		role.setName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<UserRole>();
		
		userRoles.add(new UserRole(user, role));
		
		userService.createUser(user, userRoles);
		
		return "redirect:/patient/patient-list";
	}
	
	@RequestMapping("/patient-list")
	private String patientList(Model model) {
		
		List<Patient> patientList = patientService.findByType("In-Patient");
		List<Patient> patientList1 = patientService.findByType("Out-Patient");
		
		System.out.println("bla "+patientList1.size());
		
		model.addAttribute("patientList",patientList);
		model.addAttribute("patientList1",patientList1);
		return "patient-list";
	}
	
	@RequestMapping("/patientInfo")
	private String patientInfo(@RequestParam("id")Long patientId, Model model) {
		Patient patient = patientService.findById(patientId);
		
		if(patient.getType().equals("In-Patient")) {
			if(patient.getRoomOrWard()==null) {
				model.addAttribute("NoRoomChosen", true);
				
			}else {
				
				model.addAttribute("RoomExist", true);
			}
			model.addAttribute("In-Patient",true);
			
		}else {
			
			model.addAttribute("Out-Patient",true);
		}
		
		model.addAttribute("patient",patient);
		return "patient-info";
		
	}
	
	@RequestMapping("/patientUpdate")
	private String patientUpdate(@RequestParam("pid")Long patientId, Model model) {
		
		Patient patient = patientService.findById(patientId);
		User user = patient.getUser();
		
		List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
		
		if(patient.getRoomOrWard()!=null) {
			Long roomId = patient.getRoomOrWard().getRoomId();
			model.addAttribute("roomId", roomId);
			roomOrWardList = roomOrWardService.findByIdAndLeftSpace(0, patient.getRoomOrWard().getRoomId());
		}
		
		if(patient.getType().equals("Out-Patient")) {
			model.addAttribute("isOutPatient","out-patient");
			model.addAttribute("inPatientDisable",true);
			
		}else {
			model.addAttribute("isInPatient","in-patient");
			
		}
		model.addAttribute("roomOrWardList",roomOrWardList);
		model.addAttribute("user",user);
		model.addAttribute("patient",patient);
		
		return "patient-update";
	}
	
	@PostMapping("/patientUpdate")
	private String patientUpdatePost(@ModelAttribute("patient") Patient patient, @ModelAttribute("user") User user, Model model, @ModelAttribute("roomOrWardId") Long roomOrWardId) throws Exception {
		
		User currentUser = userService.findById(user.getUserId());
		
		if(currentUser == null) {
			throw new Exception("User not found");
		}
		
		if(userService.findByEmail(user.getEmail())!=null) {
			if(userService.findByEmail(user.getEmail()).getUserId() != currentUser.getUserId()) {
				model.addAttribute("emailExists",true);
				
				Patient patient1 = patientService.findById(patient.getPatientId());
				
				List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
				
				if(patient1.getRoomOrWard()!=null) {
					Long roomId = patient1.getRoomOrWard().getRoomId();
					model.addAttribute("roomId", roomId);
					roomOrWardList = roomOrWardService.findByIdAndLeftSpace(0, patient1.getRoomOrWard().getRoomId());
				}
				
				if(patient1.getType().equals("Out-Patient")) {
					model.addAttribute("isOutPatient","out-patient");
					model.addAttribute("inPatientDisable",true);
					
				}else {
					model.addAttribute("isInPatient","in-patient");
					
				}
				model.addAttribute("roomOrWardList",roomOrWardList);
				model.addAttribute("user",user);
				model.addAttribute("patient",patient);
				
				return "patient-update";
			}
		}
		
		if(patientService.findByPhone(patient.getPhone())!=null) {
			
			System.out.println("cur : "+currentUser.getPatient().getPatientId());
			System.out.println("new : "+patient.getPatientId());
			
			if(patientService.findByPhone(patient.getPhone()).getPatientId() != currentUser.getPatient().getPatientId()) {
				model.addAttribute("phoneExists",true);
				
				Patient patient1 = patientService.findById(patient.getPatientId());
				
				List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
				
				if(patient1.getRoomOrWard()!=null) {
					Long roomId = patient1.getRoomOrWard().getRoomId();
					model.addAttribute("roomId", roomId);
					roomOrWardList = roomOrWardService.findByIdAndLeftSpace(0, patient1.getRoomOrWard().getRoomId());
				}
				
				if(patient1.getType().equals("Out-Patient")) {
					model.addAttribute("isOutPatient","out-patient");
					model.addAttribute("inPatientDisable",true);
					
				}else {
					model.addAttribute("isInPatient","in-patient");
					
				}
				model.addAttribute("roomOrWardList",roomOrWardList);
				model.addAttribute("user",user);
				model.addAttribute("patient",patient);
				
				return "patient-update";
			}
		}
		
		if(patientService.findByNRC(patient.getNrc())!=null) {
			if(patientService.findByNRC(patient.getNrc()).getPatientId() != currentUser.getPatient().getPatientId()) {
				model.addAttribute("nrcExists",true);
				
				Patient patient1 = patientService.findById(patient.getPatientId());
				
				List<RoomOrWard> roomOrWardList = roomOrWardService.findByLeftSpaceGreaterThan(0);
				
				if(patient1.getRoomOrWard()!=null) {
					Long roomId = patient1.getRoomOrWard().getRoomId();
					model.addAttribute("roomId", roomId);
					roomOrWardList = roomOrWardService.findByIdAndLeftSpace(0, patient1.getRoomOrWard().getRoomId());
				}
				
				if(patient1.getType().equals("Out-Patient")) {
					model.addAttribute("isOutPatient","out-patient");
					model.addAttribute("inPatientDisable",true);
					
				}else {
					model.addAttribute("isInPatient","in-patient");
					
				}
				model.addAttribute("roomOrWardList",roomOrWardList);
				model.addAttribute("user",user);
				model.addAttribute("patient",patient);
				
				return "patient-update";
			}
		}
		
		
		Patient oldPatient = patientService.findById(patient.getPatientId());
		
		if(patient.getType()==null && roomOrWardId==-1) { // auto fill in-patient
			patient.setType("In-Patient");
			patient.setRoomOrWard(oldPatient.getRoomOrWard());
		}else {
			if(patient.getType().equals("In-Patient")) { //out to in
				
				System.out.println("ggwp : "+roomOrWardId);
				RoomOrWard roomOrWard = roomOrWardService.findById(roomOrWardId);
				
				patient.setRoomOrWard(roomOrWard);
				Integer spaceCount = roomOrWard.getLeftSpace();
				
				if(spaceCount>0) {
					roomOrWard.setLeftSpace(spaceCount-1);
					roomOrWardService.save(roomOrWard);
				}
				
				
				
			}else {
				if(patient.getRoomOrWard()!=null) {
					RoomOrWard roomOrWard = patient.getRoomOrWard();
					roomOrWard.setLeftSpace(roomOrWard.getLeftSpace()+1);
					roomOrWardService.save(roomOrWard);
					//treatment + dateCount
					patient.setRoomOrWard(null);
				}
				
			}
		}
		
//		if(patient.getType().equals("Out-Patient") && roomOrWardId==-1) { // change from in to out-patient
//			patient.setRoomOrWard(null);
//			patient.setAdmissionDate(null);
//			RoomOrWard roomOrWard = oldPatient.getRoomOrWard();
//			roomOrWard.setLeftSpace(roomOrWard.getLeftSpace()+1);
//			roomOrWardService.save(roomOrWard);
//		}
		
		if(patient.getDischargeDate()!=null) {
			patient.setIsDeleteable(true);
		
			LocalDate admissionDate = LocalDate.parse(patient.getAdmissionDate());
			LocalDate dischargeDate = LocalDate.parse(patient.getDischargeDate());
			
			Period period = Period.between(admissionDate, dischargeDate);
			int dateCount = period.getDays();
			
			patient.setDateCount(dateCount);
		}
		
		if(oldPatient.getDateCount()!=null) {
			
			oldDateCount = oldPatient.getDateCount().toString();
		}
		
		Integer index = user.getEmail().indexOf("@");
		
		String username = patient.getFirstName()+" "+patient.getLastName()+" "+user.getEmail().substring(0,index);
		
		currentUser.setEmail(user.getEmail());
		currentUser.setUsername(username);	
				
				
		userService.saveUser(currentUser);
		patientService.save(patient);
		
		if(patient.getDischargeDate()!=null) {
			return "redirect:/patient/patient-payment?pid="+patient.getPatientId();
		}
		
		return "redirect:/patient/patient-list";
	}
	
	@RequestMapping("/patientDelete")
	private String patientDelete(@RequestParam("pid")Long patientId, Model model) {
				
		Patient patient = patientService.findById(patientId);
		
		if(patient.getType().equals("In-Patient")) {
			Long roomOrWardId = patient.getRoomOrWard().getRoomId();
			
			RoomOrWard roomOrWard = roomOrWardService.findById(roomOrWardId);
			roomOrWard.setLeftSpace(roomOrWard.getLeftSpace()+1);
			
			roomOrWardService.save(roomOrWard);
		}
		
		patientService.deleteById(patientId);
				
		return "redirect:/patient/patient-list";
		
	}
	
	
	@RequestMapping("/patient-treatment")
	private String patientTreatment(@RequestParam("pid")Long patientId, Model model ) {
		
		Patient patient = patientService.findById(patientId);		
		
		pattid = patientId;
		
		model.addAttribute("patient",patient);
		
		model.addAttribute("treatmentList", patient.getTreatmentList());		
		
		return "patient-treatment";
	}
	
	@RequestMapping("/treatment-form")
	private String treatmentForm(Model model) {
		
		Treatment treatment = new Treatment();
		Patient patient = patientService.findById(pattid);
		
		List<Doctor> doctorList= doctorService.findAll();
		model.addAttribute("patient",patient);
		model.addAttribute("treatment",treatment);
		model.addAttribute("doctorList",doctorList);
		
		return "treatment-form";
	}
	
	@PostMapping("/treatment-form")
	private String treatmentFromPost(@ModelAttribute("treatment") Treatment treatment, @ModelAttribute("doctorIdSpec") String idSpec , Model model, @ModelAttribute("patientId") Long patientId) {
		
		Long doctorId = Long.parseLong(idSpec.substring(0, idSpec.indexOf(" ")));
		
		treatment.setDoctor(doctorService.findById(doctorId));
		
		LocalDate localDate = LocalDate.now();
		
		String recordDate = localDate.toString();
		
		treatment.setRecordDate(recordDate);
		treatment.setPatient(patientService.findById(patientId));
		
		treatmentService.save(treatment);
		
		return "redirect:patient-treatment?pid="+patientId;
		
	}
	
	@RequestMapping("/treatment-update")
	private String treatmentUpdate(@ModelAttribute("tid") Long treatmentId, Model model) {
		
		Treatment treatment = treatmentService.findById(treatmentId);
		List<Doctor> doctorList= doctorService.findAll();
		
		model.addAttribute("treatment",treatment);
		model.addAttribute("patient",treatment.getPatient());
		model.addAttribute("doctorList",doctorList);
		
		String ggwpValue = treatment.getDoctor().getDoctorId()+" "+treatment.getDoctor().getSpecialization();
		
		model.addAttribute("ggwpValue", ggwpValue);
		
		return "treatment-update";
	}
	
	@PostMapping("/treatment-update")
	private String treatmentUpdatePost(@ModelAttribute("treatment")Treatment treatment, @ModelAttribute("doctorIdSpec") String idSpec, @ModelAttribute("patientId")Long patientId) {
		
		if(idSpec!=null) {
			
			System.out.println("This is not null");
			Long doctorId = Long.parseLong(idSpec.substring(0, idSpec.indexOf(" ")));
			
			treatment.setDoctor(doctorService.findById(doctorId));
		}else {
			treatment.setDoctor(treatmentService.findById(treatment.getTreatmentId()).getDoctor());
		}
		
		
		LocalDate localDate = LocalDate.now();
		
		String recordDate = localDate.toString();
		
		treatment.setRecordDate(recordDate);
		treatment.setPatient(patientService.findById(patientId));

		treatmentService.save(treatment);
		
		return "redirect:patient-treatment?pid="+patientId;
	}

	@RequestMapping("/treatmentDelete")
	private String treatmentDelete(@ModelAttribute("tid")Long treatmentId) {
		
		Long patientId = treatmentService.findById(treatmentId).getPatient().getPatientId();
		
		treatmentService.remove(treatmentId);
		
		return "redirect:patient-treatment?pid="+patientId;
	}
	
	@RequestMapping("/patient-payment")
	private String payment(@RequestParam("pid") Long patientId,
			@RequestParam(value = "missingRequiredField",required = false) boolean missingRequiredField, Model model) {
		
		Double remainingAmt = 0.0;		
		Double totalAmt = 0.0;
		
		patId = patientId;
		
		Payment payment = new Payment();
		
		Patient patient = patientService.findById(patientId);		

		model.addAttribute("patient", patient);
		model.addAttribute("payment", payment);
		model.addAttribute("paymentList", patient.getPaymentList());
		
		List<Payment> paymentList = patient.getPaymentList();	
		
		Integer dateCount = patient.getDateCount();
		
		Double amt = patient.getRoomOrWard().getPrice();
		
		payment.setTotalAmount(amt*dateCount);
		
		totalAmt = payment.getTotalAmount();	
		
		if(paymentList.size() == 0) {
			
			remainingAmt = totalAmt;					
			
		} else {
			
			int dc = 0;
			if(oldDateCount!=null) {
				dc = dateCount - Integer.parseInt(oldDateCount); //
			}
			
			for (int i = 0; i < paymentList.size(); i++) {
				
				if(dc!=0) {
					paymentList.get(i).setRemainingAmount(paymentList.get(i).getRemainingAmount()+(dc*patient.getRoomOrWard().getPrice())); //
					paymentList.get(i).setTotalAmount(paymentList.get(i).getTotalAmount()+(dc*patient.getRoomOrWard().getPrice()));
				}

				if (i == paymentList.size() - 1) {

					remainingAmt = paymentList.get(i).getRemainingAmount();

					totalAmt = remainingAmt;

					payment.setTotalAmount(totalAmt);
					
					

				}
				
			}
			
			
			
			oldDateCount=null;
			
		}		

		if (remainingAmt == 0.0) {
			
			model.addAttribute("disableButtons", false);
			
			
		} else {
			
			model.addAttribute("disableButtons", true);
			
		}
				
		model.addAttribute("paymentList", paymentList);
		patient.setPaymentList(paymentList);
		patientService.save(patient);
		
		if (missingRequiredField) {			
			model.addAttribute("missingRequiredField", true);			
		}
		
		return "payment";
		
	}
	
	@PostMapping("/patient-payment")
	private String addPaymentPost(@RequestParam("pid") Long patientId, 
			@ModelAttribute("payment") Payment payment, Model model) {
		
		Double remainingAmt, totalAmt = 0.0;		
				
		Patient patient = patientService.findById(patientId);
		
		payment.setPatient(patient);
		
		paymentService.save(payment);
		
		List<Payment> paymentList = patient.getPaymentList();
		
		for(int i=0; i<paymentList.size(); i++) {
			
			if( i == paymentList.size()-1) {

				remainingAmt = paymentList.get(i).getRemainingAmount();
				
				totalAmt = remainingAmt;
				
				payment.setTotalAmount(totalAmt);
			
				model.addAttribute("paymentIf", true);
				
			} else {
				
				model.addAttribute("paymentIf", false);
				
			}
			
		}
				
		model.addAttribute("patient", patient);
		model.addAttribute("paymentList", paymentList);
		
		Payment payment1 = new Payment();
		model.addAttribute("payment", payment1);
					
		return "redirect:/patient/patient-payment?pid="+patient.getPatientId();
		
	}
	
	@RequestMapping("/patient-payment/delete") 
	private String deletePayment(@RequestParam("id") Long paymentId) {
				
		Patient patient = patientService.findById(patId);
		
		List<Payment> paymentList = patient.getPaymentList();
		
		int count = 0;
		
		for(int i=0; i<paymentList.size(); i++) {
			
			if ( paymentId == paymentList.get(i).getPaymentId() ) {
				
				count = i;
				break;
				
			} 
			
		}
		
		for(int i=0; i<paymentList.size(); i++) {
			
			if ( i > count ) {
				
				paymentList.get(i).setRemainingAmount(paymentList.get(i).getRemainingAmount()+paymentList.get(count).getPaidAmount());
				paymentList.get(i).setTotalAmount(paymentList.get(i).getTotalAmount()+paymentList.get(count).getPaidAmount());
			
			}
			
			paymentService.save(paymentList.get(i));
			
		}		
		
		paymentService.deleteById(paymentId);
		
		return "redirect:?pid="+patId;
		
	}	
	
}
