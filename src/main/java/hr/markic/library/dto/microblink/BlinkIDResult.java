package hr.markic.library.dto.microblink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlinkIDResult {

    private String primaryID;

    private String secondaryID;

    private String documentCode;

    private String documentNumber;

    private String documentType;

    private String issuer;

    private String issuerName;

    private String sex;

    private String nationality;

    private String nationalityName;

    private BlinkIDDate dateOfBirth;

    private BlinkIDDate dateOfExpiry;

    private String alienNumber;

    private String applicationReceiptNumber;

    private String immigrantCaseNumber;

    private boolean mrtdVerified;

    private String opt1;

    private String opt2;

    private String rawMRZString;

    private int age;

    private Boolean isBelowAgeLimit;

    private String type;
}
