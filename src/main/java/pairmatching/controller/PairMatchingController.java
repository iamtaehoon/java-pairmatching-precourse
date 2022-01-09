package pairmatching.controller;

import pairmatching.code.MainCode;
import pairmatching.domain.ProgramInfo;
import pairmatching.util.ProgramInfoConvertor;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    // private PairMatchingService = new PairMatchingService();
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
            System.out.println(programInfo);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            executeMatching();
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
