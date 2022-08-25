package petfriends.alarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import petfriends.alarm.model.Alarm;

public interface AlarmRepository extends CrudRepository<Alarm, Long> {


    List<Alarm> findAllByUserId(String userId);

    List<Alarm> findAllByReservedId(Long reservedId);
    Alarm save(Alarm alarm);
}
