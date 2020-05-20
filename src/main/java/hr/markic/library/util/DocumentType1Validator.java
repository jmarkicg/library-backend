package hr.markic.library.util;

import hr.markic.library.constants.BlinkConstants;
import hr.markic.library.dto.microblink.BlinkIDDate;
import hr.markic.library.dto.microblink.BlinkIDResult;

/**
 * Type 1 document ID validator.
 */
public class DocumentType1Validator {

    public static boolean checkDataValidity(BlinkIDResult result){
        String rawMRZString = result.getRawMRZString();
        String[] rows = rawMRZString.split("\\n");
        if (rows.length != 3) return false;

        //country issuer, id
        String row1 = rows[0];
        if (!checkRow1Validity(result, row1)) return false;

        //DOB,country
        String row2 = rows[1];
        if (!checkRow2Validity(result, row2)) return false;

        if (!checkCompositeCheckDigit(result, row1, row2)) return false;

        //lastname, firstname
        String row3 = rows[2];
        if (!checkRow3Validity(result, row3)) return false;

        return true;
    }

    public static boolean checkRow1Validity(BlinkIDResult response, String row1) {
        String expected = response.getDocumentCode() + response.getIssuer() + response.getDocumentNumber() +
                getCheckNumber(response.getDocumentNumber()) + response.getOpt1();

        return expected.equals(row1);
    }

    public static boolean checkRow2Validity(BlinkIDResult response, String row2) {
       String expected =  getDateAndCheckDigit(response.getDateOfBirth()) + response.getSex() +
                getDateAndCheckDigit(response.getDateOfExpiry()) + response.getNationality() + response.getOpt2();

        //overall check digit not checked here
        row2.substring(0, row2.length()-1);
        return expected.equals(row2.substring(0, row2.length()-1));

    }

    public static boolean checkRow3Validity(BlinkIDResult response, String row3) {
        String expected = response.getPrimaryID().replace(" ", "<") + "<<"
                + response.getSecondaryID().replace(" ", "<");

        if (expected.length() < BlinkConstants.TYPE_1_ROW_LENGTH){
            int length = BlinkConstants.TYPE_1_ROW_LENGTH - expected.length();
            for (int i = 0; i < length; i++) {
                expected += "<";
            }
        }

        return expected.equals(row3);
    }

    private static String getDateAndCheckDigit(BlinkIDDate date){
        String dateFormatted = DateUtil.getDateInFormat(date, DateUtil.CHECK_DATE_FORMAT);
        return dateFormatted + getCheckNumber(dateFormatted);
    }

    /**
     * String calculation check digit.
     * @param checkString
     * @return
     */
    private static String getCheckNumber(String checkString){
        int [] arrayWeight = {7, 3, 1};
        char[] ch = checkString.toCharArray();

        int indWeight = 0;
        int sum = 0;
        for (char c : ch) {
            int num = 0;
            if (c >= '0' && c <= '9') {
                num = c - '0';
            } else {
                num = c - 'A' + 10;
            }
            sum += num * arrayWeight[indWeight];
            indWeight = (indWeight == 2)? 0 : indWeight + 1;
        }
        return String.valueOf(sum % 10);
    }

    public static boolean checkCompositeCheckDigit(BlinkIDResult result, String row1, String row2) {
        String merged = row1.substring(5) + getDateAndCheckDigit(result.getDateOfBirth()) +
                getDateAndCheckDigit(result.getDateOfExpiry());
        String overallCheckDigit = getCheckNumber(merged);

        return  overallCheckDigit.equals(row2.substring(row2.length()-1));
    }

}
