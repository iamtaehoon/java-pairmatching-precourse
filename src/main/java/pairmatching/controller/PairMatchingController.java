package pairmatching.controller;

import java.util.List;

import pairmatching.code.MainCode;
import pairmatching.code.RematchCode;
import pairmatching.domain.ProgramInfo;
import pairmatching.service.PairMatchingService;
import pairmatching.util.CrewConvertor;
import pairmatching.util.ProgramInfoConvertor;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private List<String> backendCrewNames;
    private List<String> frontendCrewNames;
    private MainCode mainCode = null;
    private PairMatchingService pairMatchingService = new PairMatchingService();

    public PairMatchingController() {
        backendCrewNames = CrewConvertor.makeCrewsUsingMdFile("./src/main/resources/backend-crew.md");
        frontendCrewNames = CrewConvertor.makeCrewsUsingMdFile("./src/main/resources/frontend-crew.md");
    }

    public void run() {
        while (mainCode != MainCode.QUIT) {
            mainCode = chooseMainFunction();
            executeFunction(mainCode);
        }
    }

    private void executeFunction(MainCode mainCode) {
        if (mainCode == MainCode.MATCHING) {
            executePairMatching();
        }
        if (mainCode == MainCode.INQUERY) {
            // executePairInquery();
        }
        if (mainCode == MainCode.RESET) {
            // executeReset();
        }
        if (mainCode == MainCode.QUIT) {
            return;
        }
    }

    private void executePairMatching() {
        String programInfoPreProcessing = InputView.chooseProgramInfo();
        ProgramInfo programInfo = ProgramInfoConvertor.makeProgramInfo(programInfoPreProcessing);
        boolean alreadyHavePair = checkThisProgramInfoAlreadyHavePair(programInfo);
        if (!alreadyHavePair) {
            pairMatchingService.makePairThisProgramInfo(chooseCrews(programInfo),programInfo);
        }
        List<String> thisProgramsPair = pairMatchingService.getThisProgramsPair(programInfo);
        OutputView.showResult(thisProgramsPair);
    }

    private boolean checkThisProgramInfoAlreadyHavePair(ProgramInfo programInfo) {
        if (pairMatchingService.alreadyHavePair(programInfo)) {
            RematchCode rematchCode = RematchCode.find(InputView.chooseReMatching()); //TODO 없으면 재입력 받아야함.
            if (rematchCode == RematchCode.YES) {
                pairMatchingService.remakePairThisProgramInfo(chooseCrews(programInfo),programInfo);
            }
            return true;
        }
        return false;
    }

    private List<String> chooseCrews(ProgramInfo programInfo) {
        if (programInfo.isBackend()) {
            return backendCrewNames;
        }
        if (programInfo.isFrontend()) {
            return frontendCrewNames;
        }
        throw new IllegalStateException("해당 파일이 존재하지 않습니다.");
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
