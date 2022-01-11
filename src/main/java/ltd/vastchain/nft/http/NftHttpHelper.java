package ltd.vastchain.nft.http;

import ltd.vastchain.nft.http.model.Trait;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class NftHttpHelper {

    private static final String EMPTY_STR = "";
    public static String SERVER = "http://10.144.1.94:8100";


    private Map createHeaderMap(String token) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", "accessToken=" + token);
        return headerMap;
    }

    /**
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public String getNftToken(String appId, String appSecret) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.GET_TOKEN;
        Map<String, Object> map = new HashMap();
        map.put("appId", appId);
        map.put("appSecret", appSecret);
        return postJson(restTemplate, url, null, map);
    }

    /**
     *
     * @param mobile
     * @param username
     * @param token
     * @return
     */
    public String createAccount(String mobile, String username, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.CREATE_ACCOUNT;
        Map<String, Object> map = new HashMap();
        map.put("mobile", mobile);
        map.put("username", username);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

    /**
     *
     * @param publisherAddress
     * @param token
     * @return
     */
    public String bindPublisher(String publisherAddress, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.BIND_PUBLISH;
        Map<String, Object> map = new HashMap();
        map.put("publisherAddress", publisherAddress);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

    /**
     *
     * @param userAddress
     * @param token
     * @return
     */
    public String bindUser(String userAddress, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.CREATE_ACCOUNT;
        Map<String, Object> map = new HashMap();
        map.put("UserAddress", userAddress);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

    /**
     *
     * @param publisherAddress
     * @param name
     * @param symbol
     * @param imageUrl
     * @param description
     * @param externalLink
     * @param totalSupply
     * @param token
     * @return
     */
    public String publishAsset(String publisherAddress, String name, String symbol,
                               String imageUrl, String description, String externalLink,
                               int totalSupply, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.PUBLISH_ASSET;
        Map<String, Object> map = new HashMap();
        map.put("publisherAddress", publisherAddress);
        map.put("name", name);
        map.put("symbol", symbol);
        map.put("imageUrl", imageUrl);
        map.put("description", description);
        map.put("externalLink", externalLink);
        map.put("totalSupply", totalSupply);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

    /**
     *
     * @param publishAddress
     * @param userAddress
     * @param contractId
     * @param author
     * @param imagUrl
     * @param name
     * @param externalLink
     * @param traits
     * @param ownerMobile
     * @param ownerName
     * @param token
     * @return
     */
    public String createAssetNft(String publishAddress, String userAddress, String contractId, String author,
                                 String imagUrl, String name, String externalLink, List<Trait> traits,
                                 String ownerMobile, String ownerName, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_CREATE;

        Map<String, Object> map = new HashMap();
        map.put("publishAddress", userAddress);
        map.put("userAddress", userAddress);
        map.put("contractId", contractId);
        map.put("author", author);
        map.put("imagUrl", imagUrl);
        map.put("name", name);
        map.put("externalLink", externalLink);
        map.put("traits", traits);
        map.put("ownerMobile", ownerMobile);
        map.put("ownerName", ownerName);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

    /**
     *
     * @param contractId
     * @param tokenId
     * @param token
     * @return
     */
    public String getAssetInfo(String contractId, String tokenId, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_INFO + "/" + contractId + "/" + tokenId;
        return get(restTemplate, url, createHeaderMap(token), null);
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param userAddress
     * @param token
     * @return
     */
    public String getAssetList(int pageNum, int pageSize, String userAddress, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_LIST;
        Map<String, Object> map = new HashMap();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("userAddress", userAddress);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

    /**
     *
     * @param contractId
     * @param tokenId
     * @param pageNum
     * @param pageSize
     * @param token
     * @return
     */
    public String getAssetHistory(String contractId, String tokenId, int pageNum, int pageSize, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_HISTORY;
        Map<String, Object> map = new HashMap();
        map.put("contractId", contractId);
        map.put("tokenId", tokenId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return postJson(restTemplate, url, createHeaderMap(token), map);
    }

//    public String uploadFile(String file) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = SERVER + UrlConstants.NFT_ASSET_UPLOAD;
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
//        httpHeaders.set("Cookie", "accessToken=" + "pbfqglebcb");
//        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//        map.add("file", new FileSystemResource(file));
//        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
//        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//        System.out.println(response.getBody());
//        return response.getBody();
//    }

    /**
     *
     * @param file
     * @param token
     * @return
     * @throws IOException
     */
    public String uploadMultipartFile(MultipartFile file, String token) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFT_ASSET_UPLOAD;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.set("Cookie", "accessToken=" + token);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return System.currentTimeMillis() + "_" + file.getOriginalFilename() ;
            }
        };
        map.add("file", byteArrayResource);
        map.add("filename", file.getOriginalFilename());
        HttpEntity<Map<String, String>> request = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    /**
     *
     * @param txId
     * @param token
     * @return
     */
    public String getReceipt(String txId, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = SERVER + UrlConstants.NFC_ASSET_RECEIPT_QUERY;
        Map<String, Object> map = new HashMap();
        map.put("txId", txId);
        return postJson(restTemplate, url, createHeaderMap(token), map );
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
     * @param map
     * @return
     */
    public static String postJson(RestTemplate restTemplate, String url, Map<String,String> headerMap, Map<String,Object> map){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!CollectionUtils.isEmpty(headerMap)){
            headerMap.forEach((k,v)-> headers.set(k,v));
        }
        String resultStr = restTemplate.postForObject(url,new HttpEntity<>(map,headers),String.class);
        return resultStr;
    }

}
