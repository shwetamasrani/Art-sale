package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.model.ArtCustomTrial;
import in.ac.iiitb.speart.model.PaintingBuyerMM;
import in.ac.iiitb.speart.model.PaintingRepoAPI;
import in.ac.iiitb.speart.model.PaintingRepoDetails;
import in.ac.iiitb.speart.service.PaintingBuyerMMService;
import in.ac.iiitb.speart.service.PaintingRepoDetailsService;
import in.ac.iiitb.speart.utils.ResponseFile;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @Autowired
    PaintingBuyerMMService paintingBuyerMMService;

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

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getPainting_name()+ "\"")
                .body(new ByteArrayResource(fileDB.getPainting_image()));

    }

    //Add for bidding changes.


//    @RequestMapping(value = "/bidAnArtPiece", method = RequestMethod.POST)
//    public ResponseEntity<PaintingRepoAPI> bidAnArtPiece(@RequestBody PaintingRepoAPI paintingRepoAPI){
//        paintingRepoDetailsService.bidArtPieceByUser(paintingRepoAPI.getBuyer_id(), paintingRepoAPI.getP_id());
//        return new ResponseEntity<PaintingRepoAPI>(paintingRepoAPI, HttpStatus.ACCEPTED);
//    }

    @RequestMapping(value="/getArtBiddingDetails/{painting_id}", method = RequestMethod.GET)
    public ResponseEntity<?> artBiddingDetails(@PathVariable Integer painting_id){
        Object obj = paintingRepoDetailsService.getBiddingDetailsArtPiece(painting_id);
        return new ResponseEntity<Object>(obj, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/postBiddingRequestBuyer", method = RequestMethod.POST)
    public ResponseEntity<?> getBiddingRequestBuyer(@RequestBody PaintingRepoAPI paintingRepoAPI){
        PaintingBuyerMM paintingRepoDetails = paintingBuyerMMService.postBiddingReqBuyerPrice(paintingRepoAPI.getP_id(),
                paintingRepoAPI.getBidded_price(), paintingRepoAPI.getUser_id());
        return new ResponseEntity<PaintingRepoAPI>(paintingRepoAPI, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/addExtraArtistImage", method = RequestMethod.POST)
    public ResponseEntity<?> addExtraImageForDashboard(@RequestBody PaintingRepoAPI paintingRepoAPI){
        paintingRepoAPI = paintingRepoDetailsService.addExtraArtistImage(paintingRepoAPI);
        return new ResponseEntity<PaintingRepoAPI>(paintingRepoAPI, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/saveArtistSampleImage", method = RequestMethod.POST)
    public ResponseEntity<?> saveArtistExtraImage(@RequestParam("file") MultipartFile file, @RequestParam("painting_id") Integer painting_id) throws IOException {
        PaintingRepoDetails paintingRepoDetails = paintingRepoDetailsService.getPaintingByPaintingID(painting_id);
        System.out.println(paintingRepoDetails.getPainting_name());
        paintingRepoDetails.setPainting_image(file.getBytes());
        paintingRepoDetailsService.save(paintingRepoDetails);
        return new ResponseEntity<PaintingRepoDetails>(paintingRepoDetails, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getAllBiddingPurchasesByUserID/{bidder_conf_id}", method = RequestMethod.GET)
    public List<PaintingRepoDetails> getAllBidding(@PathVariable Integer bidder_conf_id){
        return paintingRepoDetailsService.getAllBiddingConf(bidder_conf_id);
    }

    @RequestMapping(value = "/getAllBiddings/{bidder_id}", method = RequestMethod.GET)
    public List<Object> getAllBiddingsActive(@PathVariable Integer bidder_id){
        return paintingRepoDetailsService.getAllBiddingsActive(bidder_id);
    }

    @RequestMapping(value = "/getAllBiddings/{bidder_id}", method = RequestMethod.GET)
    public List<Object> getAllBiddingsActive(@PathVariable Integer bidder_id){
        return paintingRepoDetailsService.getAllBiddingsActive(bidder_id);
    }

}
