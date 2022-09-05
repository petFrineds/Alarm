package petfriends.alarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import petfriends.alarm.model.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {


    List<Alarm> findAllByUserId(String userId);

    List<Alarm> findAllByReservedId(Long reservedId);
    Alarm save(Alarm alarm);

    @Modifying
    @Query("update Alarm a set a.readYn = :readYn where a.userId= :userId")
    int updateAlarmRead(String userId, String readYn);

}
