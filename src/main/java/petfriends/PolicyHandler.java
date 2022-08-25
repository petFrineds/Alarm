package petfriends;

import lombok.extern.slf4j.Slf4j;
import petfriends.config.KafkaProcessor;
import petfriends.alarm.dto.*;
import petfriends.alarm.model.Alarm;
import petfriends.alarm.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    @Autowired
    AlarmRepository alarmRepository;

    // 산책 시작
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverWalkStarted_(@Payload WalkStarted walkStarted) throws ParseException {

        if(walkStarted.isMe()) {
            Alarm alarm = new Alarm();
            alarm.setReservedId(walkStarted.getReservedId());
            alarm.setDogWalkerId(walkStarted.getDogWalkerId());
            alarm.setUserId(walkStarted.getUserId());
            alarm.setMessage(
                    String.format("예약번호 %s 의 산책이 %s 에 시작되었습니다",
                            walkStarted.getReservedId().toString(),
                            walkStarted.getWalkStartDate()));
            LocalDateTime current = LocalDateTime.now();
            alarm.setRegDate(java.sql.Timestamp.valueOf(current));
            alarmRepository.save(alarm);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverWalkEnded_(@Payload WalkEnded walkEnded) throws ParseException {
        if(walkEnded.isMe()) {
            Alarm alarm = new Alarm();
            alarm.setReservedId(walkEnded.getReservedId());
            alarm.setDogWalkerId(walkEnded.getDogWalkerId());
            alarm.setUserId(walkEnded.getUserId());
            alarm.setMessage(
                    String.format("예약번호 %s 의 산책이 %s 에 종료되었습니다",
                            walkEnded.getReservedId().toString(),
                            walkEnded.getWalkEndDate()));


            LocalDateTime current = LocalDateTime.now();
            alarm.setRegDate(java.sql.Timestamp.valueOf(current));

            alarmRepository.save(alarm);
        }
    }
}
