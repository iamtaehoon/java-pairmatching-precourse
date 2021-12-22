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
                executeMatchingPairs();
            }
            if (mainCode == MainCode.SEARCH) {
                executeSearchingPairs();
            }
            if (mainCode == MainCode.INITIALIZE) {
                pairMatchingService.clearAllMatchingInfo();
            }
        }
    }

    private void executeMatchingPairs() {
        ProgramInfo programInfo = makeProgramInfo();
        if (pairMatchingService.isAlreadyMatching(programInfo)) {
            String rematchingCode = inputView.determineReMatching();
            if (rematchingCode.equals("아니오")) {
                return;
            }
            if (rematchingCode.equals("네")) {
                pairMatchingService.deleteMatching(programInfo);
            }
            throw new IllegalArgumentException(INVALID_INPUT_ERROR);
        }
        putPairsInProgramInfo(programInfo);
    }

    private void putPairsInProgramInfo(ProgramInfo programInfo) {
        pairMatchingService.matchPairs(programInfo);
        OutputView.showThisProgramPair(programInfo);
    }

    private void executeSearchingPairs() {
        ProgramInfo programInfo = makeProgramInfo();
        if (pairMatchingService.hasProgramInfo(programInfo)) {
            programInfo = pairMatchingService.getThisProgramInfo(programInfo);
            OutputView.showThisProgramPair(programInfo);
            return;
        }
        throw new IllegalArgumentException(MACHING_INFO_NOT_EXIST_YET_ERROR);
    }

    private ProgramInfo makeProgramInfo() {
        try {
            ProgramInfo programInfo = ProgramInfoTransformer.makeProgramInfo(inputView.determineProgramInfo());
            return programInfo;
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return makeProgramInfo();
        }
    }
}
