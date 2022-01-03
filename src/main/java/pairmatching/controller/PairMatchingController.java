package pairmatching.controller;

import java.util.List;

import pairmatching.code.MainCode;
import pairmatching.util.CrewConvertor;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private List<String> backendCrewNames;
    private List<String> frontendCrewNames;
    private MainCode mainCode = null;

    public PairMatchingController() {
        backendCrewNames = CrewConvertor.makeCrewsUsingMdFile("./src/main/resources/backend-crew.md");
        frontendCrewNames = CrewConvertor.makeCrewsUsingMdFile("./src/main/resources/frontend-crew.md");
    }

    public void run() {
        while (mainCode != MainCode.QUIT) {
            mainCode = executeFunction();
        }
    }

    private MainCode executeFunction() {
        try {
            return MainCode.find(InputView.chooseFunction());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return executeFunction();
        }
    }
}
