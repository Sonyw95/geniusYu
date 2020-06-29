package com.Boad;


// 중요 중요 페이징 하는 VO 입니다.
public class BoadPageVo {
    //한 페이지에 보여주는 게시물 수
    private final int pageSize = 9;
    // 화면에 보여지는 총 페이지 수
    private final int rangeSize = 5;
    //현재페이지
    private int nowPage;
    //이전
    private int prevPage;
    //다음
    private int nextPage;
    //전체 게시물 갯수
    private int pageList;
    // 전체 페이지 개수
    private int rangeList;
    // 현재 페이지 번호
    private int nowRange;
    // 이전 페이지 반호
    private  int prevRange;
    // 다음 페이지 번호
    private int nextRange;
    // list검색에 쓰일 db start
    private int pageStart;
    // list검색에 쓰일 db end
    private int pageEnd;
    //현재 페이지의 시작 번호
    private int rangeStart;
    //현재 페이지의 마지막 번호
    private int rangeEnd;

    public BoadPageVo(int nowPage, int pageList) {
        // 게시판의 n번째 게시판번과 입력 받아 오면 해당 번호를 지정합니다.
        this.nowPage = nowPage;
        nowRange=1;
        setPageList(pageList);
        setPageRange();
        setRangeList();
        setAllRange();

    }
    public void setAllRange(){
        // 현재 몇페이지 입니까 ?  ex ) 1 일때 (1-1) / 5)+1 = 1페이지.
        nowRange = (int)Math.ceil((nowPage-1)/rangeSize)+1;
        // 현제 페이지의 시작과 끝 ex )1 일때       (1-1)*9+1 = 1개의 게시판 번호
        rangeStart = (nowRange-1)*rangeSize+1;
        // 오라클을 이용했을 때 limit함수가 없다보니 where절로 검색을 했어야 했습니다. 그 검색을 위해 시작과 끝 번호를 구했었습니다.
        rangeEnd = rangeStart+rangeSize-1;
        // 범위 넘기지 않게끔 계산하기 ex) 9개의 게시글이 있고 해당 블로그의 1블록당 9개의 게시글이 존재할 수가 있음 이러면 다음페이지로 넘어가게 되는 그것을 막기 위해 .
        if(rangeEnd > pageList){ rangeEnd= pageList;}
        //페이지 범위 제한
        if(nextPage >= pageList){nextPage=pageList;}

    }

    public int getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(int rangeStart) {
        this.rangeStart = rangeStart;
    }

    public int getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(int rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public void setPageRange(){
        pageStart = (nowPage-1)*pageSize+1;
        pageEnd = pageStart+pageSize-1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getRangeSize() {
        return rangeSize;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageList() {
        return pageList;
    }

    public void setPageList(int count) {
        pageList = (int) Math.ceil(count*1.0/pageSize);
    }

    public int getRangeList() {
        return rangeList;
    }

    public void setRangeList() {
        rangeList = (int) ((double)pageList/pageSize+0.95);
    }

    public int getNowRange() {
        return nowRange;
    }

    public void setNowRange(int nowRange) {
        this.nowRange = nowRange;
    }

    public int getPrevRange() {
        return prevRange;
    }

    public void setPrevRange(int prevRange) {
        this.prevRange = prevRange;
    }

    public int getNextRange() {
        return nextRange;
    }

    public void setNextRange(int nextRange) {
        this.nextRange = nextRange;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

}