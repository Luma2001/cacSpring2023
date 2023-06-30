package ar.com.codoacodo.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import ar.com.codoacodo.Dto.reqres.ListResource;

/*feign
import ar.com.codoacodo.service.feign.FeignResourceService;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
*/
import lombok.RequiredArgsConstructor;
 

@RestController
@RequestMapping("/resource") 
@RequiredArgsConstructor 
public class ResourceController {
	
	
	@Value(value="${ENDPOINT_REQ_RES}") //lo toma de applicationPropierty
	private String apiEndPoint;
	
	 @GetMapping()
    public ResponseEntity<ListResource> findAll(){

		
		  //Código correspondiente a microservicio externo: RestTemplate
		 RestTemplate restTemplate = new RestTemplate();
		
		 ResponseEntity<ListResource> response = restTemplate.getForEntity(apiEndPoint, ListResource.class);
		
		 return ResponseEntity.ok(response.getBody());
		 
		 /* 
		 //Código correspondiente a microservicio externo: Feign
		 FeignResourceService response =Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .logger(new Slf4jLogger(FeignResourceService.class))
				  .logLevel(Logger.Level.FULL)
				  .target(FeignResourceService.class, "apiEndPoint");
		 return ResponseEntity.ok(response.findAll());
		 */
    }

}
