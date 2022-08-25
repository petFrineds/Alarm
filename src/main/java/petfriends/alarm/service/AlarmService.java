package petfriends.alarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petfriends.alarm.model.Alarm;
import petfriends.alarm.repository.AlarmRepository;

@Service
public class AlarmService {
	 
	 @Autowired
	 AlarmRepository alarmRepository;

	public List<Alarm> findAllByUserId(String userId) {
		return alarmRepository.findAllByUserId(userId);
	}

	public List<Alarm> findAllByReservationId(Long reservedId){
		return alarmRepository.findAllByReservedId(reservedId);
	}


}

