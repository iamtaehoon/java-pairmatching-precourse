package pairmatching.controller;

import pairmatching.code.MainCode;
import pairmatching.code.RematchCode;
import pairmatching.domain.Pairs;
import pairmatching.domain.ProgramInfo;
import pairmatching.service.PairMatchingService;
import pairmatching.util.ProgramInfoConvertor;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private PairMatchingService pairMatchingService = new PairMatchingService();
    private MainCode mainCode = null;

    public void run() {
        while (mainCode != MainCode.QUIT) {
            mainCode = chooseMainFunction();
            executeFunction(mainCode);
        }
    }

    private void executeFunction(MainCode mainCode) {
        if (mainCode == MainCode.MATCHING) {
            executeMatching();
        }
        // if (mainCode == MainCode.INQUERY) {
        //     executeInquery();
        // }
        // if (mainCode == MainCode.CLEAR) {
        //     executeClear();
        // }
    }

    private void executeMatching() {
        try {
            ProgramInfo programInfo = ProgramInfoConvertor.makeProgramInfo(InputView.enterProgramInfo());
            if (pairMatchingService.alreadyHaveThisProgramInfo(programInfo)) {
                determineRematching(programInfo);
                return;
            }
            Pairs pairs = pairMatchingService.matchPairsThisProgramInfo(programInfo);
            OutputView.showPairs(pairs);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            executeMatching();
        }
    }

    private void determineRematching(ProgramInfo programInfo) {
        try {
            RematchCode rematchCode = RematchCode.find(InputView.enterRematch());
            if (rematchCode == RematchCode.YES) {
                Pairs pairs = pairMatchingService.rematchPairsThisProgramInfo(programInfo);
                OutputView.showPairs(pairs);
            }
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            determineRematching(programInfo);
        }
    }

    private MainCode chooseMainFunction() {
        try {
            return MainCode.find(InputView.enterMainFunction());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return chooseMainFunction();
        }
    }
}
