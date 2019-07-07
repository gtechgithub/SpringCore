

# RestTemplate handling to call another URI.

1) first define http headers for the operation (GET /POST)

```
HttpHeaders headers = new HttpHeaders();

//set headers
headers.setContentType(MediaType.APPLICATION_JSON);
headers.add("clientId", "myproj");

 MultiValueMap<String, String> inputMap = new LinkedMultiValueMap<>();
 //set queryparams
 inputMap.add(name, "myName");
 
 HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(inputMap, headers);


```
2) 

we can add path Params and query param to the uri builder  and call the rest template 

```
//set path varaiables first
URI uri = UriComponentsBuilder.fromUriString(apiUrl).buildAndExpand(pathVariables).toUri();
		
//set query param next 
		
//uri = UriComponentsBuilder.fromUri(apiUrl).queryParam("name", "myName").build().toUri();
uri = UriComponentsBuilder.fromUri(apiUrl).queryParam(requestEntity.getBody()).build().toUri();
		
restTemplate.exchange(uri , HttpMethod.PUT, requestEntity, String.class);
//restTemplate.exchange(uri , HttpMethod.GET, requestEntity, String.class);
```
