package com.Contoller;

import com.Boad.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@Controller // 스프링 MVC에서 컨테이너로 사용한다는 의
public class BoadContoller {

    // ControllerConfig에서 자동주입 받은 빈을 사용하기 위해서 선언합니다.
    private BoadDao boadDao;
    // 여기서 사용될 boadDao객체의 쿼리 접근하기 위해 생성합니다.
    //ControllerConfig에서 주입을 받습니다.
    public void setBoadController(BoadDao boadDao) {
        this.boadDao = boadDao;
    }


    @RequestMapping(value = "/BoadList")
    //                                      page처리를 위해 파라미터를 받습니다 기본적으로는 맨처음 보여줄 1페이지에 대한 값을 주었습니다. durltj model객체는
    // Servlet에서 배운 request.addAttribute() 처럼 결과 값을 뷰단으로 보여주기 위해 사용합니다.
    public String handleList(@RequestParam(name = "page", defaultValue = "1") int nowPage, Model model) {
        int listCnt = boadDao.listCount();
        BoadPageVo boadPageVo = new BoadPageVo(nowPage, listCnt);
        List<BoadListVo> list = boadDao.listflush(boadPageVo.getPageStart() - 1); // -1을 준 이유 RowNumber로 가저오는데 0번부터 시작하기 때문입니다 . 그럼 위에서 왜 0일 주지 않았느냐
        // 페이징 하는 Vo에서 0으로 계산하면 에러가 나기 때문에 1로주고 쿼리에서는 0으로 검색하게 했습니다.
        model.addAttribute("boadPage", boadPageVo);
        model.addAttribute("list", list);
        return "boad/BoadList";
    }

    @RequestMapping("/boad/BoadTech")
    // 위와 동일 합니다 테크탭 검색을 위해 맹글어 두었습니다 지금 위 기능과 합칠 예정입니다.
    public String TechBoad(@RequestParam(name = "page", defaultValue = "1") int nowPage, Model model) {
        System.out.println("텍텍");
        int listCnt = boadDao.TypeCount();
        BoadPageVo boadPageVo = new BoadPageVo(nowPage, listCnt);
        List<BoadListVo> list = boadDao.Typelist(boadPageVo.getPageStart() - 1);
        model.addAttribute("boadPage", boadPageVo);
        model.addAttribute("list", list);
        return "boad/BoardTech";
    }

    @RequestMapping("/boad/list")
    public String BoadList(@RequestParam(name = "page", defaultValue = "1") int nowPage, Model model) {
        int listCnt = boadDao.listCount();
        BoadPageVo boadPageVo = new BoadPageVo(nowPage, listCnt);
        System.out.println(boadPageVo.getPageStart());
        System.out.println(boadPageVo.getPageEnd());
        List<BoadListVo> list = boadDao.listflush(boadPageVo.getPageStart() - 1);
        model.addAttribute("boadPage", boadPageVo);
        model.addAttribute("list", list);
        return "boad/BoadList";
    }

    @RequestMapping("/boad/Write")
    public String WriteBoad(BoadWriteVo boadWriteVo, Model model) {
        return "boad/BoadWrite";
    }

    @RequestMapping("/boad/BoadUpdate")
    public String UpdateBoad(BoadWriteVo boadWriteVo, Model model) {
        return "boad/BoadUpdate";
    }

    @RequestMapping("/boad/SuccessWirte")
    // @Valid : 커맨드 객체에서 입력하는 과정중 나오는 유효성 검사를 위해 사용됩니다. 중요사항은 @Valid붙은 객체와 Errors는 반드시 같이 붙어야합니다 떨어지면 오류 났었습니다.
    public String SuccessWirte(@Valid BoadWriteVo boadWriteVo, Errors errors) {
        if (errors.hasErrors()) {
            // 만약 커맨드 객체 안에서 설정된 Validator 애노테이션에 부합되는 에러가 발생시 !
            return "boad/BoadWrite";
        }
       int boad_index =  boadDao.insert(boadWriteVo);
        return "redirect:/BoadView?boad-index=" + boad_index;

    }

    @RequestMapping("/BoadView")
    public String getBoadview(@RequestParam(name = "boad-index") int boad_index, Model model) {
        CommentVo commentVo= new CommentVo();
        int index=0;
        List<BoadListVo> list = boadDao.SearchByBoad(boad_index);
        for(BoadListVo boadListVo : list){
            index= boadListVo.getIndex();
        }
        List<CommentVo> comment = boadDao.commentList(index);
        model.addAttribute("list", list);
        model.addAttribute("commentVo",commentVo);
        model.addAttribute("commentList",comment);
        return "boad/ViewBoad";
    }

    @RequestMapping("/boad/BoadView")
    public String ViewBoad(@RequestParam(name = "boad-index") int boad_index,  Model model) throws UnsupportedEncodingException {
        CommentVo commentVo= new CommentVo();
        int index=0;
        List<BoadListVo> list = boadDao.SearchByBoad(boad_index);

        for(BoadListVo boadListVo : list){
           index= boadListVo.getIndex();
        }
        List<CommentVo> comment = boadDao.commentList(index);
        model.addAttribute("list", list);
        model.addAttribute("commentVo",commentVo);
        model.addAttribute("commentList",comment);
        return "boad/ViewBoad";
    }


    @RequestMapping("/boad/delBoad")
    public String delBoad(@RequestParam(name = "title") String title, @RequestParam(name = "name") String name) {
        boadDao.delete(title, name);
        return "redirect:/BoadList";

    }

    @RequestMapping("/boad/EditBoad")
    public String EditBoad(@Valid BoadWriteVo boadWriteVo, Errors errors) throws UnsupportedEncodingException {
        if (errors.hasErrors()) {
            return "boad/BoadWrite";
        }
        boadDao.Update(boadWriteVo);
        return "redirect:/BoadView?title=" ;

    }
    @RequestMapping("/boad/comment")
    public String commentUpload(@Valid CommentVo commentVo, Errors errors) throws UnsupportedEncodingException {

        if(errors.hasErrors()){
            return "redirect:/BoadView?boad-index=" + commentVo.getBoard_index();
        }

        boadDao.comment(commentVo);
        return "redirect:/BoadView?boad-index=" + commentVo.getBoard_index();

    }

    // 이미지 업로드 처리 오류가 계속나서 비활성
//    @RequestMapping(value = "boad/writeImage",  consumes = "multipart/form-data" )
//    public String uploadImage(HttpServletRequest req, HttpServletResponse res, @RequestBody @RequestParam MultipartFile upload) throws Exception {
//        JsonObject jsonObject = new JsonObject();
//        PrintWriter printWriter = null;
//        String fileName = upload.getOriginalFilename();
//        res.setCharacterEncoding("utf-8");
//        // 파일 이름을 바이트로 변환.
//        byte[] bytes = fileName.getBytes();
//
//        String uploadPath = "com/UploadImage/";
//
//        OutputStream out = new FileOutputStream(new File(uploadPath + fileName));
//        out.write(bytes);
//
//        printWriter = res.getWriter();
//        res.setContentType("text/html");
//
//        String fileUrl = req.getContextPath() + "/UploadImage/" + fileName;
//        jsonObject.addProperty("upload", 1);
//        jsonObject.addProperty("fileName", fileName);
//        jsonObject.addProperty("url",fileUrl);
//
//        printWriter.println(jsonObject);
//
//        return null;
//    }
}

