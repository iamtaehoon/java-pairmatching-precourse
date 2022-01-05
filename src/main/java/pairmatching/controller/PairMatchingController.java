package pairmatching.controller;

import java.util.List;

import pairmatching.code.MainCode;
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
        // System.out.println(programInfo);
        // if (pairMatchingService.alreadyHavePair(programInfo)) {
        //     //재입력을 물어본다.
        // }

        //programInfo에 따라 어떤 크루들이 들어갈지 정해준다.
        pairMatchingService.makePairThisProgramInfo(chooseCrews(programInfo),programInfo);

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
