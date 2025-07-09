package no.zachen.whatsmyip.controller;

import jakarta.servlet.http.HttpServletRequest;
import no.zachen.whatsmyip.dto.BasicIpInfo;
import no.zachen.whatsmyip.service.GeoLocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IpController {

  private final GeoLocationService geoLocationService;

  public IpController(GeoLocationService geoLocationService) {
    this.geoLocationService = geoLocationService;
  }

  @GetMapping("/api/lookup")
  public Mono<BasicIpInfo> lookup(HttpServletRequest request) {
    String ip = extractClientIp(request);
    return geoLocationService.getBasicInfo(ip);
  }

  private String extractClientIp(HttpServletRequest request) {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader != null) {
      return xfHeader.split(",")[0];
    }
    return request.getRemoteAddr();
  }
}