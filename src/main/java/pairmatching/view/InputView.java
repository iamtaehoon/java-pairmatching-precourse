package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String chooseFunction() {
        System.out.println("기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료\n");
        return Console.readLine();
    }
}
