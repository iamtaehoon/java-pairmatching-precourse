package pairmatching.controller;

import static pairmatching.ErrorMessage.*;

import java.util.LinkedHashMap;

import pairmatching.code.MainCode;
import pairmatching.code.RematchCode;
import pairmatching.domain.ProgramInfo;
import pairmatching.service.PairMatchingService;
import pairmatching.util.ProgramInfoConvertor;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private MainCode mainCode = null;
    private final PairMatchingService pairMatchingService = new PairMatchingService();

    public void run() {
        while (mainCode != MainCode.QUIT) {
            try {
                mainCode = chooseMainFunction();
                executeFunction(mainCode);
            } catch (IllegalArgumentException e) {
                OutputView.showErrorMessage(e);
                run();
            }
        }
    }

    private void executeFunction(MainCode mainCode) {
        if (mainCode == MainCode.MATCHING) {
            executePairMatching();
        }
        if (mainCode == MainCode.INQUERY) {
            executePairInquery();
        }
        if (mainCode == MainCode.RESET) {
            executeReset();
        }
    }

    private void executeReset() {
        pairMatchingService.resetAllPair();
        OutputView.showResetMessage();
    }

    private void executePairInquery() {
        ProgramInfo programInfo = makeProgramInfoUsingInput();
        if (pairMatchingService.alreadyHavePair(programInfo)) {
            LinkedHashMap<String, String> thisProgramsPair = pairMatchingService.getThisProgramsPair(programInfo);
            OutputView.showResult(thisProgramsPair);
            return;
        }
        throw new IllegalArgumentException(HAVE_NOT_MATCHING_ERROR);
    }

    private void executePairMatching() {
        ProgramInfo programInfo = makeProgramInfoUsingInput();
        if (pairMatchingService.alreadyHavePair(programInfo)) {
            checkRematching(programInfo);
            return;
        }
        pairMatchingService.makePairThisProgramInfo(programInfo);
        OutputView.showResult(pairMatchingService.getThisProgramsPair(programInfo));
    }

    private ProgramInfo makeProgramInfoUsingInput() {
        try {
            String programInfoPreProcessing = InputView.chooseProgramInfo();
            return ProgramInfoConvertor.makeProgramInfo(programInfoPreProcessing);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return makeProgramInfoUsingInput();
        }
    }

    private void checkRematching(ProgramInfo programInfo) {
        try {
            RematchCode rematchCode = RematchCode.find(InputView.chooseReMatching());
            if (rematchCode == RematchCode.YES) {
                pairMatchingService.remakePairThisProgramInfo(programInfo);
                OutputView.showResult(pairMatchingService.getThisProgramsPair(programInfo));
            }
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            checkRematching(programInfo);
        }
    }

    private MainCode chooseMainFunction() {
        try {
            return MainCode.find(InputView.chooseFunction());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return chooseMainFunction();
        }
    }
}
