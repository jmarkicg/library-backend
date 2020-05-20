package hr.markic.library;

import hr.markic.library.dto.microblink.BlinkIDDate;
import hr.markic.library.dto.microblink.BlinkIDResult;
import hr.markic.library.util.DocumentType1Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApplicationTests {


	@Test
	void testCheckRowsValidity() {
		String documentCode = "IO";
		String issuer = "HRV";
		String documentNumber = "113572751";
		String opt1 = "46791056953<<<<";

		BlinkIDResult result = new BlinkIDResult();
		result.setDocumentCode(documentCode);
		result.setIssuer(issuer);
		result.setDocumentNumber(documentNumber);
		result.setOpt1(opt1);

		String expectedRow1 = "IOHRV113572751646791056953<<<<";

		System.out.println(DocumentType1Validator.checkRow1Validity(result, expectedRow1));

		int dobDay = 21;
		int dobMonth = 10;
		int dobYear = 1986;
		int doeDay = 15;
		int doeMonth = 01;
		int doeYear = 2023;
		String sex = "F";
		String nationality = "HRV";
		String opt2 = "<<<<<<<<<<<";

		result.setDateOfBirth(new BlinkIDDate(dobDay, dobMonth, dobYear, ""));
		result.setDateOfExpiry(new BlinkIDDate(doeDay, doeMonth, doeYear, ""));
		result.setSex(sex);
		result.setNationality(nationality);
		result.setOpt2(opt2);

		String expectedRow2 = "8610212F2301158HRV<<<<<<<<<<<1";

		System.out.println(DocumentType1Validator.checkRow2Validity(result, expectedRow2));

		String primaryId = "MARKIC GLAVAS";
		String secondaryId = "JELENA";
		result.setPrimaryID(primaryId);
		result.setSecondaryID(secondaryId);

		String expectedRow3 = "MARKIC<GLAVAS<<JELENA<<<<<<<<<";
		System.out.println(DocumentType1Validator.checkRow3Validity(result, expectedRow3));

		System.out.println(DocumentType1Validator.checkCompositeCheckDigit(result, expectedRow1, expectedRow2));
	}

}
