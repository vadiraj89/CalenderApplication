package com.interview.serviceLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.interview.DoaLayer.CalenderDoa;
import com.interview.Exception.UserAlreadyPresentException;
import com.interview.Exception.UseridNotPresentException;
import com.interview.Models.Calender;
import com.interview.Models.Employee;
import com.interview.Models.FreeslotRequest;
import com.interview.Models.Meeting;

public class CalenderService {
	public CalenderService() {
		super();
	}

	private CalenderDoa calenderdao = new CalenderDoa();

	public Employee addEmployee(String name, int age, String empid, String LoginTime, String LogOfftime) throws UserAlreadyPresentException {
		if (getCalenderdao().getEmployee(empid) == null) {
			
			return getCalenderdao().addEmployee(name, age, empid, LoginTime, LogOfftime);
		}

		else throw new UserAlreadyPresentException(empid);
	}

	public List<String> getConflicts(Meeting meetingrequest) throws UseridNotPresentException {
		List<String> output = new ArrayList<String>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		if(getCalenderdao().getEmployee(meetingrequest.getHost_id())!=null) {
			try {
				if(!getCalenderdao().getEmployee(meetingrequest.getHost_id()).getCal().isTimeConflicts(formatter.parse(meetingrequest.getStartTime()),
							formatter.parse(meetingrequest.getEndTime())))
				{
					output.add(meetingrequest.getHost_id());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			throw new UseridNotPresentException(meetingrequest.getHost_id());
		}
		for (String empid : meetingrequest.getParticipant_ids()) {
			Employee emp = getCalenderdao().getEmployee(empid);
			if(emp==null) {
				throw new UseridNotPresentException(meetingrequest.getHost_id());
			}
			else {
			try {
				if (emp != null && !emp.getCal().isTimeConflicts(formatter.parse(meetingrequest.getStartTime()),
						formatter.parse(meetingrequest.getEndTime()))) {
					output.add(emp.getEmpid());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
	
        if(output.size()<=0) {
        	output.add("No Conflicts");
        }
		return output;
	}

	public List<Employee> getAllEmployee() {
		
		return getCalenderdao().getEmployee();
	}
	
	public Meeting CreateMeeting(Meeting meetingrequest) throws UseridNotPresentException {
		String meetingid = meetingrequest.getMeeting_description()+meetingrequest.getMeeting_locatin()+meetingrequest.getStartTime()+meetingrequest.getEndTime();
		meetingrequest.setMeeting_id(meetingid);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Employee host = getCalenderdao().getEmployee(meetingrequest.getHost_id());
		if(host==null) {
			throw new UseridNotPresentException(meetingrequest.getHost_id());
		}
		List<Employee> participants = new ArrayList<Employee>();
		for(String s:meetingrequest.getParticipant_ids()) {
			Employee ee = getCalenderdao().getEmployee(s);
			if(ee==null) {
				throw new UseridNotPresentException(s);
			}
			participants.add(ee);
		}
		try {
			host.getCal().addMeeting(meetingid, formatter.parse(meetingrequest.getStartTime()), formatter.parse(meetingrequest.getEndTime()), "Host");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		for(Employee e:participants) {
			try {
				e.getCal().addMeeting(meetingid, formatter.parse(meetingrequest.getStartTime()), formatter.parse(meetingrequest.getEndTime()), "participant");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return getCalenderdao().addMeeting( meetingrequest);
	}
	
	public TreeMap<Date,Date> getEmployeesandFreeSlots(FreeslotRequest request) throws UseridNotPresentException{
		List<Calender> cal=new ArrayList<Calender>();
		for(String str:request.getEmpids()) {
			Employee emp = getCalenderdao().getEmployee(str);
			if(emp==null) {
				throw new UseridNotPresentException(str);
			}
			cal.add(emp.getCal());
		}
		
		return getFreeSlots(cal,request.getDuration(),request.getDate());
	}
	
	public TreeMap<Date,Date> getFreeSlots(List<Calender> cal,int duration,String date){
		
		Date starttime = cal.get(0).getLoginTimeDate(date);
		Date endtime = cal.get(0).getLogOffTimeDate(date);
		for(Calender c:cal) {
			if(c.getLoginTimeDate(date).compareTo(starttime)>0)
			{
				starttime=c.getLoginTimeDate(date);
			}
		}
		for(Calender c:cal) {
			if(endtime.compareTo(c.getLogOffTimeDate(date))>0)
			{
				endtime=c.getLogOffTimeDate(date);
			}
		}
		
		final long ONE_MINUTE_IN_MILLIS = 60000;
		TreeMap<Date,Date> answer = new TreeMap<Date,Date>();
		while(true) {
			long starttimeinlong = starttime.getTime();
			Date interstop = new Date(starttimeinlong + (duration * ONE_MINUTE_IN_MILLIS));
			
			if(interstop.compareTo(endtime)>0) {
				break;
			}
			else {
				boolean noconflict = true;
				for(Calender c:cal) {
					noconflict = noconflict && c.isTimeConflicts(starttime, interstop);
				}
				if(noconflict) {
					answer.put(starttime, interstop);
				}
			}
			
			starttime=interstop;
		}
		
		return answer;
		
	}

	public CalenderDoa getCalenderdao() {
		return calenderdao;
	}

	public void setCalenderdao(CalenderDoa calenderdao) {
		this.calenderdao = calenderdao;
	}
}
