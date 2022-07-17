import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String FILE_PATH;

    private static String ZIP_FILE_NAME="total.zip";




    public ResposeModel fileDownLoad(HttpServletResponse response, String filePath,  String fileName){
        filePath = StringUtils.isEmpty(filePath)?FILE_PATH:filePath;
        File file = new File( filePath+'/'+ fileName);
        if(!file.exists()){
            return ResposeModel.failed("下载文件不存在");
        }
        response.reset();
//        response.setContentType("application/x-download");
        response.setContentLength((int) file.length());
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("文件名加密错误！");
            throw new RuntimeException("文件名加密错误");
        }

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error("",e);
            return ResposeModel.failed("下载失败");
        }
        return ResposeModel.success("下载成功");
    }


}
