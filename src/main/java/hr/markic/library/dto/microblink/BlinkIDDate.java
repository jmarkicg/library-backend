package hr.markic.library.dto.microblink;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlinkIDDate {

    private int day;

    private int month;

    private int year;

    private String originalString;
}
