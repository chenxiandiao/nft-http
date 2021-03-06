package ltd.vastchain.nft.http.controller;


import ltd.vastchain.nft.http.NftHttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class HomeController {

    static final String SERVER = "http://10.144.1.94:8100";
    static final String PUBLISH_ADDRESS = "a453659f8d2288c63dd2fe250f5ac8ab35750b42";
    static final String token = "dxgqmyojqm";

    @Autowired
    NftHttpHelper helper;

    @RequestMapping(value = "getToken")
    public String getToken() {
        System.out.println(helper);
        return helper.getNftToken("yl_test", "yl_test");
    }

    @RequestMapping(value = "createCount")
    public String createCount() {
        System.out.println(helper);
        return helper.createAccount("15068808239", "cxd",token);
    }

    @RequestMapping(value = "bindPublisher")
    public String bindPublisher() {
        return helper.bindPublisher(PUBLISH_ADDRESS,token);
    }

    @RequestMapping(value = "publishAsset")
    public String publishAsset() {
        return helper.publishAsset(PUBLISH_ADDRESS,
                "布尔熊",
                "dgc",
                ": https://gss0.bdstatic.com/5bd1bjqh_Q23odCf/static/indexatom/personalcenter/assets/img/default_icon_02f13d8.png",
                "独一无二的艺术数字典藏", "", 1000,token);
    }

    @RequestMapping(value = "createAsset")
    public String createAsset(@RequestParam("contractId") String contractId) {
        return helper.createAssetNft(PUBLISH_ADDRESS, PUBLISH_ADDRESS, contractId,
                "cxd", "", "布尔熊1号", "",
                null, "15068808239", "cxd",token);
    }

    @RequestMapping(value = "getAssetInfo/{contractId}/{tokenId}")
    public String getAssetInfo(@PathVariable("contractId") String contractId, @PathVariable("tokenId") String tokenId) {
        return helper.getAssetInfo(contractId, tokenId,token);
    }


    @RequestMapping(value = "getAssetList")
    public String getAssetList() {
        System.out.println(helper);
        return helper.getAssetList(1, 10, PUBLISH_ADDRESS,token);
    }

    @RequestMapping(value = "getAssetHistory")
    public String getAssetHistory(@RequestParam("contractId") String contractId, @RequestParam("tokenId") String tokenId) {
        System.out.println(helper);
        return helper.getAssetHistory(contractId, tokenId, 1, 10,token);
    }

//    @RequestMapping(value = "uploadFile")
//    public String uploadFile() {
//        return helper.uploadFile("/Users/cxd/Desktop/WechatIMG1.jpeg");
//    }

    @RequestMapping(value = "uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return helper.uploadMultipartFile(file, token);
    }

    @RequestMapping(value = "getReceipt")
    public String getReceipt(@RequestParam("txId") String txId) {
        return helper.getReceipt(txId, token);
    }

}
