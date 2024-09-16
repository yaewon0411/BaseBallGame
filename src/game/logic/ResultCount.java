package game.logic;

import util.CustomDesign;

class ResultCount {

    int strikeCnt;
    int ballCnt;
    int outCnt;

    public ResultCount(int strikeCnt, int ballCnt, int outCnt) {
        this.strikeCnt = strikeCnt;
        this.ballCnt = ballCnt;
        this.outCnt = outCnt;
    }

    public void printResult(){
        CustomDesign.printResult(this.strikeCnt, this.ballCnt, this.outCnt);
    }

    public int getStrikeCnt() {
        return strikeCnt;
    }

}