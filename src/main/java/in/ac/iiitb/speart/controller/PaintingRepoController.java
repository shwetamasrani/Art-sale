package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.service.PaintingRepoDetailsService;
import in.ac.iiitb.speart.utils.ResponseFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paintings")
public class PaintingRepoController {

    private static final Logger logger = LogManager.getLogger(PaintingRepoController.class);

    @Autowired
    PaintingRepoDetailsService paintingRepoDetailsService;

//    @GetMapping("/getAllPaint")
//    public List<PaintingRepoDetails> getAll(){
//
//        return paintingRepoDetailsService.get();
//    }

    //Move Images to painting_repo
    @GetMapping("/getAllPaintings")
    public List<ResponseFile> getListFiles() {
        try{
        List<PaintingRepoDetails> files = paintingRepoDetailsService.get();
        if(files == null || files.size() == 0)
            throw new Exception();
        List<ResponseFile> paintings_list = new ArrayList<ResponseFile>();
        for(int i = 0; i < files.size(); i++){
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/paintings/files/")
                    .path(files.get(i).getP_id()+"")
                    .toUriString();
            paintings_list.add(new ResponseFile(
                    files.get(i).getPainting_name(),
                    fileDownloadUri

            ));
        }
        return paintings_list;
        } catch(Exception e){
            logger.error("No files exist");
            //What to return in cases of errors like these.
            return Collections.emptyList();
        }

    }

    @RequestMapping(value = "/files/{pname}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFile(@PathVariable String pname, HttpServletResponse response) {
        PaintingRepoDetails fileDB = paintingRepoDetailsService.get(pname);
        String contentType = null;
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getPainting_name() + "\"")
//                .body(new ByteArrayResource(fileDB.getPainting_image()));
        // Response type: byte[]
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getPainting_name()+ "\"")
                .body(new ByteArrayResource(fileDB.getPainting_image()));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileDB.getPainting_name());
//
//        return new HttpEntity<byte[]>(fileDB.getPainting_image(), headers);
//        Document doc = documentDao.get(documentId);
//        try {
//            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFilename()+ "\"");
//            OutputStream out = response.getOutputStream();
//            response.setContentType(doc.getContentType());
//            IOUtils.copy(doc.getContent().getBinaryStream(), out);
//            out.flush();
//            out.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    //Add for bidding changes.


}
