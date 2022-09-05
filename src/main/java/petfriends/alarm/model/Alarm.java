package petfriends.alarm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="alarm")
@Data
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;                            // 산책ID
    private String message;
    private Long reservedId;            // 예약ID
    private String userId;              // 회원ID

    @Column(name = "dogwalker_id")
    private String dogWalkerId;         // 도그워커ID
    private Date regDate;
    private String readYn;              // 확인여부
}