# common rest template error handling using ResponseErrorHandler interface

ResponseErrorHandler interface in a RestTemplate instance – to gracefully handle HTTP errors returned by remote APIs

By default, the RestTemplate will throw one of these exceptions in case of an HTTP error:

1) HttpClientErrorException – in case of HTTP status 4xx
2) HttpServerErrorException – in case of HTTP status 5xx

3) UnknownHttpStatusCodeException – in case of an unk	nown HTTP status

## Implementing a ResponseErrorHandler

**So let’s first implement our RestTemplateResponseErrorHandler:**

```
@Component
public class RestTemplateResponseErrorHandler 
  implements ResponseErrorHandler {
 
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) 
      throws IOException {
 
        return (
          httpResponse.getStatusCode().series() == CLIENT_ERROR 
          || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }
 
    @Override
    public void handleError(ClientHttpResponse httpResponse) 
      throws IOException {
 
        if (httpResponse.getStatusCode()
          .series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
          .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }
        }
    }
}
```


**Next, we build the RestTemplate instance using the RestTemplateBuilder**
**to introduce our RestTemplateResponseErrorHandler:**

```
@Service
public class SimpleService {
 
    private RestTemplate restTemplate;
 
    @Autowired
    public SimpleService(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder
          .errorHandler(new RestTemplateResponseErrorHandler())
          .build();
    }
 
    public Bar fetchBarById(String barId) {
        return restTemplate.getForObject("/bars/4242", Bar.class);
    }
 
}

```
