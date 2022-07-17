import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class FileController {
    @Resource
    private FileService fileService;


    @GetMapping(value = "/download")
    public ResposeModel fileDownLoad(HttpServletResponse response, @RequestParam(value = "filePath", required = false) String filePath, @RequestParam("fileName") String fileName){
        fileService.fileDownLoad(response, filePath, fileName);
        return ResposeModel.success("11");
    }
}
