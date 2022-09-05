package petfriends.alarm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import petfriends.alarm.model.Alarm;
import petfriends.alarm.repository.AlarmRepository;
import petfriends.alarm.service.AlarmService;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/")
 public class AlarmController {

	 @Autowired
	 AlarmService alarmService;

	 @Autowired
	 AlarmRepository alarmRepository;


	@RequestMapping(method= RequestMethod.GET, path="/alarms/{userId}")
	public ResponseEntity<List<Alarm>> findAllByUserId(@PathVariable String userId){
		List<Alarm> alarms =  alarmService.findAllByUserId(userId);
		if(!alarms.isEmpty()){
			return new ResponseEntity<List<Alarm>>(alarms, HttpStatus.OK);
		}
		//내용 없을 때
		return new ResponseEntity<List<Alarm>>(alarms, HttpStatus.NO_CONTENT);
	}


	@RequestMapping(method= RequestMethod.GET, path="/alarms/count/{userId}")
	public int findUnreadAlarm(@PathVariable String userId){
		List<Alarm> alarms = alarmService.findAllByUserId(userId);
		alarms = alarms.stream()
				.filter(a -> a.getReadYn().equals("N"))
				.collect(Collectors.toList());

		return alarms.size();
	}

	@Transactional
	@RequestMapping(method= RequestMethod.PATCH, path="/alarms/{userId}")
	public int updateAlarmRead(@PathVariable String userId){

		int count = alarmRepository.updateAlarmRead(userId, "Y");
		return count;
	}

}

 