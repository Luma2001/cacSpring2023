package ar.com.codoacodo.service.feign;



import ar.com.codoacodo.Dto.reqres.ListResource;
import feign.RequestLine;

public interface FeignResourceService {
	@RequestLine("GET")
	ListResource findAll();
}
