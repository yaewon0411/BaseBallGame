package util;

import user.User;

public class CustomDesign {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void printUserWelcomeMessage(User user) {
        System.out.println(ANSI_YELLOW + "=================================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + user.getUsername() + "님 환영합니다! 야구 숫자 게임" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "=================================" + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_GREEN + "     ⚾ 규칙을 맞혀보세요! ⚾" + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_BLUE + "   숫자를 맞추면: " + ANSI_RED + "스트라이크 ✓" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "   숫자만 맞으면: " + ANSI_PURPLE + "볼 ●" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "   전혀 없으면  : " + ANSI_YELLOW + "아웃 ✗" + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_CYAN + "  준비되셨나요? 행운을 빕니다!" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "=================================" + ANSI_RESET);
    }

    public static void printMainMenu() {
        System.out.println(ANSI_YELLOW + "┌───────────────────────────────┐" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│     야구 숫자 게임 메뉴        │" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "├───────────────────────────────┤" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_GREEN + "1.  게임 시작하기🤍         " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_BLUE + "2.  게임 기록 보기🤍       " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_RED + "3.  게임 종료하기🤍         " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "└───────────────────────────────┘" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "원하는 옵션의 번호를 입력하세요: " + ANSI_RESET);
    }

    public static void printDifficultyMenu() {
        System.out.println(ANSI_YELLOW + "┌───────────────────────────────┐" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│        난이도 선택            │" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "├───────────────────────────────┤" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_GREEN + "1.  쉬움 (3자리 숫자)🤍     " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_BLUE + "2.  보통 (4자리 숫자)🤍     " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "│ " + ANSI_RED + "3.  어려움 (5자리 숫자)🤍   " + ANSI_YELLOW + "│" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "└───────────────────────────────┘" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "난이도를 선택하세요: " + ANSI_RESET);
    }


}
