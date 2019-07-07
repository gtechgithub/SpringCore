how do we call the other system uri using rest template
-------------------------------------------------------

		String apiUrl = "http://10.10.10.20:9999/api/v1/client/{id/details/";
        	String outputInJson = "";
		
		//set path variables first 
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", "1234");

		//set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        	headers.add("clientId", "myproj");
		
		//set queryparams
        	MultiValueMap<String, String> inputMap = new LinkedMultiValueMap<>();
		inputMap.add("name", "myName");	
		
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(inputMap, headers);

		//set path varaiables first
		URI uri = UriComponentsBuilder.fromUriString(apiUrl).buildAndExpand(pathVariables).toUri();
		
		//set query param next 
		
		//uri = UriComponentsBuilder.fromUri(apiUrl).queryParam("name", "myName").build().toUri();
		uri = UriComponentsBuilder.fromUri(apiUrl).queryParam(requestEntity.getBody()).build().toUri();
		
		restTemplate.exchange(uri , HttpMethod.PUT, requestEntity, String.class);
		//restTemplate.exchange(uri , HttpMethod.GET, requestEntity, String.class);
