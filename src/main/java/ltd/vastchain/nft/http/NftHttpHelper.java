package ltd.vastchain.nft.http;

import com.google.gson.JsonObject;
import ltd.vastchain.nft.http.model.HttpResponseModel;
import ltd.vastchain.nft.http.model.Trait;
import ltd.vastchain.nft.http.model.UserToken;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NftHttpHelper {

    private static NftHttpHelper instance = new NftHttpHelper();
    public static String SERVER = "http://10.144.1.94:8100";
//    private String token = "";
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    private NftHttpHelper() {
    }

    public static NftHttpHelper getInstance() {
        return instance;
    }

//    public HttpHeaders getCommonHeaders(String token) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Cookie", "accessToken=" + token);
//        return headers;
//    }

    public HttpHeaders getCommonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", "accessToken=" + "pbfqglebcb");
        return headers;
    }

    public String getNftToken(String appId, String appSecret) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.GET_TOKEN;
        Map<String, String> map = new HashMap<String, String>();
        map.put("appId", appId);
        map.put("appSecret", appSecret);
//        ResponseEntity<HttpResponseModel> response = restTemplate.postForEntity(url, map, HttpResponseModel.class);
//        System.out.println(response.getBody());
//        HttpResponseModel<UserToken> data = response.getBody();
//        return data;
        ResponseEntity<String> response = restTemplate.postForEntity(url, map, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String createAccount(String mobile, String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.CREATE_ACCOUNT;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("username", username);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String bindPublisher(String publisherAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.BIND_PUBLISH;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, String> map = new HashMap();
        map.put("publisherAddress", publisherAddress);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String bindUser(String mobile, String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.CREATE_ACCOUNT;
        Map<String, String> map = new HashMap<String, String>();
        map.put("UserAddress", mobile);
        ResponseEntity<String> response = restTemplate.postForEntity(url, map, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String publishAsset(String publisherAddress, String name, String symbol,
                               String imageUrl, String description, String externalLink,
                               int totalSupply) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.PUBLISH_ASSET;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, Object> map = new HashMap();
        map.put("publisherAddress", publisherAddress);
        map.put("name", name);
        map.put("symbol", symbol);
        map.put("imageUrl", imageUrl);
        map.put("description", description);
        map.put("externalLink", externalLink);
        map.put("totalSupply", totalSupply);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String createAssetNft(String publishAddress, String userAddress, String contractId, String author,
                                 String imagUrl, String name, String externalLink, List<Trait> traits,
                                 String ownerMobile, String ownerName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_CREATE;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, String> map = new HashMap<String, String>();
        map.put("publishAddress", userAddress);
        map.put("userAddress", userAddress);
        map.put("contractId", contractId);
        map.put("author", author);
        map.put("imagUrl", imagUrl);
        map.put("name", name);
        map.put("externalLink", externalLink);
        // TODO: 2022/1/10
//        map.put("traits", traits);
        map.put("ownerMobile", ownerMobile);
        map.put("ownerName", ownerName);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String getAssetInfo(String contractId, String tokenId) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders httpHeaders = getCommonHeaders();
//        String url = SERVER + UrlConstants.NFT_ASSET_INFO + "/" + contractId + "/" + tokenId;
//        System.out.println(url);
//        HttpEntity<Map<String, String>> request = new HttpEntity(httpHeaders);
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, request);
//        System.out.println(response.getBody());

        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_INFO + "/" + contractId + "/" + tokenId;
        HttpHeaders headers = getCommonHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        return response.getBody();
    }

    public String getAssetList(int pageNum, int pageSize, String userAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_LIST;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, Object> map = new HashMap();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("userAddress", userAddress);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String getAssetHistory(String contractId, String tokenId, int pageNum, int pageSize) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_HISTORY;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, Object> map = new HashMap();
        map.put("contractId", contractId);
        map.put("tokenId", tokenId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String uploadFile(String file) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = SERVER + UrlConstants.NFT_ASSET_UPLOAD;
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, map, String.class);
//        System.out.println(response.getBody());
//        return response.getBody();
        return "";
    }

    public String getReceipt(String txId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFC_ASSET_RECEIPT_QUERY;
        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, Object> map = new HashMap();
        map.put("txId", txId);
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

}
