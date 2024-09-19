package game.logic;

import util.CustomDesign;

/**
 * 숫자 야구 게임의 한 라운드 결과를 나타내는 클래스입니다
 * 스트라이크, 볼, 아웃의 개수를 저장하고 관리합니다
 * NumberBaseballLogic 클래스에서 사용됩니다
 */
class ResultCount {

    int strikeCnt; //스트라이크 개수
    int ballCnt; //볼 개수
    int outCnt; //아웃 개수

    public ResultCount(int strikeCnt, int ballCnt, int outCnt) {
        this.strikeCnt = strikeCnt;
        this.ballCnt = ballCnt;
        this.outCnt = outCnt;
    }

    /**
     * 현재 라운드 결과를 출력합니다
     * CustomDesign 클래스의 printResult 메소드를 사용하여 결과를 표시합니다
     */
    public void printResult(){
        CustomDesign.printResult(this.strikeCnt, this.ballCnt, this.outCnt);
    }


    /**
     * 현재 라운드의 스트라이크 개수를 반환합니다
     *
     * @return 스트라이크 개수
     */
    public int getStrikeCnt() {
        return strikeCnt;
    }

}