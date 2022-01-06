package pairmatching.controller;

import java.util.List;

import pairmatching.code.MainCode;
import pairmatching.code.RematchCode;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;
import pairmatching.service.PairMatchingService;
import pairmatching.util.CrewConvertor;
import pairmatching.util.ProgramInfoConvertor;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private List<Crew> backendCrews;
    private List<Crew> frontendCrews;
    private MainCode mainCode = null;
    private PairMatchingService pairMatchingService = new PairMatchingService();

    public PairMatchingController() {
        backendCrews = CrewConvertor.makeCrewsUsingMdFile(Course.BACKEND, "./src/main/resources/backend-crew.md");
        frontendCrews = CrewConvertor.makeCrewsUsingMdFile(Course.FRONTEND,
            "./src/main/resources/frontend-crew.md");
    }

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
        if (mainCode == MainCode.QUIT) {
            return;
        }
    }

    private void executeReset() {
        pairMatchingService.resetAllPair();
        OutputView.showResetMessage();
    }

    private void executePairInquery() {
        ProgramInfo programInfo = makeProgramInfoUsingInput();
        if (pairMatchingService.havePairThisProgramInfo(programInfo)) {
            List<Crew> thisProgramsPair = pairMatchingService.getThisProgramsPair(programInfo);
            OutputView.showResult(thisProgramsPair);
            return;
        }
        throw new IllegalArgumentException("매칭 이력이 없습니다.");
    }

    private void executePairMatching() {
        ProgramInfo programInfo = makeProgramInfoUsingInput();
        boolean alreadyHavePair = checkThisProgramInfoAlreadyHavePair(programInfo);
        if (!alreadyHavePair) {
            pairMatchingService.makePairThisProgramInfo(chooseCrews(programInfo),programInfo);
        }
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

    private boolean checkThisProgramInfoAlreadyHavePair(ProgramInfo programInfo) {
        if (pairMatchingService.alreadyHavePair(programInfo)) {
            checkRematching(programInfo);
            return true;
        }
        return false;
    }

    private void checkRematching(ProgramInfo programInfo) {
        try {
            RematchCode rematchCode = RematchCode.find(InputView.chooseReMatching()); //TODO 없으면 재입력 받아야함.
            if (rematchCode == RematchCode.YES) {
                pairMatchingService.remakePairThisProgramInfo(chooseCrews(programInfo), programInfo);
            }
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
        }
    }

    private List<Crew> chooseCrews(ProgramInfo programInfo) {
        if (programInfo.isBackend()) {
            return backendCrews;
        }
        if (programInfo.isFrontend()) {
            return frontendCrews;
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
