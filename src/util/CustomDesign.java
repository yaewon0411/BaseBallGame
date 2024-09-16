package util;

import game.record.GameRecord;
import user.User;

import java.util.List;

public class CustomDesign {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PINK =  "\u001B[95m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_BACKGROUND_RED = "\u001B[41m";

    public static void printStartMessage() {
        System.out.println(ANSI_CYAN + "╔════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║          환영합니다! 야구 숫자 게임                ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╠════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║                                                ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║" + ANSI_GREEN + "     ⚾ 규칙을 맞혀보세요! ⚾                      " + ANSI_CYAN + "║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║                                                ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║" + ANSI_BLUE + "   숫자를 맞추면: " + ANSI_RED + "스트라이크 ✓" + ANSI_CYAN + "                       ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║" + ANSI_BLUE + "   숫자만 맞으면: " + ANSI_PURPLE + "볼 ●" + ANSI_CYAN + "                             ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║" + ANSI_BLUE + "   전혀 없으면  : " + ANSI_YELLOW + "아웃 ✗" + ANSI_CYAN + "                           ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║                                                ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╠════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║                                                ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║  " + ANSI_YELLOW + "닉네임을 입력하여 게임을 시작하세요!" + ANSI_CYAN + "                 ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║  " + ANSI_WHITE + "또는 'exit'를 입력하여 게임을 종료할 수 있습니다." + ANSI_CYAN + "     ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║                                                ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╚════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.print(ANSI_GREEN + "✏️  입력: " + ANSI_RESET);
    }

    public static void printLogoutMessage() {
        System.out.println( "  로그아웃 되었습니다. 👋        " );
    }

    public static void printExitMessage() {
        System.out.println("\n\n"+ANSI_RED + "게임을 종료합니다. 안녕히 가세요! 👋        " +  ANSI_RESET+"\n");
        System.out.println(ANSI_CYAN + "모든 게임 기록이 삭제됩니다. 🗑️           " +ANSI_RESET);
        System.out.println( ANSI_WHITE + "다음에 다시 도전해주세요!                 " + ANSI_RESET+"\n");
        System.out.println(ANSI_GREEN + "야구 숫자 게임을 이용해 주셔서 감사합니다. " + ANSI_RESET+"\n\n");
    }

    public static void printUserWelcomeMessage(User user) {
        System.out.printf("\n"+ANSI_CYAN + "환영합니다, %s님! 🎉\n" + ANSI_RESET, user.getUsername());
        System.out.println(ANSI_GREEN + "야구 숫자 게임의 세계에 오신 것을 환영합니다!" + ANSI_RESET+"\n");
        System.out.println(ANSI_WHITE + "🎯 목표: 난이도 별 숨겨진 숫자를 맞추세요" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "🧠 전략: 논리적 추론으로 숫자를 찾아내세요" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "🏆 도전: 최소 시도로 정답을 맞춰보세요!" + ANSI_RESET+"\n");
        System.out.println(ANSI_PURPLE + "준비되셨나요? 행운을 빕니다! 🍀" + ANSI_RESET);
        System.out.println();
    }

    public static void printMainMenu() {
        System.out.println(ANSI_YELLOW + "┌───────────────────────────────┐" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│     야구 숫자 게임 메뉴          │" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "├───────────────────────────────┤" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_GREEN + "1.  게임 시작하기🤍             " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_BLUE + "2.  게임 기록 보기🤍            " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_RED + "3.  로그아웃 하기🤍             " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "└───────────────────────────────┘" + ANSI_RESET);
        System.out.print(ANSI_CYAN + "원하는 옵션의 번호를 입력하세요: " + ANSI_RESET);
    }

    public static void printDifficultyMenu() {
        System.out.println(ANSI_YELLOW + "┌───────────────────────────────┐" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│        난이도 선택            │" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "├───────────────────────────────┤" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_GREEN + "1.  쉬움 (3자리 숫자)🤍     " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_BLUE + "2.  보통 (4자리 숫자)🤍     " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_RED + "3.  어려움 (5자리 숫자)🤍   " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "└───────────────────────────────┘" + ANSI_RESET);
        System.out.print(ANSI_CYAN + "난이도를 선택하세요: " + ANSI_RESET);
    }

    public static void printResult(int strikeCnt, int ballCnt, int outCnt){
        System.out.print(ANSI_RED + "스트라이크: " + strikeCnt + ANSI_CYAN + " ║ ");
        System.out.print(ANSI_GREEN + "볼: " + ballCnt + ANSI_CYAN + " ║ ");
        System.out.println(ANSI_YELLOW + "아웃: " + outCnt + ANSI_RESET);

        if (strikeCnt == 3) {
            System.out.println(ANSI_PINK + "축하합니다! 정답을 맞추셨습니다!" + ANSI_RESET);
        }
    }

    public static void printExceptionMessage(String msg){
        System.out.println(ANSI_BOLD + ANSI_BACKGROUND_RED + ANSI_CYAN + " 오류 " + ANSI_RESET +
                ANSI_BOLD + ANSI_BRIGHT_RED + " " + msg + " " + ANSI_RESET);

        // 추가적인 구분선으로 메시지를 강조
        String separator = "=".repeat(msg.length() + 5);
        System.out.println(ANSI_BRIGHT_RED + separator + ANSI_RESET);
    }

    public static void  printUserRecords(User user, List<GameRecord> records) {
        System.out.println( "===== " + user.getUsername() + "님의 게임 기록 =====");

        if (records.isEmpty()) {
            System.out.println(CustomDesign.ANSI_YELLOW + "아직 게임을 진행한 이력이 없습니다." + CustomDesign.ANSI_RESET);
        } else {
            for (int i = 0; i < records.size(); i++) {
                GameRecord record = records.get(i);
                System.out.printf(CustomDesign.ANSI_YELLOW + "%d번째 게임 [%s] 시도 횟수: %d\n" + CustomDesign.ANSI_RESET,
                        i + 1, record.getDifficultyMode(), record.getAttemptCnt());
            }
        }

        System.out.println("================================");
    }


}
