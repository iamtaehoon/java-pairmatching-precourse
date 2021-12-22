package pairmatching.controller;

import static pairmatching.ErrorMessage.*;

import pairmatching.code.MainCode;
import pairmatching.domain.ProgramInfo;
import pairmatching.service.PairMatchingService;
import pairmatching.util.ProgramInfoTransformer;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    PairMatchingService pairMatchingService = new PairMatchingService();
    InputView inputView = new InputView();
    MainCode mainCode = null;

    public void run() {
        try {
            executeFunction();
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            run();
        }
    }

    private void executeFunction() {
        while (mainCode != MainCode.QUIT) {
            MainCode mainCode = MainCode.find(inputView.determineMainFunction());
            if (mainCode == MainCode.QUIT) {
                break;
            }
            if (mainCode == MainCode.MATCHING) {
                matchingPairs();
            }
            if (mainCode == MainCode.SEARCH) {
                searchProgramsPair();
            }
            if (mainCode == MainCode.INITIALIZE) {
                pairMatchingService.clearAllMatchingInfo();
            }
        }
    }

    private void matchingPairs() {
        ProgramInfo programInfo = matchPairs();
        if (isRematching(programInfo)) {
            //해당 programInfo를 먼저 지워준다.
            pairMatchingService.rematchPairs(programInfo);
            OutputView.showThisProgramPair(programInfo);
        }
    }

    private boolean isRematching(ProgramInfo programInfo) {
        if (pairMatchingService.isAlreadyMatching(programInfo)) {
            String rematchingCode = inputView.determineReMatching();
            if (rematchingCode.equals("아니오")) {
                return false;
            }
            if (rematchingCode.equals("네")) {
                programInfo.clearPairs();
                return true;
            }
            throw new IllegalArgumentException(INVALID_INPUT_ERROR);
        }
        return true;
    }

    private void searchProgramsPair() {
        ProgramInfo programInfo = ProgramInfoTransformer.makeProgramInfo(inputView.determineProgramInfo());
        if (pairMatchingService.hasProgramInfo(programInfo)) {
            programInfo = pairMatchingService.getThisProgramInfo(programInfo);
            OutputView.showThisProgramPair(programInfo);
            return;
        }
        OutputView.showThisMissionIsNotMatchingMessage();
    }

    private ProgramInfo matchPairs() {
        try {
            ProgramInfo programInfo = ProgramInfoTransformer.makeProgramInfo(inputView.determineProgramInfo());
            return programInfo;
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return matchPairs();
        }
    }
}
