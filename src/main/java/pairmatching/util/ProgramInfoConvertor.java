package pairmatching.util;

import static pairmatching.Constant.*;
import static pairmatching.ErrorMessage.*;

import pairmatching.domain.ProgramInfo;

public class ProgramInfoConvertor {
    public static ProgramInfo makeProgramInfo(String programInfoPreProcessing) {
        String[] programDetailPreProcessing = programInfoPreProcessing.split(PROGRAM_INFO_DETAIL_DELIMETER);
        validateProgramDetailsCnt(programDetailPreProcessing);
        return new ProgramInfo(programDetailPreProcessing);
    }

    private static void validateProgramDetailsCnt(String[] programDetailsPreProcessing) {
        if (programDetailsPreProcessing.length != PROGRAM_INFO_DETAILS_CNT) {
            throw new IllegalArgumentException(INVALID_INPUT_FORM_ERROR);
        }
    }
}
