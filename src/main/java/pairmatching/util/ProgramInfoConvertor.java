package pairmatching.util;

import static pairmatching.Constant.*;

import pairmatching.domain.ProgramInfo;

public class ProgramInfoConvertor {
    public static ProgramInfo makeProgramInfo(String programInfoPreProcessing) {
        String[] programDetailPreProcessing = programInfoPreProcessing.split(", ");
        validateProgramDetailsCnt(programDetailPreProcessing);
        return new ProgramInfo(programDetailPreProcessing);
    }

    private static void validateProgramDetailsCnt(String[] programDetailsPreProcessing) {
        if (programDetailsPreProcessing.length != PROGRAM_INFO_DETAILS_CNT) {
            throw new IllegalStateException("입력값의 양식이 잘못되었습니다.");
        }
    }
}
