package ltd.vastchain.nft.http;

import com.google.gson.JsonObject;
import ltd.vastchain.nft.http.model.HttpResponseModel;
import ltd.vastchain.nft.http.model.Trait;
import ltd.vastchain.nft.http.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class NftHttpHelper {

    private static final String EMPTY_STR = "";
//    private static NftHttpHelper instance = new NftHttpHelper();
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

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

//    @Autowired
//    private RestTemplate restTemplate;

//    public static NftHttpHelper getInstance() {
//        return instance;
//    }

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
        System.out.println(restTemplate);
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
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String url = SERVER + UrlConstants.NFT_ASSET_UPLOAD;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.set("Cookie", "accessToken=" + "pbfqglebcb");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new FileSystemResource(file));
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String uploadMultipartFile(MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String url = SERVER + UrlConstants.NFT_ASSET_UPLOAD;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.set("Cookie", "accessToken=" + "pbfqglebcb");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//        map.add("file", new FileSystemResource(file));
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        map.add("file", byteArrayResource);
        map.add("filename", file.getOriginalFilename());

        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }


    public String getReceipt(String txId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFC_ASSET_RECEIPT_QUERY;
//        HttpHeaders httpHeaders = getCommonHeaders();
        Map<String, Object> map = new HashMap();
        map.put("txId", txId);
//        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
//        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//        System.out.println(response.getBody());
//        return response.getBody();
        Map<String, String> headerMap = new HashMap();
        headerMap.put("Cookie","accessToken=" + "pbfqglebcb");

        return postJson(restTemplate, url, headerMap, map );
    }


    /**
     * get请求
     * @param restTemplate
     * @param url
     * @param headerMap
     * @param paramMap
     * @return
     */
    public static String get(RestTemplate restTemplate, String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> headers.set(k, v));
        }
        StringBuffer paramStr = new StringBuffer(EMPTY_STR);
        if (!CollectionUtils.isEmpty(paramMap)) {
            paramMap.forEach((k, v) -> {
                if (paramStr.toString().equals(EMPTY_STR)) {
                    paramStr.append("?").append(k).append("=").append(v);
                } else {
                    paramStr.append("&").append(k).append("=").append(v);
                }
            });
        }
        HttpEntity<String> httpEntity = restTemplate.exchange(url + paramStr, HttpMethod.GET, CollectionUtils.isEmpty(headerMap) ? null : new HttpEntity<>(headers), String.class);
        return httpEntity.getBody();
    }

    /**
     * post JSON
     * @param restTemplate
     * @param url
     * @param headerMap
     * @param paramObjectStr
     * @return
     */
    public static String postJson(RestTemplate restTemplate, String url, Map<String,String> headerMap, Map<String,Object> paramObjectStr){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        String resultStr = restTemplate.postForObject(url,new HttpEntity<>(paramObjectStr,headers),String.class);
        return resultStr;
    }

}
