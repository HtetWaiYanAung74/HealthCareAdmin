$(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
   
    
    
    var year1 = dtToday.getFullYear()-120;
    
    var year3 = year-25;
   
    var year4 = year-70;
	
	var month1;
	var year2;    
	
    if(month<4){
		year2 = year-1;
		if(month==3){
			month1 = 12;
			
		}else if(month==2){
			month1 = 11;
			
		}else{
			month1 = 10;
		}
	}else{
		month1 = month-3;
		year2 = year;
	}
	
	if(month <= 9){
		month = '0' + month;
	}
	
	if(month1 <= 9){
		month1 = '0' + month1;
	}
	if(day <= 9){
		day = '0' + day;
	}
	
	var maxDate = year + '-' + month + '-' + day;
    var minDate = year1 + '-' + month + '-' + day;
	var maxDate1 = year3 + '-' + month + '-' + day;
    var minDate1 = year2 + '-' + month1 + '-' + day;
    var minDate2 = year4 + '-' + month1 + '-' + day;
    
    console.log(year);
    console.log(year2);
    console.log(minDate1);
    console.log(minDate);
    console.log(maxDate);
    $('#dob').attr('max', maxDate);
    $('#dob').attr('min', minDate);
    
    $('#dischargeDate').attr('min', maxDate);
    $('#admissionDate').attr('max', maxDate);
	$('#admissionDate').attr('min', minDate1);
	
	$('#doctorDob').attr('max', maxDate1);
	$('#doctorDob').attr('min', minDate2);
});