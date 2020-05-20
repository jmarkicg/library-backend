package hr.markic.library.dto.microblink;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlinkIDData {

    private String recognizer;

    private String version;

    private Date startTime;

    private Date finishTime;

    private float durationTimeInSeconds;

    private long taskId;

    private long workerId;

    private BlinkIDResult result;
}
