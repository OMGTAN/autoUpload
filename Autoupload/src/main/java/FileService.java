import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {



    /**
     * 文件下载
     * @param response
     * @param fileName
     * @return
     */
    ResposeModel fileDownLoad(HttpServletResponse response, String filePath, String fileName);

}
