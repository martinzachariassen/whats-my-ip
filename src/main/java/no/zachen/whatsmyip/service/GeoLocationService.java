package no.zachen.whatsmyip.service;

import no.zachen.whatsmyip.dto.BasicIpInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeoLocationService {

  private final WebClient webClient = WebClient.create("https://ipapi.co");

  public Mono<BasicIpInfo> getBasicInfo(String ip) {
    return webClient
        .get()
        .uri("/{ip}/json", ip)
        .retrieve()
        .bodyToMono(IpApiResponse.class)
        .map(response -> new BasicIpInfo(
            ip,
            response.city(),
            response.country_name(),
            response.org(),
            response.security() != null && response.security().is_vpn()
        ));
  }

  // DTO for ipapi.co svar
  private record IpApiResponse(
      String city,
      String country_name,
      String org,
      Security security
  ) {

    private record Security(boolean is_vpn) {

    }
  }
}